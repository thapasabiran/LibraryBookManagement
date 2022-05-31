package com.example.librarybookmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.librarybookmanagement.adapters.BooksAdapter
import com.example.librarybookmanagement.adapters.MyBooksadapter
import com.example.librarybookmanagement.data.Books
import com.example.librarybookmanagement.data.StudentBooks
import com.example.librarybookmanagement.viewmodels.BookViewModel
import com.example.librarybookmanagement.viewmodels.StudentBookViewModel

class MyBooksActivity : AppCompatActivity() {
    lateinit var vm: BookViewModel
    lateinit var vmStudentBook: StudentBookViewModel
    lateinit var adapter: MyBooksadapter
    lateinit var recyclerView: RecyclerView
    lateinit var booksList: ArrayList<Books>
    lateinit var studentBookList: ArrayList<StudentBooks>
    lateinit var loadingDialog: LoadingDialog
    lateinit var myBookList: ArrayList<MyBooks>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_books)

        vm = BookViewModel(application)
        vmStudentBook = StudentBookViewModel(application)
        loadingDialog = LoadingDialog(this)

        booksList = ArrayList<Books>()
        myBookList = ArrayList<MyBooks>()

        studentBookList = ArrayList<StudentBooks>()
        adapter = MyBooksadapter({position -> onCardClick(position) }, myBookList)
        recyclerView = findViewById(R.id.myBookRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        loadingDialog.startLoadingDialog()

        //ToDo: studentId is hardcoded for now
        vmStudentBook.getStudentBook(1)?.observe(this) {
            studentBookList = it as ArrayList<StudentBooks> /* = java.util.ArrayList<com.example.librarybookmanagement.data.StudentBooks> */

            for (stuBook in studentBookList) {
                var myBook = MyBooks("", "", "","","")
                var vmBook = BookViewModel(application)

                vmBook.getBook(stuBook.bookId)?.observe(this@MyBooksActivity) {
                    myBook.bookAuthor = "Author: " + it.author
                    myBook.bookTitle = "Title: " + it.title
                    myBook.checkOut = stuBook.submissionDate
                    myBook.checkedIn = stuBook.issueDate
                    myBookList.add(myBook)
                    adapter.setItem(myBookList)
                    loadingDialog.dismissDialog()
                }
            }
        }
    }

    fun goBack(view: View) {
        val intentNext = Intent(this, MainActivity::class.java)
        startActivity(intentNext)
    }

    fun onCardClick(position: Int) {
//        val myIntent = Intent(this, BookExtensionActivity::class.java)
//        myIntent.putExtra("isbn", booksList[position].isbn)
//        myIntent.putExtra("title", booksList[position].title)
//        myIntent.putExtra("author", booksList[position].author)
//        startActivity(myIntent)
    }
}