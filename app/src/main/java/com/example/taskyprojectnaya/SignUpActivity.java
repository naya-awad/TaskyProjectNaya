package com.example.taskyprojectnaya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity
{
    private EditText FirstName,LastName,Password,VerPassword,Email,Phone;
    private Button Savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirstName=findViewById(R.id.FirstName);
        LastName=findViewById(R.id.LastName);
        Password=findViewById(R.id.Password);
        VerPassword=findViewById(R.id.VerPassword);
        Email=findViewById(R.id.Email);
        Phone=findViewById(R.id.Phone);
        Savebtn=findViewById(R.id.Savebtn);
    }

    private void checkForm()//hl 3bahn sa7(sign up)
    {
        String firstn=FirstName.getText().toString();
        String lastn=LastName.getText().toString();
        String pass=Password.getText().toString();
        String verPass=VerPassword.getText().toString();
        String email=Email.getText().toString();
        String phone=Phone.getText().toString();
        String save=Savebtn.getText().toString();

        boolean isOK=true;

        if(firstn.length()<2)
        {
            isOK=false;
            FirstName.setError("at least two letter");
        }
        if(lastn.length()<2) {
            isOK = false;
            FirstName.setError("at least two letter");
        }

            if (email.length()<5 || (email.indexOf('@')==0 ) || email.indexOf('@')>=email.length()-2 || email.indexOf('.')==0
                ||email.indexOf('.')>=email.length()-1 ||email.lastIndexOf('.')<email.indexOf('@'))
        {
            isOK=false;
            Email.setError("Wrong E-mail. Try again");
        }
    }
}