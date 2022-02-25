package com.example.testattractor.domain.repository

import androidx.lifecycle.LiveData
import com.example.testattractor.domain.models.Gallery

interface GalleryRepository {

    fun addPhoto(gallery: Gallery)

    fun getPhoto(): LiveData<List<Gallery>>

    fun deletePhoto(gallery: Gallery)

}