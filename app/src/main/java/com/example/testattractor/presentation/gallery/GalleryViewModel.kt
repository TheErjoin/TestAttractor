package com.example.testattractor.presentation.gallery

import androidx.lifecycle.ViewModel
import com.example.testattractor.data.GalleryImpl
import com.example.testattractor.domain.models.Gallery
import com.example.testattractor.domain.usecase.gallery.AddPhotoUseCase
import com.example.testattractor.domain.usecase.gallery.DeletePhotoUseCase
import com.example.testattractor.domain.usecase.gallery.GetPhotoUseCase

class GalleryViewModel : ViewModel() {

    private val repository = GalleryImpl()

    private val addPhotoUseCase = AddPhotoUseCase(repository)

    private val getPhotoUseCase = GetPhotoUseCase(repository)

    private val deletePhotoUseCase = DeletePhotoUseCase(repository)

    val galleryList = getPhotoUseCase.getPhoto()

    fun addShopItem(gallery: Gallery) {
        addPhotoUseCase.addPhoto(gallery)
    }

    fun deletePhoto(gallery: Gallery) {
        deletePhotoUseCase.deletePhoto(gallery)
    }
}