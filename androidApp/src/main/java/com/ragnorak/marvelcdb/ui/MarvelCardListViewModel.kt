package com.ragnorak.marvelcdb.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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

    private val _marvelCardList =
        MutableStateFlow<ViewState<SnapshotStateList<MarvelCardModel>>>(ViewState.Idle)
    val marvelCardList: StateFlow<ViewState<SnapshotStateList<MarvelCardModel>>> = _marvelCardList

    val marvelCardFavoriteList: Flow<List<MarvelCardModel>> = getMarvelCardFavoriteListUseCase()

    fun getMarvelCardList() {
        _marvelCardList.value = ViewState.Loading

        viewModelScope.launch {
            marvelCardListUseCase()
                .onFailure {
                    _marvelCardList.value = ViewState.Error(it.message ?: "ERROR")
                }
                .onSuccess {
                    viewModelScope.launch {
                        val mutableStateListOf = mutableStateListOf<MarvelCardModel>()
                        mutableStateListOf.addAll(it)
                        marvelCardFavoriteList.collect { favouriteList ->
                            matchingMarvelCardListWithFavouriteList(
                                favouriteList = favouriteList,
                                marvelCardList = mutableStateListOf
                            )
                            _marvelCardList.emit(ViewState.Success(mutableStateListOf))
                        }
                    }
                }
        }
    }

    private fun matchingMarvelCardListWithFavouriteList(
        favouriteList: List<MarvelCardModel>,
        marvelCardList: SnapshotStateList<MarvelCardModel>
    ) {
        marvelCardList.map { marvelCardModel ->
                marvelCardModel.isFavourite = favouriteList.any { it.code == marvelCardModel.code }
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