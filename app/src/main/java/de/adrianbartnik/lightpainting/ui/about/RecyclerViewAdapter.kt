package de.adrianbartnik.lightpainting.ui.about

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.adrianbartnik.lightpainting.R


class RecyclerViewAdapter(var context: Context, var items: List<LibraryItem>, internal var listener: OnClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.card_view_library_name) as TextView
        val creator: TextView = view.findViewById(R.id.card_view_library_creator) as TextView
        val version: TextView = view.findViewById(R.id.card_view_library_version) as TextView
        val description: TextView = view.findViewById(R.id.card_view_library_description) as TextView
        val license: TextView = view.findViewById(R.id.card_view_library_license) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {

        val viewHolder = LayoutInflater.from(context).inflate(R.layout.card_view_library, parent, false)

        return ViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val (name, creator, version, description, license, url) = items[position]
        holder.name.text = name
        holder.description.text = description
        holder.version.text = version
        holder.license.text = license
        holder.creator.text = creator
        holder.view.setOnClickListener { listener.onLibraryClick(url) }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface OnClickListener {
        fun onLibraryClick(url: String)
    }

    data class LibraryItem(val name: String,
                           val creator: String,
                           val version: String,
                           val description: String,
                           val license: String,
                           val url: String)
}