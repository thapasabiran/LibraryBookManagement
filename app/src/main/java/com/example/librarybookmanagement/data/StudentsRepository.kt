package com.example.librarybookmanagement.data

import android.content.Context
import androidx.lifecycle.LiveData

class StudentsRepository(context: Context) {
    var db:StudentDao? = AppDatabase.getInstance(context)?.studentDao()

    fun getAllStudents(): LiveData<MutableList<Students>>? {
        return db?.selectStudents()
    }

    fun getStudent(studentId: Int): LiveData<Students>? {
        return db?.selectStudent(studentId)
    }

    fun insertStudent(student: Students) {
        db?.insertStudent(student)
    }

    fun updateStudent(student: Students) {
        db?.updateStudent(student)
    }

    fun deleteStudent(student: Students) {
        db?.deleteStudent(student)
    }
}