package com.kagojernouka.utils.api.services

import com.kagojernouka.data.Const
import com.kagojernouka.data.CustomerData
import com.kagojernouka.data.DeliveryManData
import com.kagojernouka.data.SimpleResponse
import io.reactivex.Flowable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url


/*
 * Created by : 
 * <a href="https://www.github.com/ashiqursuperfly">Ashiqur Rahman</a> on 11/14/20.
*/

interface CommonApiService {

    @GET(Const.Api.ENDPOINTS.TEST)
    fun getTestResponse() : Flowable<Any>

    @GET
    fun getCustomerInfo(@Url url: String) : Flowable<CustomerData>

    @GET
    fun getDeliveryInfo(@Url url: String) : Flowable<DeliveryManData>

    @POST
    fun completeDelivery(@Url url: String, @Body params: Map<String, String>) : Flowable<SimpleResponse>

    @POST
    fun confirmPickup(@Url url: String, @Body params: Map<String, String>) : Flowable<SimpleResponse>
}