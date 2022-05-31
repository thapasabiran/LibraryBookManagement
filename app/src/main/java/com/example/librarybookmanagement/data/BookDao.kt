package com.example.librarybookmanagement.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDao {
    //Create
    @Insert
    fun insertBook(books: Books)

    //Read
    @Query("select * from books")
    fun selectBooks(): LiveData<MutableList<Books>>

    //Read
    @Query("select * from books where bookId = :bookId")
    fun selectBook(bookId: Int): LiveData<Books>

    @Query("select * from books where title like :searchQry or author like :searchQry or publisher like :searchQry or description like :searchQry")
    fun searchBook(searchQry: String): LiveData<MutableList<Books>>

    //Update
    @Update
    fun updateBook(book: Books)

    //Delete
    @Delete
    fun deleteBook(book: Books)

    //Read
    @Query("select * from books order by author asc")
    fun sortBookByAuthor(): LiveData<MutableList<Books>>

    //Read
    @Query("select * from books order by title asc")
    fun sortBookByTitle(): LiveData<MutableList<Books>>

    //Read
    @Query("select * from books order by publisher asc")
    fun sortBookByPublisher(): LiveData<MutableList<Books>>

    //Read
    @Query("select * from books order by bookId asc")
    fun sortBookById(): LiveData<MutableList<Books>>

    //Read
    @Query("select * from books order by bookId desc")
    fun sortBookByIdDes(): LiveData<MutableList<Books>>

    //Read
    @Query("select * from books order by author desc")
    fun sortBookByAuthorDes(): LiveData<MutableList<Books>>

    //Read
    @Query("select * from books order by title desc")
    fun sortBookByTitleDes(): LiveData<MutableList<Books>>

    //Read
    @Query("select * from books order by publisher desc")
    fun sortBookByPublisherDes(): LiveData<MutableList<Books>>
}