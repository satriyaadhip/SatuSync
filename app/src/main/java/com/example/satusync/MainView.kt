package com.example.satusync

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainView : AppCompatActivity() {
    private lateinit var teganganTextView: TextView
    private lateinit var arusTextView: TextView
    private lateinit var dayaTextView: TextView
    private lateinit var frekuensiTextView: TextView
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listrik)

        // Initialize UI elements
        teganganTextView = findViewById(R.id.teganganTextView)
        arusTextView = findViewById(R.id.arusTextView)
        dayaTextView = findViewById(R.id.dayaTextView)
        frekuensiTextView = findViewById(R.id.frekTextView)

        // Initialize Firebase
        databaseReference = FirebaseDatabase.getInstance().getReference("PZEMData")

        // Read data from the "temp" node
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    Toast.makeText(
                        this@MainView, "Data ditemukan",
                        Toast.LENGTH_LONG
                    ).show()

                    val dataListrikCuy = snapshot.getValue(DataListrik::class.java)
                    if(dataListrikCuy == null){
                        Toast.makeText(
                            this@MainView, "Data tidak ditemukan",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else{
                        teganganTextView.text = "Tegangan: ${dataListrikCuy.Voltage}V"
                        arusTextView.text = "Arus: ${dataListrikCuy.Current}A"

                        val currentTemp = dataListrikCuy.Current
                        val voltTemp = dataListrikCuy.Voltage

                        val powerTemp = currentTemp!! * voltTemp!!
                        dayaTextView.text = "Daya: $powerTemp W"
//                        dayaTextView.text = "Daya: ${dataListrikCuy.Power}W"
                        frekuensiTextView.text = "Frekuensi: ${dataListrikCuy.Frequency}Hz"
                    }
                }
                else{
                    Toast.makeText(
                        this@MainView, "Data tidak ditemukan",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        // Read data from the "humid" node
    }
}