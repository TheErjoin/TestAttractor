package com.example.testattractor.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testattractor.domain.models.Gallery
import com.example.testattractor.domain.repository.GalleryRepository

class GalleryImpl : GalleryRepository {

    private val galleryListLD = MutableLiveData<List<Gallery>>()
    private val galleryList = sortedSetOf<Gallery>({ o1, o2 -> o1.id.compareTo(o2.id) })

    private var autoIncrementId = 0

    override fun addPhoto(gallery: Gallery) {
        if (gallery.id == Gallery.UNDEFINED_ID) {
            gallery.id = autoIncrementId++
        }
        galleryList.add(gallery)
        galleryUpdateList()
    }

    override fun getPhoto(): LiveData<List<Gallery>> {
        return galleryListLD
    }

    override fun deletePhoto(gallery: Gallery) {
        galleryList.remove(gallery)
        galleryUpdateList()
    }

    private fun galleryUpdateList() {
        galleryListLD.value = galleryList.toList()
    }
}