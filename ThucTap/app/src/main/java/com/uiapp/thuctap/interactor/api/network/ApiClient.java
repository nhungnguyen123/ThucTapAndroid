package com.uiapp.thuctap.interactor.api.network;

import com.uiapp.thuctap.interactor.api.request.GardenBodyRequest;
import com.uiapp.thuctap.interactor.api.request.VegetableBodyRequest;
import com.uiapp.thuctap.interactor.api.response.garden.AllGardenResponse;
import com.uiapp.thuctap.interactor.api.response.garden.CreateGardenResponse;
import com.uiapp.thuctap.interactor.api.response.user.AllUserResponse;
import com.uiapp.thuctap.interactor.api.response.user.LoginResponse;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;
import com.uiapp.thuctap.interactor.api.response.vegetable.GetAllVegetableResponse;
import com.uiapp.thuctap.interactor.api.request.UpdateGardenRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by hongnhung on 7/19/16.
 */
public interface ApiClient {


    @GET("/alluser")
    Call<AllUserResponse> getallUser();

    /**
     * API to login
     *
     * @param email
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("/khachhang")
    Call<LoginResponse> login(
            @Field("tenDichVu") String email,
            @Field("mota") String password
    );

    /**
     * API to Signup
     *
     * @param displayName
     * @param email
     * @param password
     * @param roles
     * @param username
     * @return
     */
    @FormUrlEncoded
    @POST("/register")
    Call<SignUpResponse> register(
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("displayName") String displayName,
            @Field("email") String email,
            @Field("password") String password,
            @Field("roles") String roles,
            @Field("username") String username
    );


    @POST("/garden")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<CreateGardenResponse> createGarden(
            @Header("x-access-token") String accessToken,
            @Body GardenBodyRequest gardenBodyRequest
    );


    @PUT("/garden/{gardenId}")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<SignUpResponse> updateGarden(
            @Path("gardenId") String gardenId,
            @Body UpdateGardenRequest updateGardenRequest,
            @Header("x-access-token") String accessToken
    );

    @DELETE("/garden/{gardenId}")
    Call<SignUpResponse> deleteGarden(
            @Path("gardenId") String gardenId,
            @Header("x-access-token") String accessToken
    );

    /**
     * Get All Garden of boss
     *
     * @param accessToken
     * @return
     */
    @GET("/garden")
    Call<AllGardenResponse> getAllGarden(
            @Header("x-access-token") String accessToken
    );


    /**
     * Get All Garden for
     *
     * @param userId
     * @return
     */
    @GET("/clientgardens/{userId}")
    Call<AllGardenResponse> getAllGardenForClient(
            @Path("userId") String userId
    );

    /**
     * Create vegetable :
     * unique admin create vegetable
     */

    @POST("/vegetable")
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<SignUpResponse> createVegetable(
            @Header("x-access-token") String accessToken,
            @Body VegetableBodyRequest vegetableBody
    );


    /**
     * Get All vegetable
     */

    @GET("/allvegetable")
    Call<GetAllVegetableResponse> getAllVegetable(
    );


}
