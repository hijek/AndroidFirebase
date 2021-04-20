package com.example.apitest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddActivity : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etNama: EditText
    lateinit var etHarga: EditText
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        declaration()
        myfunction()
    }
    fun declaration(){
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etHarga = findViewById(R.id.et_add_price)
        etNama = findViewById(R.id.et_add_name)
        ref = FirebaseDatabase.getInstance().getReference("Minuman")
    }
    fun myfunction(){
        val pindah = Intent(this, MainActivity::class.java)
        val arrayKu = FirebaseDataClassAdd()
        btnSubmit.setOnClickListener {
            arrayKu.nama = etNama.text.toString()
            arrayKu.harga = etHarga.text.toString().toDouble()
            val taskPush = ref.push()
            taskPush.setValue(arrayKu)
            startActivity(pindah)
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}