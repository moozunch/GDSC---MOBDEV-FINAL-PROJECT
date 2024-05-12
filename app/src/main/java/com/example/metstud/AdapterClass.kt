package com.example.metstud

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass (private val dataList: ArrayList<studentData>): RecyclerView.Adapter<AdapterClass.ViewHolderClass>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.studentlist, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvImage.setImageResource(currentItem.dataImage)
        holder.rvName.text = currentItem.dataName
        holder.rvNim.text = currentItem.dataNim
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class ViewHolderClass (itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.profile_image)
        val rvName: TextView = itemView.findViewById(R.id.name)
        val rvNim: TextView = itemView.findViewById(R.id.nim)

    }
}