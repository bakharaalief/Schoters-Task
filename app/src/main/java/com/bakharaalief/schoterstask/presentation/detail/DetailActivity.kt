package com.bakharaalief.schoterstask.presentation.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bakharaalief.schoterstask.R
import com.bakharaalief.schoterstask.databinding.ActivityDetailBinding
import com.bakharaalief.schoterstask.domain.model.News
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var news: News
    private lateinit var viewModel: DetailViewModel
    private lateinit var menu: Menu

    private var isNewsBookmarked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        news = intent.getParcelableExtra<News>(NEWS_EXTRA) as News

        setUpActionBar()
        setUpViewModel()
        setUpWebView()
        loadWebView()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.title = news.sourceName
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        getNewsIsBookmarked()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.bookmark -> {
                onClickBookmark()
                true
            }
            else -> true
        }
    }

    private fun onClickBookmark() {
        if (isNewsBookmarked) {
            Toast.makeText(this, "Remove Bookmark", Toast.LENGTH_SHORT).show()
            viewModel.deleteNews(news)
        } else {
            Toast.makeText(this, "Add Bookmark", Toast.LENGTH_SHORT).show()
            viewModel.saveNews(news)
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
    }

    private fun getNewsIsBookmarked() {
        viewModel.isNewsBookmarked(news.title).observe(this) {
            isNewsBookmarked = it
            setBookmarkIcon()
        }
    }

    private fun setBookmarkIcon() {
        menu.getItem(0).icon =
            ContextCompat.getDrawable(
                this,
                if (isNewsBookmarked) R.drawable.ic_baseline_bookmark_24 else R.drawable.ic_baseline_bookmark_border_24
            )
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() {
        binding.WebView.settings.javaScriptEnabled = true
    }

    private fun loadWebView() {
        binding.WebView.loadUrl(news.url)
    }

    companion object {
        const val NEWS_EXTRA = "NEWS_EXTRA"
    }
}