package com.example.coroutinewithpicasso.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinewithpicasso.Model.RecyclerData
import com.example.coroutinewithpicasso.R
import com.example.coroutinewithpicasso.databinding.RecyclerListBinding
import com.squareup.picasso.Picasso

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){



    init {

    }
        var items = ArrayList<RecyclerData>()


    fun setUpdateData(items : ArrayList<RecyclerData>){
        this.items = items
        notifyDataSetChanged()
    }



    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.iamgeVIew)
        val textTitle =  view.findViewById<TextView>(R.id.tvTitle)
        val textContent =  view.findViewById<TextView>(R.id.tvContent)

        fun bind(data : RecyclerData){

            textTitle.text = data.name
            textContent.text = data.description

            val url = data.owner.avatar_url

            Picasso.get()
                .load(url)
                .into(imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
       return items.size
    }
}