package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="finances")
public class Finances implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFinances;
    @Column
    private double periodEVA;
    private double EVAforPeriod;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCompany")
    private Company company;
    @OneToMany(mappedBy = "finances", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EVAcounting> evAcountings;
    public Finances() {
    }
    public int getIdFinances() {
        return idFinances;
    }

    public void setIdFinances(int idFinances) {
        this.idFinances = idFinances;
    }

    public int getIdCompany() {
        return getCompany().getIdCompany();
    }

    public void setIdCompany(int idCompany) {
        this.getCompany().setIdCompany(idCompany);
    }

    public double getPeriodEVA() {
        return periodEVA;
    }

    public void setPeriodEVA(double periodEVA) {
        this.periodEVA = periodEVA;
    }

    public double getEVAforPeriod() {
        return EVAforPeriod;
    }

    public void setEVAforPeriod(double EVAforPeriod) {
        this.EVAforPeriod = EVAforPeriod;
    }


    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<EVAcounting> getEvAcountings() {
        return evAcountings;
    }

    public void setEvAcountings(List<EVAcounting> evAcountings) {
        this.evAcountings = evAcountings;
    }
}
