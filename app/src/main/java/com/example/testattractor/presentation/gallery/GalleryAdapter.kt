package com.example.testattractor.presentation.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testattractor.R
import com.example.testattractor.domain.models.Gallery

class GalleryAdapter :
    ListAdapter<Gallery, GalleryAdapter.GalleryViewHolder>(GalleryItemDiffCall()) {

    var galleryOnLongListener: ((Gallery) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val galleryItem = getItem(position)
        holder.ivPhoto.load(galleryItem.photo)
        holder.itemView.setOnLongClickListener {
            galleryOnLongListener?.invoke(galleryItem)
            true
        }
    }

    class GalleryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPhoto: ImageView = itemView.findViewById(R.id.iv_image)
    }
}