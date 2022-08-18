package com.bakharaalief.schoterstask.presentation.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bakharaalief.schoterstask.R
import com.bakharaalief.schoterstask.databinding.ItemNewsBinding
import com.bakharaalief.schoterstask.domain.model.News
import com.bakharaalief.schoterstask.presentation.detail.DetailActivity
import com.bumptech.glide.Glide

class NewsListAdapter : ListAdapter<News, NewsListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    class MyViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) {
            binding.titleNewsItem.text = news.title
            binding.sourceNewsItem.text = news.sourceName

            Glide.with(itemView.context)
                .load(news.urlToImage)
                .placeholder(R.drawable.ic_baseline_image_24)
                .centerCrop()
                .into(binding.imageNewsItem)

            binding.cardNewsItem.setOnClickListener {
                val intent = Intent(it.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.NEWS_EXTRA, news)
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }
}