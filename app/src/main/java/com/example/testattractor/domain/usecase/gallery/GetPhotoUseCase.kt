package com.example.testattractor.domain.usecase.gallery

import com.example.testattractor.domain.repository.GalleryRepository

class GetPhotoUseCase(private val repository: GalleryRepository) {

    fun getPhoto() = repository.getPhoto()

}