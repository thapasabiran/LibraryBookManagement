package com.example.librarybookmanagement.data

import android.content.Context
import androidx.lifecycle.LiveData

class StudentBooksRepository(context: Context) {
    var db:StudentBookDao? = AppDatabase.getInstance(context)?.studentBooksDao()

    fun getAllStudentBooks(): LiveData<MutableList<StudentBooks>>? {
        return db?.selectStudentBooks()
    }

    fun getStudentBook(studentId: Int): LiveData<MutableList<StudentBooks>>? {
        return db?.selectStudentBook(studentId)
    }

    fun insertStudentBook(studentBook: StudentBooks) {
        db?.insertStudentBook(studentBook)
    }

    fun updateStudentBook(studentBook: StudentBooks) {
        db?.updateStudentBook(studentBook)
    }

    fun deleteStudentBook(studentBook: StudentBooks) {
        db?.deleteStudentBook(studentBook)
    }
}