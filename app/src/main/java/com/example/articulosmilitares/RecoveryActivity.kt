package com.example.articulosmilitares

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RecoveryActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recovery)

        email = findViewById(R.id.txtEmail)
        auth = FirebaseAuth.getInstance()
        progressBar = findViewById(R.id.progressBar)
    }

    fun recovery(@Suppress("UNUSED_PARAMETER")view: View){
        revoreyAccount()
    }

    private fun revoreyAccount(){
        val email:String = email.text.toString().trim()

        progressBar.visibility=View.VISIBLE
        auth.sendPasswordResetEmail(email).addOnCompleteListener(this){
            task ->
            if (task.isSuccessful){
                Toast.makeText(this, "Correo enviado", Toast.LENGTH_LONG).show()
                progressBar.visibility=View.GONE
                startActivity(Intent(this, IntroActivity::class.java))
                finish()
            }else{
                Toast.makeText(this, "Error en el envio de los datos", Toast.LENGTH_LONG).show()
                progressBar.visibility=View.GONE

            }
        }
    }

    fun gologin(@Suppress("UNUSED_PARAMETER")view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
        //finish()
    }

    fun goregister(@Suppress("UNUSED_PARAMETER")view: View){
        startActivity(Intent(this, RecoveryActivity::class.java))
        //finish()
    }
}
