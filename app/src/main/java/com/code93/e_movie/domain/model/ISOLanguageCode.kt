package com.code93.e_movie.domain.model

import android.content.Context
import com.code93.e_movie.R

enum class ISOLanguageCode(val code: String, val resource: Int) {
    ENGLISH("en", R.string.english),
    SPANISH("es", R.string.spanish),
    FRENCH("fr", R.string.french),
    GERMAN("de", R.string.german),
    ITALIAN("it", R.string.italian),
    PORTUGUESE("pt", R.string.portuguese),
    DUTCH("nl", R.string.dutch),
    RUSSIAN("ru", R.string.russian),
    CHINESE("zh", R.string.chinese),
    JAPANESE("ja", R.string.japanese),
    KOREAN("ko", R.string.korean),
    ARABIC("ar", R.string.arabic),
    HINDI("hi", R.string.hindi),
    TURKISH("tr", R.string.turkish),
    SWEDISH("sv", R.string.swedish),
    DANISH("da", R.string.danish),
    NORWEGIAN("no", R.string.norwegian),
    FINNISH("fi", R.string.finnish),
    GREEK("el", R.string.greek),
    HEBREW("he", R.string.hebrew),
    POLISH("pl", R.string.polish),
    CZECH("cs", R.string.czech),
    HUNGARIAN("hu", R.string.hungarian),
    ROMANIAN("ro", R.string.romanian),
    SLOVAK("sk", R.string.slovak),
    SLOVENE("sl", R.string.slovene),
    CROATIAN("hr", R.string.croatian),
    BULGARIAN("bg", R.string.bulgarian),
    SERBIAN("sr", R.string.serbian),
    UKRAINIAN("uk", R.string.ukrainian);

    companion object {
        fun fromCode(code: String): ISOLanguageCode? {
            return values().find { it.code == code }
        }

        fun getCodes(listIso: List<ISOLanguageCode>): List<String> {
            return listIso.map { it.code }
        }

        fun getListISOLanguageCode(listCode: List<String>): List<ISOLanguageCode> {
            val listMutable: MutableList<ISOLanguageCode> = emptyList<ISOLanguageCode>().toMutableList()
            listCode.map {
                fromCode(it)?.apply {
                    listMutable.add(this)
                }
            }
            return listMutable.toList()
        }

        fun getNames(codes: List<ISOLanguageCode>, context: Context): List<String> {
            val languages = codes.map { it.resource }
            return languages.map { context.getString(it) }
        }
    }
}

