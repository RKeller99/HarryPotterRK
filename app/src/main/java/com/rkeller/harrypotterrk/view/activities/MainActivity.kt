package com.rkeller.harrypotterrk.view.activities

import android.content.Intent
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


        val b = intent.extras
        var seleccion = "" // or other values
        if (b!= null) {
            seleccion = b.getString("Selec").toString()
        }

        val call = if (seleccion == "Personal") {
            RetrofitService.getRetrofit().create(HarryPotterAPI::class.java)
                .getPersonajes("api/characters/staff")
        } else if (seleccion == "Estudiantes") {
            RetrofitService.getRetrofit().create(HarryPotterAPI::class.java)
                .getPersonajes("api/characters/students")
        } else {
            //Error
            RetrofitService.getRetrofit().create(HarryPotterAPI::class.java)
                .getPersonajes("api/characters/")
        }


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
                    binding.rvMenu.adapter = HarryPotterAdapter(this@MainActivity, response.body()!!) {selectedPersonaje:  Personaje ->
                        personajeClicked(selectedPersonaje)
                    }
                }



            }

            override fun onFailure(call: Call<ArrayList<Personaje>>, t: Throwable) { //Cuando no hay conexión
                binding.pbConexion.visibility = View.GONE
                Toast.makeText(this@MainActivity,R.string.falloConexion, Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun personajeClicked(personaje:Personaje){  //Función que quiero que me cache los clicks a un personaje
        /* Toast.makeText(this,"Click en el elemento con título ${personaje.name}", Toast.LENGTH_SHORT).show()*/
        //val bundle = Bundle()
        //bundle.putString("id", personaje.id) //Empaquetar para enviarle un objeto de la clase Detalles Personaje, o realmente en personaje obtener los valores completos

        val intent = Intent(this, DetallesPersonaje::class.java)
        intent.putExtra("personaje", personaje)
        startActivity(intent)

    }


}