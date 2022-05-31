package com.example.librarybookmanagement

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.librarybookmanagement.data.StudentBooks
import com.example.librarybookmanagement.data.Students
import com.example.librarybookmanagement.databinding.ActivityStudentCheckoutBinding
import com.example.librarybookmanagement.viewmodels.BookViewModel
import com.example.librarybookmanagement.viewmodels.StudentBookViewModel
import com.example.librarybookmanagement.viewmodels.StudentViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*
import kotlin.collections.ArrayList

class StudentCheckoutActivity : AppCompatActivity() {
    lateinit var vmStudent: StudentViewModel
    lateinit var vmBook: BookViewModel
    lateinit var vmStudentBook: StudentBookViewModel
    lateinit var binding: ActivityStudentCheckoutBinding
    lateinit var studentList: ArrayList<Students>
    lateinit var studentBook: StudentBooks
    var studentId = 0
    var bookId = 0

    val positiveButtonClick = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext,
            android.R.string.yes, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentCheckoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmStudent = StudentViewModel(application)
        vmBook = BookViewModel(application)
        vmStudentBook = StudentBookViewModel(application)

        studentList = ArrayList<Students>()

        bookId = intent.getIntExtra("bookId",0)
        vmBook.getBook(bookId)?.observe(this) {
            binding.studentCheckoutAuthorT.text = it.author
            binding.studentCheckoutTitleT.text = it.title
            binding.studentCheckoutPublisherT.text = it.publisher
            binding.studentCheckoutDescriptionT.text = it.description

            //ToDo: use Date and Time properly
            var period = Period.of(0, 0, 14)
            var currentDate = LocalDate.now()
            var expiryDate = currentDate.plus(period)

            binding.studentCheckoutFromT.text = currentDate.toString()
            binding.studentCheckoutToT.text = expiryDate.toString()
        }
    }

    fun addNewStudent(view: View) {
        val intentNext = Intent(this, StudentFormActivity::class.java)
        startActivity(intentNext)
    }

    fun selectExistingStudent(view: View) {
        vmStudent.allStudents?.observe(this) {
            studentList = it as ArrayList<Students> /* = java.util.ArrayList<com.example.librarybookmanagement.data.Students> */
            val items = Array<String>(studentList.size){ "" }
            var i = 0
            for (stu in studentList) {
                items[i] = stu.studentId.toString() + " " + stu.firstName + " " + stu.lastName
                i++
            }
            val builder = AlertDialog.Builder(this)
            with(builder)
            {
                setTitle("List of Students")
                setItems(items) { dialog, which ->
                    val selectItem = items[which]
                    val selectItemArr = selectItem.split(" ")
                    binding.studentCheckoutSnameT.text = selectItemArr[1] + " " + selectItemArr[2]
                    studentId = selectItemArr[0].toInt()
                }

                setPositiveButton("OK", positiveButtonClick)
                show()
            }
        }
    }

    fun checkoutStudentBook(view: View) {
        studentBook = StudentBooks(studentId,bookId,1, binding.studentCheckoutFromT.text.toString(), binding.studentCheckoutToT.text.toString(), binding.studentCheckoutFromT.text.toString(), binding.studentCheckoutToT.text.toString())
        vmStudentBook.insertStudentBook(studentBook)
        val intentNext = Intent(this, MainActivity::class.java)
        startActivity(intentNext)
    }

    fun cancelStudentBookCheckout(view: View) {
        val intentNext = Intent(this, MainActivity::class.java)
        startActivity(intentNext)
    }
}