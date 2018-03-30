package io.mochadwi.mobilenews.articles

import android.support.v7.widget.SearchView
import io.mochadwi.mobilenews.common.BasePresenter
import io.mochadwi.mobilenews.common.BaseView
import io.mochadwi.mobilenews.articles.adapter.ArticlesAdapter
import io.mochadwi.mobilenews.domain.data.articles.ArticlesModel

/**
 * Created by mochadwi on 3/13/18.
 */
interface ArticlesContract {
    interface View : BaseView<Presenter> {
        fun setRecyclerView(data: ArticlesModel)
        fun setDataNotAvailable()
    }

    interface Presenter : BasePresenter {
        fun getArticles(sources: String)
        fun searchArticles(sv: SearchView, adapter: ArticlesAdapter)
    }
}