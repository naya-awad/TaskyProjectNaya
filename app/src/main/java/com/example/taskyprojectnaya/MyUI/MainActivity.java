package com.example.taskyprojectnaya.MyUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.taskyprojectnaya.R;
import com.example.taskyprojectnaya.data.MyTaskAdapter;

public class MainActivity extends AppCompatActivity {

    private ImageButton ibtnAdd;
    //a.1 after building the array adapter
    private ListView lstTasks;
    //a.2 adapter
    MyTaskAdapter taskAdapter;

    //a.1 after building the array adapter
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ibtnAdd=findViewById(R.id.ibtnAdd);

        ibtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(i);

            }
        });
        //a.3
        lstTasks=findViewById(R.id.lstTasks);
        //a.4
        taskAdapter=new MyTaskAdapter(getBaseContext(),R.layout.item_task_view);
        //a.5
        lstTasks.setAdapter(taskAdapter);
    }

}