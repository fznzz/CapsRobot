package ugm.fznzz.nightowlreceptionistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_FAQChoose extends AppCompatActivity {
    Button bt_backFAQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__faqchoose);

        bt_backFAQ = findViewById(R.id.bt_backFAQ);
        bt_backFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
