package com.example.backendsql.controller

import com.example.backendsql.model.Consult
import com.example.backendsql.repository.ConsultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/consult/office")
class ConsultController (
    @Autowired private val consultRepository : ConsultRepository
){
    @GetMapping()
    fun getAllBooks():List<Consult> = consultRepository.findAll()

    @PostMapping()
    fun createNewConsult(@Validated @RequestBody consult: Consult) : Consult
            = consultRepository.save(consult)

    @GetMapping("/{id}")
    fun getConsultById(@PathVariable(value = "id")consultId:Long)
            :ResponseEntity<Consult>{
        return consultRepository.findById(consultId).map {
                consult -> ResponseEntity.ok(consult)
        }.orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/{id}")
    fun updateConsultById(
        @PathVariable(value = "id")consultId: Long,
        @Validated @RequestBody newConsult: Consult
    ): ResponseEntity<Consult>{
        return consultRepository.findById(consultId).map {
                existingConsult ->
            val updatedConsult = existingConsult.copy(
                pacient = newConsult.pacient, medical = newConsult.medical)
            ResponseEntity.ok().body(consultRepository.save(updatedConsult))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun deleteConsultById(
        @PathVariable(value = "id")consultId: Long
    ): ResponseEntity<Void>{
        return consultRepository.findById(consultId).map {
                consult ->
            consultRepository.delete(consult)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }
    @PostMapping("/filter")
    fun getConsultByPacient(
        @RequestParam("title",required = true)pacient:String)
            :List<Consult> = consultRepository.findConsultByPacient(pacient)

}