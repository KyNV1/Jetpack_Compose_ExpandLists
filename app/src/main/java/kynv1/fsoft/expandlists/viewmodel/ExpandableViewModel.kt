package kynv1.fsoft.expandlists.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kynv1.fsoft.expandlists.model.SampleData

class ExpandableViewModel : ViewModel() {
    private val _cards = MutableStateFlow(listOf<SampleData>())
    val cards: StateFlow<List<SampleData>>
        get() = _cards

    private val _expandedCardList = MutableStateFlow(listOf<Int>())
    val expandedCardList: StateFlow<List<Int>>
        get() = _expandedCardList

    init {
        viewModelScope.launch(Dispatchers.Default) {
            val sampleList = arrayListOf<SampleData>()
            repeat(10) {
                sampleList += SampleData(
                    id = it + 1,
                    title = "Make ${it + 1}"
                )
            }
            _cards.emit(sampleList)
        }
    }

    fun cardArrowClick(cardId: Int) {
        _expandedCardList.value = _expandedCardList.value.toMutableList().also { list ->
            if (list.contains(cardId)) {
                list.remove(cardId)
            } else {
                list.add(cardId)
            }
        }
    }
}