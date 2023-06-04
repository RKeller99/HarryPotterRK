package com.rkeller.harrypotterrk.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.rkeller.harrypotterrk.databinding.ActivitySelectorBinding

class Selector : AppCompatActivity() {

    private lateinit var binding: ActivitySelectorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun ClickPersonal(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        val b = Bundle()
        b.putString("Selec", "Personal")
        intent.putExtras(b)
        startActivity(intent)
    }
    fun ClickEstudiantes(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        val b = Bundle()
        b.putString("Selec", "Estudiantes")
        intent.putExtras(b)
        startActivity(intent)
    }
}