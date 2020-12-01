package com.example.application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.application.data.adapter.FeedAdapter
import com.example.application.data.viewmodel.FeedViewModel
import com.example.application.databinding.ActivityFeedBinding
import com.squareup.picasso.Picasso
import com.synnapps.carouselview.CarouselView
import com.synnapps.carouselview.ImageListener

class FeedActivity : AppCompatActivity() {
    var binding : ActivityFeedBinding? = null
    var feedAdapter = FeedAdapter()
    var viewmodel : FeedViewModel? = null
    var sampleImages = arrayOf(
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885_960_720.jpg",
        "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_1.jpg",
        "https://raw.githubusercontent.com/sayyam/carouselview/master/sample/src/main/res/drawable/image_2.jpg"
    )

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed);
        val carouselView = findViewById(R.id.carouselView) as CarouselView;
        viewmodel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        carouselView.setPageCount(sampleImages.size);
        carouselView.setImageListener(imageListener);

        binding?.recycler?.adapter = feedAdapter;
        getSharedPreferences("APPLICATION_PREFERENCES_NAME", MODE_PRIVATE).getString("USER_TOKEN", "")
            ?.let { viewmodel?.feed(it) }
        observerFeed()
    }

    fun observerFeed(): Unit{
        viewmodel?.multableListFeed?.observe(this,
            Observer { feeds ->

                if(feeds != null){
                   feedAdapter.setFeed(feeds)
                }
                binding?.executePendingBindings()
            })
    }
    var imageListener: ImageListener = object : ImageListener {
        override fun setImageForPosition(position: Int, imageView: ImageView) {
            // You can use Glide or Picasso here
            Picasso.get().load(sampleImages[position]).into(imageView)
        }
    }
}