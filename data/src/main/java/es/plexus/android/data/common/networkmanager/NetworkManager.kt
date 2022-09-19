package es.plexus.android.data.common.networkmanager

import android.content.Context
import es.plexus.android.data.common.extensions.isNetworkAvailable

interface NetworkManager {

    companion object{
        const val NETWORK_MANAGER_TAG = "networkManagerTag"
    }
    suspend fun isNetworkAvailable(): Boolean
}

internal class NetworkManagerImpl(private val context: Context) : NetworkManager {

    override suspend  fun isNetworkAvailable(): Boolean = context.isNetworkAvailable()
}