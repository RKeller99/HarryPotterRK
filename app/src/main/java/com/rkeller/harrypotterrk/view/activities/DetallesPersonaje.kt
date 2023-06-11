package com.rkeller.harrypotterrk.view.activities

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.rkeller.harrypotterrk.R
import com.rkeller.harrypotterrk.databinding.ActivityDetallesPersonajeBinding
import com.rkeller.harrypotterrk.model.Personaje

class DetallesPersonaje : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesPersonajeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        var personaje: Personaje? = null

        personaje = if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            intent.getParcelableExtra<Personaje>("personaje", Personaje::class.java)
        }else{
            intent.getParcelableExtra<Personaje>("personaje")
        }


        personaje?.let {
            binding.pbConexion.visibility = View.GONE

            binding.tvNombrePersonaje.text = it.name
            if(it.actor == ""){ binding.tvNombreActor.text = getString(R.string.actor) + ": " + getString(R.string.noInfo)
            }else{ binding.tvNombreActor.text = getString(R.string.actor) + ": " + it.actor}
            if(it.house == ""){ binding.tvCasa.text = getString(R.string.house) + ": " + getString(R.string.noInfo)
            }else{ binding.tvCasa.text = getString(R.string.house) + ": " + it.house}
            if(it.dateOfBirth == null){ binding.tvNacimiento.text = getString(R.string.fechaNacimiento) + ": " + getString(R.string.noInfo)
            }else{binding.tvNacimiento.text = getString(R.string.fechaNacimiento) + ": " + it.dateOfBirth}
            if(it.species == ""){ binding.tvEspecie.text = getString(R.string.especie) + ": " + getString(R.string.noInfo)
            }else{binding.tvEspecie.text = getString(R.string.especie) + ": " + it.species}
            if(it.gender == ""){ binding.tvGenero.text = getString(R.string.gender) + ": " + getString(R.string.noInfo)
            }else{binding.tvGenero.text = getString(R.string.gender) + ": " + it.gender}
            if(it.ancestry == ""){ binding.tvAncestro.text = getString(R.string.ancestry) + ": " + getString(R.string.noInfo)
            }else{binding.tvAncestro.text = getString(R.string.ancestry) + ": " + it.ancestry}
            if(it.patronus == ""){ binding.tvPatronus.text = getString(R.string.patronus) + ": " + getString(R.string.noInfo)
            }else{binding.tvPatronus.text = getString(R.string.patronus) + ": " + it.patronus}

            if(it.image == ""){
                Glide.with(this)
                    .load(R.drawable.noavailable)
                    .into(binding.ivImage)
            }else{
                Glide.with(this)
                    .load(it.image)
                    .into(binding.ivImage)
            }

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