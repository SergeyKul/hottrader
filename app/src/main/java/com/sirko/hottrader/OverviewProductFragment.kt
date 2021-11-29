package com.sirko.hottrader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso

class OverviewProductFragment : Fragment() {
    private var link: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            link = it.getString("link")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_overview_product, container, false).apply {
        findViewById<ImageView>(R.id.photo_view).let {
            it.visibility = View.GONE
            Picasso.get().load(link).into(it, object : PicassoCallback() {
                override fun onSuccess() {
                    findViewById<View>(R.id.progress_bar).visibility = View.GONE
                    it.visibility = View.VISIBLE
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(link: String) =
            OverviewProductFragment().apply {
                arguments = Bundle().apply {
                    putString("link", link)
                }
            }
    }
}