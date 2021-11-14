package com.redis.demo.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redis.demo.dao.Programmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;


@Repository
public class ProgrammerServiceImpl implements ProgrammersRepo{


    private static final String REDIS_LIST_KEY="programmerslist";
    private static final String REDIS_SET_KEY="programmerset";
    private static final String REDIS_HASH_KEY="programmerhash";

    private ObjectMapper objectMapper= new ObjectMapper();

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ListOperations<String,Programmer> listOperations;

    @Autowired
    private SetOperations<String,Programmer> setOperations;

    @Autowired
    private HashOperations<String,String,Programmer> hashOperations;

    @Override
    public void setProgrammer(String id, String programmer) {

        redisTemplate.opsForValue().set(id,programmer);
        redisTemplate.expire(id,10, TimeUnit.MINUTES);

    }

    @Override
    public String getProgrammerById(String id) {

        return  (String)redisTemplate.opsForValue().get(id);
    }

    @Override
    public void AddProgrammers(Programmer programmer) {
        listOperations.leftPush(REDIS_LIST_KEY,programmer);
    }

    @Override
    public List<Programmer> getAllProgrammers() {
        return
                listOperations.range(REDIS_LIST_KEY,0,-1);


    }

    @Override
    public Long countofProgrammers() {
        return listOperations.size(REDIS_LIST_KEY);
    }

    @Override
    public void AddProgrammersSet(Programmer... prog) {

        setOperations.add(REDIS_SET_KEY,prog);

    }

    @Override
    public Set<Programmer> getAllProgrammersSet() {

       return setOperations.members(REDIS_SET_KEY);


    }

    @Override
    public boolean isProgrammerAMember(Programmer programmer) {
        return setOperations.isMember(REDIS_SET_KEY,programmer);
    }

    @Override
    public void saveHash(Programmer programmer) {
        hashOperations.put(REDIS_HASH_KEY,programmer.getId(),programmer);

    }

    @Override
    public void updateHash(Programmer programmer) {
        hashOperations.put(REDIS_HASH_KEY,programmer.getId(),programmer);
    }

    @Override
    public Map<String, Programmer> findAllHash() {
        return hashOperations.entries(REDIS_HASH_KEY);
    }

    @Override
    public Programmer findInHash(int id) {
        return hashOperations.get(REDIS_HASH_KEY,id);
    }

    @Override
    public void deleteHash(int id) {
        hashOperations.delete(REDIS_HASH_KEY,id);
    }
}
