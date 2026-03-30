package fr.awa.comeat.Modele

import java.time.LocalDate


data class Utilisateur(
    val id: Int,
    val nom: String,
    val prenom: String,
    val email: String,
    val mdp: String
)

data class SpecialiteCulinaire(
    val id: Int,
    val libelle: String
){
    override fun toString(): String {
        return libelle
    }
}

data class Repas(
    val id: Int,
    val date: LocalDate,
    val nbCouverts: Int,
    val specialite: SpecialiteCulinaire,
    val hote: Utilisateur ,
    val convives: MutableList<Utilisateur> = mutableListOf()
){

    fun inscrire( utilisateur: Utilisateur ) {
        convives.add( utilisateur )
    }

    fun getNbPlacesLibres(): Int {
        return nbCouverts - convives.size
    }

    fun encoreDeLaPlace(): Boolean {
        if( nbCouverts > convives.size ){
            return true
        }
        return false
    }

    fun estConvive( idUtilisateur: Int ): Boolean {
        for( convive in convives ){
            if( convive.id == idUtilisateur ){
                return true
            }
        }
        return false
    }

    fun estHote( idUtilisateur: Int ): Boolean {
        if( idUtilisateur == hote.id ){
            return true
        }
        return false
    }
}


object Modele {

    private val specialites: MutableList<SpecialiteCulinaire> = mutableListOf(
        SpecialiteCulinaire( 1 , "Libanais" ) ,
        SpecialiteCulinaire( 2 , "Marocain" ) ,
        SpecialiteCulinaire( 3 , "Grec" ) ,
        SpecialiteCulinaire( 4 , "Espagnol" ) ,
        SpecialiteCulinaire( 5 , "Italien" ) ,
        SpecialiteCulinaire( 6 , "Sénégalais" ) ,
        SpecialiteCulinaire( 7 , "Inuit" ) ,
    )

    private val utilisateurs: MutableList<Utilisateur> = mutableListOf(
        Utilisateur( 1 , "LOTHBROK" , "Ragnar" , "ragnar.lothbrok@gmail.com" , "Odin@Thor" ) ,
        Utilisateur( 2 , "LOTHBROK" , "Lagertha" , "lagertha.lothbrock@gmail.com" , "Loki&Freyja" ) ,
        Utilisateur( 3 , "GRIMES" , "Rick" , "rick.grimes@gmail.com" , "azerty" ) ,
        Utilisateur( 4 , "DIXON" , "Daryl" , "daryl.dixon@gmail.com" , "azerty" ) ,
        Utilisateur( 5 , "DIXON" , "Michonne" , "michonne.dixon@gmail.com" , "azerty" ) ,
        Utilisateur( 6 , "RHEE" , "Glenn" , "glenn.rhee@gmail.com" , "azerty" ) ,
        Utilisateur( 7 , "GREENE" , "Maggie" , "maggie.greene@gmail.com" , "azerty" ) ,
        Utilisateur( 8 , "GREENE" , "Carl" , "carl.greene@gmail.com" , "azerty" ) ,
        Utilisateur( 9 , "SMITH" , "Andrea" , "andrea.smith@gmail.com" , "azerty" ) ,
        Utilisateur( 10 , "SMITH" , "Negan" , "hegan.smith@gmail.com" , "azerty" ) ,
        Utilisateur( 11 , "PORTER" , "Eugène" , "eugene.porter@gmail.com" , "azerty" ) ,
        Utilisateur( 12 , "PELETIER" , "Carole" , "carole.peletier@gmail.com" , "azerty" ) ,
        Utilisateur( 13 , "GREENE" , "Beth" , "beth@gmail.com" , "azerty" )
    )

