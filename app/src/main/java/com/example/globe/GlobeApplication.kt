package com.example.globe

import android.app.Application
import com.example.globe.data.db.NewsDatabase
import com.example.globe.data.network.Api
import com.example.globe.data.network.ConnectivityInterceptorImpl
import com.example.globe.data.network.NewsDataSource
import com.example.globe.data.network.NewsDataSourceImpl
import com.example.globe.data.provider.SettingPreferences
import com.example.globe.data.repository.NewsRepository
import com.example.globe.data.repository.NewsRepositoryImpl
import com.example.globe.ui.headlines.EverythingViewModelFactory
import com.example.globe.ui.headlines.HeadlineViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class GlobeApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@GlobeApplication))

        bind() from singleton { NewsDatabase(instance()) }
        bind() from singleton { instance<NewsDatabase>().newsDao() }
        bind() from provider { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { Api(instance()) }
        bind<NewsDataSource>() with singleton { NewsDataSourceImpl(instance()) }
        //bind() from singleton { NewsRepositoryImpl(instance(), instance()) }
        bind<NewsRepository>() with singleton { NewsRepositoryImpl(instance(), instance()) }
        bind() from provider { HeadlineViewModelFactory(instance()) }
        bind() from provider { EverythingViewModelFactory(instance()) }

    }

    public val URL ="URL"

    val country_list = mapOf<String, String>(
        "Argentina" to "ar",
        "Australia" to "au",
        "Austria" to "at",
        "Belgium" to "be",
        "Brazil" to "br",
        "Bulgaria" to "bg",
        "Canada" to "ca",
        "China" to "cn",
        "Colombia" to "co",
        "Cuba" to "cu",
        "Czech Republic" to "cz",
        "Egypt" to "eg",
        "France" to "fr",
        "Germany" to "de",
        "Greece" to "gr",
        "Hong Kong" to "hk",
        "Hungary" to "hu",
        "India" to "in",
        "Indonesia" to "id",
        "Ireland" to "ie",
        "Israel" to "il",
        "Italy" to "it",
        "Japan" to "jp",
        "Latvia" to "lv",
        "Lithuania" to "lt",
        "Malaysia" to "my",
        "Mexico" to "mx",
        "Morocco" to "ma",
        "Netherlands" to "nl",
        "New Zealand" to "nz",
        "Nigeria" to "ng",
        "Norway" to "no",
        "Philippines" to "ph",
        "Poland" to "pl",
        "Portugal" to "pt",
        "Romania" to "ro",
        "Russia" to "ru",
        "Saudi Arabia" to "sa",
        "Serbia" to "rs",
        "Singapore" to "sg",
        "Slovakia" to "sk",
        "Slovenia" to "si",
        "South Africa" to "za",
        "South Korea" to "kr",
        "Sweden" to "se",
        "Switzerland" to "ch",
        "Taiwan" to "tw",
        "Thailand" to "th",
        "Turkey" to "tr",
        "UAE" to "ae",
        "Ukraine" to "ua",
        "United Kingdom" to "gb",
        "United States" to "us",
        "venezuela" to "ve"
    )

    val language_list = mapOf<String, String>(
        "Arabic" to "ar",
        "German" to "de",
        "English" to "en",
        "Spanish" to "es",
        "French" to "fr",
        "Hebrew" to "he",
        "Italian" to "it",
        "Dutch" to "nl",
        "Portuguese" to "pt",
        "Russian" to "ru",
        "Chinese" to "zh"
    )

    override fun onCreate() {
        super.onCreate()
        SettingPreferences.init(this)
    }
}

