package com.example.testtechnique2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testtechnique2.API.ActorItem
import com.example.testtechnique2.API.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val URL = "https://www.breakingbadapi.com/"

class MainActivity : AppCompatActivity() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewActor = findViewById<RecyclerView>(R.id.recyclerview_actor)
        recyclerViewActor.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerViewActor.layoutManager = linearLayoutManager

        val buttonView = findViewById<Button>(R.id.random_actor_button)
        buttonView.setOnClickListener {
            getMyData()
        }
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(URL)
            .build()
            .create<ApiInterface>(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<ActorItem>?> {
            override fun onResponse(
                call: Call<List<ActorItem>?>,
                response: Response<List<ActorItem>?>
            ) {
                val responseBody = response.body()!!

                myAdapter = MyAdapter(baseContext, responseBody)
                myAdapter.notifyDataSetChanged()

                val recyclerViewActor = findViewById<RecyclerView>(R.id.recyclerview_actor)
                recyclerViewActor.adapter = myAdapter
            }

            override fun onFailure(call: Call<List<ActorItem>?>, t: Throwable) {
                d("MainActivity", t.message.toString())
            }
        })
    }
}