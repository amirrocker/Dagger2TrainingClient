package de.amirrocker.testdagger2modules.application

import dagger.Module
import dagger.Provides
import de.amirrocker.testdagger2modules.home.data.trainingSession.TrainingSessionService
import de.amirrocker.testdagger2modules.login.LoginRetrofitService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * "Apart from @Inject annotation there is a different way to provide an instance: The
 * information inside dagger modules.
 * A module is a class with a @Module annotation in which you can define dependencies with
 * the @Provides annotation
 * (documentation)
 *
 */
@Module
class NetworkModule {

    /**
     * Note: You can use the @Provides annotation in Dagger modules to tell Dagger how to
     * provide classes that your project doesn't own (e.g. an instance of Retrofit).
     *
     * Totally unecessary comments!
     * What I need to know is how to start the mock server!
     *
     * To run the http_fake_backend go to the Tools dir (c:/Tools/)
     * and navigate to the http_fake_backend folder. Once inside run
     *
     * npm run start:dev
     *
     * to start a development server.
     *
     */
    @Provides
    fun provideLoginService(): LoginRetrofitService {
        val httpClient:OkHttpClient.Builder = OkHttpClient.Builder()
        return Retrofit.Builder()
            .baseUrl("http://DESKTOP-F8S5M89:8081")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
            .create(LoginRetrofitService::class.java)

    }

    /**
     * Note: You can use the @Provides annotation in Dagger modules to tell Dagger how to
     * provide classes that your project doesn't own (e.g. an instance of Retrofit).
     */
    @Provides
    fun provideTrainingService(): TrainingSessionService {
        val httpClient:OkHttpClient.Builder = OkHttpClient.Builder()
        return Retrofit.Builder()
            .baseUrl("http://DESKTOP-F8S5M89:8081/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
            .create(TrainingSessionService::class.java)

    }

}