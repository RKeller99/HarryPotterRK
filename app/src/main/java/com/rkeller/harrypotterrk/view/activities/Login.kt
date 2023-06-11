package com.rkeller.harrypotterrk.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.rkeller.harrypotterrk.R
import com.rkeller.harrypotterrk.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var firebaseAuth: FirebaseAuth

    private var email = ""

    private var contrasenia = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.pbConexion.visibility = View.GONE

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnIngresar.setOnClickListener {
            if(!validaCampos()) return@setOnClickListener

            binding.pbConexion.visibility = View.VISIBLE

            // Autenticando usuario

            autenticaUsuario(email, contrasenia)
        }

        binding.btnRegistrarse.setOnClickListener{
            if(!validaCampos()) return@setOnClickListener
            binding.pbConexion.visibility = View.VISIBLE
            creaUsuario(email, contrasenia)
        }
    }

    private fun validaCampos(): Boolean{

        email = binding.etEmail.text.toString().trim() //Me quita los espacios
        contrasenia = binding.etPassword.text.toString().trim()

        if(email.isEmpty()){
            binding.etEmail.error = getString(R.string.correoInvalido)
            binding.etEmail.requestFocus()
            return false
        }

        if(contrasenia.isEmpty() || contrasenia.length < 6){
            binding.etPassword.error = getString(R.string.contraseniaInvalida)
            binding.etPassword.requestFocus()
            return false
        }

        return true
    }

    private fun autenticaUsuario(usr: String, psw: String){
        firebaseAuth.signInWithEmailAndPassword(usr, psw).addOnCompleteListener{ authResult ->
            if(authResult.isSuccessful){
                startActivity(Intent(this, Selector::class.java))
                finish()
            }else{
                binding.pbConexion.visibility = View.GONE
                manejaErrores(authResult)
            }
        }
    }

    private fun creaUsuario(usr: String, psw: String){
        firebaseAuth.createUserWithEmailAndPassword(usr, psw).addOnCompleteListener{ authResult ->
            if(authResult.isSuccessful){
                Toast.makeText(this, R.string.UsuarioCreado, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, Selector::class.java))
                finish()
            }else{
                binding.pbConexion.visibility = View.GONE
                manejaErrores(authResult)
            }
        }
    }

    private fun manejaErrores(task: Task<AuthResult>){
        var errorCode: String

        try{
            errorCode = (task.exception as FirebaseAuthException).errorCode
        }catch(e: Exception){
            errorCode = "NO_NETWORK"
        }

        when(errorCode){
            "ERROR_INVALID_EMAIL" -> {
                Toast.makeText(this, R.string.invalidEmail, Toast.LENGTH_SHORT).show()
                binding.etEmail.error = getString(R.string.invalidEmail)
                binding.etEmail.requestFocus()
            }
            "ERROR_WRONG_PASSWORD" -> {
                Toast.makeText(this, R.string.wrongPass, Toast.LENGTH_SHORT).show()
                binding.etPassword.error = getString(R.string.wrongPass)
                binding.etPassword.requestFocus()
            }
            "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL" -> {
                //An account already exists with the same email address but different sign-in credentials
                Toast.makeText(this, R.string.cuentaExistente, Toast.LENGTH_SHORT).show()
            }
            "ERROR_EMAIL_ALREADY_IN_USE" -> {
                Toast.makeText(this, R.string.CorreoEnUso, Toast.LENGTH_SHORT).show()
                binding.etEmail.error = getString(R.string.CorreoEnUso)
                binding.etEmail.requestFocus()
            }
            "ERROR_USER_TOKEN_EXPIRED" -> {
                Toast.makeText(this, R.string.tokenExpired, Toast.LENGTH_SHORT).show()
            }
            "ERROR_USER_NOT_FOUND" -> {
                Toast.makeText(this, R.string.usuarioNoEncontrado, Toast.LENGTH_SHORT).show()
            }
            "ERROR_WEAK_PASSWORD" -> {
                Toast.makeText(this, R.string.WeakPass, Toast.LENGTH_SHORT).show()
                binding.etPassword.error = getString(R.string.WeakPass)
                binding.etPassword.requestFocus()
            }
            "NO_NETWORK" -> {
                Toast.makeText(this, R.string.falloConexion, Toast.LENGTH_SHORT).show()
            }else ->{
                Toast.makeText(this, R.string.noAuth, Toast.LENGTH_SHORT).show()
            }
        }
    }
}