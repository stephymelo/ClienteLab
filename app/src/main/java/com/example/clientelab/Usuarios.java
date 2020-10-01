package com.example.clientelab;

public class Usuarios {
    private String user,pass,id;

    public Usuarios(String user,String pass,String id){
        this.user=user;
        this.pass=pass;
        this.id=id;

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
