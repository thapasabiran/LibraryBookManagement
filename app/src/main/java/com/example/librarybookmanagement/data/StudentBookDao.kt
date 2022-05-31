package com.example.librarybookmanagement.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface StudentBookDao {
    //Create
    @Insert
    fun insertStudentBook(studentBook: StudentBooks)

    //Read
    @Query("select * from studentBooks")
    fun selectStudentBooks(): LiveData<MutableList<StudentBooks>>

    //Read
    @Query("select * from studentBooks where studentId = :studentId")
    fun selectStudentBook(studentId: Int): LiveData<MutableList<StudentBooks>>

    //Update
    @Update
    fun updateStudentBook(studentBook: StudentBooks)

    //Delete
    @Delete
    fun deleteStudentBook(studentBook: StudentBooks)
}