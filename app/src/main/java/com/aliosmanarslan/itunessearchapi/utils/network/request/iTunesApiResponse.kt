package com.aliosmanarslan.itunessearchapi.utils.network.request

import com.aliosmanarslan.itunessearchapi.data.ItemListData
import com.google.gson.annotations.SerializedName

data class iTunesApiResponse(
    @SerializedName("results") val results : List<ItemListData>
)
