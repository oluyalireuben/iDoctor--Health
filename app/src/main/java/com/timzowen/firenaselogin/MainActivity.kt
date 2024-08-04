package com.timzowen.firenaselogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DatabaseReference
import com.timzowen.firenaselogin.databinding.ActivityMainBinding
import com.timzowen.idoctor.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var database : DatabaseReference

     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         binding.cardEmergency.setOnClickListener {
             startActivity(Intent(this,EmergencyActivity::class.java))
         }
         binding.cardCalendar.setOnClickListener {
             startActivity(Intent(this,CalenderActivity::class.java))
         }
         binding.cardLegalSupport.setOnClickListener {
             startActivity(Intent(this,LegalSupportActivity::class.java))
         }
         binding.cardIDoctor.setOnClickListener {
             startActivity(Intent(this,DoctorsProfileActivity::class.java))
         }
         binding.cardLocateMedication.setOnClickListener {
             startActivity(Intent(this,HospitalLocationActivity::class.java))
         }
         binding.cardGroupTherapy.setOnClickListener {
             startActivity(Intent(this,GroupTherapyActivity::class.java))
         }

    }
}