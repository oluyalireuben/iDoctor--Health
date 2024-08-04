package com.timzowen.idoctor

import android.app.DatePickerDialog
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker

import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.timzowen.firenaselogin.MainActivity
import com.timzowen.firenaselogin.R
import com.timzowen.firenaselogin.databinding.ActivityBookAppointmentBinding
import com.timzowen.idoctor.model.BookAppointment
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBookAppointmentBinding
    private  lateinit var database : DatabaseReference

    lateinit var progressDialog : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppointmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBookAppointment.setOnClickListener {

            progressDialog = ProgressDialog(this)
            progressDialog.setMessage("Booking Appointment.....")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val fullName = binding.etAppointmentNames.text.toString()
            val nHIF = binding.etAppointmentNHIFStatus.text.toString()
            val reason = binding.etAppointmentReason.text.toString()
            val date = binding.etAppointmentDate.text.toString()
            val phone = binding.etAppointmentNumber.text.toString()

            database = FirebaseDatabase.getInstance().getReference("Appointments")

            val bookAppointment = BookAppointment(fullName,nHIF,reason,date,phone)

            database.child(fullName).setValue(bookAppointment).addOnSuccessListener {

                binding.etAppointmentNames.text.clear()
                binding.etAppointmentNHIFStatus.text.clear()
                binding.etAppointmentReason.text.clear()
                binding.etAppointmentDate.text.clear()
                binding.etAppointmentNumber.text.clear()

                Toast.makeText(this,"Session book successfully....", Toast.LENGTH_LONG).show()


                progressDialog.dismiss()

            }.addOnFailureListener {

                Toast.makeText(this,"Book again....Failed", Toast.LENGTH_LONG).show()
                progressDialog.dismiss()

            }
        }

    }

    // Menu inflater
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_profile -> startActivity(Intent(this, DoctorsProfileActivity::class.java))
            R.id.menu_home -> startActivity(Intent(this, MainActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}