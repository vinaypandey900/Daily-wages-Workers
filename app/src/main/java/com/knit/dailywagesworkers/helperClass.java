package com.knit.dailywagesworkers;

public class helperClass {

    String email;String password;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public helperClass(String name,String email, String password) {
        this.name=name;
        this.email = email;
        this.password = password;
    }

    public helperClass() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }






}
