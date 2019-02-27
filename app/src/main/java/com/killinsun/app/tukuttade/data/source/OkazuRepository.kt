package com.killinsun.app.tukuttade.data.source

import com.killinsun.app.tukuttade.data.Okazu
import com.killinsun.app.tukuttade.data.source.remote.OkazuRemoteDataSource

class OkazuRepository(
    val okazuRemoteDataSource: OkazuDataSource
): OkazuDataSource {


    override fun getOkazus(callback: OkazuDataSource.LoadOkazusCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOkazu(okazuId: String, callback: OkazuDataSource.GetOkazuCallback) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveOkazu(okazu: Okazu) {
        OkazuRemoteDataSource.saveOkazu(okazu)
    }
}