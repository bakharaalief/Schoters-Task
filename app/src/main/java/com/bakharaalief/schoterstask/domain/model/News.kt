package com.bakharaalief.schoterstask.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val sourceId: String,
    val sourceName: String,
    val publishedAt: String,
    val author: String,
    val urlToImage: String,
    val description: String,
    val title: String,
    val url: String,
    val content: String
) : Parcelable