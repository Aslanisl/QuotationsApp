package ru.mail.aslanisl.quotationsapp.presentation.features.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.mail.aslanisl.quotationsapp.R
import ru.mail.aslanisl.quotationsapp.network.model.SymbolModel

/**
 * Created by Ivan on 06.05.2018.
 */
class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private val items = mutableListOf<SymbolModel>()

    fun updateList(items: List<SymbolModel>?) {
        items ?: return
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.init(items[position])

    override fun getItemCount() = items.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleText = itemView.findViewById<TextView>(R.id.item_list_title)

        fun init(model: SymbolModel) {
            titleText.text = model.symbol
        }
    }
}