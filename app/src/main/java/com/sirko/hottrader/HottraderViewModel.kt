package com.sirko.hottrader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class HottraderViewModel : ViewModel() {
    val products: MutableLiveData<MutableList<Product>> by lazy {
        MutableLiveData<MutableList<Product>>().also {
            loadProducts()
        }
    }
    val current = MutableLiveData(-1)
    val overview = MutableLiveData<MutableList<String>>()
    val description = MutableLiveData<MutableList<String>>()
    val message = MutableLiveData<String>()
    var itemId = R.id.bottombaritem_overview

    private fun loadProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list: MutableList<Product> = ArrayList()
                val url = "https://hottrader.top/productlist/?features_hash=29-Y"
                val items = Jsoup.connect(url).get().select("div.ut2-gl__body")
                for ((id, item) in items.withIndex()) list.add(Product(
                    item.select("a").attr("href"),
                    item.select("div.ut2-gl__name").text(),
                    item.select("img").attr("data-src"),
                    item.select("div.ut2-gl__price").select("span.ty-price").text(),
                    id
                ))
                products.postValue(list)
            } catch (e: Exception) {
                message.postValue(e.message)
                e.printStackTrace()
            }
        }
    }

    private fun addOverview(link: String) {
        overview.value?.clear()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = Jsoup.connect(link).get()
                    .select("div.ut2-pb__wrapper.clearfix")
                    .select("meta[itemprop=image]")
                    .eachAttr("content")
                overview.postValue(list)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun addDescription(link: String) {
        description.value?.clear()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = Jsoup.connect("$link?sl=uk").get()
                    .select("#content_description > div > p").eachText()
                description.postValue(list)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addProduct(product: Product) {
        if (product.id != current.value) {
            addOverview(product.link)
            addDescription(product.link)
            current.value = product.id
        }
    }
}