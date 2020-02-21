package ugm.fznzz.nightowlreceptionistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static com.squareup.picasso.Picasso.*;

public class activity_GuideChoose extends AppCompatActivity {

    ImageView iv;
    Button bt_back,bt_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__guide_choose);

        iv = findViewById(R.id.imageView);
        bt_back = findViewById(R.id.bt_backGuide);
        bt_next = findViewById(R.id.bt_nextGuide);
        Picasso.get().load(R.drawable.map1stfloor).fit().into(iv);
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
