package com.hengvichet.myapplication;

/**
 * Created by Vichet on 07/01/2016.
 */
public class Name_List {
    private String name, ip, type;

    public Name_List() {
    }
    public Name_List(String name, String ip, String type) {
        this.name = name;
        this.ip = ip;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
