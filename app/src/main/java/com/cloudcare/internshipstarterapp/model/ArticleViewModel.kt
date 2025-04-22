package com.cloudcare.internshipstarterapp.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloudcare.internshipstarterapp.data.Article
import com.cloudcare.internshipstarterapp.data.ArticleRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ArticleViewModel(private val repository: ArticleRepository) : ViewModel() {
    val allArticles: StateFlow<List<Article>> = repository.allArticles
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val articleCount: StateFlow<Int> = repository.articleCount
        .stateIn(viewModelScope, SharingStarted.Lazily, 0)

    fun insertArticle(title: String, description: String, author: String) {
        viewModelScope.launch {
            repository.insert(
                Article(
                    title = title,
                    description = description,
                    author = author
                )
            )
        }
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            repository.delete(article)
        }
    }
}