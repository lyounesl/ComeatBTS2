package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate
import android.widget.TextView
import java.time.format.DateTimeFormatter

class ListeRepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_liste_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val boutonRetour : Button = findViewById(R.id.boutonRetour)
        boutonRetour.setOnClickListener {
            val intent = Intent(this, RechercheRepasActivity::class.java)
            startActivity( intent )
        }

        val bouton : Button = findViewById(R.id.boutonListe)
        bouton.setOnClickListener {
            val intent = Intent(this, VisuRepasActivity::class.java)
            startActivity( intent )
        }

        val specialiteRepas = intent.getStringExtra("specialite_repas")
        val dateRepas = intent.getStringExtra("date_repas")?.let { LocalDate.parse(it) }




    }
}