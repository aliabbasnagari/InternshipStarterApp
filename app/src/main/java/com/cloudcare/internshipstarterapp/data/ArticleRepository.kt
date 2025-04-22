package com.cloudcare.internshipstarterapp.data

import kotlinx.coroutines.flow.Flow

class ArticleRepository(private val articleDao: ArticleDao) {
    val allArticles: Flow<List<Article>> = articleDao.getAllArticles()
    val articleCount: Flow<Int> = articleDao.getArticleCount()

    suspend fun insert(article: Article) {
        articleDao.insert(article)
    }

    suspend fun delete(article: Article) {
        articleDao.delete(article)
    }
}