    private val repas: MutableList<Repas> = mutableListOf(
        Repas( 1 , LocalDate.of( 2026 , 4 , 15 ) , 4 , specialites.get( 3 ) , utilisateurs.get( 1 )  ) ,
        Repas( 2 , LocalDate.of( 2026 , 4 , 16 ) , 2 , specialites.get( 0 ) , utilisateurs.get( 0 )  ) ,
        Repas( 3 , LocalDate.of( 2026 , 4 , 17 ) , 2 , specialites.get( 3 ) , utilisateurs.get( 3 )  ) ,
        Repas( 4 , LocalDate.of( 2026 , 4 , 18 ) , 13 , specialites.get( 2 ) , utilisateurs.get( 2 )  ) ,
        Repas( 5 , LocalDate.of( 2026 , 4 , 20 ) , 3 , specialites.get( 1 ) , utilisateurs.get( 1 )  ) ,
        Repas( 6 , LocalDate.of( 2026 , 4 , 20 ) , 4 , specialites.get( 4 ) , utilisateurs.get( 4 )  ) ,
        Repas( 7 , LocalDate.of( 2026 , 4 , 20 ) , 4 , specialites.get( 5 ) , utilisateurs.get( 1 )  )
    )


    init {

        repas.get(0).inscrire( utilisateurs.get( 12 ) )
        repas.get(0).inscrire( utilisateurs.get( 7 ) )
        repas.get(0).inscrire( utilisateurs.get( 3 ) )

        repas.get(1).inscrire( utilisateurs.get( 10 ) )
        repas.get(1).inscrire( utilisateurs.get( 7 ) )

        repas.get(2).inscrire( utilisateurs.get( 4 ) )

        repas.get(3).inscrire( utilisateurs.get( 5 ) )
    }


    // Méthode utilisée dans l'activité MainActivity

    fun findUtilisateur( email: String , mdp: String ): Utilisateur? {
        for( utilisateur in utilisateurs ){
            if( utilisateur.email == email && utilisateur.mdp == mdp ){
                return utilisateur
            }
        }

        return null
        // return utilisateurs.find { it.email == email && it.mdp == mdp }
    }

    fun getUtilisateur( id: Int ): Utilisateur? {
        for( utilisateur in utilisateurs ){
            if( utilisateur.id == id ){
                return utilisateur
            }
        }
        return null
    }

    fun getUtilisateurs(): List<Utilisateur> {
        return utilisateurs
    }

    fun getSpecialites(): List<SpecialiteCulinaire> {
        return specialites
    }

    fun inscrireRepas( repas: Repas , utilisateur: Utilisateur ) {
        repas.inscrire( utilisateur )
    }

    fun annulerInscription( repas: Repas , utilisateur: Utilisateur ): Boolean {
        // à implémenter
        return true ;
    }

    fun getRepas(): List<Repas> {
        return repas
    }

    // Méthode utilisée dans l'activité ListeRepasActivity

    fun getRepasByDateSpecialite( specialite: String , date: LocalDate , idUtilisateur: Int ): List<Repas> {

        val repasSelect: MutableList<Repas> = mutableListOf()

        for( unRepas in repas ){
            if( unRepas.date.isEqual( date ) && unRepas.specialite.libelle.equals( specialite ) ){
                if( unRepas.estHote( idUtilisateur ) == false && unRepas.estConvive( idUtilisateur ) == false ){
                    repasSelect.add( unRepas )
                }
            }
        }

        return repasSelect
    }

    // Méthode utilisée dans l'activité RepasActivity

    fun getSesRepas( idUtilisateur: Int ): List<Repas> {

        val repasSelect: MutableList<Repas> = mutableListOf()

        for( unRepas in repas ){
            if( unRepas.estConvive( idUtilisateur ) == true && unRepas.date.isAfter( LocalDate.now() ) ){
                repasSelect.add( unRepas )
            }
        }

        return repasSelect
    }

    fun getRepasById( id : Int ): Repas? {
        for( unRepas in repas ){
            if( unRepas.id == id ){
                return unRepas
            }
        }
        return null
    }

    fun getNbPlacesLibres( idRepas: Int ): Int? {
        val repas = getRepasById( idRepas )
        if( repas != null ){
            return repas.nbCouverts - repas.convives.size
        }
        return null
    }

    fun getRepasUtilisateurConvive( idUtilisateur: Int ): List<Repas> {

        val repasSelect: MutableList<Repas> = mutableListOf()

        for( unRepas in repas ){
            if( unRepas.estConvive( idUtilisateur ) ){
                repasSelect.add( unRepas )
            }
        }

        return repasSelect

    }
}



