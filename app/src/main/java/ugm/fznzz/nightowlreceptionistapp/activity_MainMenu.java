package ugm.fznzz.nightowlreceptionistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_MainMenu extends AppCompatActivity {
    Button bt_guide, bt_maps, bt_faq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__main_menu);
        
        bt_guide = findViewById(R.id.bt_guideMain);
        bt_maps = findViewById(R.id.bt_mapsMain);
        bt_faq = findViewById(R.id.bt_faqMain);

        bt_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_MainMenu.this, activity_GuideChoose.class);
                startActivity(i);
            }
        });

        bt_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(activity_MainMenu.this, activity_MapsChoose.class);
                startActivity(i2);
            }
        });

        bt_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(activity_MainMenu.this, activity_FAQChoose.class);
                startActivity(i3);
            }
        });
    }
}
