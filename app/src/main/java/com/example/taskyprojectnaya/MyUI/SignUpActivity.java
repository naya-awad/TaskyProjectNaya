package com.example.taskyprojectnaya.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class SignUpActivity extends AppCompatActivity {
    private EditText FirstName, LastName, Password, VerPassword, Email, Phone;
    private Button Savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        Password = findViewById(R.id.Password);
        VerPassword = findViewById(R.id.VerPassword);
        Email = findViewById(R.id.etEmail);
        Phone = findViewById(R.id.Phone);
        Savebtn = findViewById(R.id.Savebtn);

        Savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)//v 7adath
            {
                validateForm();
            }
        });
    }

    private void validateForm()//hl 3bahn sa7(sign up)
    {
        String firstn = FirstName.getText().toString();
        String lastn = LastName.getText().toString();
        String pass = Password.getText().toString();
        String verPass = VerPassword.getText().toString();
        String email = Email.getText().toString();
        String phone = Phone.getText().toString();
        String save = Savebtn.getText().toString();

        boolean isOK = true;

        if (firstn.length() < 2)//בודק אם השם הראשון כתוב לא נכון(צריך להיות יותר משני אותיות)
        {
            isOK = false;
            FirstName.setError("at least two letter");
        }
        if (lastn.length() < 2)//בודק אם השם האחרון כתוב לא נכון(צריך להיות יותר משני אותיות)
        {
            isOK = false;
            LastName.setError("at least two letter");
        }

        if (email.length() < 5 || (email.indexOf('@') == 0) || email.indexOf('@') >= email.length() - 2 || email.indexOf('.') == 0
                || email.indexOf('.') >= email.length() - 1 || email.lastIndexOf('.') < email.indexOf('@'))//בודק אם האימיל כתוב לא נכון
        {
            isOK = false;
            Email.setError("Wrong E-mail. Try again");
        }
        if (pass.equals(verPass) == false) {
            isOK = false;
            VerPassword.setError("Passwords must be the same!");

        } else {
            if (pass.length()<8) {
                isOK = false;
                Password.setError("Invalid Password");
            }
        }
        if (isOK)// isOk = true
        {
            // todo: create account and return to sign in screen/ close this screen
            createNewAccount(firstn, lastn, pass, email, phone);
        }
    }


    /**
     * @param firstn
     * @param lastn
     * @param pass
     * @param email
     * @param phone
     */

    private void createNewAccount(String firstn, String lastn, String pass, String email, String phone) {
        //1
        FirebaseAuth auth = FirebaseAuth.getInstance();//אחראית על רישום וכניסת משתמשים
        //2
        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())// ההרשמה הצליחה כמו שנדרש
                {
                    Toast.makeText(SignUpActivity.this, "Successfully up", Toast.LENGTH_SHORT).show();
                    //next screen or close this screen
                    finish();//close this screen
                    //next screen
                    //Intent i= new Intent(getBaseContext(),MainActivity.class); startActivity(i);
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Signing up, Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    Email.setError("Signing up, Failed: " + task.getException().getMessage());

                }
            }
        };
        //3
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(listener);

    }
}