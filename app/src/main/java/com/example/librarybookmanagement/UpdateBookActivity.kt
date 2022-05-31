package com.example.librarybookmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AlertDialog
import com.example.librarybookmanagement.data.Books
import com.example.librarybookmanagement.databinding.ActivityBookFormBinding
import com.example.librarybookmanagement.databinding.ActivityUpdateBookBinding
import com.example.librarybookmanagement.viewmodels.BookViewModel
import java.time.LocalDate
import java.time.Period

class UpdateBookActivity : AppCompatActivity() {
    lateinit var vm: BookViewModel
    lateinit var binding: ActivityUpdateBookBinding
    var bookId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = BookViewModel(application)
        bookId = intent.getIntExtra("bookId",0)

        vm.getBook(bookId)?.observe(this) {
            binding.updateIsbnTextviewT.setText(it.isbn.toString())
            binding.updateTitleTextviewT.setText(it.title)
            binding.updateAuthorTextviewT.setText(it.author)
            binding.updatePublishedTextviewT.setText(it.published)
            binding.updatePublisherTextviewT.setText(it.publisher)
            binding.updatePagesTextviewT.setText(it.pages.toString())
            binding.updateSubtitleTextviewT.setText(it.subtitle)
            binding.updateWebsiteTextviewT.setText(it.website)
            binding.updateDescriptionTextviewT.setText(it.description)
        }

        isbnFocusListener()
        pagesFocusListener()
        authorFocusListener()
        publishedFocusListener()
        publisherFocusListener()
        descriptionFocusListener()
        websiteFocusListener()
        subtitleFocusListener()
        titleFocusListener()

        binding.updateBookButton.setOnClickListener() {
            submitForm()
        }

        binding.cancelBookButton.setOnClickListener() {
            cancelForm()
        }

