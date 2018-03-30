package io.mochadwi.mobilenews.articles.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import io.mochadwi.mobilenews.articles.R
import io.mochadwi.mobilenews.domain.data.articles.ArticlesItem
import io.mochadwi.mobilenews.domain.data.articles.ArticlesModel
import java.util.*

/**
 * Created by mochadwi on 3/13/18.
 */
class ArticlesAdapter : RecyclerView.Adapter<ArticlesViewHolder>, Filterable {

    private var mArticles: List<ArticlesItem?>? = null
    private var mFilteredArticles: ArrayList<ArticlesItem?>? = null
    private val mCtx: Context
    private var count: Int

    constructor(c: Context, dataIn: ArticlesModel) {
        this.mCtx = c
        mArticles = dataIn.articles
        mFilteredArticles = mArticles as? ArrayList<ArticlesItem?>
        count = mFilteredArticles!!.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_articles, parent, false)
        return ArticlesViewHolder(v)
    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) =
            holder.bind(mFilteredArticles!![position]!!, mCtx)

    override fun getItemCount(): Int {
        return count
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charString = p0.toString().toLowerCase()

                when (charString.isEmpty()) {

                    true -> {
                        mFilteredArticles = mArticles as? ArrayList<ArticlesItem?>
                    }
                    false -> {

                        val filteredList = ArrayList<ArticlesItem?>()

                        for (article in mArticles!!.iterator()) {

                            if (article!!.title?.toLowerCase()!!.contains(charString) ||
                                    article.description?.toLowerCase()!!.contains(charString)) {

                                filteredList.add(article)
                            }
                        }

                        mFilteredArticles = filteredList
                        count = filteredList.size
                    }
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = mFilteredArticles

                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                mFilteredArticles = p1?.values as? ArrayList<ArticlesItem?>
                notifyDataSetChanged()
            }
        }
    }
}
