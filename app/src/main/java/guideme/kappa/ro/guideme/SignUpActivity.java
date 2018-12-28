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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button mCreateAccount;
    private EditText mName, mEmail, mPass, mPass2;
    private String Name, Email, Pass, Pass2;
    private FirebaseDatabase database;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        mCreateAccount = findViewById(R.id.buttonCreateAccount);
        mName = findViewById(R.id.formSignUpUser);
        mEmail = findViewById(R.id.formSignUpEmail);
        mPass = findViewById(R.id.formSignUpPass);
        mPass2 = findViewById(R.id.formSignUpPass2);

        mCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Name = mName.getText().toString().trim();
                Email = mEmail.getText().toString().trim();
                Pass = mPass.getText().toString().trim();
                Pass2 = mPass2.getText().toString().trim();
                database = FirebaseDatabase.getInstance();
                mRef = database.getReference();
                if(Validate(Name,Email,Pass,Pass2)){

                    mAuth.createUserWithEmailAndPassword(Email, Pass)
                    .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                mRef.child("Users").child(uid).child("Name").setValue(Name);
                                mRef.child("Users").child(uid).child("Email").setValue(Email);
                                Intent intent = new Intent(SignUpActivity.this, MapActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
                }
            }
        });
    }

    private boolean Validate(String name, String email, String password, String password2){
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(SignUpActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(SignUpActivity.this, "Please enter your name", Toast.LENGTH_LONG).show();
            return false;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(SignUpActivity.this, "Please enter a password", Toast.LENGTH_LONG).show();
            return false;
        }
        if(password.length() < 6){
            Toast.makeText(SignUpActivity.this, "The password must have more than 6 characters", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!(Objects.equals(password, password2))){
            Toast.makeText(SignUpActivity.this, "Passwords are not the same", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
