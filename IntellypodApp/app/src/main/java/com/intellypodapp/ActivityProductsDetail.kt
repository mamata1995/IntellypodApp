package com.intellypodapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_products_detail.imgvDetails
import kotlinx.android.synthetic.main.activity_products_detail.txtvDetails

class ActivityProductsDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products_detail)

        var url = intent.extras?.getString("thumbnail")
        var description = intent.extras?.getString("description")

        Glide.with(this@ActivityProductsDetail)
            .load(url)
            .error(R.drawable.ic_launcher_foreground)
            .override(100, 100)
            .centerCrop()
            .into(imgvDetails)

        txtvDetails.text = description
    }
}