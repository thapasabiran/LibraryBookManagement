package com.example.librarybookmanagement.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "studentBooks", foreignKeys = [
    ForeignKey(entity = Students::class,
        parentColumns = ["studentId"],
        childColumns = ["studentId"],
        onDelete = CASCADE),
    ForeignKey(entity = Books::class,
        parentColumns = ["bookId"],
        childColumns = ["bookId"],
        onDelete = CASCADE)
])
data class StudentBooks(
    @ColumnInfo(name = "studentId") var studentId: Int,
    @ColumnInfo(name = "bookId") var bookId: Int,
    @ColumnInfo(name = "status") var status: Int,
    @ColumnInfo(name = "issueDate") var issueDate: String,
    @ColumnInfo(name = "submissionDate") var submissionDate: String,
    @ColumnInfo(name = "modifiedDate") var modifiedDate: String,
    @ColumnInfo(name = "extensionDate") var extensionDate: String
){
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "studentBookId")
    var studentBookId: Int = 0
}
