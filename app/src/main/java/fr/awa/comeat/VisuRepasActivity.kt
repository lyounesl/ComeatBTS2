package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import fr.awa.comeat.Modele.Modele
import java.time.format.DateTimeFormatter

class VisuRepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_visu_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Récupération des données de l'Intent
        val idRepas = intent.getIntExtra("idRepas", -1)
        val idUtilisateur = intent.getIntExtra("idUtilisateur", -1)

        // Récupération du repas depuis le Modèle
        val repas = Modele.getRepasById(idRepas)

        // Affichage des données
        val tvSpecialite: TextView = findViewById(R.id.tvRepasSpecialite)
        val tvDate: TextView = findViewById(R.id.tvRepasDate)
        val tvHote: TextView = findViewById(R.id.tvRepasHote)
        val tvPlaces: TextView = findViewById(R.id.tvRepasPlaces)

        if (repas != null) {
            val formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            tvSpecialite.text = "Spécialité : ${repas.specialite.libelle}"
            tvDate.text = "Date : ${repas.date.format(formateur)}"
            tvHote.text = "Hôte : ${repas.hote.prenom} ${repas.hote.nom}"
            tvPlaces.text = "Places libres : ${repas.getNbPlacesLibres()}"
        }

        val boutonRetour: Button = findViewById(R.id.boutonRetour)
        boutonRetour.setOnClickListener {
            val intent = Intent(this, ListeRepasActivity::class.java)
            startActivity(intent)
        }

        val bouton: Button = findViewById(R.id.boutonParticiperAuRepas)
        bouton.setOnClickListener {
            val intent = Intent(this, ConfirmationActivity::class.java)
            intent.putExtra("idRepas", idRepas)         // ✅ transmet l'id du repas
            intent.putExtra("idUtilisateur", idUtilisateur) // ✅ transmet l'id utilisateur
            startActivity(intent)
        }
    }
}