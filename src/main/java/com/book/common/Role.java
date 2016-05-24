package com.book.common;

/**
 * Created by admin on 2016/4/25.
 */
public enum Role {
    ADMIN("admin"),SELLER("seller"),BUYERS("buyers"),VISITOR("visitor");

    private String value;

    private Role(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
    public static Role get(String value){
        for(Role role : values()){
            if(role.getValue().equalsIgnoreCase(value)){
                return role;
            }
        }

        return null;
    }

}
