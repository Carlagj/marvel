package edu.iesam.marvelapp.features.superheroes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import edu.iesam.marvelapp.features.superheroes.domain.GetSuperHeroesUseCase
import edu.iesam.marvelapp.features.superheroes.domain.SuperHero

class SuperHeroListViewModel(private val getSuperHeroesUseCase: GetSuperHeroesUseCase) :
    ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState : LiveData<UiState> = _uiState
    fun loadSuperHeroes() {
        _uiState.value = UiState(
            isLoading = true)


        _uiState.value = UiState(
            superHeroes = getSuperHeroesUseCase.invoke())
    }

    data class UiState (
        val isLoading : Boolean = false,
        val hasError : Boolean = false,
        val superHeroes: List<SuperHero>? = null,
        val superHero: SuperHero? = null
    )

    companion object{
        fun factory(getSuperHeroesUseCase: GetSuperHeroesUseCase) = viewModelFactory {
            initializer {
                SuperHeroListViewModel(getSuperHeroesUseCase)
            }
        }
    }


}