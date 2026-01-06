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
import com.example.upc_pre_202401_cc238_ea_lucascoronel_nadiaalessandra_u202120430.model.Package


class PackageAdapter(
    var packages: List<Package>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<PackagePrototype>() {

    interface OnItemClickListener {
        fun onItemClick(pack: Package)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackagePrototype {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.prototype_package, parent, false)

        return PackagePrototype(view)
    }

    override fun onBindViewHolder(holder: PackagePrototype, position: Int) {
        holder.bind(packages[position], clickListener)
    }

    override fun getItemCount(): Int {
        return packages.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updatePackage(newPackage: List<Package>) {
        packages = newPackage
        notifyDataSetChanged()
    }
}

class PackagePrototype(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvProductId = itemView.findViewById<TextView>(R.id.tvProductId)
    val tvName = itemView.findViewById<TextView>(R.id.tvName)
    val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)
    val tvUbication = itemView.findViewById<TextView>(R.id.tvUbication)
    val ivPicture = itemView.findViewById<ImageView>(R.id.ivPicture)
    val ibLike = itemView.findViewById<ImageButton>(R.id.ibLike)

    @SuppressLint("SetTextI18n")
    fun bind(pack: Package, clickListener: PackageAdapter.OnItemClickListener) {
        tvProductId.text = pack.idProducto.toString()
        tvName.text = pack.nombre
        tvDescription.text = pack.descripcion
        tvUbication.text = pack.ubicacin

        Glide.with(itemView).load(pack.imagen).into(ivPicture)

        ibLike.setOnClickListener {
            clickListener.onItemClick(pack)
        }
    }
}