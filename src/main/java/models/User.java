package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

//import java.util.list;
@Entity
@Table(name="user")
public class User implements Serializable {
    private static final long serialVersionUID = 123456789L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idUser;
    @Column
    private String Username;
    private int employeesNumber=1;
    private String email;
    private String password;
    private String login;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idAdmin")
    private Admin admin;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Company> companies;
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public void setEmployeesNumber(int employeesNumber) {
        this.employeesNumber = employeesNumber;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
