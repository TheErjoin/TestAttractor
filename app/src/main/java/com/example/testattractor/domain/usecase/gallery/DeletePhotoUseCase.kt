package com.example.testattractor.domain.usecase.gallery

import com.example.testattractor.domain.models.Gallery
import com.example.testattractor.domain.repository.GalleryRepository

class DeletePhotoUseCase(private val repository: GalleryRepository) {

    fun deletePhoto(gallery: Gallery){
        repository.deletePhoto(gallery)
    }

}