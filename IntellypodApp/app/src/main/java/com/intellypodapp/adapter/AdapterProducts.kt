package com.intellypodapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.intellypodapp.ActivityProductsDetail
import com.intellypodapp.R
import com.intellypodapp.datamodel.DataModelProducts
import kotlinx.android.synthetic.main.adapter_products_layout.view.imgvThumbnail
import kotlinx.android.synthetic.main.adapter_products_layout.view.txtvPrice
import kotlinx.android.synthetic.main.adapter_products_layout.view.txtvTitle


class AdapterProducts(private val listdataModelProducts: List<DataModelProducts>) :
    RecyclerView.Adapter<ViewHolderProducts>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderProducts {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_products_layout, parent, false)
        return ViewHolderProducts(view, parent.context)
    }

    override fun getItemCount(): Int {
        return listdataModelProducts.size
    }

    override fun onBindViewHolder(viewHolderProducts: ViewHolderProducts, position: Int) {
        return viewHolderProducts.bindView(listdataModelProducts[position])
    }
}

class ViewHolderProducts(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {

    var mContext = context

    fun bindView(dataModelProducts: DataModelProducts) {

        if (dataModelProducts.title != null && dataModelProducts.title.isNotEmpty()) {
            itemView.txtvTitle.text = dataModelProducts.title
        }

        if (dataModelProducts.price != null) {
            itemView.txtvPrice.text = "₹ " + dataModelProducts.price
        }

        if(dataModelProducts.thumbnail != null && dataModelProducts.thumbnail.isNotEmpty()) {
            Glide.with(mContext)
                .load(dataModelProducts.thumbnail)
                .error(R.drawable.ic_launcher_foreground)
                .override(100, 100)
                .centerCrop()
                .into(itemView.imgvThumbnail)
        }

            itemView.setOnClickListener {

                var intent: Intent = Intent(mContext, ActivityProductsDetail::class.java)
                intent.putExtra("description", dataModelProducts.description)
                intent.putExtra("thumbnail", dataModelProducts.thumbnail)
                mContext.startActivity(intent)

            }
        }
    }








