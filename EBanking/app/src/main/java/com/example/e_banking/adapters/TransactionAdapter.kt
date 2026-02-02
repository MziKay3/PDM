package com.example.e_banking.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.e_banking.R
import com.example.e_banking.api.dtos.TransactionDto

class TransactionAdapter(
    private val items: List<TransactionDto>,
    private val currentAccountIban: String
) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sender: TextView = view.findViewById(R.id.tvSender)
        val receiver: TextView = view.findViewById(R.id.tvReceiver)
        val direction: TextView = view.findViewById(R.id.tvDirection)
        val amount: TextView = view.findViewById(R.id.tvAmount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        val isIncoming = item.receiverIban == currentAccountIban

        "From: ${item.senderIban}".also { holder.sender.text = it }
        "To: ${item.receiverIban}".also { holder.receiver.text = it }

        holder.direction.text =
            if (isIncoming) "Incoming transaction"
            else "Outgoing transaction"

        "${item.amount} RON".also { holder.amount.text = it }

        val colorRes =
            if (isIncoming) android.R.color.holo_green_dark
            else android.R.color.holo_red_dark

        holder.amount.setTextColor(
            ContextCompat.getColor(holder.itemView.context, colorRes)
        )
    }

    override fun getItemCount(): Int = items.size
}