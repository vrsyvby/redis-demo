package com.redis.demo.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.demo.dao.Programmer;
import com.redis.demo.service.ProgrammerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController(value = "/programmer")
public class ProgrammerController {

    @Autowired
    private ProgrammerService programmerService;


    @PostMapping("/add-programmer")
    public void setProgrammerService(@RequestBody Programmer programmer) throws JsonProcessingException {

        ObjectMapper mapper= new ObjectMapper();
        programmerService.setProgrammer(programmer.getId(), mapper.writeValueAsString(programmer));

    }


    @GetMapping("/{id}")
    public String getProgrammer(@PathVariable String id){
        return programmerService.getProgrammer(id);
    }

    @PostMapping("/add-programmers-to-list")
   public void AddProgrammers(@RequestBody Programmer programmer){

        programmerService.AddProgrammers(programmer);


    }

    @GetMapping("/get-all-programmers")
    public List<Programmer> getAllProgrammers(){
        return programmerService.getAllProgrammers();
    }

    @GetMapping("/count")
    public Long countofProgrammers(){
        return programmerService.countofProgrammers();
    }

        @PostMapping("/add-programmers-to-set")
    public void AddProgrammersSet(@RequestBody Programmer... programmers){
         programmerService.AddProgrammersSet(programmers);
    }

    @GetMapping("/set-all-programmer-members")
    Set<Programmer> getAllProgrammersSet(){
        return programmerService.getAllProgrammersSet();
    }

    @PostMapping("/is-programmer-member")
    boolean  isProgrammerAMember(@RequestBody Programmer programmer){

        return programmerService.isProgrammerAMember(programmer);
    }
    @PostMapping("/programmer-hash")
    public void saveHash(@RequestBody  Programmer programmer){
        programmerService.saveHash(programmer);
    }

    @PutMapping("/programmer-hash")
    public void updateHash(@RequestBody Programmer programmer){
        programmerService.updateHash(programmer);
    }

    @GetMapping("/programmer-hash")
    public Map<String,Programmer> findAllHash(){
        return programmerService.findAllHash();
    }

    @GetMapping("/programmer-hash/{id}")
    public Programmer findInHash(@PathVariable int id){
        return programmerService.findInHash(id);
    }

    @DeleteMapping("/delete-programmer-hash/{id}")
    public void deleteHash(@PathVariable int id){
        programmerService.deleteHash(id);
    }



}
