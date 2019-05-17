package HeroApi;

import model.Heromodel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface HeroAPI {

    @POST("heroes")
    Call<Void> registerHero(@Body Heromodel hero);

}
