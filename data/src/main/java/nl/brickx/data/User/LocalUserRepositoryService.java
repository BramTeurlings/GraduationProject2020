package nl.brickx.data.User;

import io.reactivex.Flowable;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.Gson.GetUserInfoResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalUserRepositoryService {

    @GET("getUserInfo/")
    Flowable<GetUserInfoResult> getUserInfo(@Query("apiKey") String apiKey,
                                               @Query("key") String key);
}
