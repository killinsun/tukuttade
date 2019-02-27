package com.killinsun.app.tukuttade.data.source

import com.killinsun.app.tukuttade.data.Okazu

interface OkazuDataSource {

    interface LoadOkazusCallback{

        fun onOkazuLoaded(okazuArray: Array<Okazu>)

        fun onDataNotAvailable()
    }

    interface  GetOkazuCallback{

        fun onOkazuLoaded(okazu: Okazu)

        fun onDataNotAvailable()
    }

    fun getOkazus(callback: LoadOkazusCallback)

    fun getOkazu(okazuId: String, callback: GetOkazuCallback)

    fun saveOkazu(okazu: Okazu)

}