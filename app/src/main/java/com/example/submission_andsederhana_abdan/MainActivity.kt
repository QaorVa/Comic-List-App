package com.example.submission_andsederhana_abdan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvComic: RecyclerView
    private val list = ArrayList<Comic>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        rvComic = findViewById(R.id.rv_comics)
        rvComic.setHasFixedSize(true)

        list.addAll(getListComics())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.about_button -> {
                val about = Intent(this@MainActivity, about::class.java)
                startActivity(about)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun getListComics(): ArrayList<Comic> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataAuthor = resources.getStringArray(R.array.data_author)
        val dataDescription = resources.getStringArray(R.array.data_long_description)
        val dataGenre = resources.getStringArray(R.array.data_genre)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val dataUrl = resources.getStringArray(R.array.data_url)

        val listComic = ArrayList<Comic>()

        for(i in dataTitle.indices) {
            val comic = Comic(dataTitle[i], dataAuthor[i], dataDescription[i], dataGenre[i], dataPhoto.getResourceId(i, -1), dataUrl[i])
            listComic.add(comic)
        }
        return listComic
    }

    private fun showRecyclerList() {
        rvComic.layoutManager = LinearLayoutManager(this)
        val listComicAdapter = ListComicAdapter(list)
        rvComic.adapter = listComicAdapter

        listComicAdapter.setOnItemClickCallback(object : ListComicAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Comic) {
                moveObjectIntent(data)
            }
        })
    }

    private fun moveObjectIntent(comic: Comic) {
        val moveWithObjectIntent = Intent(this@MainActivity, Details::class.java)
        moveWithObjectIntent.putExtra(Details.EXTRA_COMIC, comic)
        startActivity(moveWithObjectIntent)
    }
}