package com.rkeller.harrypotterrk.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rkeller.harrypotterrk.R
import com.rkeller.harrypotterrk.databinding.ActivityDetallesPersonajeBinding
import com.rkeller.harrypotterrk.model.Personaje
import com.rkeller.harrypotterrk.network.HarryPotterAPI
import com.rkeller.harrypotterrk.network.RetrofitService
import com.rkeller.harrypotterrk.model.DetallesPersonaje
import com.rkeller.harrypotterrk.utils.Constants
import com.rkeller.harrypotterrk.view.adapters.HarryPotterAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallesPersonaje : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesPersonajeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesPersonajeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        val id = bundle?.getString("id", "")
        //Toast.makeText(this@DetallesPersonaje, id, Toast.LENGTH_LONG).show()


        val detallesPersonaje = RetrofitService.getRetrofit().create(HarryPotterAPI::class.java).getPersonajesDetails(id)

        /*binding.tvTitle.text = detallesPersonaje.name
        binding.tvLongDesc.text = detallesPersonaje.house
        Glide.with(this@DetallesPersonaje)
            .load(detallesPersonaje.image)
            .into(binding.ivImage)*/

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