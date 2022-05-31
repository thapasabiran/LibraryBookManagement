package com.example.librarybookmanagement.adapters

import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.librarybookmanagement.MyBooks
import com.example.librarybookmanagement.R
import com.example.librarybookmanagement.data.StudentBooks
import com.example.librarybookmanagement.viewmodels.BookViewModel

class MyBooksadapter(private val onCardClick: (position: Int) -> Unit, private var myBook: List<MyBooks>): RecyclerView.Adapter<MyBookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyBookViewHolder {
        var holder = LayoutInflater.from(parent.context).inflate(R.layout.my_book_layout, parent, false)
        return MyBookViewHolder(holder, onCardClick)
    }

    override fun onBindViewHolder(holder: MyBookViewHolder, position: Int) {
        var vm = myBook[position]

        holder.checkedIn.text = vm.checkedIn
        holder.checkOut.text = vm.checkOut
        holder.fineAccumulated.text = "10"
        holder.bookAuthor.text = vm.bookAuthor
        holder.bookTitle.text = vm.bookTitle
    }

    fun setItem(myBooks: List<MyBooks>) {
        this.myBook = myBooks
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return myBook.size
    }
}

class MyBookViewHolder(view: View, private val onCardClick: (position: Int) -> Unit): RecyclerView.ViewHolder(view), View.OnClickListener {
    init {
        itemView.setOnClickListener(this)
    }
    var bookTitle: TextView = view.findViewById(R.id.my_book_title_t)
    var bookAuthor: TextView = view.findViewById(R.id.my_book_author_t)
    var checkedIn: TextView = view.findViewById(R.id.my_book_checkedin)
    var checkOut: TextView = view.findViewById(R.id.my_book_checkedout)
    var fineAccumulated: TextView = view.findViewById(R.id.my_book_fine)

    override fun onClick(v: View?) {
        val position = adapterPosition
        //7 - the function below is what is passed and referring to the one in MainActivity
        onCardClick(position)
    }
}