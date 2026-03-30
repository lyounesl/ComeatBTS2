package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MenuRepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
            }
        val idUtilisateur = intent.getIntExtra("idUtilisateur", -1) //
        val idRepas = intent.getIntExtra("idRepas", -1)

        val bouton: Button = findViewById(R.id.boutonParticipation)
        bouton.setOnClickListener {
            val intent = Intent(this, RepasActivity::class.java)
            intent.putExtra("idUtilisateur", idUtilisateur)
            intent.putExtra("idRepas", idRepas)
            startActivity(intent)
        }

        val boutonRetour: Button = findViewById(R.id.boutonRetour)
        boutonRetour.setOnClickListener { finish() }

        val bouton2: Button = findViewById(R.id.boutonRecherche)
        bouton2.setOnClickListener {
            val intent = Intent(this, RechercheRepasActivity::class.java)
            intent.putExtra("idUtilisateur", idUtilisateur)
            intent.putExtra("idRepas", idRepas)
            startActivity(intent)
        }
    }
}