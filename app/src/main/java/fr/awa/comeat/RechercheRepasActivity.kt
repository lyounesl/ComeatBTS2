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
        boutonRetour.setOnClickListener {
            val intent = Intent(this, MenuRepasActivity::class.java)
            startActivity(intent)
        }


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
            val mois = dateCourante.monthValue -1
            val jour = dateCourante.dayOfMonth

            val datePickerDialog = DatePickerDialog(
                this,
                { view, anneeSelect, moisSelect, jourSelect ->
                    val dateSelecionnee = LocalDate.of(
                        anneeSelect,
                        moisSelect + 1,
                        jourSelect
                    )

                    val formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    val dateFormatee = dateSelecionnee.format(formateur)

                    tvDate.text = dateFormatee
                },
                annee, mois, jour)
                datePickerDialog.show()
                }



        val btnValider: Button = findViewById(R.id.btnValider)
        btnValider.setOnClickListener {
            val intent = Intent(this, ListeRepasActivity::class.java)

            intent.putExtra("specialite_repas", libelleSpecialite)
            intent.putExtra("date_repas",dateRepas.toString())

            startActivity(intent)
        }
    }
}

