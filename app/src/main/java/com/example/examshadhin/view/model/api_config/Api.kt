package com.example.examshadhin.view.model.api_config
import com.example.examshadhin.view.model.CustomerDeleteResponse
import com.example.examshadhin.view.model.CustomerListResponse
import com.example.examshadhin.view.model.CustomerRegResponse
import io.reactivex.Single
import retrofit2.http.*

interface Api {
    //API END POINT
    @FormUrlEncoded
    @POST("public/v2/users")
    fun reqForRegistrationResponse(
        @Field("gender") gender: String?,
        @Field("name") name: String?,
        @Field("status") status: String?,
        @Field("email") email: String?,
    ): Single<CustomerRegResponse>
    //API END POINT
    @FormUrlEncoded
    @PUT("public/v2/users/{id}")
    fun updateUserInfo(
        @Path("id") id: Int?,
        @Field("gender") gender: String?,
        @Field("name") name: String?,
        @Field("status") status: String?,
        @Field("email") email: String?,
    ): Single<CustomerRegResponse>

    //API END POINT
    @GET("public/v2/users")
    fun getCustomerList(
    ): Single<List<CustomerListResponse>>


    //API END POINT
    @DELETE("public/v2/users/{id}")
    fun deleteUser(
        @Path("id") id: Int?
    ): Single<CustomerDeleteResponse>

}