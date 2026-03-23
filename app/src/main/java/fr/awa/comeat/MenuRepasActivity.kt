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
        val idUtilisateur = intent.getIntExtra("idUtilisateur", -1) // ← remonte cette ligne en haut

        val bouton: Button = findViewById(R.id.boutonParticipation)
        bouton.setOnClickListener {
            val intent = Intent(this, RepasActivity::class.java)
            intent.putExtra("idUtilisateur", idUtilisateur) // ✅ transmet l'id
            startActivity(intent)
        }

        val boutonRetour: Button = findViewById(R.id.boutonRetour)
        boutonRetour.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val bouton2: Button = findViewById(R.id.boutonRecherche)
        bouton2.setOnClickListener {
            val intent = Intent(this, RechercheRepasActivity::class.java)
            intent.putExtra("idUtilisateur", idUtilisateur) // ✅ déjà correct
            startActivity(intent)
        }
    }
}