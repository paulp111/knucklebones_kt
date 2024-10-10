package com.paulp1111.knucklebones

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class InstructionsPagerAdapter(context: Context) : RecyclerView.Adapter<InstructionsPagerAdapter.PageViewHolder>() {

    private val instructions = listOf(
        "Welcome to Knucklebones! In this game, you aim to outscore your opponent by placing dice strategically.",
        "Rules:\n\n1. Each player has a 3x3 grid.\n2. Players roll and place dice in a column. Placing multiple dice with the same value in a column multiplies the value.",
        "Strategy:\n\nPlace dice wisely to multiply your score or destroy your opponent's dice by matching values in the same column."
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.instruction_page, parent, false)
        return PageViewHolder(view)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        holder.bind(instructions[position])
    }

    override fun getItemCount() = instructions.size

    class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.instructionText)

        fun bind(text: String) {
            textView.text = text
        }
    }
}
