package com.example.teste

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_lista.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        // criando um objeto do tipo TextView
        val novoChar = TextView(baseContext)
        // baseContext é para "atrelar" o componete à Activity atual

        // configurando a TextView
        novoChar.text = "Meu texto em tempo de execução"
        novoChar.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
        novoChar.setTextColor(Color.parseColor("#FF0099"))

        // adicionando a TextView no LinearLayout
        ll_conteudo.addView(novoChar)

        consumirApiSimples()
    }


    fun cadastrarFilme(componente: View) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://5f97648d42706e001695708c.mockapi.io/hero-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requests = retrofit.create(ApiPersonagensRequests::class.java)

        val novoPersonagem = Personagem(
            null,
            et_nome.text.toString(),
            et_atk.text.toString(),
            et_hp.text.toString()
        )

        val callCriarFilme = requests.postFilme(novoPersonagem)

        callCriarFilme.enqueue(object: Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(baseContext,
                    getString(R.string.txt_cadastrado),
                    Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun consumirApiSimples() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://5f861cfdc8a16a0016e6aacd.mockapi.io/bandtec-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requests = retrofit.create(ApiPersonagensRequests::class.java)

        val callFilmeUnico = requests.getFilme(Integer(10))

        callFilmeUnico.enqueue(object: Callback<Personagem> {
            override fun onFailure(call: Call<Personagem>, t: Throwable) {
                Toast.makeText(baseContext, "Erro: $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Personagem>, response: Response<Personagem>) {
                val novoTv = TextView(baseContext)

                val personagem = response.body()
                novoTv.text = getString(R.string.txt_personagem, personagem?.nome, personagem?.atk,personagem?.hp)

                novoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                novoTv.setTextColor(Color.parseColor("#000000"))

                ll_conteudo.addView(novoTv);
            }
        })

    }



    fun consumirApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://5f861cfdc8a16a0016e6aacd.mockapi.io/bandtec-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val requests = retrofit.create(ApiPersonagensRequests::class.java)

        val callFilmes = requests.getFilmes()

        callFilmes.enqueue(object: Callback<List<Personagem>>{
            override fun onFailure(call: Call<List<Personagem>>, t: Throwable) {
                Toast.makeText(applicationContext, "Deu ruim $t", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Personagem>>, response: Response<List<Personagem>>) {
                response.body()?.forEach {
                    val novoTv = TextView(baseContext)

                    novoTv.text = "${it.nome} - ${it.atk} -  ${it.hp}"
                    novoTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15f)
                    novoTv.setTextColor(Color.parseColor("#000000"))

                    ll_conteudo.addView(novoTv)
                }
            }
        })

    }
}