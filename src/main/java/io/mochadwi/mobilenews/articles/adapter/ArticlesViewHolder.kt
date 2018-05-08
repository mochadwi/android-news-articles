package io.mochadwi.mobilenews.articles.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import io.mochadwi.mobilenews.articles.ArticlesWebViewActivity
import io.mochadwi.mobilenews.articles.R
import io.mochadwi.mobilenews.domain.model.articles.ArticlesItem
import io.mochadwi.mobilenews.util.PublicMethods
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_articles.*

/**
 * Created by mochadwi on 3/13/18.
 */
class ArticlesViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(item: ArticlesItem, ctx: Context) = with(containerView) {
        cv_item.setOnClickListener {
            val i = Intent(ctx, ArticlesWebViewActivity::class.java)
            i.putExtra("article", item.toString())
            ctx.startActivity(i)
        }

        txt_author.text = ctx.getString(R.string.message_author, item.author ?: "Unknown")
        txt_source.text = item.source?.name ?: "Unknown"
        Glide.with(ctx)
                .load(item.urlToImage)
                .into(img_media)
        txt_published.setReferenceTime(PublicMethods.stringToLocalDate(item.publishedAt).time)
        txt_primary.text = item.title
        txt_description.text = item.description
    }
}
