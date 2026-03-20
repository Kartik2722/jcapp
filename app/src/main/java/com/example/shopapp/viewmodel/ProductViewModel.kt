package com.example.shopapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.data.model.Product
import com.example.shopapp.data.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private val _productsState = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val productsState: StateFlow<UiState<List<Product>>> = _productsState

    private val _productDetailState = MutableStateFlow<UiState<Product>>(UiState.Loading)
    val productDetailState: StateFlow<UiState<Product>> = _productDetailState

    init {
        fetchProducts()
    }

    fun fetchProducts() {
        viewModelScope.launch {
            _productsState.value = UiState.Loading
            val result = repository.getAllProducts()
            _productsState.value = if (result.isSuccess) {
                UiState.Success(result.getOrThrow())
            } else {
                UiState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

    fun fetchProductById(id: Int) {
        viewModelScope.launch {
            _productDetailState.value = UiState.Loading
            val result = repository.getProductById(id)
            _productDetailState.value = if (result.isSuccess) {
                UiState.Success(result.getOrThrow())
            } else {
                UiState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }
}
