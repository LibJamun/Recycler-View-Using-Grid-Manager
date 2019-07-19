package com.jamun.vinsol.variables

import com.jamun.vinsol.jetpack.entites.ImageModel

interface OnImageClickListener {
    fun onClick(model: ImageModel, adapterPosition : Int)
}
