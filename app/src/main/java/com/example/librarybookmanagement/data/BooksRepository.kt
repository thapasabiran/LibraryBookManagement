package com.example.librarybookmanagement.data

import android.content.Context
import androidx.lifecycle.LiveData

class BooksRepository(context: Context) {
    var db:BookDao? = AppDatabase.getInstance(context)?.bookDao()

    fun getAllBooks(): LiveData<MutableList<Books>>? {
        return db?.selectBooks()
    }

    fun getBook(bookId: Int): LiveData<Books>? {
        return db?.selectBook(bookId)
    }

    fun insertBook(book: Books) {
        db?.insertBook(book)
    }

    fun searchBook(searchQry: String): LiveData<MutableList<Books>>? {
        return db?.searchBook(searchQry)
    }

    fun updateBook(book: Books) {
        db?.updateBook(book)
    }

    fun deleteBook(book: Books) {
        db?.deleteBook(book)
    }

    fun sortByAuthor(): LiveData<MutableList<Books>>? {
        return db?.sortBookByAuthor()
    }

    fun sortByTitle(): LiveData<MutableList<Books>>? {
        return db?.sortBookByTitle()
    }

    fun sortByPublisher(): LiveData<MutableList<Books>>? {
        return db?.sortBookByPublisher()
    }

    fun sortById(): LiveData<MutableList<Books>>? {
        return db?.sortBookById()
    }

    fun sortByIdDesc(): LiveData<MutableList<Books>>? {
        return db?.sortBookByIdDes()
    }

    fun sortByAuthorDesc(): LiveData<MutableList<Books>>? {
        return db?.sortBookByAuthorDes()
    }

    fun sortByTitleDesc(): LiveData<MutableList<Books>>? {
        return db?.sortBookByTitleDes()
    }

    fun sortByPublisherDesc(): LiveData<MutableList<Books>>? {
        return db?.sortBookByPublisherDes()
    }
}