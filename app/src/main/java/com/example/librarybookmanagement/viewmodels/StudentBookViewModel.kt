package com.example.librarybookmanagement.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.librarybookmanagement.data.StudentBooks
import com.example.librarybookmanagement.data.StudentBooksRepository
import kotlinx.coroutines.launch

class StudentBookViewModel(app: Application): AndroidViewModel(app) {
    private var repo = StudentBooksRepository(app)
    private var studentBook: LiveData<MutableList<StudentBooks>>? = MutableLiveData()
    var allStudentBooks: LiveData<MutableList<StudentBooks>>? = repo.getAllStudentBooks()

    fun getAllStudentBooks() = viewModelScope.launch {
        repo.getAllStudentBooks()
    }

    fun getStudentBook(studentId: Int): LiveData<MutableList<StudentBooks>>? {
        viewModelScope.launch {
            studentBook = repo.getStudentBook(studentId)
        }
        return studentBook
    }
    fun insertStudentBook(studentBook: StudentBooks) = viewModelScope.launch {
        repo.insertStudentBook(studentBook)
    }

    fun updateStudentBook(studentBook: StudentBooks) = viewModelScope.launch {
        repo.updateStudentBook(studentBook)
    }

    fun deleteStudentBook(studentBook: StudentBooks) = viewModelScope.launch {
        repo.deleteStudentBook(studentBook)
    }
}