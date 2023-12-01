package models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
@Entity
@Table(name="resultedcompanynetworth")
public class ResultedCompanyNetworth implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResult;
    @Column
    private double period;
    private double PVValue;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEvacounting")
    private EVAcounting evacounting;

    public int getIdResult() {
        return idResult;
    }

    public void setIdResult(int idResult) {
        this.idResult = idResult;
    }


    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public double getPVValue() {
        return PVValue;
    }

    public void setPVValue(double PVValue) {
        this.PVValue = PVValue;
    }

    public EVAcounting getEvacounting() {
        return evacounting;
    }

    public void setEvacounting(EVAcounting evacounting) {
        this.evacounting = evacounting;
    }
}
