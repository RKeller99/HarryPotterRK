package com.rkeller.harrypotterrk.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rkeller.harrypotterrk.R
import com.rkeller.harrypotterrk.databinding.ActivityMainBinding
import com.rkeller.harrypotterrk.model.Personaje
import com.rkeller.harrypotterrk.network.HarryPotterAPI
import com.rkeller.harrypotterrk.network.RetrofitService
import com.rkeller.harrypotterrk.utils.Constants
import com.rkeller.harrypotterrk.view.adapters.HarryPotterAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitService.getRetrofit().create(HarryPotterAPI::class.java).getPersonajes("api/characters/")

        call.enqueue(object: Callback<ArrayList<Personaje>>{
            override fun onResponse(                          // Cuando tenemos una respuesta por parte del servidor
                call: Call<ArrayList<Personaje>>,
                response: Response<ArrayList<Personaje>>       // Ya viene mi arraylist
            ) {
                binding.pbConexion.visibility = View.GONE
                Log.d(Constants.LOGTAG, "Respuesta del Servidor: ${response.toString()}")
                Log.d(Constants.LOGTAG, "Datos: ${response.body().toString()}")

                binding.rvMenu.layoutManager = LinearLayoutManager(this@MainActivity)

                //Aquí poner una validación en caso de que llegue nulo, un dialogo, un toast, min 43 del segundo video
                if (response.body().isNullOrEmpty()){
                    Toast.makeText(this@MainActivity,R.string.falloConexion, Toast.LENGTH_LONG).show()
                }else{
                    binding.rvMenu.adapter = HarryPotterAdapter(this@MainActivity, response.body()!!)
                }



            }

            override fun onFailure(call: Call<ArrayList<Personaje>>, t: Throwable) { //Cuando no hay conexión
                binding.pbConexion.visibility = View.GONE
                Toast.makeText(this@MainActivity,R.string.falloConexion, Toast.LENGTH_LONG).show()
            }

        })
    }
}