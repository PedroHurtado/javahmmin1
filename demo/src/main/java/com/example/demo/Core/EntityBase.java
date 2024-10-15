package com.example.demo.Core;

import java.util.UUID;

import lombok.Getter;

@Getter
public class EntityBase {
    private UUID id;
    protected EntityBase(UUID id){
        this.id = id;
    }
    @Override
    public int hashCode() {
       return id.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityBase e){
            return e.id.equals(id);
        }
        return false;
    } 
       
}
