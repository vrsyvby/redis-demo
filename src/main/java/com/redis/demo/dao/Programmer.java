package com.redis.demo.dao;

import org.springframework.core.serializer.Serializer;

import java.io.Serializable;
import java.util.Objects;

public class Programmer implements Serializable  {

    private String id;
    private String name;
    private String company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Programmer that = (Programmer) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(company, that.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, company);
    }

    public Programmer(String id, String name, String compnany){

        this.id
                = id;
        this.company=company;
        this.name=name;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Programmer(){

    }


}
