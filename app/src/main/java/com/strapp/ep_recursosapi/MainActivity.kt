package com.strapp.ep_recursosapi

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.strapp.ep_recursosapi.data.model.AbilityGroup
import com.strapp.ep_recursosapi.data.model.Pokemon
import com.strapp.ep_recursosapi.data.remote.JSONPlaceHolderAPIService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var resultPokemon: TextView
    private lateinit var id_namePokemon: EditText
    private lateinit var retrofit: Retrofit
    private lateinit var apiService : JSONPlaceHolderAPIService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        id_namePokemon = findViewById(R.id.id_name)
        resultPokemon = findViewById(R.id.resultPokemon)
        retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(JSONPlaceHolderAPIService::class.java)

    }

    fun getPokemon(view_: android.view.View) {
        closeKeyBoard()
        val id_name = id_namePokemon.text.toString()
        resultPokemon.text = null
        if((id_namePokemon.text.toString() == "")){
            Toast.
            makeText(applicationContext,"No hay nada escrito",Toast.LENGTH_SHORT).
            show()
        } else {
            val pk : Call<Pokemon> = apiService.getPok(id_name)
            pk.enqueue(object : Callback<Pokemon> {
                override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                    if(!response.isSuccessful){
                        Toast.
                        makeText(applicationContext,"Ha ocurrido un problema al intentar conectar a la API",Toast.LENGTH_SHORT).
                        show()
                        return
                    }
                    val pokemon : Pokemon? = response.body()
                    if (pokemon != null) {
                        var content = ""
                        content += "Nombre: " + pokemon.name + "\n"
                        content += "Altura: " + pokemon.height + "\n"
                        content += "Peso: " + pokemon.weight + "\n"
                        content += "Habilidades: " + "\n"
                        val abilitiesGroup : List<AbilityGroup> = pokemon.abilities
                        for(ability in abilitiesGroup){
                            content += " - " + ability.ability.name
                        }
                        resultPokemon.text = content
                    }
                }

                override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                    Toast.
                    makeText(applicationContext,"Ha ocurrido un problema al intentar conectar a la API",Toast.LENGTH_SHORT).
                    show()
                }
            })
        }
    }
    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

}