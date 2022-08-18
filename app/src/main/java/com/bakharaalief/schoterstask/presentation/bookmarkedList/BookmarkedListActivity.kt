package com.bakharaalief.schoterstask.presentation.bookmarkedList

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakharaalief.schoterstask.R
import com.bakharaalief.schoterstask.databinding.ActivityBookmarkedListBinding
import com.bakharaalief.schoterstask.presentation.adapter.NewsListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkedListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookmarkedListBinding
    private lateinit var viewModel: BookmarkedListViewModel
    private lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBookmarkedListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()
        setUpViewModel()
        setUpRecyclerView()
        getBookmarkedNews()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.topAppBar)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> true
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[BookmarkedListViewModel::class.java]
    }

    private fun setUpRecyclerView() {
        adapter = NewsListAdapter()
        binding.newsBookmarkedRv.adapter = adapter
        binding.newsBookmarkedRv.layoutManager = LinearLayoutManager(this)
    }

    private fun getBookmarkedNews() {
        viewModel.getBookmarkedNews().observe(this) { response ->

            if (response.isEmpty()) {
                binding.newsBookmarkedRv.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.errorText.text = getString(R.string.news_bookmarked_empty)
            } else {
                binding.newsBookmarkedRv.visibility = View.VISIBLE
                binding.errorText.visibility = View.GONE
                adapter.submitList(response)
            }
        }
    }
}