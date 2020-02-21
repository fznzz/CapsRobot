package ugm.fznzz.nightowlreceptionistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_MapsChoose extends AppCompatActivity {

    Button bt_back, bt_1st, bt_2nd, bt_3rd, bt_4th;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__maps_choose);

        bt_back = findViewById(R.id.bt_backMaps);
        bt_1st = findViewById(R.id.bt_1stfloorMaps);
        bt_2nd = findViewById(R.id.bt_2ndfloorMaps);
        bt_3rd = findViewById(R.id.bt_3rdfloorMaps);
        bt_4th = findViewById(R.id.bt_4thfloorMaps);

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bt_1st.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_MapsChoose.this, activity_Maps1st.class);
                startActivity(i);
            }
        });
    }
}
