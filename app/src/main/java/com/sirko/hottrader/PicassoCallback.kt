package com.sirko.hottrader

import com.squareup.picasso.Callback
import java.lang.Exception

abstract class PicassoCallback : Callback {
    override fun onError(e: Exception?) {}
}