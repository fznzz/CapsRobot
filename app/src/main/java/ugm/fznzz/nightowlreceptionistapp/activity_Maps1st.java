package ugm.fznzz.nightowlreceptionistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class activity_Maps1st extends AppCompatActivity {

    ImageView iv;
    Button bt_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__maps1st);
        iv = findViewById(R.id.iv_1stfloormaps);
        Picasso.get().load(R.drawable.map1stfloor).fit().into(iv);
        bt_back = findViewById(R.id.bt_backMaps1st);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
