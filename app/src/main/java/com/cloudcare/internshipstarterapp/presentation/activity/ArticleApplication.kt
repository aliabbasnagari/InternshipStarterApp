package com.cloudcare.internshipstarterapp.presentation.activity

import android.app.Application
import com.cloudcare.internshipstarterapp.data.ArticleDatabase
import com.cloudcare.internshipstarterapp.data.ArticleRepository

class ArticleApplication : Application() {
    val database by lazy { ArticleDatabase.getDatabase(this) }
    val repository by lazy { ArticleRepository(database.articleDao()) }
}