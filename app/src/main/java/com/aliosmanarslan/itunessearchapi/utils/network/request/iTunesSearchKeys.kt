package com.aliosmanarslan.itunessearchapi.utils.network.request

import androidx.annotation.StringDef

class iTunesSearchKeys {

    companion object{

        @StringDef(MOVIES, MUSIC, PODCAST, BOOKS)
        @kotlin.annotation.Retention
        annotation class iTunesSearchKeysAnnotations


        const val MOVIES = "movie"
        const val MUSIC = "musicTrack"
        const val PODCAST = "podcast"
        const val BOOKS = "ebook"

    }
}