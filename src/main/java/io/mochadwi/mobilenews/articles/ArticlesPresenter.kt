package io.mochadwi.mobilenews.articles

import android.support.v7.widget.SearchView
import io.mochadwi.mobilenews.articles.adapter.ArticlesAdapter
import io.mochadwi.mobilenews.common.util.RxSearch
import io.mochadwi.mobilenews.domain.data.articles.ArticlesModel
import io.mochadwi.mobilenews.domain.network.RESTClient
import io.mochadwi.mobilenews.domain.network.RESTGenerator
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit


/**
 * Created by mochadwi on 3/13/18.
 */
class ArticlesPresenter(private val mView: ArticlesContract.View) : ArticlesContract.Presenter {
    private val mRest = RESTGenerator.createService(BuildConfig.BASEURL, RESTClient::class.java)

    override fun start() {
        mView.setPresenter(this)
    }

    override fun getArticles(sources: String) {
        mView.showProgress()

        mRest
                .getArticles(sources)
                .enqueue(object : Callback<ArticlesModel> {
                    override fun onResponse(call: Call<ArticlesModel>, response: Response<ArticlesModel>) {
                        mView.hideProgress()

                        if (response.isSuccessful) {
                            mView.setRecyclerView(response.body()!!)
                        } else {
                            mView.setDataNotAvailable()
                        }
                    }

                    override fun onFailure(call: Call<ArticlesModel>, t: Throwable) {
                        mView.hideProgress()
                        mView.showToast(t.message!!)
                        mView.setDataNotAvailable()
                    }
                })
    }

    override fun searchArticles(sv: SearchView, adapter: ArticlesAdapter) {
        RxSearch.fromSearchView(sv)
                .debounce(300, TimeUnit.MILLISECONDS)
//                .filter(adapter.)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { query ->
                    adapter.filter.filter(query)
                    adapter.notifyDataSetChanged()
                }
    }
}