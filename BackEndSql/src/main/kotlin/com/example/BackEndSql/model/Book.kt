package com.example.BackEndSql.model

import javax.persistence.*

@Entity
@Table(name = "Book")
data class Book (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long = 0,
    //@Column
    val title:String = "",
    //@OneToMany(mappedBy = "authors",cascade = arrayOf(CascadeType.ALL),fetch = FetchType.EAGER)
    val authors:String = ""
)