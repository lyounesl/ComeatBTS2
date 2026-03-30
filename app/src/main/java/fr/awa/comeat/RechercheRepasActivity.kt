package fr.awa.comeat


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fr.awa.comeat.Modele.Modele
import android.view.View
import android.widget.TextView
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class RechercheRepasActivity : AppCompatActivity() {

    private var libelleSpecialite: String = ""
    private var dateRepas: String = ""

    private var idUtilisateur: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recherche_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val boutonRetour: Button = findViewById(R.id.boutonRetour)
        boutonRetour.setOnClickListener { finish() }


        // Spinner de la selection de spécialité
        val spiSpecialite: Spinner = findViewById(R.id.spiRepas)
        val specialites = Modele.getSpecialites()

        val adapteur =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, specialites)

        spiSpecialite.adapter = adapteur

        spiSpecialite.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>,
                View: View?,
                position: Int,
                id: Long
            ) {
                // Récupère la spécialité sélectionnée
                libelleSpecialite = specialites[position].libelle
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }


        //selection de la date

        val btnDate: Button = findViewById( R.id.selectDate)
        val tvDate: TextView = findViewById( R.id.affDate)

        btnDate.setOnClickListener {
            val dateCourante = LocalDate.now()
            val annee = dateCourante.year
            val mois = dateCourante.monthValue - 1
            val jour = dateCourante.dayOfMonth

            val datePickerDialog = DatePickerDialog(
                this,
                { view, anneeSelect, moisSelect, jourSelect ->
                    val dateSelectionnee = LocalDate.of(
                        anneeSelect,
                        moisSelect + 1,
                        jourSelect
                    )

                    // ✅ On stocke la date au format ISO pour LocalDate.parse() plus tard
                    dateRepas = dateSelectionnee.toString()  // format "2026-03-17"

                    // Affichage formaté pour l'utilisateur
                    val formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    tvDate.text = dateSelectionnee.format(formateur)
                },
                annee, mois, jour)
            datePickerDialog.show()
        }

        val idUtilisateur = intent.getIntExtra("idUtilisateur", -1)
        val idRepas = intent.getIntExtra("idRepas", -1)

        val btnValider: Button = findViewById(R.id.btnValider)
        btnValider.setOnClickListener {
            val intent = Intent(this, ListeRepasActivity::class.java)

            intent.putExtra("specialite_repas", libelleSpecialite)
            intent.putExtra("date_repas",dateRepas.toString())
            intent.putExtra("idUtilisateur", idUtilisateur)
            intent.putExtra("idRepas", idRepas)

            startActivity(intent)
        }
    }
}

