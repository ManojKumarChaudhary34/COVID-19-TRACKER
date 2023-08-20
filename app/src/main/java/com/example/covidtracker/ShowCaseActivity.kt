package com.example.covidtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.covidtracker.databinding.ActivityShowCaseBinding

class ShowCaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowCaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShowCaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.districtName.text= intent.getStringExtra("district")
        binding.activeCaseId.text= intent.getStringExtra("active")
        binding.confirmedCaseId.text= intent.getStringExtra("confirmed")
        binding.deathCaseId.text= intent.getStringExtra("deaths")
        binding.recoveredCaseId.text= intent.getStringExtra("recovered")
        binding.deltaConfirmedId.text= intent.getStringExtra("delta_confirmed")
        binding.deltaRecoveredId.text= intent.getStringExtra("delta_recovered")
        binding.deltaDeathsId.text= intent.getStringExtra("delta_death")
    }
}