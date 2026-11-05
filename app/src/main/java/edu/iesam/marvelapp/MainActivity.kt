package edu.iesam.marvelapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import edu.iesam.marvelapp.features.superheroes.data.local.xml.SuperHeroXmlLocalDataSource
import edu.iesam.marvelapp.features.superheroes.domain.SuperHero

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets






        }
        // Crear un fichero SharedPreferences para verlo

        val xml = SuperHeroXmlLocalDataSource(this)
        xml.save(mutableListOf(SuperHero("0","SuperMam","super-man","https://"),
            SuperHero("1","SuperMam1","super-man1","https://"),
            SuperHero("2","SuperMam2","super-man2","https://")

        ))

        val superHero = xml.findById("2")
        Log.d(MainActivity::class.java.simpleName, superHero.toString())

    }
}