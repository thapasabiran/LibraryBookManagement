package com.example.librarybookmanagement.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.librarybookmanagement.data.Books
import com.example.librarybookmanagement.data.BooksRepository
import kotlinx.coroutines.launch

class BookViewModel(var app: Application): AndroidViewModel(app) {
    private var repo = BooksRepository(app)
    private var book: LiveData<Books>? = MutableLiveData<Books>()
    var allBooks: LiveData<MutableList<Books>>? = repo.getAllBooks()
    private var searchList: LiveData<MutableList<Books>>? = MutableLiveData()

    fun getAllBooks() = viewModelScope.launch {
        repo.getAllBooks()
    }

    fun getBook(bookId: Int): LiveData<Books>? {
        viewModelScope.launch {
            book = repo.getBook(bookId)
        }
        return book
    }

    fun insertBook(book: Books) = viewModelScope.launch {
        repo.insertBook(book)
    }

    fun searchBook(searchQry: String): LiveData<MutableList<Books>>? {
        viewModelScope.launch {
            searchList = repo.searchBook(searchQry)
        }
        return searchList
    }

    fun updateBook(book: Books) = viewModelScope.launch {
        repo.updateBook(book)
    }

    fun deleteBook(book: Books) = viewModelScope.launch {
        repo.deleteBook(book)
    }

    fun sortBookByAuthor(): LiveData<MutableList<Books>>? {
        viewModelScope.launch {
            searchList = repo.sortByAuthor()
        }
        return searchList
    }

    fun sortBookByTitle(): LiveData<MutableList<Books>>? {
        viewModelScope.launch {
            searchList = repo.sortByTitle()
        }
        return searchList
    }

    fun sortBookByPublisher(): LiveData<MutableList<Books>>? {
        viewModelScope.launch {
            searchList = repo.sortByPublisher()
        }
        return searchList
    }

    fun sortBookById(): LiveData<MutableList<Books>>? {
        viewModelScope.launch {
            searchList = repo.sortById()
        }
        return searchList
    }

    fun sortBookByIdDesc(): LiveData<MutableList<Books>>? {
        viewModelScope.launch {
            searchList = repo.sortByIdDesc()
        }
        return searchList
    }

    fun sortBookByAuthorDesc(): LiveData<MutableList<Books>>? {
        viewModelScope.launch {
            searchList = repo.sortByAuthorDesc()
        }
        return searchList
    }

    fun sortBookByTitleDesc(): LiveData<MutableList<Books>>? {
        viewModelScope.launch {
            searchList = repo.sortByTitleDesc()
        }
        return searchList
    }

    fun sortBookByPublisherDesc(): LiveData<MutableList<Books>>? {
        viewModelScope.launch {
            searchList = repo.sortByPublisherDesc()
        }
        return searchList
    }
}