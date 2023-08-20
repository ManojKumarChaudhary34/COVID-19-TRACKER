package com.example.covidtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Objects


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //https://data.covid19india.org/data.json

        fetchData()
    }

    private fun fetchData() {
        val retrofit= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://data.covid19india.org/")
            .build()
            .create(ApiInterface::class.java)

        val response= retrofit.getCovidDAta()
        response.enqueue(object : Callback<CovidDataClass?> {
            override fun onResponse(
                call: Call<CovidDataClass?>,
                response: Response<CovidDataClass?>
            ) {
                val responseBody= response.body()
                val stateList = responseBody?.statewise
//                val stringBuilder= StringBuilder()
//                if (stateList != null) {
//                    for (list in stateList){
//                        stringBuilder.append(list.state)
//                    }
//                }
//                binding.tv.text= stringBuilder
                if (stateList != null) {
                    initiateLayout(stateList)
                }
            }

            override fun onFailure(call: Call<CovidDataClass?>, t: Throwable) {

            }
        })

    }

    private fun initiateLayout(state: List<Statewise>) {
        binding.myRecyclerView.setHasFixedSize(true)
        binding.myRecyclerView.setItemViewCacheSize(13)
        binding.myRecyclerView.layoutManager= LinearLayoutManager(this@MainActivity)
        myAdapter= MyAdapter(state)
        binding.myRecyclerView.adapter= myAdapter
        myAdapter.setListener(object : MyAdapter.SetOnClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "You clicked: ${state[position].state}", Toast.LENGTH_SHORT).show()
                val intent= Intent(applicationContext, ShowCaseActivity::class.java)
                intent.putExtra("district", state[position].state)
                intent.putExtra("active", state[position].active)
                intent.putExtra("confirmed", state[position].confirmed)
                intent.putExtra("deaths", state[position].deaths)
                intent.putExtra("recovered", state[position].recovered)
                intent.putExtra("delta_death", state[position].deltadeaths)
                intent.putExtra("delta_confirmed", state[position].deltaconfirmed)
                intent.putExtra("delta_recovered", state[position].deltarecovered)
                startActivity(intent)
            }
        })
    }
}