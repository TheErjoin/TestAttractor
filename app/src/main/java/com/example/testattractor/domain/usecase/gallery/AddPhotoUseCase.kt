package com.example.testattractor.domain.usecase.gallery

import com.example.testattractor.domain.models.Gallery
import com.example.testattractor.domain.repository.GalleryRepository

class AddPhotoUseCase(private val repository: GalleryRepository) {

    fun addPhoto(gallery: Gallery) {
        repository.addPhoto(gallery)
    }

}