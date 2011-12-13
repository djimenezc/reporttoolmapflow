package com.mapflow.geo.common.helper.dataaccess;

public class DBDataSource {

    private String name;
    private String username;
    private String password;

    public DBDataSource(String name){
        this.name = name;
    }
    
    public DBDataSource(String name, String username, String password){
        this(name);
        this.username = username;
        this.password = password;
    }
    
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
