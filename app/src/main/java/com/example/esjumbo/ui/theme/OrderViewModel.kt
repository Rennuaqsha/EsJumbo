package com.example.esjumbo.ui.theme

import android.icu.text.NumberFormat
import androidx.lifecycle.ViewModel
import com.example.esjumbo.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

private const val Harga_Per_CUP = 3000;
class OrderViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(OrderUiState())
    val stateUI: StateFlow<OrderUiState> = _stateUI.asStateFlow()

    fun setJumlah(jmlEsJumbo:Int){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlEsJumbo,
                harga = hitunganHarga(jumlah = jmlEsJumbo)
            )
        }
    }
    fun setRasa(rasaPilihan : String){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(rasa = rasaPilihan)
            }
        }

    fun resetOrder() {
        _stateUI.value = OrderUiState()
    }

    private fun hitunganHarga(
        jumlah: Int = _stateUI.value.jumlah,
    ):String{
        val kalkulasHarga = jumlah * Harga_Per_CUP

        return NumberFormat.getNumberInstance().format(kalkulasHarga)
    }
}