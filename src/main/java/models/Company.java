package models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int idCompany;
    @Column
    private String CompanyName;
    private String Departament;
    private Date yearFoundation;
    private String adress;
    private String owner;
    private String UNP;
    //private int idUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="idUser")
    private User user;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Finances> finances;
    public int getIdUser(){
        return user.getIdUser();
    }


    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getDepartament() {
        return Departament;
    }

    public void setDepartament(String departament) {
        Departament = departament;
    }

    public Date getYearFoundation() {
        return yearFoundation;
    }

    public void setYearFoundation(Date yearFoundation) {
        this.yearFoundation = yearFoundation;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUNP() {
        return UNP;
    }

    public void setUNP(String UNP) {
        this.UNP = UNP;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public List<Finances> getFinances() {
        return finances;
    }

    public void setFinances(List<Finances> finances) {
        finances = finances;
    }
}
