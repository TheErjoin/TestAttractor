package com.example.testattractor.domain.models

data class Gallery(
    val photo: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
