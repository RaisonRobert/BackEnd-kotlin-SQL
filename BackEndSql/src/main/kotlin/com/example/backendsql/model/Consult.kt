package com.example.backendsql.model

import javax.persistence.*

@Entity
@Table(name = "Consult")
data class Consult (
    //@Column
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    val pacient:String = "",
    val medical:String = ""
)