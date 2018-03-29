package io.mochadwi.mobilenews.articles

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.mochadwi.mobilenews.common.util.PublicMethods


/**
 * Created by mochadwi on 3/13/18.
 */
class ArticlesActivity : AppCompatActivity() {

    private var mPresenter: ArticlesContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        supportActionBar?.title = getString(R.string.message_articles)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val sources = intent.getStringExtra("sources")

        var view: ArticlesFragment? = supportFragmentManager
                .findFragmentById(R.id.fragment_layout) as? ArticlesFragment

        if (view == null) {
            view = ArticlesFragment.newInstance(sources!!, "")

            PublicMethods.addFragmentToActivity(supportFragmentManager,
                    view, R.id.fragment_layout)
        }

        mPresenter = ArticlesPresenter(view)
        mPresenter!!.start()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}