package com.rv.githubapp.ui.contributors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rv.githubapp.ui.BaseViewState
import com.rv.githubapp.usecase.ContributorModel
import com.rv.githubapp.usecase.ContributorsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContributorViewModel @Inject constructor(
    private val contributorsUseCase: ContributorsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<BaseViewState<ContributorModel>>(BaseViewState.LOADING)
    val state: StateFlow<BaseViewState<ContributorModel>>
        get() = _state

    fun loadContributors(owner: String, repo: String) {
        viewModelScope.launch {
            val contributors = contributorsUseCase.getContributors(owner, repo)
            if (contributors.isEmpty()) {
                _state.value = BaseViewState.ERROR("Error while retrieving contributors")
            } else {
                _state.value = BaseViewState.SUCCESS(contributors)
            }
        }
    }
}