package guideme.kappa.ro.guideme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {

    private Button mButtonGuide, mButtonTourist;
    private float x1 = 0, x2 = 0, y1 = 0, y2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        mButtonGuide = findViewById(R.id.buttonAmGuide);
        mButtonTourist = findViewById(R.id.buttonAmTourist);
        mButtonTourist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, AmTouristActivity.class);
                startActivity(intent);
            }
        });
        mButtonGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThirdActivity.this, AmGuideActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        int action = motionEvent.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                x1 = motionEvent.getX();
                y1 = motionEvent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = motionEvent.getX();
                y2 = motionEvent.getY();
                if(x1 < x2){  // inspre dreapta
                    Log.d("debug", "s-a miscat la dreapta");
                    finish();
                    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                }
                else if(x1 > x2){  // inspre stanga
                    Log.d("debug", "s-a miscat la stanga");
                }
                break;
        }
        return false;
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
