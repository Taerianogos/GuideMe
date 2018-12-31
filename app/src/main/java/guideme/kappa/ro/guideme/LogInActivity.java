package guideme.kappa.ro.guideme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LogInActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mLogin;
    private EditText mEmail, mPass;
    public  String Email, Pass;
    private FirebaseDatabase database;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        mAuth = FirebaseAuth.getInstance();
        mEmail = findViewById(R.id.formSignInEmail);
        mPass = findViewById(R.id.formSignInPass);
        mLogin = findViewById(R.id.buttonLogIn);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = mEmail.getText().toString().trim();
                Pass = mPass.getText().toString().trim();
                if(Validate(Email, Pass)){
                    mAuth.signInWithEmailAndPassword(Email, Pass)
                           .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        //startActivity(new Intent(LogInActivity.this, MapsActivity.class));
                                        int PLACE_PICKER_REQUEST = 1;
                                        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                                        try {
                                            startActivityForResult(builder.build(LogInActivity.this), PLACE_PICKER_REQUEST);
                                        } catch (GooglePlayServicesRepairableException e) {
                                            e.printStackTrace();
                                        } catch (GooglePlayServicesNotAvailableException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    else Toast.makeText(LogInActivity.this, "Log in failure", Toast.LENGTH_LONG).show();
                                }
                            });
                }

            }
        });
    }

    private boolean Validate(String email, String password){
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(LogInActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(LogInActivity.this, "Please enter a password", Toast.LENGTH_LONG).show();
            return false;
        }
        if(email.length() < 6){
            Toast.makeText(LogInActivity.this, "The password must have more than 6 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
