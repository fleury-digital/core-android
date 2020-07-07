package br.com.grupofleury.core.di.modules

import android.app.Application
import android.content.Context
import br.com.grupofleury.core.utils.SharedPref
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [NetworkModule::class]
)
class CoreModule{
    @Singleton
    @Provides
    fun providesSharedPref(context: Context): SharedPref = SharedPref(context, Gson())
}
