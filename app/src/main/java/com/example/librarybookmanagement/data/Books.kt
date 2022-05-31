package com.example.librarybookmanagement.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Books (
    @ColumnInfo(name = "isbn") var isbn: Long,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "subtitle") var subtitle: String,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "published") var published: String,
    @ColumnInfo(name = "publisher") var publisher: String,
    @ColumnInfo(name = "pages") var pages: Int,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "website") var website: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bookId")
    var bookId: Int = 0
}