package com.xuweic.annoyingex

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AllPhrases(
    val messages: List<String>
): Parcelable
