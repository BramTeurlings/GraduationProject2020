package nl.brickx.data.Authentication;

import io.reactivex.Flowable;
import nl.brickx.domain.Models.Gson.Authentication;
import nl.brickx.domain.Models.Gson.TestAuthentication;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocalAuthenticationRepositoryService {

    @GET("ValidateApiKey/")
    Flowable<Authentication> validateApiKey(@Query("apiKey") String apiKey,
                                            @Query("key") String key);
}
