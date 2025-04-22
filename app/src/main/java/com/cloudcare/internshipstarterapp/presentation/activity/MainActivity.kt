package com.cloudcare.internshipstarterapp.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cloudcare.internshipstarterapp.R
import com.cloudcare.internshipstarterapp.data.Quotes
import com.cloudcare.internshipstarterapp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnShowQuote.setOnClickListener {
            val quote = Quotes.getRandomQuote();
            MaterialAlertDialogBuilder(this)
                .setTitle(getString(R.string.quote_alert_title))
                .setMessage(quote)
                .setPositiveButton(getString(R.string.btn_quote_dismiss_text)) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }

        binding.btnGotoArticles.setOnClickListener{
            val intent = Intent(this, ArticleActivity::class.java)
            startActivity(intent)
        }
    }
}