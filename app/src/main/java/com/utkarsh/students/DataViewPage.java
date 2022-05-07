package com.utkarsh.students;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.utkarsh.students.Adaptors.StudentAdaptor;
import com.utkarsh.students.Databases.StudentTable;
import com.utkarsh.students.Helper.DatabaseHelper;

import java.util.List;

public class DataViewPage extends AppCompatActivity {

    // RecyclerView variable
    RecyclerView var_recyclerView;
    // StudentAdaptor
    StudentAdaptor studentAdaptor;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view_page);
        helper = DatabaseHelper.getInstance(this);

        // Targeting recycler View
        var_recyclerView = findViewById(R.id.recycler_view);
        helper.getAllStudentsData();
    }

    public void setRecyclerView (List<StudentTable> studentTablesList)
    {
        var_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        studentAdaptor = new StudentAdaptor(this , studentTablesList);
        var_recyclerView.setAdapter(studentAdaptor);
    }
}