package model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import view.DogAPIService;

public interface DogsAPI{

    @GET("DevTides/DogsApi/master/dogs.json")
    Single<List<DogLs>> getDogs();
}
