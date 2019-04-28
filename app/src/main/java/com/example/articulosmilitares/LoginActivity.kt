package com.example.articulosmilitares

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtUser = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)
        progressBar = findViewById(R.id.progressBar)
        auth = FirebaseAuth.getInstance()
    }

    fun login(@Suppress("UNUSED_PARAMETER")view: View){
        loginUser()
        //action()
    }

    private fun loginUser(){
        val user:String = txtUser.text.toString().trim()
        val password:String = txtPassword.text.toString().trim()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            progressBar.visibility= View.VISIBLE

            auth.signInWithEmailAndPassword(user,password)
                    .addOnCompleteListener(this){
                        task ->

                        if(task.isSuccessful){
                            progressBar.visibility = View.GONE
                            action()

                        }else {
                            Toast.makeText(this, "Error en la autenticacion", Toast.LENGTH_LONG).show()
                            progressBar.visibility = View.GONE
                        }
                    }
        }
    }

    private fun action(){
        startActivity(Intent(this, DiscountActivity::class.java))
    }

    fun goregister(@Suppress("UNUSED_PARAMETER")view: View){
        startActivity(Intent(this, RegisterActivity::class.java))
        //finish()
    }

    fun gorecovery(@Suppress("UNUSED_PARAMETER")view: View){
        startActivity(Intent(this, RecoveryActivity::class.java))
        //finish()
    }
}
