package com.example.backendsql.repository

import com.example.backendsql.model.Consult
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ConsultRepository : JpaRepository<Consult, Long> {
    @Query("SELECT b from Consult b where b.pacient = :pacient")
    fun findConsultByPacient(@Param("pacient")pacient:String):MutableList<Consult>
}