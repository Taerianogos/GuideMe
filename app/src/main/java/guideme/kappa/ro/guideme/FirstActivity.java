package guideme.kappa.ro.guideme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class FirstActivity extends AppCompatActivity {

    private float x1=0,x2=0,y1=0,y2=0;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(FirstActivity.this, MapActivity.class));
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
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
                }
                else if(x1 > x2){  // inspre stanga
                    Log.d("debug", "s-a miscat la stanga");
                    Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                break;
        }
        return false;
    }
}
