package com.intellypodapp

import android.app.ProgressDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.intellypodapp.adapter.AdapterProducts
import com.intellypodapp.util.Util
import kotlinx.android.synthetic.main.activity_main.recyclerview

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelProducts: ViewModelProducts
    var util: Util = Util()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle(getString(R.string.please_wait))
        progressDialog.setMessage(getString(R.string.loading))

        progressDialog.show()

        viewModelProducts = ViewModelProvider(this).get(ViewModelProducts::class.java)

        if (util.verifyAvailableNetwork(this)) {
            viewModelProducts.getProductsData()!!.observe(this, Observer { productList ->

                progressDialog.dismiss()

                if (productList != null && productList.products != null && productList.products.isNotEmpty()) {
                    recyclerview?.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = AdapterProducts(productList.products)
                    }
                } else {
                    Toast.makeText(this, getString(R.string.data_not_available), Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            Toast.makeText(this, getString(R.string.internet_not_available), Toast.LENGTH_SHORT).show()
        }
    }
}