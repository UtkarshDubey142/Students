package com.utkarsh.students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.utkarsh.students.Helper.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    EditText et_name , et_standard;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = DatabaseHelper.getInstance(this);

        et_name = findViewById(R.id.et_fullName);
        et_standard = findViewById(R.id.et_standard);

        // Setting Data
        ((Button)findViewById(R.id.btn_submitData)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( !(et_name.getText().toString().isEmpty()) && !(et_standard.getText().toString().isEmpty()) )
                    helper.addNewStudent(et_name.getText().toString(), et_standard.getText().toString());
            }
        });

        // onClick for jump in new Activity
        ((Button)findViewById(R.id.btn_showData)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dataViewIntent = new Intent(MainActivity.this , DataViewPage.class);
                startActivity(dataViewIntent);
            }
        });
    }
}