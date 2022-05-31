package com.example.librarybookmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class BookExtensionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_extension)

        var isbn: TextView = findViewById(R.id.textView27)
        var author: TextView = findViewById(R.id.textView28)
        var title: TextView = findViewById(R.id.textView29)

        isbn.setText(intent.getIntExtra("isbn",0).toString())
        author.setText(intent.getStringExtra("author"))
        title.setText(intent.getStringExtra("title"))
    }
}