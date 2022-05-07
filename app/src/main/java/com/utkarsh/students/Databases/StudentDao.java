package com.utkarsh.students.Databases;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDao {

    // Insert Data
    @Insert
    void insetData (StudentTable studentTable);

    // Getting All Data
    @Query("SELECT * FROM studentTable")
    List<StudentTable> selectAll ();

    // Update Data
    @Update
    void updateData (StudentTable studentTable);

    // Delete Data
    @Delete
    void deleteData (StudentTable studentTable);
}
