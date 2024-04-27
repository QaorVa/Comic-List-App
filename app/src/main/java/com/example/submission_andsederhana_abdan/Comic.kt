package com.example.submission_andsederhana_abdan

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comic(
    val title: String,
    val author: String,
    val longDescription: String,
    val genre: String,
    val photo: Int,
    val url: String
) : Parcelable
