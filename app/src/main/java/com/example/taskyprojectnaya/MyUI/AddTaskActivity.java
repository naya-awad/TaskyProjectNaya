package com.example.taskyprojectnaya.MyUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskyprojectnaya.data.MyTask;
import com.example.taskyprojectnaya.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTaskActivity extends AppCompatActivity
{


    private TextView Neccessarytxt,Importanttxt;
    private EditText etDueDate,etTitle,etSub;
    private Button Uploadbtn,PickADatebtn,SaveTaskbtn;
    private SeekBar skbrNeccessary,skbrImportant;
    private ImageButton imgbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Neccessarytxt=findViewById(R.id.Neccessarytxt);
        Importanttxt=findViewById(R.id.Importanttxt);
        etDueDate=findViewById(R.id.etDueDate);
        etTitle=findViewById(R.id.etTitle);
        etSub=findViewById(R.id.etSub);
        Uploadbtn=findViewById(R.id.Uploadbtn);
        PickADatebtn=findViewById(R.id.PickADatebtn);
        SaveTaskbtn=findViewById(R.id.SaveTaskbtn);
        skbrNeccessary=findViewById(R.id.skbrNeccessary);
        skbrImportant=findViewById(R.id.skbrImportant);
        imgbtn=findViewById(R.id.imgbtn);

        //4.listener
        SaveTaskbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForm();
            }
        });

    }

    private void validateForm()
    {
        String title=etTitle.getText().toString();
        String sub=etSub.getText().toString();
        String date=etDueDate.getText().toString();
        int imp=skbrImportant.getProgress();
        int necc=skbrNeccessary.getProgress();

        boolean isOK=true;

        if(title.length()==0)
        {
            isOK=false;
            etTitle.setError("at least one char");
        }


        if(sub.length()==0)
         {
             isOK=false;
             etSub.setError("at least two letter");
         }

        if (date.length()<10||(!(date.indexOf('.')==2&&date.indexOf('.')==5)))
        {
            isOK=false;
            etDueDate.setError("at least two letter");
        }

        if (isOK)
        {
            //6.save on firebase database
            //6.1:build your data object
            MyTask myTask= new MyTask();
            myTask.setTitle(title);
            myTask.setSub(sub);
            myTask.setImportant(imp);
            myTask.setNeccessary(necc);
            //6.2:
            saveTask(myTask);
        }


    }

    private void saveTask(MyTask myTask) {
        //1.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //2.
        DatabaseReference reference = database.getReference();
        //3. user id
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        //4. My Object Key
        String key = reference.child("AllTasks").push().getKey();
        //5. Update Your Object
        myTask.setOwner(uid);
        myTask.setKey(key);
        //6. Actual Stroring
        reference.child("AllTasks").child(uid).child(key).setValue(myTask).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(AddTaskActivity.this,"add Successful",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else{
                    Toast.makeText(AddTaskActivity.this,"add Failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });

    }
}