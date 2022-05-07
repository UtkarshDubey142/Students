package com.utkarsh.students;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.utkarsh.students.Databases.StudentTable;
import com.utkarsh.students.Helper.DatabaseHelper;

public class UpdateDataActivity extends AppCompatActivity {

    EditText et_upEditTextName , et_updateStandard;
    TextView roll_no_tv;
    StudentTable studentTable;

    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);


        helper = DatabaseHelper.getInstance(this);

        roll_no_tv = findViewById(R.id.tv_rollNo);
        et_upEditTextName = findViewById(R.id.et_fullName);
        et_updateStandard = findViewById(R.id.et_standard);

        if (getIntent() != null)
        {
            studentTable = (StudentTable) getIntent().getSerializableExtra("stu_table");

            roll_no_tv.setText("Roll No: " + studentTable.getId());
            et_upEditTextName.setText(studentTable.getStu_name());
            et_updateStandard.setText(studentTable.getStu_standard());
        }
    }

    public void updateData(View view)
    {
        if ( !(et_upEditTextName.getText().toString().isEmpty() && et_updateStandard.getText().toString().isEmpty()) )
            helper.updateData(studentTable , et_upEditTextName.getText().toString() , et_updateStandard.getText().toString());
    }

    public void showData(View view)
    {
        startActivity(new Intent(this , DataViewPage.class));
    }
}