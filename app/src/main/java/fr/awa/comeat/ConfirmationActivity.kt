package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fr.awa.comeat.Modele.Modele
import java.time.format.DateTimeFormatter

class ConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Récupération des ids transmis
        val idRepas = intent.getIntExtra("idRepas", -1)
        val idUtilisateur = intent.getIntExtra("idUtilisateur", -1)

        // Récupération des objets depuis le Modèle
        val repas = Modele.getRepasById(idRepas)
        val utilisateur = Modele.getUtilisateur(idUtilisateur)

        // Affichage des infos du repas
        val tvSpecialite: TextView = findViewById(R.id.tvConfirmSpecialite)
        val tvDate: TextView = findViewById(R.id.tvConfirmDate)
        val tvHote: TextView = findViewById(R.id.tvConfirmHote)

        if (repas != null) {
            val formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            tvSpecialite.text = "Spécialité : ${repas.specialite.libelle}"
            tvDate.text = "Date : ${repas.date.format(formateur)}"
            tvHote.text = "Hôte : ${repas.hote.prenom} ${repas.hote.nom}"
        }

        // Bouton retour
        val boutonRetour: Button = findViewById(R.id.boutonRetour)
        boutonRetour.setOnClickListener {
            val intent = Intent(this, VisuRepasActivity::class.java)
            intent.putExtra("idRepas", idRepas)
            intent.putExtra("idUtilisateur", idUtilisateur)
            startActivity(intent)
        }

        // Bouton confirmer l'inscription
        val boutonConfirmer: Button = findViewById(R.id.boutonConfirmer)
        boutonConfirmer.setOnClickListener {
            if (repas != null && utilisateur != null) {

                // Vérifier s'il reste des places
                if (repas.encoreDeLaPlace()) {
                    // ✅ Inscrire l'utilisateur au repas
                    Modele.inscrireRepas(repas, utilisateur)
                    Toast.makeText(this, "Inscription confirmée !", Toast.LENGTH_SHORT).show()

                    // ✅ Naviguer vers RepasActivity pour voir le repas inscrit
                    val intent = Intent(this, RepasActivity::class.java)
                    intent.putExtra("idUtilisateur", idUtilisateur)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Plus de places disponibles !", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}