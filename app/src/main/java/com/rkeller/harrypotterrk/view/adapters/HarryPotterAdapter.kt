package com.rkeller.harrypotterrk.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.rkeller.harrypotterrk.databinding.HarrypotterElementBinding
import com.rkeller.harrypotterrk.model.Personaje

class HarryPotterAdapter(private var context: Context, private var personajes: ArrayList<Personaje>, private val clickListener: (Personaje) -> Unit): RecyclerView.Adapter<HarryPotterAdapter.ViewHolder>() {

    class ViewHolder(view: HarrypotterElementBinding): RecyclerView.ViewHolder(view.root){
        val ivImagen = view.ivImagen
        val tvNombrePersonaje = view.tvNombrePersonaje
        val tvNombreActor = view.tvNombreActor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HarrypotterElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = personajes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvNombrePersonaje.text = personajes[position].name
        holder.tvNombreActor.text = personajes[position].actor

        Glide.with(context)
            .load(personajes[position].image)
            .into(holder.ivImagen)

        holder.itemView.setOnClickListener {
            //Programamos eventos click al ViewHolder completo
            clickListener(personajes[position])

        }
    }


}