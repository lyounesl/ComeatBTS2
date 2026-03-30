package fr.awa.comeat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fr.awa.comeat.Modele.Modele
import fr.awa.comeat.Modele.Utilisateur


class MainActivity : AppCompatActivity() {

    private var utilisateurConnecte: Utilisateur? = null  // stockage de l'utilisateur connecté


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saisieEmail: EditText = findViewById(R.id.email)
        val saisieMdp: EditText = findViewById(R.id.mdp)
        val boutonConnecter: Button = findViewById(R.id.connecter)

        boutonConnecter.setOnClickListener {
            val email: String = saisieEmail.text.toString()
            val mdp: String = saisieMdp.text.toString()
            //Log.d( "ACT_CONN" , "Connexion : $email/$mdp")

            // ← fonction de vérification
            if (verifierConnexion(email, mdp)) {
                val intent = Intent(this, MenuRepasActivity::class.java)
                // passe l'id de l'utilisateur à l'activité suivante
                intent.putExtra("idUtilisateur", utilisateurConnecte!!.id)
                startActivity(intent)
            }
        }

        val boutonAnnuler: Button = findViewById(R.id.annuler)
        boutonAnnuler.setOnClickListener {
            saisieEmail.setText("")
            saisieMdp.setText("")
            //log.d( "ACT_CONN" , "Annulation" )
        }
    }


        fun verifierConnexion(email: String, mdp: String): Boolean {
            // Vérifier que les champs ne sont pas vides
            if (email.isEmpty() || mdp.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
                return false
            }

            // Appel de findUtilisateur() du Modele
            val utilisateur = Modele.findUtilisateur(email, mdp)

            return if (utilisateur != null) {
                utilisateurConnecte = utilisateur  // ← on stocke l'utilisateur trouvé
                true
            } else {
                Toast.makeText(this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show()
                false
            }


        }
    }
