package com.example.librarybookmanagement

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.librarybookmanagement.data.Books
import com.example.librarybookmanagement.databinding.ActivityBookFormBinding
import com.example.librarybookmanagement.viewmodels.BookViewModel

class BookFormActivity : AppCompatActivity() {
    lateinit var vm: BookViewModel
    lateinit var binding: ActivityBookFormBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = BookViewModel(application)

        isbnFocusListener()
        pagesFocusListener()
        authorFocusListener()
        publishedFocusListener()
        publisherFocusListener()
        descriptionFocusListener()
        websiteFocusListener()
        subtitleFocusListener()
        titleFocusListener()

        binding.saveBookFormButton.setOnClickListener() {
            submitForm()
        }

        binding.cancelBookFormButton.setOnClickListener() {
            cancelForm()
        }

        binding.clearBookFormButton.setOnClickListener() {
            binding.isbnTextviewT.text = null
            binding.titleTextviewT.text = null
            binding.authorTextviewT.text = null
            binding.subtitleTextviewT.text = null
            binding.pagesTextviewT.text = null
            binding.websiteTextviewT.text = null
            binding.descriptionTextviewT.text = null
            binding.publisherTextviewT.text = null
            binding.publishedTextviewT.text = null
        }
    }

    private fun submitForm() {
        binding.isbnTextviewL.helperText = validIsbn()
        binding.authorTextviewL.helperText = validAuther()
        binding.titleTextviewL.helperText = validTitle()
        binding.subtitleTextviewL.helperText = validSubtitle()
        binding.publisherTextviewL.helperText = validPublisher()
        binding.publishedTextviewL.helperText = validPublished()
        binding.pagesTextviewL.helperText = validPages()
        binding.websiteTextviewL.helperText = validWebsite()
        binding.descriptionTextviewL.helperText = validDescription()

        val validIsbn = binding.isbnTextviewL.helperText == null
        val validSubtitle = binding.subtitleTextviewL.helperText == null
        val validAuthor = binding.authorTextviewL.helperText == null
        val validPublisher = binding.publisherTextviewL.helperText == null
        val validPublished = binding.publishedTextviewL.helperText == null
        val validWebsite = binding.websiteTextviewL.helperText == null
        val validDescription = binding.descriptionTextviewL.helperText == null
        val validPages = binding.pagesTextviewL.helperText == null
        val validTitle = binding.titleTextviewL.helperText == null

        if (validIsbn && validSubtitle && validAuthor && validPublisher && validPublished && validWebsite && validDescription && validPages && validTitle) {
            resetForm()
        } else {
            invalidForm()
        }
    }

    private fun resetForm() {
        var message = "\n\nISBN: " + binding.isbnTextviewT.text
        message += "\n\nAuthor: " + binding.authorTextviewT.text
        message += "\n\nTitle: " + binding.titleTextviewT.text
        message += "\n\nSubtitle: " + binding.subtitleTextviewT.text
        message += "\n\nPublished: " + binding.publishedTextviewT.text
        message += "\n\nPublisher: " + binding.publisherTextviewT.text
        message += "\n\nWebsite: " + binding.websiteTextviewT.text
        message += "\n\nPages: " + binding.pagesTextviewT.text
        message += "\n\nDescription: " + binding.descriptionTextviewT.text

        AlertDialog.Builder(this)
            .setTitle("Are you sure you want to submit Form?")
            .setMessage(message)
            .setPositiveButton("Ok") {_, _ ->
                var book = Books(
                    binding.isbnTextviewT.text.toString().toLong(),
                    binding.titleTextviewT.text.toString(),
                    binding.subtitleTextviewT.text.toString(),
                    binding.authorTextviewT.text.toString(),
                    binding.publishedTextviewT.text.toString(),
                    binding.publisherTextviewT.text.toString(),
                    binding.pagesTextviewT.text.toString().toInt(),
                    binding.descriptionTextviewT.text.toString(),
                    binding.websiteTextviewT.text.toString()
                )
                vm.insertBook(book)
//                var nextIntent = Intent(this, MainActivity::class.java)
//                startActivity(nextIntent)

                binding.isbnTextviewT.text = null
                binding.titleTextviewT.text = null
                binding.authorTextviewT.text = null
                binding.subtitleTextviewT.text = null
                binding.pagesTextviewT.text = null
                binding.websiteTextviewT.text = null
                binding.descriptionTextviewT.text = null
                binding.publisherTextviewT.text = null
                binding.publishedTextviewT.text = null
                println("Ok pressed")
            }
            .setNegativeButton("Cancel") {_, _ ->
                println("Cancel pressed")
            }
            .show()
    }

    private fun invalidForm() {
        var message = ""
        if(binding.isbnTextviewL.helperText != null)
            message += "\n\nISBN: " + binding.isbnTextviewL.helperText
        if(binding.authorTextviewL.helperText != null)
            message += "\n\nAuthor: " + binding.authorTextviewL.helperText
        if(binding.titleTextviewL.helperText != null)
            message += "\n\nTitle: " + binding.titleTextviewL.helperText
        if(binding.subtitleTextviewL.helperText != null)
            message += "\n\nSubtitle: " + binding.subtitleTextviewL.helperText
        if(binding.publishedTextviewL.helperText != null)
            message += "\n\nPublished: " + binding.publishedTextviewL.helperText
        if(binding.publisherTextviewL.helperText != null)
            message += "\n\nPublisher: " + binding.publisherTextviewL.helperText
        if(binding.websiteTextviewL.helperText != null)
            message += "\n\nWebsite: " + binding.websiteTextviewL.helperText
        if(binding.pagesTextviewL.helperText != null)
            message += "\n\nPages: " + binding.pagesTextviewL.helperText
        if(binding.descriptionTextviewL.helperText != null)
            message += "\n\nDescription: " + binding.descriptionTextviewL.helperText

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
        binding.isbnTextviewT.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.isbnTextviewL.helperText = validIsbn()
            } else {
                binding.isbnTextviewL.helperText = ""
            }
        }
    }

    private fun validIsbn(): String? {
        if (binding.isbnTextviewT.text.toString().toLongOrNull() == null) {
            if (binding.isbnTextviewT.length() == 0) {
                return "Required"
            }
            return "Invalid ISBN number"
        }
        return null
    }

    private fun titleFocusListener() {
        binding.titleTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.titleTextviewL.helperText = ""
            } else if(binding.titleTextviewT.length() == 0) {
                binding.titleTextviewL.helperText = "Required"
            }
        }
    }

    private fun validTitle(): String? {
        if (binding.titleTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun pagesFocusListener() {
        binding.pagesTextviewT.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.pagesTextviewL.helperText = validPages()
            } else {
                binding.pagesTextviewL.helperText = ""
            }
        }
    }

    private fun validPages(): String? {
        if (binding.pagesTextviewT.text.toString().toIntOrNull() == null) {
            if (binding.pagesTextviewT.length() == 0) {
                return "Required"
            }
            return "Please enter valid page numbers"
        }
        return null
    }

    private fun authorFocusListener() {
        binding.authorTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.authorTextviewL.helperText = ""
            } else if(binding.authorTextviewT.length() == 0) {
                binding.authorTextviewL.helperText = "Required"
            }
        }
    }

    private fun validAuther(): String? {
        if (binding.authorTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun publisherFocusListener() {
        binding.publisherTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.publisherTextviewL.helperText = ""
            } else if(binding.publisherTextviewT.length() == 0) {
                binding.publisherTextviewL.helperText = "Required"
            }
        }
    }

    private fun validPublisher(): String? {
        if (binding.publisherTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun publishedFocusListener() {
        binding.publishedTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.publishedTextviewL.helperText = ""
            } else if(binding.publishedTextviewT.length() == 0) {
                binding.publishedTextviewL.helperText = "Required"
            }
        }
    }

    private fun validPublished(): String? {
        if (binding.publishedTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun websiteFocusListener() {
        binding.websiteTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.websiteTextviewL.helperText = ""
            } else if(binding.websiteTextviewT.length() == 0) {
                binding.websiteTextviewL.helperText = "Required"
            }
        }
    }

    private fun validWebsite(): String? {
        if (binding.websiteTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun descriptionFocusListener() {
        binding.descriptionTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.descriptionTextviewL.helperText = ""
            } else if(binding.descriptionTextviewT.length() == 0) {
                binding.descriptionTextviewL.helperText = "Required"
            }
        }
    }

    private fun validDescription(): String? {
        if (binding.descriptionTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun subtitleFocusListener() {
        binding.subtitleTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.subtitleTextviewL.helperText = ""
            } else if(binding.subtitleTextviewT.length() == 0) {
                binding.subtitleTextviewL.helperText = "Required"
            }
        }
    }

    private fun validSubtitle(): String? {
        if (binding.subtitleTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }
}