package nl.brickx.brickxwms2020;

import javax.inject.Singleton;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import nl.brickx.brickxwms2020.Presentation.Login.LoginActivityModule;
import nl.brickx.brickxwms2020.Presentation.MainMenu.MainMenuActivityModule;


@Singleton
@Component(
        modules = {
            AppModule.class,
            AndroidSupportInjectionModule.class,
            MainMenuActivityModule.class,
            LoginActivityModule.class
        }
)
interface AppComponent extends AndroidInjector<BaseApplication> {

    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseApplication>{

        abstract Builder appModule(AppModule appModule);
    }
}
