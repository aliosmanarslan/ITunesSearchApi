package com.aliosmanarslan.itunessearchapi.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.ArrayList

@Parcelize
data class ItemListData(
    @SerializedName("trackId") val trackId : Int,
    @SerializedName("artistId") val artistId: Int?,
    @SerializedName("trackName") val trackName: String,
    @SerializedName("artworkUrl100") val artwork100: String,
    @SerializedName("primaryGenreName") var genreName: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("country") val country: String,
    @SerializedName("currency") val currency: String,
    @SerializedName("trackPrice") val trackPrice: Float?,
    @SerializedName("genres") val genreList: ArrayList<String>?,
    @SerializedName("trackTimeMillis") val trackTimeMillis: Int,
    @SerializedName("longDescription") val longDescription: String?,
    @SerializedName("trackRentalPrice") val rentalPrice: Float?,
    @SerializedName("previewUrl") val previewUrl: String,
    @SerializedName("trackViewUrl") val trackViewUrl: String,
    @SerializedName("price") val price: Float?,
    @SerializedName("description") val description: String?,
    @SerializedName("wrapperType") val wrapperType: String?,
) : Parcelable
