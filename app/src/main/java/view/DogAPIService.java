package view;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Single;
import model.DogLs;
import model.DogsAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DogAPIService {
    private final String BASE_URL = "https://raw.githubusercontent.com";
    public DogsAPI api;

    public DogAPIService(){
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(DogsAPI.class);
    }
    public Single<List<DogLs>> getDogs(){
        DogsAPI apiservice = new DogsAPI() {
            @Override
            public Single<List<DogLs>> getDogs() {
                return api.getDogs();
            }
        };
        return apiservice.getDogs();
    }
}
