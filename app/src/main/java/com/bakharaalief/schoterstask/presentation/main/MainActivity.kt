package com.bakharaalief.schoterstask.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bakharaalief.schoterstask.R
import com.bakharaalief.schoterstask.data.Resource
import com.bakharaalief.schoterstask.databinding.ActivityMainBinding
import com.bakharaalief.schoterstask.presentation.adapter.NewsListAdapter
import com.bakharaalief.schoterstask.presentation.bookmarkedList.BookmarkedListActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpActionBar()
        setUpViewModel()
        setUpRecyclerView()
        getNews()
    }

    private fun setUpActionBar() {
        setSupportActionBar(binding.topAppBar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bookmarked_list -> {
                toBookmarkedList()
                true
            }
            else -> true
        }
    }

    private fun toBookmarkedList() {
        val intent = Intent(this, BookmarkedListActivity::class.java)
        startActivity(intent)
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun setUpRecyclerView() {
        adapter = NewsListAdapter()
        binding.newsRv.adapter = adapter
        binding.newsRv.layoutManager = LinearLayoutManager(this)
    }

    private fun getNews() {
        viewModel.getNews().observe(this) { response ->
            when (response) {
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    showLoading(false)
                    adapter.submitList(response.data)
                }
                is Resource.Error -> {
                    showLoading(false)
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = response.error
                }
            }
        }
    }

    private fun showLoading(status: Boolean) {
        binding.loadingIndicator.visibility = if (status) View.VISIBLE else View.GONE
    }
}