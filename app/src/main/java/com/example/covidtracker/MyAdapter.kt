package com.example.covidtracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler

class MyAdapter(private val stateList: List<Statewise>):RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var listener: SetOnClickListener
    interface SetOnClickListener{
        fun onItemClick(position: Int)
    }


    fun setListener(listener:SetOnClickListener){
           this.listener= listener
    }
    class MyViewHolder(itemView:View, listener: SetOnClickListener):RecyclerView.ViewHolder(itemView) {
        val stateName: TextView = itemView.findViewById<TextView>(R.id.tvStateName)

        init {
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.each_state,parent, false)
        return MyViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
     return stateList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentState= stateList[position]
        holder.stateName.text = currentState.state
    }
}