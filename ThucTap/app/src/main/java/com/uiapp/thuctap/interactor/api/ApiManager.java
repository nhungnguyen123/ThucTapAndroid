package com.uiapp.thuctap.interactor.api;

import com.google.gson.Gson;
import com.uiapp.thuctap.interactor.api.network.ApiCallback;
import com.uiapp.thuctap.interactor.api.network.ApiClient;
import com.uiapp.thuctap.interactor.api.network.RestCallback;
import com.uiapp.thuctap.interactor.api.network.RestError;
import com.uiapp.thuctap.interactor.api.request.GardenBodyRequest;
import com.uiapp.thuctap.interactor.api.request.VegetableBodyRequest;
import com.uiapp.thuctap.interactor.api.response.garden.AllGardenResponse;
import com.uiapp.thuctap.interactor.api.response.garden.CreateGardenResponse;
import com.uiapp.thuctap.interactor.api.response.user.AllUserResponse;
import com.uiapp.thuctap.interactor.api.response.user.LoginResponse;
import com.uiapp.thuctap.interactor.api.response.user.SignUpResponse;
import com.uiapp.thuctap.interactor.api.response.vegetable.GetAllVegetableResponse;
import com.uiapp.thuctap.interactor.api.request.UpdateGardenRequest;
import com.uiapp.thuctap.model.Customer;
import com.uiapp.thuctap.utils.LogUtils;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by hongnhung on 7/6/16.
 */
public class ApiManager {

    public ApiClient apiClient;

    @Inject
    public ApiManager(Retrofit retrofit) {
        apiClient = retrofit.create(ApiClient.class);

    }

    public void login(String email, String password,
                      final ApiCallback<LoginResponse> callback) {
        apiClient.login(email, password).enqueue(new RestCallback<LoginResponse>() {
            @Override
            public void success(LoginResponse res) {

                String login = new Gson().toJson(res);
                LogUtils.logE("Json login", login+"");
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });

    }


    public void getAllUser(final ApiCallback<AllUserResponse> callback) {
        apiClient.getallUser().enqueue(new RestCallback<AllUserResponse>() {
            @Override
            public void success(AllUserResponse res) {
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });
    }

    public void signUp(String firstName, String lastName, String displayName, String email, String password, String roles, String username, final ApiCallback<SignUpResponse> callback) {
        apiClient.register(firstName, lastName, displayName, email, password, roles, username).enqueue(new RestCallback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse res) {
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });
    }


    public void createGarden(GardenBodyRequest gardenBodyRequest, String accessToken, final ApiCallback<CreateGardenResponse> callback) {
        apiClient.createGarden(accessToken, gardenBodyRequest).enqueue(new RestCallback<CreateGardenResponse>() {
            @Override
            public void success(CreateGardenResponse res) {
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });
    }


    public void updateGarden(String idGarden, UpdateGardenRequest updateGardenRequest, String accessToken, final ApiCallback<SignUpResponse> callback) {
        apiClient.updateGarden(idGarden, updateGardenRequest, accessToken).enqueue(new RestCallback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse res) {
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });
    }


    public void deteleGarden(String idGarden, String accessToken, final ApiCallback<SignUpResponse> callback) {
        apiClient.deleteGarden(idGarden, accessToken).enqueue(new RestCallback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse res) {
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });
    }


    public void getAllGarden(String accessToken, final ApiCallback<AllGardenResponse> callback) {
        apiClient.getAllGarden(accessToken).enqueue(new RestCallback<AllGardenResponse>() {
            @Override
            public void success(AllGardenResponse res) {
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });
    }

    public void getAllGardenClient(String userId, final ApiCallback<AllGardenResponse> callback) {
        apiClient.getAllGardenForClient(userId).enqueue(new RestCallback<AllGardenResponse>() {
            @Override
            public void success(AllGardenResponse res) {
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });
    }


    public void createVegetable(String accessToken, VegetableBodyRequest vegetableBodyRequest, final ApiCallback<SignUpResponse> callback) {
        apiClient.createVegetable(accessToken, vegetableBodyRequest).enqueue(new RestCallback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse res) {
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });
    }

    public void getAllVegetable(final ApiCallback<GetAllVegetableResponse> callback) {
        apiClient.getAllVegetable().enqueue(new RestCallback<GetAllVegetableResponse>() {
            @Override
            public void success(GetAllVegetableResponse res) {
                callback.success(res);
            }

            @Override
            public void failure(RestError error) {
                callback.failure(error);
            }
        });
    }

}
