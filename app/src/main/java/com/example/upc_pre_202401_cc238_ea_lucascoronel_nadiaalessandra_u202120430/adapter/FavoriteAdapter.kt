package com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.R
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.model.FavoritePackage

class FavoriteAdapter(
    var favoritePackages: List<FavoritePackage>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<FavoriteAdapter.FavoritePrototype>() {

    interface OnItemClickListener {
        fun onItemClick(favoritePackage: FavoritePackage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_package, parent, false)

        return FavoritePrototype(view)
    }

    override fun onBindViewHolder(holder: FavoritePrototype, position: Int) {
        holder.bind(favoritePackages[position], clickListener)
    }

    override fun getItemCount(): Int {
        return favoritePackages.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePackage(newPackages: List<FavoritePackage>) {
        favoritePackages = newPackages
        notifyDataSetChanged()
    }

    class FavoritePrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName = itemView.findViewById<TextView>(R.id.tvName)
        val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
        val ivPicture = itemView.findViewById<ImageView>(R.id.ivPicture)
        val ibLike = itemView.findViewById<ImageButton>(R.id.ibLike)

        @SuppressLint("SetTextI18n")
        fun bind(favoritePackage: FavoritePackage, clickListener: OnItemClickListener) {
            tvName.text = favoritePackage.nombre
            tvDescription.text = favoritePackage.descripcion

            Glide.with(itemView).load(favoritePackage.imagen).into(ivPicture)

            ibLike.setOnClickListener {
                clickListener.onItemClick(favoritePackage)
            }
        }
    }
}