package com.ragnorak.marvelcdb.android.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragnorak.marvelcdb.domain.GetMarvelCardListUseCase
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import com.ragnorak.marvelcdb.onFailure
import com.ragnorak.marvelcdb.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MarvelCardListViewModel(
    private val marvelCardListUseCase: GetMarvelCardListUseCase
) : ViewModel() {

    private val _marvelCardList = MutableStateFlow<ViewState<List<MarvelCardModel>>>(ViewState.Idle)
    val marvelCardList: StateFlow<ViewState<List<MarvelCardModel>>> = _marvelCardList

    fun getMarvelCardList() {
        _marvelCardList.value = ViewState.Loading

        viewModelScope.launch {
            marvelCardListUseCase.execute()
                .onSuccess {
                    _marvelCardList.value = ViewState.Success(it)
                }
                .onFailure {
                    _marvelCardList.value = ViewState.Error(it.message ?: "ERROR")
                }
        }
    }
}