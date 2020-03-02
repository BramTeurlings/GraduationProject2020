package nl.brickx.data.Authentication;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import nl.brickx.domain.Models.Gson.Authentication;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface LocalAuthenticationRepositoryService {

    @GET("todos/{person_id}")
    Flowable<Authentication> validateApiKey(@Path("person_id") int personId);
}
