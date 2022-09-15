package com.ekenya.rnd.mycards.utils

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

/**
 * GENERIC TYPES
 * This is a generic function and that means it can work with any type of data,
 * @param CacheEntity is the data type loaded the local cache. Can be any thing, a list or any object.
 * @param ApiResponse is the data type loaded from the network. Can be any thing, a list or any object.
 * @see networkBoundResource
 *
 * ARGUMENT PARAMETERS
 * This function takes in four argument parameters which are functions
 *
 * NOTE!!! -> all the parameters are function implementations of the following pieces of logic
 *
 * @param queryDao is a function that loads data from your local cache and returns a flow of your specified data type <ResultType>
 * @return Flow<ResultType>
 *
 * @param fetchApi is a function, a suspend function, that loads data from your rest api and returns an object of <ApiResponse>
 * @return <ApiResponse>
 * @param saveFetchResult is a function that just takes in <ApiResponse> (The data type got from the network) and saves it in the local cache.
 * @return Unit
 *
 * @param shouldFetch is function that has the logic to whether the algorithm should make a networking call or not.
 * In this case, this function takes in data loaded from @param query and determines whether to make a networking call or not.
 * This can vary with your implementation however, say fetch depending on the last time you made a networking call....e.t.c.
 *
 * @return Boolean
 *
 */

inline fun <CacheEntity, ApiResponse> networkBoundResource(
    crossinline queryDao: () -> Flow<CacheEntity>,
    crossinline fetchApi: suspend () -> ApiResponse,
    crossinline saveFetchResult: suspend (ApiResponse) -> Unit,
    crossinline shouldFetch: (CacheEntity) -> Boolean = { true }
) = flow {

    //First step, fetch data from the local cache
    val data = queryDao().first()

    //Dispatch a message to the UI that you're doing some background work
    emit(Resource.Loading(data))

    //If shouldFetch returns true,
    val resource = if (shouldFetch(data)) {

        try {

            //make a networking call
            val resultType = fetchApi()

            //save it to the database
            saveFetchResult(resultType)

            //Now fetch data again from the database and Dispatch it to the UI
            queryDao().map { Resource.Success(it) }

        } catch (throwable: Throwable) {

            //Dispatch any error emitted to the UI, plus data emmited from the Database
            queryDao().map { Resource.Error(throwable, it) }

        }

        //If should fetch returned false
    } else {
        //Make a query to the database and Dispatch it to the UI.
        queryDao().map { Resource.Success(it) }
    }

    delay(1500L)

    //Emit the resource variable
    emitAll(resource)
}