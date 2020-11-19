package com.example.taskyprojectnaya.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskyprojectnaya.MyUtils.MyValidations;
import com.example.taskyprojectnaya.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity<isOK> extends AppCompatActivity
{
    private EditText etEmail,etPassword;
    private Button SignUpbtn,Loginbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //7. check if I signed in before
        FirebaseAuth auth=FirebaseAuth.getInstance().getInstance();
        if(auth.getCurrentUser()!=null)
        {
            Intent i=new Intent(getBaseContext(),MainActivity.class);
            finish();
            startActivity(i);
        }

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        SignUpbtn = findViewById(R.id.SignUpbtn);
        Loginbtn = findViewById(R.id.Loginbtn);


        Loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }
    //5
    private void validateForm()
    {
        String email=etEmail.getText().toString();
        String pass=etEmail.getText().toString();

        boolean isOK=true;



        if (email.length()<5 || (email.indexOf('@')==0 ) || email.indexOf('@')>=email.length()-2 || email.indexOf('.')==0
                ||email.indexOf('.')>=email.length()-1 ||email.lastIndexOf('.')<email.indexOf('@'))//בודק אם האימיל כתוב לא נכון
        {
            isOK=false;
            etEmail.setError("Wrong E-mail. Try again");
        }

            MyValidations myValidation=new MyValidations();
            if (myValidation.validatePasword(pass)==false){
                isOK= false;
                etPassword.setError("Invalid Password");
            }
        if(isOK)// isOk = true
        {
            signIn(email, pass);
        }
    }
    private void signIn(String email,String pass)
    {
        FirebaseAuth auth=FirebaseAuth.getInstance().getInstance();
        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Intent i=new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(SignInActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    etEmail.setError(task.getException().getMessage());
                }
            }
        });

    }

}