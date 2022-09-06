package com.monstertechno.loginsignupui.modal;

public class name {
    private  String Name,id;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public name(String name, String id) {
        Name = name;
        this.id = id;
    }
}
