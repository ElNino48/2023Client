package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//import java.util.list;
@Entity
@Table(name="admin")
public class Admin implements Serializable {
    private static final long serialVersionUID = 123456789L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idAdmin;
    @Column
    private String username;
    private String role;
    private String password;
    private String login;
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;
    public int getIdAdmin() {
        return idAdmin=1;
    }
    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        username = username;
    }
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
