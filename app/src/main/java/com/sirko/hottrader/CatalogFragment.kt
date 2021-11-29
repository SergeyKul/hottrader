package com.sirko.hottrader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CatalogFragment : Fragment(), (Product) -> Unit {
    private val model: HottraderViewModel
        get() = ViewModelProvider(requireActivity())[HottraderViewModel::class.java]
    private val adapter = RecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_catalog, container, false).apply {
        findViewById<RecyclerView>(R.id.recycler_view).let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model.products.observe(viewLifecycleOwner, { products ->
            adapter.products = products
        })
        model.current.observe(viewLifecycleOwner, { current ->
            if (current != -1) addProduct()
            adapter.current = current
        })
    }

    override fun invoke(product: Product) {
        model.addProduct(product)
    }

    private fun addProduct() {
        parentFragmentManager.popBackStack()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_product, ProductFragment.newInstance())
            .addToBackStack(null)
            .commit()
    }
}