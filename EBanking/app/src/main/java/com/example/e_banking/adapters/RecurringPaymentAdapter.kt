package com.example.e_banking.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.e_banking.R
import com.example.e_banking.api.dtos.RecurringPaymentDto
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RecurringPaymentAdapter(
    private val items: MutableList<RecurringPaymentDto>,
    private val onDeleteClick: (RecurringPaymentDto) -> Unit
) : RecyclerView.Adapter<RecurringPaymentAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val beneficiary: TextView = view.findViewById(R.id.tvBeneficiary)
        val amount: TextView = view.findViewById(R.id.tvAmount)
        val frequency: TextView = view.findViewById(R.id.tvFrequency)
        val nextPayment: TextView = view.findViewById(R.id.tvNextPayment)
        val deleteButton: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recurring_payment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        "Beneficiary: ${item.receiverAccountName}".also { holder.beneficiary.text = it }
        "SUM: ${item.amount} RON".also { holder.amount.text = it }
        "Recurrency: ${item.recurrency}".also { holder.frequency.text = it }
        "Next payment date: ${formatDate(item.nextPayment)}".also { holder.nextPayment.text = it }

        holder.deleteButton.setOnClickListener {
            onDeleteClick(item)
            removeItem(position)
        }
    }

    override fun getItemCount(): Int = items.size

    private fun removeItem(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun formatDate(date: Date): String {
        val formatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        return formatter.format(date)
    }
}