package ugm.fznzz.nightowlreceptionistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import static com.squareup.picasso.Picasso.*;

public class activity_GuideChoose extends AppCompatActivity {

    TextView tv;
    ImageView iv;
    Button bt_back,bt_next, bt_openT, bt_closeT;
    BluetoothAdapter bAdapter;
    BluetoothSocket bSocket;
    BluetoothDevice bDevice;
    OutputStream os;
    InputStream is;
    Thread workerThread;
    byte[] readBuffer;
    int readBufferPosition;
    int counter;
    volatile boolean stopWorker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__guide_choose);

        iv = findViewById(R.id.imageView);
        bt_back = findViewById(R.id.bt_backGuide);
        bt_next = findViewById(R.id.bt_nextGuide);
        bt_openT = findViewById(R.id.bt_testOPEN);
        bt_closeT = findViewById(R.id.bt_testCLOSE);
        tv = findViewById(R.id.textviewtesting);

        Picasso.get().load(R.drawable.map1stfloor).fit().into(iv);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_openT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opening
                try {
                    findBT();
                    openBT();
                }
                catch (IOException ex) { }
            }
        });
        bt_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sending
                try
                {
                    sendData();
                }
                catch (IOException ex) { }
            }
        });
        bt_closeT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //closing
                try
                {
                    closeBT();
                }
                catch (IOException ex) { }
            }
        });
    }

    void findBT()
    {
        bAdapter = BluetoothAdapter.getDefaultAdapter();
        if(bAdapter==null)
        {
            Toast.makeText(activity_GuideChoose.this, "No bluetooth adapter available", Toast.LENGTH_SHORT).show();
        }
        if(!bAdapter.isEnabled())
        {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }
        Set<BluetoothDevice> pairedDevice = bAdapter.getBondedDevices();
        if(pairedDevice.size()>0)
        {
            for(BluetoothDevice device : pairedDevice)
            {
                if(device.getName().equals("testingIShere"))  //NAMA PERANGKAT BLUETOOTH
                {
                    bDevice=device;
                    break;
                }
            }
        }
        Toast.makeText(activity_GuideChoose.this, "Bluetooth device found!", Toast.LENGTH_SHORT).show();
    }

    void openBT() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");  //UUID PERANGKAT BLUETOOTH INI
        bSocket = bDevice.createRfcommSocketToServiceRecord(uuid);
        bSocket.connect();
        os = bSocket.getOutputStream();
        is = bSocket.getInputStream();

        beginListenData();

        Toast.makeText(activity_GuideChoose.this, "Bluetooth Opened", Toast.LENGTH_SHORT).show();
    }

    void beginListenData()
    {
        final Handler handler = new Handler();
        final Byte delimiter = 10;

        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try {
                        int bytesAvailable = is.available();
                        if(bytesAvailable>0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            is.read(packetBytes);
                            for(int i=0; i<bytesAvailable; i++)
                            {
                                byte b = packetBytes[i];
                                if(b==delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition=0;
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            tv.setText(data);
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++]=b;
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });
        workerThread.start();
    }

    void sendData() throws IOException
    {
        String msg = "testingdikirim";
        msg += "\n";
        os.write(msg.getBytes());
        Toast.makeText(activity_GuideChoose.this, "Data send", Toast.LENGTH_SHORT).show();
    }

    void closeBT() throws IOException
    {
        stopWorker = true;
        try {
            os.close();
            is.close();
            bSocket.close();
        }
        catch (IOException ex) {Toast.makeText(activity_GuideChoose.this, "eror", Toast.LENGTH_SHORT).show();}
        Toast.makeText(activity_GuideChoose.this, "Bluetooth closed", Toast.LENGTH_SHORT).show();
    }
}
