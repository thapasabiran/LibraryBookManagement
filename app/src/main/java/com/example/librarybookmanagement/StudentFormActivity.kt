package com.example.librarybookmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.librarybookmanagement.data.Books
import com.example.librarybookmanagement.data.Students
import com.example.librarybookmanagement.databinding.ActivityStudentFormBinding
import com.example.librarybookmanagement.viewmodels.StudentViewModel

class StudentFormActivity : AppCompatActivity() {
    lateinit var vm: StudentViewModel
    lateinit var binding: ActivityStudentFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vm = StudentViewModel(application)

        firstNameFocusListener()
        lastNameFocusListener()
        phoneNumberFocusListener()
        emailFocusListener()
        addressFocusListener()

        binding.saveStudentFormButton.setOnClickListener() {
            submitForm()
        }

        binding.cancelStudentFormButton.setOnClickListener() {
            cancelForm()
        }

        binding.clearStudentButton.setOnClickListener() {
            binding.fnameTextviewT.text = null
            binding.lnameTextviewT.text = null
            binding.addressTextviewT.text = null
            binding.emailTextviewT.text = null
            binding.phoneNumberTextviewT.text = null
        }
    }

    private fun submitForm() {
        binding.fnameTextviewL.helperText = validFirstName()
        binding.lnameTextviewL.helperText = validLastName()
        binding.emailTextviewL.helperText = validEmail()
        binding.addressTextviewL.helperText = validAddress()
        binding.phoneNumberTextviewL.helperText = validPhoneNumber()

        val validFirstName = binding.fnameTextviewL.helperText == null
        val validLastName = binding.lnameTextviewL.helperText == null
        val validEmail = binding.emailTextviewL.helperText == null
        val validPhoneNumber = binding.phoneNumberTextviewL.helperText == null
        val validAddress = binding.addressTextviewL.helperText == null

        if (validFirstName && validLastName && validEmail && validPhoneNumber && validAddress) {
            resetForm()
        } else {
            invalidForm()
        }
    }

    private fun resetForm() {
        var message = "\n\nFirst Name: " + binding.fnameTextviewT.text
        message += "\n\nLast Name: " + binding.lnameTextviewT.text
        message += "\n\nEmail: " + binding.emailTextviewT.text
        message += "\n\nPhone Number: " + binding.phoneNumberTextviewT.text
        message += "\n\nAddress: " + binding.addressTextviewT.text

        AlertDialog.Builder(this)
            .setTitle("Are you sure you want to submit Form?")
            .setMessage(message)
            .setPositiveButton("Ok") {_, _ ->
                var student = Students(
                    binding.fnameTextviewT.text.toString(),
                    binding.lnameTextviewT.text.toString(),
                    binding.addressTextviewT.text.toString(),
                    binding.emailTextviewT.text.toString(),
                    binding.phoneNumberTextviewT.text.toString()
                )
                vm.insertStudent(student)

                binding.fnameTextviewT.text = null
                binding.lnameTextviewT.text = null
                binding.addressTextviewT.text = null
                binding.emailTextviewT.text = null
                binding.phoneNumberTextviewT.text = null
            }
            .setNegativeButton("Cancel") {_, _ ->
                println("Cancel pressed")
            }
            .show()
    }

    private fun invalidForm() {
        var message = ""
        if(binding.fnameTextviewL.helperText != null)
            message += "\n\nFirst Name: " + binding.fnameTextviewL.helperText
        if(binding.lnameTextviewL.helperText != null)
            message += "\n\nLast Name: " + binding.lnameTextviewL.helperText
        if(binding.addressTextviewL.helperText != null)
            message += "\n\nAddress: " + binding.addressTextviewL.helperText
        if(binding.emailTextviewL.helperText != null)
            message += "\n\nEmail: " + binding.emailTextviewL.helperText
        if(binding.phoneNumberTextviewL.helperText != null)
            message += "\n\nPhone Number: " + binding.phoneNumberTextviewL.helperText

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

    private fun firstNameFocusListener() {
        binding.fnameTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.fnameTextviewL.helperText = ""
            } else if(binding.fnameTextviewT.length() == 0) {
                binding.fnameTextviewL.helperText = "Required"
            }
        }
    }

    private fun validFirstName(): String? {
        if (binding.fnameTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun lastNameFocusListener() {
        binding.lnameTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.lnameTextviewL.helperText = ""
            } else if(binding.lnameTextviewT.length() == 0) {
                binding.lnameTextviewL.helperText = "Required"
            }
        }
    }

    private fun validLastName(): String? {
        if (binding.lnameTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }

    private fun emailFocusListener() {
        binding.emailTextviewT.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailTextviewL.helperText = validEmail()
            } else {
                binding.emailTextviewL.helperText = ""
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailTextviewT.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email address"
        }
        return null
    }

    private fun phoneNumberFocusListener() {
        binding.phoneNumberTextviewT.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.phoneNumberTextviewL.helperText = validPhoneNumber()
            } else {
                binding.phoneNumberTextviewL.helperText = ""
            }
        }
    }

    private fun validPhoneNumber(): String? {
        val phoneNumberText = binding.phoneNumberTextviewT.text.toString()
        if (!phoneNumberText.matches(".*[0-9].*".toRegex())) {
            return "Must be all digits"
        }
        if (phoneNumberText.length != 10) {
            return "Must be 10 digits"
        }
        return null
    }

    private fun addressFocusListener() {
        binding.addressTextviewT.setOnFocusChangeListener { _, focused ->
            if (focused) {
                binding.addressTextviewL.helperText = ""
            } else if(binding.lnameTextviewT.length() == 0) {
                binding.addressTextviewL.helperText = "Required"
            }
        }
    }

    private fun validAddress(): String? {
        if (binding.addressTextviewT.length() == 0) {
            return "Required"
        }
        return null
    }
}