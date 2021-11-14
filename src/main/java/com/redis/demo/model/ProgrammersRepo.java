package com.redis.demo.model;

import com.redis.demo.dao.Programmer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProgrammersRepo {

    void setProgrammer(String id, String programmer);

    String getProgrammerById(String id);

    void AddProgrammers(Programmer programmer);
    List<Programmer> getAllProgrammers();
    Long countofProgrammers();


    void AddProgrammersSet(Programmer... prog);
    Set<Programmer> getAllProgrammersSet();
    boolean  isProgrammerAMember(Programmer programmer);

    void saveHash(Programmer programmer);
    void updateHash(Programmer programmer);
    Map<String,Programmer> findAllHash();
    Programmer findInHash(int id);
    void deleteHash(int id);



}
