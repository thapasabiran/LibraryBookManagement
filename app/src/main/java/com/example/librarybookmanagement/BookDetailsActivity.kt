package com.example.librarybookmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class BookDetailsActivity : AppCompatActivity() {
    var bookId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        bookId = intent.getIntExtra("bookId",0)
        val isbn: TextView = findViewById(R.id.book_detail_isbn_t)
        val author: TextView = findViewById(R.id.book_detail_author_t)
        val title: TextView = findViewById(R.id.book_detail_title_t)
        val subtitle: TextView = findViewById(R.id.book_detail_subtitle_t)
        val pages: TextView = findViewById(R.id.book_detail_pages_t)
        val published: TextView = findViewById(R.id.book_detail_published_t)
        val publisher: TextView = findViewById(R.id.book_detail_publisher_t)
        val website: TextView = findViewById(R.id.book_detail_website_t)
        val description: TextView = findViewById(R.id.book_detail_description_t)

        isbn.text = intent.getLongExtra("isbn",0).toString()
        author.text = intent.getStringExtra("author")
        title.text = intent.getStringExtra("title")
        subtitle.text = intent.getStringExtra("subtitle")
        pages.text = intent.getIntExtra("pages",0).toString()
        published.text = intent.getStringExtra("published")
        publisher.text = intent.getStringExtra("publisher")
        website.text = intent.getStringExtra("website")
        description.text = intent.getStringExtra("description")
    }

    fun getStudentDetails(view: View) {
        val nextIntent = Intent(this, StudentCheckoutActivity::class.java)
        nextIntent.putExtra("bookId", bookId)
        startActivity(nextIntent)
    }

    fun goBack(view: View) {
        val nextIntent = Intent(this, MainActivity::class.java)
        startActivity(nextIntent)
    }

    fun updateBook(view: View) {
        val nextIntent = Intent(this, UpdateBookActivity::class.java)
        nextIntent.putExtra("bookId", bookId)
        startActivity(nextIntent)
    }
}