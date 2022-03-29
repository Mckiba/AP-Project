package model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -7457037016119921266L;

    protected String password ;
    protected  String email;
    protected String type;

    public User(String password, String email, String type) {

        this.password = password;
        this.email = email;
        this.type = type;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
