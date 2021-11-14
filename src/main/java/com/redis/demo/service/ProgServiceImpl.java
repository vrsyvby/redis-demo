package com.redis.demo.service;

import com.redis.demo.dao.Programmer;
import com.redis.demo.model.ProgrammersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ProgServiceImpl implements ProgrammerService{


    @Autowired
    ProgrammersRepo programmerrepo;

    @Override
    public void setProgrammer(String id, String programmer) {

        programmerrepo.setProgrammer(id,programmer);

    }

    @Override
    public String getProgrammer(String id) {
        return programmerrepo.getProgrammerById(id);
    }

    @Override
    public void AddProgrammers(Programmer programmer) {

        programmerrepo.AddProgrammers(programmer);

    }

    @Override
    public List<Programmer> getAllProgrammers() {
        return programmerrepo.getAllProgrammers();
    }

    @Override
    public Long countofProgrammers() {
        return programmerrepo.countofProgrammers();
    }

    @Override
    public void AddProgrammersSet(Programmer... programmers) {
        programmerrepo.AddProgrammersSet(programmers);
    }

    @Override
    public Set<Programmer> getAllProgrammersSet() {
        return programmerrepo.getAllProgrammersSet();
    }

    @Override
    public boolean isProgrammerAMember(Programmer programmer) {
        return programmerrepo.isProgrammerAMember(programmer);
    }

    @Override
    public void saveHash(Programmer programmer) {
        programmerrepo.saveHash(programmer);
    }

    @Override
    public void updateHash(Programmer programmer) {
        programmerrepo.updateHash(programmer);
    }

    @Override
    public Map<String, Programmer> findAllHash() {
        return programmerrepo.findAllHash();
    }

    @Override
    public Programmer findInHash(int id) {
        return programmerrepo.findInHash(id);
    }

    @Override
    public void deleteHash(int id) {
        programmerrepo.deleteHash(id);
    }
}
