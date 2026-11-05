package edu.iesam.marvelapp.features.superheroes.data.local.xml

import android.content.Context
import edu.iesam.marvelapp.R
import edu.iesam.marvelapp.core.cache.AppCache
import edu.iesam.marvelapp.core.serialization.JsonSerializer
import edu.iesam.marvelapp.features.superheroes.domain.SuperHero

/**
 *Conector para gestionar datos en un fichero XML a traves de sharedPreferences
 *
 */


class SuperHeroXmlLocalDataSource(
    private val context: Context,
    private  val serializer: JsonSerializer <List<SuperHero>>,
    private val appCache: AppCache
) {

    private val keySuperHero: String = "superhero"
    private val ttl: Long = 5*60*1000

    val sharedPref = context.getSharedPreferences(
        context.getString(R.string.local_source_superhero), Context.MODE_PRIVATE
    )

    fun save(superHeroes: List<SuperHero>){
        sharedPref.edit().apply{
            putString(keySuperHero, serializer.toJson(superHeroes))
            apply()
            appCache.updateTime(keySuperHero)
        }

    }

     fun findAll(): List<SuperHero> {
         if (!appCache.isExpired(keySuperHero, ttl)){
             val jsonList = sharedPref.getString(keySuperHero, null)
             jsonList?.let {
                 return serializer.fromJson(it)
             }
         }
        return emptyList()
    }

    fun findById(id: String): SuperHero?{
        return findAll().find {it.id == id }
    }

    fun deleteById(id: String){
       save(findAll().filterNot { it.id == id })
    }

    fun deleteAll(){
        sharedPref.edit().apply(){
            remove(keySuperHero)
            apply()
        }
    }



}