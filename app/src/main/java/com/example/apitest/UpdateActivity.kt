package com.example.apitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    lateinit var btnSubmit:Button
    lateinit var btnCancel:Button
    lateinit var etNama: EditText
    lateinit var etHarga: EditText
    lateinit var valName: String
    lateinit var valPrice: String
    lateinit var valUid: String
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        getMyData()
        declaration()
        myfunction()
    }

    fun declaration(){
        btnSubmit = findViewById(R.id.btn_up_submit)
        btnCancel = findViewById(R.id.btn_up_cancel)
        etHarga = findViewById(R.id.et_up_price)
        etNama = findViewById(R.id.et_up_name)
        ref = FirebaseDatabase.getInstance().getReference("Minuman").child(valUid)
    }


    fun getMyData(){
        val myValue = intent.extras
        if (myValue!=null){
            valName = myValue.getString("nama").toString()
            valPrice = myValue.getString("harga").toString()
            valUid = myValue.getString("uid").toString()
        }
    }

    fun myfunction(){
        etNama.setText(valName)
        etHarga.setText(valPrice)
        val pindah = Intent(this, MainActivity::class.java)
        btnSubmit.setOnClickListener {
            ref.child("nama").setValue(etNama.text.toString())
            ref.child("harga").setValue(etHarga.text.toString().toDouble())
            startActivity(pindah)
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}
