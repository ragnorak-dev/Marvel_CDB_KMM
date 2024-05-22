package com.ragnorak.marvelcdb.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragnorak.marvelcdb.domain.interfaces.AddMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.DeleteMarvelFavouriteCardUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardFavoriteListUseCase
import com.ragnorak.marvelcdb.domain.interfaces.GetMarvelCardListUseCase
import com.ragnorak.marvelcdb.domain.models.MarvelCardModel
import com.ragnorak.marvelcdb.onFailure
import com.ragnorak.marvelcdb.onSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MarvelCardListViewModel(
    private val marvelCardListUseCase: GetMarvelCardListUseCase,
    private val getMarvelCardFavoriteListUseCase: GetMarvelCardFavoriteListUseCase,
    private val addMarvelFavouriteCardUseCase: AddMarvelFavouriteCardUseCase,
    private val deleteMarvelFavouriteCardUseCase: DeleteMarvelFavouriteCardUseCase
) : ViewModel() {

    private val _marvelCardList = MutableStateFlow<ViewState<List<MarvelCardModel>>>(ViewState.Idle)
    val marvelCardList: StateFlow<ViewState<List<MarvelCardModel>>> = _marvelCardList

    val test: MutableState<Boolean> = mutableStateOf(false)
    val marvelCardFavoriteList: Flow<List<MarvelCardModel>> = getMarvelCardFavoriteListUseCase()

    fun getMarvelCardList() {
        _marvelCardList.value = ViewState.Loading

        viewModelScope.launch {
            marvelCardListUseCase()
                .onFailure {
                    _marvelCardList.value = ViewState.Error(it.message ?: "ERROR")
                }
                .onSuccess {
                    _marvelCardList.value = ViewState.Success(it)
                }
        }
    }

    fun addMarvelFavouriteCard(marvelCardModel: MarvelCardModel) {
        viewModelScope.launch {
            addMarvelFavouriteCardUseCase(marvelCardModel)
        }
    }

    fun deleteMarvelFavouriteCard(marvelCardModel: MarvelCardModel) {
        viewModelScope.launch {
            deleteMarvelFavouriteCardUseCase(marvelCardModel)
        }
    }

}