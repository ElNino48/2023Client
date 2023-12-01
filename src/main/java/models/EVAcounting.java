package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="evacounting")
public class EVAcounting implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvacounting;
    @Column
    private String CapitalInv;
    private String OwnCapital;
    private String BorrowedCapital;
    private String DsPercentage;
    private String DdPercentage;
    private String rsValuePercentage;
    private String rdValuePercentage;
    private String WaccPercentage;
    private String EBIT;
    private String Amortization;
    private String NOPAT;
    private String EVA;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idFinances")
    private Finances finances;

    @OneToMany(mappedBy = "evacounting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ResultedCompanyNetworth> resultedCompanyNetworths;

    public int getIdEvacounting() {
        return idEvacounting;
    }

    public void setIdEvacounting(int idEvacounting) {
        this.idEvacounting = idEvacounting;
    }


    public String getCapitalInv() {
        return CapitalInv;
    }

    public void setCapitalInv(String capitalInv) {
        CapitalInv = capitalInv;
    }

    public String getOwnCapital() {
        return OwnCapital;
    }

    public void setOwnCapital(String ownCapital) {
        OwnCapital = ownCapital;
    }

    public String getBorrowedCapital() {
        return BorrowedCapital;
    }

    public void setBorrowedCapital(String borrowedCapital) {
        BorrowedCapital = borrowedCapital;
    }

    public String getDsPercentage() {
        return DsPercentage;
    }

    public void setDsPercentage(String dsPercentage) {
        DsPercentage = dsPercentage;
    }

    public String getDdPercentage() {
        return DdPercentage;
    }

    public void setDdPercentage(String ddPercentage) {
        DdPercentage = ddPercentage;
    }

    public String getRsValuePercentage() {
        return rsValuePercentage;
    }

    public void setRsValuePercentage(String rsValuePercentage) {
        this.rsValuePercentage = rsValuePercentage;
    }

    public String getRdValuePercentage() {
        return rdValuePercentage;
    }

    public void setRdValuePercentage(String rdValuePercentage) {
        this.rdValuePercentage = rdValuePercentage;
    }

    public String getWaccPercentage() {
        return WaccPercentage;
    }

    public void setWaccPercentage(String waccPercentage) {
        WaccPercentage = waccPercentage;
    }

    public String getEBIT() {
        return EBIT;
    }

    public void setEBIT(String EBIT) {
        this.EBIT = EBIT;
    }

    public String getAmortization() {
        return Amortization;
    }

    public void setAmortization(String amortization) {
        Amortization = amortization;
    }

    public String getNOPAT() {
        return NOPAT;
    }

    public void setNOPAT(String NOPAT) {
        this.NOPAT = NOPAT;
    }

    public String getEVA() {
        return EVA;
    }

    public void setEVA(String EVA) {
        this.EVA = EVA;
    }

    public Finances getFinances() {
        return finances;
    }

    public void setFinances(Finances finances) {
        this.finances = finances;
    }

    public List<ResultedCompanyNetworth> getResultedCompanyNetworths() {
        return resultedCompanyNetworths;
    }

    public void setResultedCompanyNetworths(List<ResultedCompanyNetworth> resultedCompanyNetworths) {
        this.resultedCompanyNetworths = resultedCompanyNetworths;
    }
}
