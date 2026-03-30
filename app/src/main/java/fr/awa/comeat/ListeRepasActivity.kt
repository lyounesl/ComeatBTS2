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
import fr.awa.comeat.Modele.Modele
import java.time.format.DateTimeFormatter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
        boutonRetour.setOnClickListener { finish() }

        val specialiteRepas = intent.getStringExtra("specialite_repas") ?: ""
        val dateRepas = intent.getStringExtra("date_repas")?.let { LocalDate.parse(it) }
        val idUtilisateur = intent.getIntExtra("idUtilisateur", -1)
        val idRepas = intent.getIntExtra("idRepas", -1)

        // Affichage de la spécialité et de la date dans des TextView
        val tvSpecialite: TextView = findViewById(R.id.tvSpecialite)
        val tvDate: TextView = findViewById(R.id.tvDate)

        tvSpecialite.text = "Spécialité : $specialiteRepas"

        // Formatage de la date pour l'affichage
        if (dateRepas != null) {
            val formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy")
            tvDate.text = "Date : ${dateRepas.format(formateur)}"
        }

        // RecyclerView avec RepasProposesAdapter uniquement
        val rvListeRepas = findViewById<RecyclerView>(R.id.rvListeRepas)
        rvListeRepas.layoutManager = LinearLayoutManager(this)



        // Récupération de la liste
        if (dateRepas != null) {
            val listeRepas =
                Modele.getRepasByDateSpecialite(specialiteRepas, dateRepas, idUtilisateur)

            val rvListeRepas = findViewById<RecyclerView>(R.id.rvListeRepas)
            // LinearLayoutManager = affichage vertical une ligne après l'autre
            rvListeRepas.layoutManager = LinearLayoutManager(this)

            if (listeRepas.isEmpty()) {
                tvDate.text = "Aucun repas trouvé"
            } else {
                // Brancher l'adapter avec la liste
                rvListeRepas.adapter = RepasAdapter(listeRepas)
            }

        }
            val rvRepas = findViewById<RecyclerView>(R.id.rvListeRepas)
            rvRepas.layoutManager = LinearLayoutManager(this)

            val adapterRepas = RepasProposesAdapter(
                Modele.getRepasByDateSpecialite(specialiteRepas, dateRepas!! , idUtilisateur)
            ) { repas ->

                val intent = Intent(this, VisuRepasActivity::class.java)
                intent.putExtra("idRepas", repas.id)
                intent.putExtra("idUtilisateur", idUtilisateur)
                startActivity(intent)

            }
            rvRepas.adapter = adapterRepas
    }
}