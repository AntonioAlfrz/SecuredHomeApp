package proyectos.antonio.securedhome;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Antonio on 09/10/2016.
 */

public interface MyAPI {
    @GET("api/test")
    Call<APIMessage> testAPI();

    @POST("api/photo")
    Call<APIMessage> takePhoto();
}
