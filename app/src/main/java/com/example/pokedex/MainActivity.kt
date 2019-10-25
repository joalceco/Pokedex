package com.example.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val requestQueue = Volley.newRequestQueue(this)
        val url = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=40"

        var solicitud = StringRequest(Request.Method.GET,
            url,
            Response.Listener {request:String ->

                var gson = Gson().fromJson(request,PokeApiData::class.java)
                var names = ""
                for( poke in gson.results){
                    var text_view = TextView(this)
                    text_view.setOnClickListener {
                        var intent:Intent = Intent(this,PokemonDetails::class.java)
                        intent.putExtra("url",poke.url)
                        startActivity(intent)
//                        Toast.makeText(this,poke.url,Toast.LENGTH_SHORT).show()
                    }
                    text_view.text = poke.name
                    scroll_inner.addView(text_view)
                }
                salida.text = names
//                Toast.makeText(this ,"Funciono", Toast.LENGTH_SHORT).show()
            }, Response.ErrorListener {
                Toast.makeText(this ,"No Funciono", Toast.LENGTH_SHORT).show()
            })

        requestQueue.add(solicitud)
        requestQueue.start()

    }
}

data class PokeApiData (

    @SerializedName("count") val count : Int,
    @SerializedName("next") val next : String,
    @SerializedName("previous") val previous : String,
    @SerializedName("results") val results : List<Results>
)

data class Results (

    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String
)
