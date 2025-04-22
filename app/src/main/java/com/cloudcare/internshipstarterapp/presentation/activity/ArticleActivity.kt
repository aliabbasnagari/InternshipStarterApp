package com.cloudcare.internshipstarterapp.presentation.activity

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cloudcare.internshipstarterapp.R
import com.cloudcare.internshipstarterapp.databinding.ActivityArticleBinding
import com.cloudcare.internshipstarterapp.model.ArticleViewModel
import com.cloudcare.internshipstarterapp.model.ArticleViewModelFactory
import com.cloudcare.internshipstarterapp.ui.ArticleAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding
    private val viewModel: ArticleViewModel by viewModels {
        ArticleViewModelFactory((application as ArticleApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupFab()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        val adapter = ArticleAdapter { article ->
            viewModel.deleteArticle(article)
            Toast.makeText(this, "Article deleted", Toast.LENGTH_SHORT).show()
        }

        binding.recyclerView.apply {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(this@ArticleActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@ArticleActivity,
                    DividerItemDecoration.VERTICAL
                )
            )

            // Swipe to delete
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                override fun onMove(
                    recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ) = false

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    adapter.currentList[viewHolder.adapterPosition].let { article ->
                        viewModel.deleteArticle(article)
                    }
                }
            }).attachToRecyclerView(this)
        }
    }

    private fun setupFab() {
        binding.fab.setOnClickListener {
            showAddArticleDialog()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.allArticles.collectLatest { articles ->
                (binding.recyclerView.adapter as ArticleAdapter).submitList(articles)
            }
        }

        lifecycleScope.launch {
            viewModel.articleCount.collectLatest { count ->
                binding.toolbar.subtitle = "Saved articles: $count"
            }
        }
    }

    private fun showAddArticleDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("Add Article")
            val view = layoutInflater.inflate(R.layout.dialog_add_article, null)
            setView(view)

            setPositiveButton("Save") { _, _ ->
                with(view) {
                    val title = findViewById<EditText>(R.id.editTextTitle).text.toString()
                    val desc = findViewById<EditText>(R.id.editTextDescription).text.toString()
                    val author = findViewById<EditText>(R.id.editTextAuthor).text.toString()

                    if (title.isNotEmpty() && desc.isNotEmpty() && author.isNotEmpty()) {
                        viewModel.insertArticle(title, desc, author)
                        Toast.makeText(context, "Article saved", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            setNegativeButton("Cancel", null)
        }.show()
    }
}