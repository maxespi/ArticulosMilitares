package com.example.articulosmilitares

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtName: EditText
    private lateinit var txtLastName:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth:FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtName = findViewById(R.id.txtName)
        txtLastName = findViewById(R.id.txtLastName)
        txtEmail = findViewById(R.id.txtEmail)
        txtPassword = findViewById(R.id.txtPassword)

        progressBar = findViewById(R.id.progressBar)

        auth = FirebaseAuth.getInstance()

    }

    fun register(@Suppress("UNUSED_PARAMETER")view: View){
        createNewAccount()
    }

    private fun createNewAccount(){
        val name:String=txtName.text.toString().trim()
        val lastName:String=txtLastName.text.toString().trim()
        val email:String=txtEmail.text.toString().trim()
        val password:String=txtPassword.text.toString().trim()

        if (name.isEmpty()){
            txtName.error = "Ingrese su nombre"
            return
        }
        if (lastName.isEmpty()){
            txtLastName.error = "Ingrese su apellido"
            return
        }
        if (email.isEmpty()){
            txtEmail.error = "Ingrese su email"
            return
        }
        if (password.isEmpty()){
            txtPassword.error = "Ingrese su clave"
            return
        }

        database = FirebaseDatabase.getInstance().getReference("User")
        //val userID:String = database.push().key!!

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){
                    task ->

                    if(task.isSuccessful) {
                        val user: FirebaseUser? = auth.currentUser
                        verifyEmail(user)

                        val userDB = database.child(user?.uid!!)

                        userDB.child("Nombre").setValue(name)
                        userDB.child("Apellido").setValue(lastName)
                        userDB.child("Email").setValue(email)

                        progressBar.visibility = View.GONE
                        action()

                    } else {
                        Toast.makeText(this, "Error en crear usuario", Toast.LENGTH_LONG).show()
                        progressBar.visibility = View.GONE
                    }
                }
    }

    private fun action(){
        startActivity(Intent(this,LoginActivity::class.java))
    }

    private fun verifyEmail(user: FirebaseUser?){

        user?.sendEmailVerification()
                ?.addOnCompleteListener(this){
                    task ->

                    if(task.isSuccessful){
                        Toast.makeText(this, "Email enviado", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_LONG).show()
                    }
                }
    }

    fun gologin(@Suppress("UNUSED_PARAMETER")view: View){
        startActivity(Intent(this, LoginActivity::class.java))
        //finish()
    }

    fun gorecovery(@Suppress("UNUSED_PARAMETER")view: View){
        //startActivity(Intent(this, RecoveryActivity::class.java))
        //finish()
    }

}
