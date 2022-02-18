package com.rv.githubapp.ui.repo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rv.githubapp.ui.BaseViewState
import com.rv.githubapp.usecase.RepoModel
import com.rv.githubapp.usecase.RepoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoViewModel @Inject constructor(
    private val repoUseCase: RepoUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<BaseViewState<RepoModel>>(BaseViewState.LOADING)
    val state: StateFlow<BaseViewState<RepoModel>>
        get() = _state

    init {
        viewModelScope.launch {
            val repos = repoUseCase.getTopStartedRepositories()
            if (repos.isEmpty()) {
                _state.value = BaseViewState.ERROR("Error while retrieving repositories")
            } else {
                _state.value = BaseViewState.SUCCESS(repos)
            }
        }
    }
}