        binding.clearBookButton.setOnClickListener() {
            binding.updateIsbnTextviewT.text = null
            binding.updateTitleTextviewT.text = null
            binding.updateAuthorTextviewT.text = null
            binding.updateSubtitleTextviewT.text = null
            binding.updatePagesTextviewT.text = null
            binding.updateWebsiteTextviewT.text = null
            binding.updateDescriptionTextviewT.text = null
            binding.updatePublisherTextviewT.text = null
            binding.updatePublishedTextviewT.text = null
        }
    }

    private fun submitForm() {
        binding.updateIsbnTextviewL.helperText = validIsbn()
        binding.updateAuthorTextviewL.helperText = validAuther()
        binding.updateTitleTextviewL.helperText = validTitle()
        binding.updateSubtitleTextviewL.helperText = validSubtitle()
        binding.updatePublisherTextviewL.helperText = validPublisher()
        binding.updatePublishedTextviewL.helperText = validPublished()
        binding.updatePagesTextviewL.helperText = validPages()
        binding.updateWebsiteTextviewL.helperText = validWebsite()
        binding.updateDescriptionTextviewL.helperText = validDescription()

        val validIsbn = binding.updateIsbnTextviewL.helperText == null
        val validSubtitle = binding.updateSubtitleTextviewL.helperText == null
        val validAuthor = binding.updateAuthorTextviewL.helperText == null
        val validPublisher = binding.updatePublisherTextviewL.helperText == null
        val validPublished = binding.updatePublishedTextviewL.helperText == null
        val validWebsite = binding.updateWebsiteTextviewL.helperText == null
        val validDescription = binding.updateDescriptionTextviewL.helperText == null
        val validPages = binding.updatePagesTextviewL.helperText == null
        val validTitle = binding.updateTitleTextviewL.helperText == null

        if (validIsbn && validSubtitle && validAuthor && validPublisher && validPublished && validWebsite && validDescription && validPages && validTitle) {
            resetForm()
        } else {
            invalidForm()
        }
    }

    private fun resetForm() {
        var message = "\n\nISBN: " + binding.updateIsbnTextviewT.text
        message += "\n\nAuthor: " + binding.updateAuthorTextviewT.text
        message += "\n\nTitle: " + binding.updateTitleTextviewT.text
        message += "\n\nSubtitle: " + binding.updateSubtitleTextviewT.text
        message += "\n\nPublished: " + binding.updatePublishedTextviewT.text
        message += "\n\nPublisher: " + binding.updatePublisherTextviewT.text
        message += "\n\nWebsite: " + binding.updateWebsiteTextviewT.text
        message += "\n\nPages: " + binding.updatePagesTextviewT.text
        message += "\n\nDescription: " + binding.updateDescriptionTextviewT.text

        AlertDialog.Builder(this)
            .setTitle("Are you sure you want to update book?")
            .setMessage(message)
            .setPositiveButton("Ok") {_, _ ->
                var book = Books(
                    binding.updateIsbnTextviewT.text.toString().toLong(),
                    binding.updateTitleTextviewT.text.toString(),
                    binding.updateSubtitleTextviewT.text.toString(),
                    binding.updateAuthorTextviewT.text.toString(),
                    binding.updatePublishedTextviewT.text.toString(),
                    binding.updatePublisherTextviewT.text.toString(),
                    binding.updatePagesTextviewT.text.toString().toInt(),
                    binding.updateDescriptionTextviewT.text.toString(),
                    binding.updateWebsiteTextviewT.text.toString()
                )
                book.bookId = bookId
                vm.updateBook(book)
                var nextIntent = Intent(this, MainActivity::class.java)
                startActivity(nextIntent)

                binding.updateIsbnTextviewT.text = null
                binding.updateTitleTextviewT.text = null
                binding.updateAuthorTextviewT.text = null
                binding.updateSubtitleTextviewT.text = null
                binding.updatePagesTextviewT.text = null
                binding.updateWebsiteTextviewT.text = null
                binding.updateDescriptionTextviewT.text = null
                binding.updatePublisherTextviewT.text = null
                binding.updatePublishedTextviewT.text = null
            }
            .setNegativeButton("Cancel") {_, _ ->
                println("Cancel pressed")
            }
            .show()
    }

    private fun invalidForm() {
        var message = ""
        if(binding.updateIsbnTextviewL.helperText != null)
            message += "\n\nISBN: " + binding.updateIsbnTextviewL.helperText
        if(binding.updateAuthorTextviewL.helperText != null)
            message += "\n\nAuthor: " + binding.updateAuthorTextviewL.helperText
        if(binding.updateTitleTextviewL.helperText != null)
            message += "\n\nTitle: " + binding.updateTitleTextviewL.helperText
        if(binding.updateSubtitleTextviewL.helperText != null)
            message += "\n\nSubtitle: " + binding.updateSubtitleTextviewL.helperText
        if(binding.updatePublishedTextviewL.helperText != null)
            message += "\n\nPublished: " + binding.updatePublishedTextviewL.helperText
        if(binding.updatePublisherTextviewL.helperText != null)
            message += "\n\nPublisher: " + binding.updatePublisherTextviewL.helperText
        if(binding.updateWebsiteTextviewL.helperText != null)
            message += "\n\nWebsite: " + binding.updateWebsiteTextviewL.helperText
        if(binding.updatePagesTextviewL.helperText != null)
            message += "\n\nPages: " + binding.updatePagesTextviewL.helperText
        if(binding.updateDescriptionTextviewL.helperText != null)
            message += "\n\nDescription: " + binding.updateDescriptionTextviewL.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Ok") {_, _ ->
                println("Ok pressed")
            }
            .show()
    }

    private fun cancelForm() {
        var nextIntent = Intent(this, MainActivity::class.java)
        startActivity(nextIntent)
    }

    private fun isbnFocusListener() {
        binding.updateIsbnTextviewT.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.updateIsbnTextviewL.helperText = validIsbn()
            } else {
                binding.updateIsbnTextviewL.helperText = ""
            }
        }
    }

    private fun validIsbn(): String? {
        if (binding.updateIsbnTextviewT.text.toString().toLongOrNull() == null) {
            if (binding.updateIsbnTextviewT.length() == 0) {
                return "Required"
            }
            return "Invalid ISBN number"
        }
        return null
    }

    private fun titleFocusListener() {
        binding.updateTitleTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.updateTitleTextviewL.helperText = ""
            } else if(binding.updateTitleTextviewT.length() == 0) {
                binding.updateTitleTextviewL.helperText = "Required"
            }
        }
    }

    private fun validTitle(): String? {
        if (binding.updateTitleTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun pagesFocusListener() {
        binding.updatePagesTextviewT.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.updatePagesTextviewL.helperText = validPages()
            } else {
                binding.updatePagesTextviewL.helperText = ""
            }
        }
    }

    private fun validPages(): String? {
        if (binding.updatePagesTextviewT.text.toString().toIntOrNull() == null) {
            if (binding.updatePagesTextviewT.length() == 0) {
                return "Required"
            }
            return "Please enter valid page numbers"
        }
        return null
    }

    private fun authorFocusListener() {
        binding.updateAuthorTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.updateAuthorTextviewL.helperText = ""
            } else if(binding.updateAuthorTextviewT.length() == 0) {
                binding.updateAuthorTextviewL.helperText = "Required"
            }
        }
    }

    private fun validAuther(): String? {
        if (binding.updateAuthorTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun publisherFocusListener() {
        binding.updatePublisherTextviewL.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.updatePublisherTextviewL.helperText = ""
            } else if(binding.updatePublisherTextviewT.length() == 0) {
                binding.updatePublisherTextviewL.helperText = "Required"
            }
        }
    }

    private fun validPublisher(): String? {
        if (binding.updatePublisherTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun publishedFocusListener() {
        binding.updatePublishedTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.updatePublishedTextviewL.helperText = ""
            } else if(binding.updatePublishedTextviewT.length() == 0) {
                binding.updatePublishedTextviewL.helperText = "Required"
            }
        }
    }

    private fun validPublished(): String? {
        if (binding.updatePublishedTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun websiteFocusListener() {
        binding.updateWebsiteTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.updateWebsiteTextviewL.helperText = ""
            } else if(binding.updateWebsiteTextviewT.length() == 0) {
                binding.updateWebsiteTextviewL.helperText = "Required"
            }
        }
    }

    private fun validWebsite(): String? {
        if (binding.updateWebsiteTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun descriptionFocusListener() {
        binding.updateDescriptionTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.updateDescriptionTextviewL.helperText = ""
            } else if(binding.updateDescriptionTextviewT.length() == 0) {
                binding.updateDescriptionTextviewL.helperText = "Required"
            }
        }
    }

    private fun validDescription(): String? {
        if (binding.updateDescriptionTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun subtitleFocusListener() {
        binding.updateSubtitleTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.updateSubtitleTextviewL.helperText = ""
            } else if(binding.updateSubtitleTextviewT.length() == 0) {
                binding.updateSubtitleTextviewL.helperText = "Required"
            }
        }
    }

    private fun validSubtitle(): String? {
        if (binding.updateSubtitleTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }
}