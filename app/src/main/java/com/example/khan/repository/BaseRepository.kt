package com.example.khan.repository

import com.example.khan.model.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    // We will use this function in all repos to handle api errors
    suspend fun <T> safeApiCall(apiToBeCalled: suspend () -> Response<T>): BaseResponse<T> {
        // Returning api response
        // wrapped in Resource class
        return withContext(Dispatchers.IO) {
            try {
                // Here we are calling api lambda
                // function that will return response
                // wrapped in Retrofit's Response class
                val response: Response<T> = apiToBeCalled()

                if (response.isSuccessful) {
                    // In case of success response we
                    // are returning Resource.Success object
                    // by passing our data in it.
                    BaseResponse.Success(data = response.body())
                } else {
                    // parsing api's own custom json error
                    // response in Response pojo
                    val errorResponse = response.errorBody()
                    BaseResponse.Error(
                        errorMessage = errorResponse.toString() ?: "Something went wrong"
                    )
                }
            } catch (e: HttpException) {
                // Returning HttpException's message
                // Wrapped in Resource.Error
                BaseResponse.Error(errorMessage = e.message ?: "Something went wrong")
            } catch (e: IOException) {
                // Returning no internet message
                // wrapped in Resource.Error
                BaseResponse.Error("Please check your network connection")
            } catch (e: Exception) {
                // Returning "Something went wrong" in case
                // of unknown error wrapped in Resource.Error
                BaseResponse.Error(errorMessage = "Something went wrong")
            }
        }
    }
}