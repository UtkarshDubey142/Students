package com.utkarsh.students.Helper;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.utkarsh.students.DataViewPage;
import com.utkarsh.students.Databases.DatabaseClient;
import com.utkarsh.students.Databases.StudentTable;

import java.util.List;

public class DatabaseHelper {

    Context context;

    // Constructor
    public DatabaseHelper(Context context) {
        this.context = context;
    }

    public static DatabaseHelper getInstance(Context context)
    {
        return new DatabaseHelper(context);
    }

    // Insert Data
    public void addNewStudent (String stu_name , String stu_standard)
    {
        class NewStudent extends AsyncTask<Void ,Void , StudentTable>
        {
            @Override
            protected StudentTable doInBackground(Void... voids) {
                StudentTable studentTable = new StudentTable();
                studentTable.setStu_name(stu_name); // setting student name
                studentTable.setStu_standard(stu_standard); // setting student standard

                DatabaseClient.getInstance(context)
                        .getStudentsDatabase()
                        .studentDao()
                        .insetData(studentTable);
                return studentTable;
            }

            @Override
            protected void onPostExecute(StudentTable studentTable) {
                super.onPostExecute(studentTable);
                if (studentTable != null)
                {
                    Toast.makeText(context , studentTable.getStu_name() + "\n" + studentTable.getStu_standard(), Toast.LENGTH_SHORT).show();
                }
            }
        }

        /* Object NewStudent class*/
        NewStudent newStudent = new NewStudent();
        newStudent.execute();
    }

    // Show all data from StudentTable
    public void getAllStudentsData()
    {
        class  AllStudents extends AsyncTask<Void,Void,List<StudentTable>>
        {

            @Override
            protected List<StudentTable> doInBackground(Void... voids) {
                List<StudentTable> list = DatabaseClient.getInstance(context)
                        .getStudentsDatabase()
                        .studentDao()
                        .selectAll();
                return list;
            }

            @Override
            protected void onPostExecute(List<StudentTable> studentTables) {
                super.onPostExecute(studentTables);
                if (studentTables != null && studentTables.size() > 0)
                {
                    ((DataViewPage)context).setRecyclerView(studentTables);
                }
            }
        }

        // Object
        AllStudents allStudents = new AllStudents();
        allStudents.execute();
    }

    // Update Data
    public void updateData (StudentTable table , String stu_name , String stu_standard)
    {
        class UpdateStudentData extends  AsyncTask<Void , Void , StudentTable>
        {

            @Override
            protected StudentTable doInBackground(Void... voids) {
                table.setStu_name(stu_name);
                table.setStu_standard(stu_standard);

                DatabaseClient.getInstance(context)
                        .getStudentsDatabase()
                        .studentDao()
                        .updateData(table);
                return table;
            }

            @Override
            protected void onPostExecute(StudentTable studentTable) {
                super.onPostExecute(studentTable);
                if (table != null)
                {
                    Toast.makeText(context , "Updated!" + "\n" + table.getStu_name() + "\n" + table.getStu_standard() , Toast.LENGTH_SHORT).show();
                }
            }
        }

        UpdateStudentData updateStudentData = new UpdateStudentData();
        updateStudentData.execute();
    }

    public void deleteData (StudentTable studentTable)
    {
        class DeleteData extends  AsyncTask<Void , Void , Void>
        {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(context)
                        .getStudentsDatabase()
                        .studentDao()
                        .deleteData(studentTable);
                return null;
            }
        }

        DeleteData deleteData = new DeleteData();
        deleteData.execute();
    }



}
