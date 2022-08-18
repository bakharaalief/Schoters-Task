package com.bakharaalief.schoterstask.util

import com.bakharaalief.schoterstask.data.local.entity.NewsEntity
import com.bakharaalief.schoterstask.data.remote.response.ArticlesItemResponse
import com.bakharaalief.schoterstask.domain.model.News

object DataMapper {
    fun responseToNewsModel(input: List<ArticlesItemResponse>): List<News> {
        return input.map {
            News(
                it.source?.id ?: "id source not found",
                it.source?.name ?: "source not found",
                it.publishedAt,
                it.author ?: "author not found",
                it.urlToImage ?: "url image not found",
                it.description ?: "description not found",
                it.title,
                it.url ?: "url not found",
                it.content ?: "content not found"
            )
        }
    }

    fun modelToNewsEntity(news: News): NewsEntity {
        return NewsEntity(
            news.sourceId,
            news.sourceName,
            news.publishedAt,
            news.author,
            news.urlToImage,
            news.description,
            news.title,
            news.url,
            news.content
        )
    }

    fun newsEntityToNewsModel(input: List<NewsEntity>): List<News> {
        return input.map {
            News(
                it.sourceId,
                it.sourceName,
                it.publishedAt,
                it.author,
                it.urlToImage,
                it.description,
                it.title,
                it.url,
                it.content
            )
        }
    }
}