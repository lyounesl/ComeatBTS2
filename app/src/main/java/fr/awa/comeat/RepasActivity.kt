package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.awa.comeat.Modele.Modele

class RepasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_repas)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val boutonRetour : Button = findViewById(R.id.boutonRetour)
        boutonRetour.setOnClickListener {
            val intent = Intent(this, MenuRepasActivity::class.java)
            startActivity( intent )
        }

        val rvRepas = findViewById<RecyclerView>(R.id.rvRepas)
        rvRepas.layoutManager = LinearLayoutManager( this)

        val idUtilisateur = intent.getIntExtra("idUtilisateur", -1)
        rvRepas.adapter = RepasAdapter( Modele.getSesRepas(idUtilisateur))

    }
}