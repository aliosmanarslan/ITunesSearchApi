package com.aliosmanarslan.itunessearchapi.ui.home

import androidx.lifecycle.*
import androidx.paging.cachedIn
import com.aliosmanarslan.itunessearchapi.data.iTunesSearchRepository
import com.aliosmanarslan.itunessearchapi.utils.network.request.iTunesSearchKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(private val repository: iTunesSearchRepository): ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val listItems = currentQuery.switchMap { queryPair ->
        repository.getSearchResults(queryPair.first,getCategoryName(queryPair.second)).cachedIn(viewModelScope)
    }

    fun searchItems(query: String, category: Int) {
        currentQuery.value = Pair(query, category)
    }

    fun getCurrentQuery() : String = getCategoryName(currentQuery.value!!.second)

    private fun getCategoryName(value : Int) : String {
        return when(value){
            0 -> iTunesSearchKeys.MOVIES
            1 -> iTunesSearchKeys.MUSIC
            2 -> iTunesSearchKeys.BOOKS
            3 -> iTunesSearchKeys.PODCAST
            else -> iTunesSearchKeys.MOVIES
        }
    }

    companion object {
        private val DEFAULT_QUERY = Pair("",0)
    }
}