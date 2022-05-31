package com.example.librarybookmanagement.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.librarybookmanagement.data.Students
import com.example.librarybookmanagement.data.StudentsRepository
import kotlinx.coroutines.launch

class StudentViewModel(app: Application): AndroidViewModel(app) {
    private var repo = StudentsRepository(app)
    private var student: LiveData<Students>? = MutableLiveData()
    var allStudents: LiveData<MutableList<Students>>? = repo.getAllStudents()

    fun getAllStudents() = viewModelScope.launch {
        repo.getAllStudents()
    }

    fun getStudent(studentId: Int): LiveData<Students>? {
        viewModelScope.launch {
            student = repo.getStudent(studentId)
        }
        return student
    }
    fun insertStudent(student: Students) = viewModelScope.launch {
        repo.insertStudent(student)
    }

    fun updateStudent(student: Students) = viewModelScope.launch {
        repo.updateStudent(student)
    }

    fun deleteStudent(student: Students) = viewModelScope.launch {
        repo.deleteStudent(student)
    }
}