package com.redis.demo.service;

import com.redis.demo.dao.Programmer;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ProgrammerService {

    void setProgrammer(String id, String programmer);

    String getProgrammer(String id);

    void AddProgrammers(Programmer programmer);
    List<Programmer> getAllProgrammers();
    Long countofProgrammers();

    void AddProgrammersSet(Programmer... programmers);
    Set<Programmer> getAllProgrammersSet();
    boolean  isProgrammerAMember(Programmer programmer);

    void saveHash(Programmer programmer);
    void updateHash(Programmer programmer);
    Map<String,Programmer> findAllHash();
    Programmer findInHash(int id);
    void deleteHash(int id);
}
