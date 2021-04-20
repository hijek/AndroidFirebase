package com.example.apitest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList
import android.content.Intent
import android.os.Bundle
import com.google.firebase.database.DatabaseReference


class FirebaseAdapter(private val listDataku: ArrayList<FirebaseDataClassView>): RecyclerView.Adapter<FirebaseAdapter.FirebaseViewHolder>() {
    inner class FirebaseViewHolder(myView: View):RecyclerView.ViewHolder(myView) {
        lateinit var ref: DatabaseReference
        var tvName: TextView = myView.findViewById(R.id.tv_name)
        var tvPrice: TextView = myView.findViewById(R.id.tv_price)
        var btnEdit: ImageButton = myView.findViewById(R.id.btn_data_edit)
        var btnDel: ImageButton = myView.findViewById(R.id.btn_data_del)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirebaseViewHolder {
        val viewku: View = LayoutInflater.from(parent.context).inflate(R.layout.data_item, parent, false)
        return FirebaseViewHolder(viewku)
    }

    override fun onBindViewHolder(holder: FirebaseViewHolder, position: Int) {
        val dataku = listDataku[position]
        holder.tvName.text = dataku.nama
        holder.tvPrice.text = dataku.harga.toInt().toString()
        holder.ref = FirebaseDatabase.getInstance().getReference("Minuman").child(dataku.uid)
        holder.btnEdit.setOnClickListener {
            val bundle = Bundle()
            val pindah = Intent(holder.itemView.context, UpdateActivity::class.java)
            bundle.putString("uid", dataku.uid)
            bundle.putString("nama", dataku.nama)
            bundle.putString("harga", dataku.harga.toInt().toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
        holder.btnDel.setOnClickListener {
            holder.ref.removeValue()
        }
    }

    override fun getItemCount(): Int {
        return listDataku.size
    }
}