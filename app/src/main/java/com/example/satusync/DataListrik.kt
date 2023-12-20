package com.example.satusync

import java.util.UUID

data class DataListrik(
    val Current: Float? = null,
    val Energy: Float? = null,
    val Frequency: Float? = null,
    val Power: Float? = null,
    val PowerFactor: Float? = null,
    val Voltage: Float? = null
)