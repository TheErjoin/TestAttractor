package com.example.testattractor.presentation.gallery

import androidx.recyclerview.widget.DiffUtil
import com.example.testattractor.domain.models.Gallery

class GalleryItemDiffCall : DiffUtil.ItemCallback<Gallery>() {

    override fun areItemsTheSame(oldItem: Gallery, newItem: Gallery): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gallery, newItem: Gallery): Boolean {
        return oldItem == newItem
    }
}