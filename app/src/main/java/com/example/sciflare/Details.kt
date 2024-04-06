package com.example.sciflare

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Details : AppCompatActivity() {
    var dataList = ArrayList<UserModel>()
    lateinit var recyclerView: RecyclerView
    lateinit var dataadapter: DataAdpter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)


        val userDao = MyAppDatabase.getDatabase(this).userDao()
        recyclerView = findViewById(R.id.recycler_view)

        GlobalScope.launch {
            val users = userDao.getAll()
            for (user in users) {
                dataList.add(user);
                Log.e("User*** :", user.name);
            }
        }


        recyclerView.layoutManager = LinearLayoutManager(
            this@Details,
            LinearLayoutManager.VERTICAL,
            false
        )


        val layoutManager = recyclerView.layoutManager as LinearLayoutManager

        val totalItemCount = recyclerView.adapter?.itemCount ?: 0

        for (i in 0 until totalItemCount) {
            recyclerView.postDelayed({
                layoutManager.smoothScrollToPosition(recyclerView, RecyclerView.State(), i)
            }, (i * 100).toLong())
        }

        recyclerView.adapter = DataAdpter(dataList, this@Details)

    }


}