package nl.brickx.data.User;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import nl.brickx.domain.Models.Gson.UserInfo.UserInfo;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalUserRepositoryService {

    @GET("getUserInfo/")
    Observable<UserInfo> getUserInfo(@Query("apiKey") String apiKey,
                                     @Query("key") String key);
}
