package com.example.teste

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import com.example.teste.ListaActivity as ListaActivity1

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Login(Componente: View) {
        val  login = login_Input.text.toString()
        val  senha = senha_Input.text.toString()

        if (login.equals("hero") && senha.equals("123")) {
            val intent = Intent(this,

                //tela que aparecerá apos a splashActivity
                com.example.teste.ListaActivity::class.java)
            startActivity(intent)
        }

        else{
            Toast.makeText(this, "Senha ou usuário errados", Toast.LENGTH_SHORT)
                .show()
        }

    }


      }
