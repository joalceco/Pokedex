package com.example.pokedex

import PokeDetailJson
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_pokemon_details.*

class PokemonDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)
        var url = intent.getStringExtra("url")
        var queue = Volley.newRequestQueue(this)

        var request = StringRequest(Request.Method.GET,
            url,
            Response.Listener { response ->
                var poke = Gson().fromJson(response,PokeDetailJson::class.java)
                var text = ""
                for(move in poke.moves){
                    text += move.move.name + "\n"
                }
                salida.text = text
            },
            Response.ErrorListener { "No jalo" })

        queue.add(request)
    }
}
