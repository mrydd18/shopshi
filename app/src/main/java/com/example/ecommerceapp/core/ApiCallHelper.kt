package com.example.ecommerceapp.core

import android.util.Log
import retrofit2.Response

object ApiCallHelper {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): OperationStatus<T> {
        return try {
            val result = apiCall.invoke()
            OperationStatus.Success(result.body()!!)
        } catch (e: Exception) {
            Log.d("api call helper", "Binding Product: ")
            OperationStatus.Failure(e)
        }
    }

}