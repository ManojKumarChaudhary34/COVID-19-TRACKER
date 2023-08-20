package com.example.covidtracker

data class CovidDataClass(
    val cases_time_series: List<CasesTimeSery>,
    val statewise: List<Statewise>,
    val tested: List<Tested>
)