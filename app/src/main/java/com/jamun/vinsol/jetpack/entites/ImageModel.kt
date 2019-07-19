package com.jamun.vinsol.jetpack.entites

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageModel(var id: Int, var image: String, var adapterPosition : Int =0): Parcelable
