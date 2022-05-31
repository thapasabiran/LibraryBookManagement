package com.example.librarybookmanagement.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentDao {
    //Create
    @Insert
    fun insertStudent(student: Students)

    //Read
    @Query("select * from students")
    fun selectStudents(): LiveData<MutableList<Students>>

    //Read
    @Query("select * from students where studentId = :studentId")
    fun selectStudent(studentId: Int): LiveData<Students>

    //Update
    @Update
    fun updateStudent(student: Students)

    //Delete
    @Delete
    fun deleteStudent(student: Students)
}