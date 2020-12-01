package com.example.application.data.adapter

import android.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.application.data.model.Feed
import com.example.application.databinding.CardviewBinding
import com.squareup.picasso.Picasso


class FeedAdapter: RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    private var listaFeed = ArrayList<Feed>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardviewBinding.inflate(inflater)
        return FeedViewHolder(binding)
    }

    fun setFeed(listaFeed: ArrayList<Feed>){
        this.listaFeed = listaFeed
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        if (listaFeed != null){
            var feed = listaFeed.get(position)
            holder.bind(feed)
            holder.binding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
       return listaFeed.size;

    }

    class FeedViewHolder(val binding: CardviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(feedItem: Feed){
            binding.feed = feedItem
        }
    }
}


@BindingAdapter("imageUrl")
fun setImage(imageView: ImageView, imageUrl: String?) {
    Picasso.get().load(imageUrl).into(imageView)
}