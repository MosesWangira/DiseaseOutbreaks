package com.example.diseaseoutbreaks.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.data.Model.DataClass
import com.example.diseaseoutbreaks.data.adapter.DiseasesAdapter
import com.example.diseaseoutbreaks.data.network.RetrofitBuilder
import com.mlsdev.animatedrv.AnimatedRecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var adapter: DiseasesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchDiseases()

    }

    private fun fetchDiseases() {
        val fetching_diseases = RetrofitBuilder.apiService.getDiseases()
        fetching_diseases.enqueue(object : Callback<DataClass> {
            override fun onFailure(call: Call<DataClass>, t: Throwable) {
//                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                Toast.makeText(this@MainActivity, "No internet", Toast.LENGTH_LONG).show()
//                Log.d("Check error", t.message)
            }

            override fun onResponse(call: Call<DataClass>, response: Response<DataClass>) {
                if (response.isSuccessful) {
                    val diseases = response.body()

                    val dis = diseases?.items?.size
                    Log.d("Successful :", "${dis}")

                    diseases?.let {
                        showDisease(it)

                    }
                } else {
                }
            }

        })
    }

    private fun showDisease(items: DataClass) {
        recycler_view.layoutManager = LinearLayoutManager(this@MainActivity)
        recycler_view.hasFixedSize()
        adapter = DiseasesAdapter(items)
        recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_view, menu)

//        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        val search_item = menu?.findItem(R.id.search)

//        val search_view = search_item?.actionView as SearchView

//        search_view.setSearchableInfo(manager.getSearchableInfo(componentName))
//
//        search_view.setOnQueryTextListener(object :
//            SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String): Boolean {
//                adapter.filter(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String): Boolean {
//                adapter.filter(newText)
//                return false
//            }
//        })

        return super.onCreateOptionsMenu(menu)
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
//    }

}
