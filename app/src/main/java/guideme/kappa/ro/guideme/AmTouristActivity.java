package guideme.kappa.ro.guideme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AmTouristActivity extends AppCompatActivity {

    private Button mButtonSignUp, mButtonLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_tourist);
        mButtonSignUp = findViewById(R.id.buttonSignUp);
        mButtonLogIn = findViewById(R.id.buttonLogIn);
        mButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmTouristActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        mButtonLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AmTouristActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
