package com.cloudcare.internshipstarterapp.ui

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cloudcare.internshipstarterapp.R
import com.cloudcare.internshipstarterapp.data.Article
import java.text.SimpleDateFormat
import java.util.Date

class ArticleViewHolder(itemView: View, private val onItemClick: (Article) -> Unit) :
    RecyclerView.ViewHolder(itemView) {

    fun bind(article: Article) {
        itemView.apply {
            findViewById<TextView>(R.id.textViewTitle).text = article.title
            findViewById<TextView>(R.id.textViewAuthor).text = article.author
            findViewById<TextView>(R.id.textViewTimestamp).text =
                SimpleDateFormat("MMM dd, yyyy").format(Date(article.timestamp))
            setOnLongClickListener {
                onItemClick(article)
                true
            }
        }
    }
}