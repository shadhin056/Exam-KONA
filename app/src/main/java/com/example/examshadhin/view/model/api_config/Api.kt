package com.example.examshadhin.view.model.api_config
import com.example.examshadhin.view.model.CustomerRegResponse
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

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
}