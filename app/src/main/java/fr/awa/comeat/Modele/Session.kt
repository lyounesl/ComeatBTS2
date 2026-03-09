package fr.awa.comeat.Modele

object Session {

    private var nom : String? = null

    init {
    }

    fun ouvrir( nom : String):Boolean {
        if( this.nom == null ){
            this.nom = nom
            return true
        }
        return false
    }

    fun fermer() {
        this.nom = null
    }

    fun getUser():String?{
        return this.nom
    }

    fun estFermee():Boolean {
        if( this.nom == null ){
            return true
        }
        return false ;
    }

    fun estOuverte():Boolean {
        if( this.nom != null ){
            return true
        }
        return false ;
    }
}