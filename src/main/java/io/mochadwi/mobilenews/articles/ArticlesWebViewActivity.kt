package io.mochadwi.mobilenews.articles

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.WebViewClient
import butterknife.ButterKnife
import com.google.gson.Gson
import io.mochadwi.mobilenews.domain.model.articles.ArticlesItem
import kotlinx.android.synthetic.main.activity_articles_web_view.*

class ArticlesWebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles_web_view)
        ButterKnife.bind(this)

        val article = Gson().fromJson<ArticlesItem>(
                intent.getStringExtra("article"),
                ArticlesItem::class.java)

        supportActionBar?.title = article?.title
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        wv_article.webViewClient = WebViewClient()
        wv_article.settings.javaScriptEnabled = true
        wv_article.loadUrl(article?.url)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
