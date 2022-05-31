package com.example.librarybookmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.librarybookmanagement.adapters.BooksAdapter
import com.example.librarybookmanagement.data.Books
import com.example.librarybookmanagement.viewmodels.BookViewModel
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var vm: BookViewModel
    lateinit var adapter: BooksAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var booksList: ArrayList<Books>
    lateinit var searchBooksList: ArrayList<Books>
    lateinit var sortBooksList: ArrayList<Books>
    lateinit var searchView: SearchView
    lateinit var loadingDialog: LoadingDialog
    val sortBy = arrayOf("BookID Desc", "Title Desc", "Author Desc", "Publisher Desc", "BookID", "Title", "Author", "Publisher")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadingDialog = LoadingDialog(this)

        vm = BookViewModel(application)
        booksList = ArrayList<Books>()
        adapter = BooksAdapter({position -> onCardClick(position) }, booksList)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter


        loadingDialog.startLoadingDialog()
        vm.allBooks?.observe(this) {
            booksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
            adapter.setItem(booksList)
            loadingDialog.dismissDialog()
        }

        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                vm.searchBook("%$query%")?.observe(this@MainActivity) {
                    searchBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                    booksList = searchBooksList
                    adapter.setItem(searchBooksList)
                }
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                vm.searchBook("%$newText%")?.observe(this@MainActivity) {
                    searchBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                    booksList = searchBooksList
                    adapter.setItem(searchBooksList)
                }
                return false
            }
        })

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                val deletedBook: Books =
                    booksList.get(viewHolder.adapterPosition)

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                vm.deleteBook(deletedBook)
                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                booksList.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                adapter.notifyItemRemoved(viewHolder.adapterPosition)

                // below line is to display our snackbar with action.
                Snackbar.make(recyclerView, "${deletedBook.title} deleted", Snackbar.LENGTH_LONG)
                    .setAction("Undo",
                        View.OnClickListener { // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            booksList.add(position, deletedBook)
                            vm.insertBook(deletedBook)
                            // below line is to notify item is
                            // added to our adapter class.
                            adapter.notifyItemInserted(position)
                        }).show()
            } // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(recyclerView)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, sortBy)
        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                searchView.setQuery("", false)
                searchView.clearFocus()
                if (sortBy[position] == "Author") {
                    vm.sortBookByAuthor()?.observe(this@MainActivity) {
                        sortBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                        booksList = sortBooksList
                        adapter.setItem(sortBooksList)
                    }
                } else if (sortBy[position] == "Title") {
                    vm.sortBookByTitle()?.observe(this@MainActivity) {
                        sortBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                        booksList = sortBooksList
                        adapter.setItem(sortBooksList)
                    }
                } else if (sortBy[position] == "Publisher") {
                    vm.sortBookByPublisher()?.observe(this@MainActivity) {
                        sortBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                        booksList = sortBooksList
                        adapter.setItem(sortBooksList)
                    }
                } else if (sortBy[position] == "Title Desc") {
                    vm.sortBookByTitleDesc()?.observe(this@MainActivity) {
                        sortBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                        booksList = sortBooksList
                        adapter.setItem(sortBooksList)
                    }
                } else if (sortBy[position] == "BookID Desc") {
                    vm.sortBookByIdDesc()?.observe(this@MainActivity) {
                        sortBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                        booksList = sortBooksList
                        adapter.setItem(sortBooksList)
                    }
                } else if (sortBy[position] == "BookID") {
                    vm.sortBookById()?.observe(this@MainActivity) {
                        sortBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                        booksList = sortBooksList
                        adapter.setItem(sortBooksList)
                    }
                } else if (sortBy[position] == "Author Desc") {
                    vm.sortBookByAuthorDesc()?.observe(this@MainActivity) {
                        sortBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                        booksList = sortBooksList
                        adapter.setItem(sortBooksList)
                    }
                } else if (sortBy[position] == "Publisher Desc") {
                    vm.sortBookByPublisherDesc()?.observe(this@MainActivity) {
                        sortBooksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                        booksList = sortBooksList
                        adapter.setItem(sortBooksList)
                    }
                } else {
                    vm.allBooks?.observe(this@MainActivity) {
                        booksList = it as ArrayList<Books> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Books> */
                        adapter.setItem(booksList)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    fun getBookList(view: View) {
        val intentNext = Intent(this, BookFormActivity::class.java)
        startActivity(intentNext)
    }

    fun getMyBooks(view: View) {
        val intentNext = Intent(this, MyBooksActivity::class.java)
        startActivity(intentNext)
    }

    private fun onCardClick(position: Int) {
        val myIntent = Intent(this, BookDetailsActivity::class.java)
        myIntent.putExtra("bookId", booksList[position].bookId)
        myIntent.putExtra("author", booksList[position].author)
        myIntent.putExtra("title", booksList[position].title)
        myIntent.putExtra("subtitle", booksList[position].subtitle)
        myIntent.putExtra("publisher", booksList[position].publisher)
        myIntent.putExtra("published", booksList[position].published)
        myIntent.putExtra("website", booksList[position].website)
        myIntent.putExtra("pages", booksList[position].pages)
        myIntent.putExtra("description", booksList[position].description)
        myIntent.putExtra("isbn", booksList[position].isbn)
        startActivity(myIntent)
    }
}