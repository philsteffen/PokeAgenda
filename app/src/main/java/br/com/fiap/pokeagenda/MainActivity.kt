package br.com.fiap.pokeagenda

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import br.com.fiap.pokeagenda.Model.Pkmn
import br.com.fiap.pokeagenda.api.PkmnApi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btCapturar.setOnClickListener {
            pesquisarPkmn()
        }
    }

    fun pesquisarPkmn() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val api = retrofit.create(PkmnApi::class.java)

        api.buscarPkmn(etNumero.text.toString().toInt())
                .enqueue(object : Callback<Pkmn> {
                    override fun onResponse(call: Call<Pkmn>?, response: Response<Pkmn>?) {
                        tvNomePKMN.text = response!!.body()!!.name
                        Picasso.with(applicationContext).load(response!!.body()!!.sprites!!.frontShiny).into(ivPokemon);
                    }

                    override fun onFailure(call: Call<Pkmn>?, t: Throwable?) {
                        tvNomePKMN.text = t?.message.toString()
                    }
                })

    }


}
