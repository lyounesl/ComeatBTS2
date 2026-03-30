package fr.awa.comeat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.awa.comeat.Modele.Repas
import java.time.format.DateTimeFormatter

//ViewHolder pour chaque item
class RepasAdapter( private val lesRepas: List<Repas>) :
RecyclerView.Adapter< RepasAdapter.RepasViewHolder >(){
    class RepasViewHolder(view: View) : RecyclerView.ViewHolder( view ) {
        val tvDate: TextView = view.findViewById(R.id.TextViewDate)
        val tvSpecialite: TextView = view.findViewById(R.id.TextViewSpecialite)
    }

    //Création d'un nouvel item (ViewHolder)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepasViewHolder{
        val view = LayoutInflater.from( parent.context )
            .inflate( R.layout.item_repas , parent, false)
        return RepasViewHolder( view )
    }

    //Lier les données au ViewHolder
    override fun onBindViewHolder( holder: RepasViewHolder, position: Int){
        val repas = lesRepas.get( position )
        val formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        holder.tvDate.text = repas.date.format( formateur )
        holder.tvSpecialite.text = repas.specialite.libelle
    }

    override fun getItemCount(): Int {
        return lesRepas.size
    }

}