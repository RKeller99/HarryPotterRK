package com.rkeller.harrypotterrk.view.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.rkeller.harrypotterrk.databinding.ActivityDetallesPersonajeBinding
import com.rkeller.harrypotterrk.model.Personaje
import com.rkeller.harrypotterrk.network.HarryPotterAPI
import com.rkeller.harrypotterrk.network.RetrofitService

class DetallesPersonaje : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesPersonajeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var personaje: Personaje? = null

        personaje = if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra<Personaje>("personaje", Personaje::class.java)
        }else{
            intent.getParcelableExtra<Personaje>("personaje")
        }


        personaje?.let {
            binding.pbConexion.visibility = View.GONE

            binding.tvNombrePersonaje.text = it.name
            binding.tvNombreActor.text = it.actor
            binding.tvCasa.text = it.house
            binding.tvNacimiento.text = it.dateOfBirth
            binding.tvEspecie.text = it.species
            binding.tvGenero.text = it.gender
            binding.tvAncestro.text = it.ancestry
            binding.tvPatronus.text = it.patronus
            //binding.tvVivo.text = it.alive


            Glide.with(this)
                .load(it.image)
                .into(binding.ivImage)
        }

        //val personaje = intent.getParcelableExtra<Personaje>("personaje")

        /*val bundle = intent.extras

        val id = bundle?.getString("id", "")
        Toast.makeText(this@DetallesPersonaje, id, Toast.LENGTH_LONG).show()

        val call = RetrofitService.getRetrofit().create(HarryPotterAPI::class.java).getPersonajesDetails(id)*/


        /*call.enqueue(object: Callback<DetallesPersonaje> {
            override fun onResponse(                          // Cuando tenemos una respuesta por parte del servidor
                call: Call<DetallesPersonaje>,
                response: Response<DetallesPersonaje>       // Ya viene mi arraylist
            ) {
                binding.pbConexion.visibility = View.GONE

                binding.tvTitle.text = response.body()!!.name
                binding.tvLongDesc.text = response.body()!!.house

                Glide.with(this@DetallesPersonaje)
                    .load(response.body()!!.image)
                    .into(binding.ivImage)

                Log.d(Constants.LOGTAG, "Respuesta del Servidor: ${response.toString()}")
                Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

            }

            override fun onFailure(call: Call<DetallesPersonaje>, t: Throwable) { //Cuando no hay conexión
                binding.pbConexion.visibility = View.GONE
                Toast.makeText(this@DetallesPersonaje,R.string.falloConexion, Toast.LENGTH_LONG).show()

            }

        })*/





    }
}