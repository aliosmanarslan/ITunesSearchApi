package com.aliosmanarslan.itunessearchapi.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bumptech.glide.load.HttpException
import com.aliosmanarslan.itunessearchapi.utils.network.request.iTunesSearchApiService
import java.io.IOException

private const val START_PAGE_INDEX = 1
private const val LIMIT = 10


class DataPagingSource(
    private val itunesSearchApiService: iTunesSearchApiService,
    private val query: String,
    private val category: String) : PagingSource<Int, ItemListData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemListData> {
        val position = params.key ?: START_PAGE_INDEX
        val offset = (position - 1) * LIMIT

        return try {
            val response = itunesSearchApiService.getSearchItems(query, offset, LIMIT, category)
            val resultList = response.results

            LoadResult.Page(
                data = resultList,
                prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                nextKey = if (resultList.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ItemListData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val conPage = state.closestPageToPosition(anchorPosition)
            conPage?.prevKey?.plus(1) ?: conPage?.nextKey?.minus(1)
        }
    }
}