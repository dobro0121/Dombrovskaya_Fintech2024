package com.example.fintech_2024_dombrovskaya.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "films_table")
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    @ColumnInfo(name = "name")
    var filmName: String? = null,

    @ColumnInfo(name = "year")
    var year: String?= null,

    @ColumnInfo(name = "genre")
    var genre: String?= null,

    @ColumnInfo(name = "country")
    var country: String?= null,

    @ColumnInfo(name = "description")
    var description: String?= null,

    @ColumnInfo(name = "image")
    var image: String?= null,

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Boolean? = false
): Parcelable {

    @JvmOverloads constructor() : this(
        0,
        null,
        null,
        null,
        null,
        null,
        null,
        false
    )
}