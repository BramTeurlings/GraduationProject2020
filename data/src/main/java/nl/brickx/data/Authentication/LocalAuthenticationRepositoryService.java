package nl.brickx.data.Authentication;

import io.reactivex.Single;
import nl.brickx.domain.Models.Gson.Authentication;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LocalAuthenticationRepositoryService {

    @GET("apiKey/{api_key}")
    Single<Authentication> validateApiKey(@Query("api_key") String apiKey);
}
