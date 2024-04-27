package com.example.submission_andsederhana_abdan

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView

class Details : AppCompatActivity() {

    companion object {
        const val EXTRA_COMIC = "extra_comic"
    }

    private var comic: Comic? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        title = "Details Page"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val detailPhoto: ImageView = findViewById(R.id.iv_details_photo)
        val detailTitle: TextView = findViewById(R.id.tv_details_title)
        val detailAuthor: TextView = findViewById(R.id.tv_details_author)
        val detailGenre: TextView = findViewById(R.id.tv_details_genre)
        val detailDescription: TextView = findViewById(R.id.tv_details_description)

        comic = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Comic>(EXTRA_COMIC, Comic::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Comic>(EXTRA_COMIC)
        }

        if(comic != null) {
            detailTitle.text = comic!!.title
            detailAuthor.text = comic!!.author
            detailGenre.text = comic!!.genre
            detailPhoto.setImageResource(comic!!.photo)
            detailDescription.text = comic!!.longDescription
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
            R.id.action_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, comic?.url)
                    type = "text/plain"
                }
                val chooser = Intent.createChooser(sendIntent, null)
                startActivity(chooser)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}