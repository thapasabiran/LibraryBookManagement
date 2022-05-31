package com.example.librarybookmanagement.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.librarybookmanagement.R
import com.example.librarybookmanagement.data.Books

class StudentsAdapter(private val onCardClick: (position: Int) -> Unit, private var books: List<Books>): RecyclerView.Adapter<StudentsViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        var holder = LayoutInflater.from(parent.context).inflate(R.layout.book_details_layout, parent, false)
        return StudentsViewHolder(holder, onCardClick)
    }

    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        var vm = books[position]

        holder.bookTitle.text = vm.title
//        holder.isbn.text = vm.isbn.toString()
        holder.bookAuthor.text = vm.author
        holder.bookPublisher.text = vm.publisher
//        holder.bookDescription.text = vm.description
    }

    fun setItem(books: List<Books>) {
        this.books = books
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return books.size
    }
}

class StudentsViewHolder(view: View, private val onCardClick: (position: Int) -> Unit): RecyclerView.ViewHolder(view), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    //    var isbn: TextView = view.findViewById(R.id.book_isbn)
    var bookTitle: TextView = view.findViewById(R.id.book_title)
    var bookAuthor: TextView = view.findViewById(R.id.book_author)
    var bookPublisher: TextView = view.findViewById(R.id.book_publisher)
//    var bookDescription: TextView = view.findViewById(R.id.book_description)

    override fun onClick(v: View?) {
        val position = adapterPosition
        onCardClick(position)
    }
}