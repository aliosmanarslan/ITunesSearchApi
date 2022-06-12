package com.aliosmanarslan.itunessearchapi.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.aliosmanarslan.itunessearchapi.utils.network.request.iTunesSearchApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class iTunesSearchRepository @Inject constructor(private val iTunesSearchApiService: iTunesSearchApiService) {

    fun getSearchResults(query: String, category: String) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 60,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { DataPagingSource(iTunesSearchApiService, query, category) }
        ).liveData
}