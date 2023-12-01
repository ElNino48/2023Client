package UI;

import File.TextFileWriter;
import models.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Calculator extends JFrame{
    private JPanel mainPanel;
    private JTextField inv1Text;
    private JTextField inv2Text;
    private JTextField dsText;
    private JTextField ddText;
    private JTextField rsText;
    private JTextField rdText;
    private JButton WACCguideButton;
    private JTextField eText;
    private JTextField dText;
    private JTextField ReText;
    private JTextField RdText;
    private JTextField taxText;
    private JTextField amortizationText;
    private JTextField nopatText;
    private JTextField EVAtext;
    private JTextField pvText;
    private JButton commitButton;
    private JTextField ebitText;
    private JPanel labelPanel;
    private JPanel InvPanel;
    private JPanel dsddPanel;
    private JPanel rsrdPanel;
    private JPanel WaccPanel;
    private JPanel edReRdPanel;
    private JPanel taxAmortEbitPanel;
    private JPanel nopatEvaPanel;
    private JPanel resultPanel;
    private JTextField waccText;
    private JButton PeriodButton;
    private JComboBox companyChoose;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    private Date startPeriod;
    private Date endPeriod;
    private Company company;
    private User user;
    private List<Company> companies;
    private class ComboBoxListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            company.getIdCompany();
        }
    }
    private void addCompanyComboBox()
    {  try {
            System.out.println("GetAllCompanies");
            coos.writeObject("GetAllCompanies");
            this.companies=(List<Company>)cois.readObject();
        System.out.println(companies);
        System.out.println(user.getIdUser());

        for(int i=0;i<companies.size();i++)
            {
                if(companies.get(i).getIdUser()==user.getIdUser())
                    companyChoose.addItem(companies.get(i).getCompanyName());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    }
    public Calculator(ObjectOutputStream coos, ObjectInputStream cois, User user){
        this.coos=coos;
        this.cois=cois;
        this.user= user;
        setVisible(true);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setSize(1250,1000);
        inv1Text.setText("0");
        inv2Text.setText("0");
        dsText.setText("0");
        ddText.setText("0");
        rsText.setText("0");
        rdText.setText("0");
        waccText.setText("0");
        eText.setText("0");
        dText.setText("0");
        ReText.setText("0");
        RdText.setText("0");
        taxText.setText("0");
        amortizationText.setText("0");
        ebitText.setText("0");
        WACCguideButton.addActionListener(new WACCGuide());
        this.addWindowListener(new WindowClosing());
        commitButton.addActionListener(new CalculateEVA());
        //nameOfActionLabel.setText("Редактирование компании");
        PeriodButton.addActionListener(new SetPeriod());
        addCompanyComboBox();
        }
    public Calculator(ObjectOutputStream coos, ObjectInputStream cois, Date startPeriod, Date endPeriod, User user){
        this.coos=coos;
        this.cois=cois;
        this.user= user;
        this.startPeriod=startPeriod;
        this.endPeriod=endPeriod;
        //int idCompany = company.getIdCompany();
        setVisible(true);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
        setSize(800,800);


        inv1Text.setText("0");
        inv2Text.setText("0");
        dsText.setText("0");
        ddText.setText("0");
        rsText.setText("0");
        rdText.setText("0");
        waccText.setText("0");
        eText.setText("0");
        dText.setText("0");
        ReText.setText("0");
        RdText.setText("0");
        taxText.setText("0");
        amortizationText.setText("0");
        ebitText.setText("0");

        addCompanyComboBox();
        WACCguideButton.addActionListener(new WACCGuide());
        this.addWindowListener(new WindowClosing());
        commitButton.addActionListener(new CalculateEVA());
        //nameOfActionLabel.setText("Редактирование компании");
        PeriodButton.addActionListener(new SetPeriod());
    }
        private class WindowClosing extends WindowAdapter {
            @Override
            public void windowClosing(WindowEvent e){
                setVisible(false);
                System.out.println("Calculator window closed!");
                LoggedInWindow loggedInWindow = new LoggedInWindow(coos,cois,user);
            }
        }
    public double checkDouble(String string){
        double stringDouble=0;
        if (string.matches("^[^a-zA-Zа-яА-Я]*$")) {
//            ---------------------"^[a-zA-Z]*$"
            JOptionPane.showMessageDialog(null, "Неверный ввод! Данные должны быть числовыми");
        } else {
            stringDouble = Double.parseDouble(string.trim());
        }
        return stringDouble;
    }
//    public double checkDouble(String string,String name){
//        double stringDouble=0;
//        System.out.println("If1");
//        if (string.matches("^[a-zA-Z]*$")) {
//            System.out.println("If2");
//            if(string.matches(null)){
//                stringDouble = 0;
//            }
//            System.out.println("If3");
//            JOptionPane.showMessageDialog(null, "Неверный ввод! Данные должны быть числовыми");
//        } else {
//            stringDouble = Double.parseDouble(string.trim());
//
//            System.out.println("If4");
//        }
//
//        System.out.println("If5");
//        return stringDouble;
//    }
    private class WACCGuide implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            WACCguide waccGuide = new WACCguide();
        }
    }
    double periodSumm(double EVA,double WACC, double CapitalInv, Date  startPeriod,Date endPeriod ){
        Double PV=0.0;
        LocalDate startPeriod2 = startPeriod.toLocalDate();
        LocalDate endPeriod2 = endPeriod.toLocalDate();
        Period period = Period.between(startPeriod2, endPeriod2);
        double years = period.getYears();
        for(int i=0;i<years;i++){
            PV += EVA/Math.pow(1+WACC,years);
            System.out.println("За "+ i + " год PV= "+PV);
        }
        return PV;
    }
    private class CalculateEVA implements ActionListener
    {

        double CapitalInv=0;
        double E = 0;
        double D = 0;
        double Re = 0;
        double Rd = 0;
        double t = 0.2;
        double EBIT=0;
        double PV = 0;

        @Override
        public void actionPerformed(ActionEvent e) {

            EVAcounting evaCounting = new EVAcounting();
            ResultedCompanyNetworth pvCounting = new ResultedCompanyNetworth();
           // Company company = new Company();
            evaCounting.setOwnCapital(inv1Text.getText());
            evaCounting.setBorrowedCapital(inv2Text.getText());
            CapitalInv = checkDouble(inv1Text.getText())+checkDouble(inv2Text.getText());
            evaCounting.setCapitalInv(Double.toString(CapitalInv)); //КАПИТАЛ 1

            evaCounting.setDsPercentage(dsText.getText());
            evaCounting.setDdPercentage(ddText.getText());
            evaCounting.setRsValuePercentage(rsText.getText());
            evaCounting.setRdValuePercentage(rdText.getText());
            E = checkDouble(eText.getText());
            D = checkDouble(dText.getText());
            Re = checkDouble(ReText.getText());
            Rd = checkDouble(RdText.getText());
            t = checkDouble(taxText.getText());
            double V = E + D;
            double WACC = checkDouble(waccText.getText());
            if(waccText.getText().equals(null)){
                WACC = (E/V * Re) + (D/V * Rd *(1-t));
            }
            evaCounting.setWaccPercentage(Double.toString(WACC));

            EBIT = checkDouble(ebitText.getText()) + checkDouble(amortizationText.getText());
            evaCounting.setEBIT(ebitText.getText());
            evaCounting.setAmortization(amortizationText.getText());
            double NOPAT = EBIT - (1*t);
            evaCounting.setNOPAT(Double.toString(NOPAT));

            double EVA = NOPAT-WACC*CapitalInv;
            evaCounting.setEVA(Double.toString(EVA));
            System.out.println("PV ="+ PV +" = CapitalInv + СУММА(EVA/Math.pow(1+WACC,years))");
            PV = CapitalInv + periodSumm(EVA,WACC,CapitalInv,startPeriod,endPeriod);
            System.out.println("PV ="+ PV +" = CapitalInv + СУММА(EVA/Math.pow(1+WACC,years))");

            //try {
            nopatText.setText("NOPAT = " + NOPAT);
            nopatText.setEditable(false);
            EVAtext.setText("EVA = " + EVA);
            EVAtext.setEditable(false);
            pvText.setText(String.valueOf(PV));
            pvText.setEditable(false);
            LocalDate startPeriod2 = startPeriod.toLocalDate();
            LocalDate endPeriod2 = endPeriod.toLocalDate();
            Period period = Period.between(startPeriod2, endPeriod2);
            double years = period.getYears();
            JOptionPane.showMessageDialog(null, "Результаты на экране!");
            Finances finances=new Finances();
            finances.setPeriodEVA(years);
            finances.setEVAforPeriod(EVA);
            int id=0;
            for(int i=0;i<companies.size();i++)
            {
                if(companies.get(i).getCompanyName().equals(companyChoose.getSelectedItem()))
                   break;
                id++;
            }
            finances.setCompany(companies.get(id));
           // finances.setIdCompany(companies.get(id).getIdCompany());
           finances.setPeriodEVA(years);
            try {
                EVAcounting evAcounting=new EVAcounting();
                evAcounting.setCapitalInv(String.valueOf(CapitalInv));
                evAcounting.setRdValuePercentage(rdText.getText());
                evAcounting.setRsValuePercentage(rsText.getText());
                evAcounting.setOwnCapital(inv1Text.getText());
                evAcounting.setBorrowedCapital(inv2Text.getText());
                evAcounting.setDdPercentage(ddText.getText());
                evAcounting.setDsPercentage(dsText.getText());
                evAcounting.setWaccPercentage(waccText.getText());
                evAcounting.setEBIT(ebitText.getText());
                evAcounting.setAmortization(amortizationText.getText());
                evAcounting.setNOPAT(nopatText.getText());
                evAcounting.setEVA(EVAtext.getText());

                ResultedCompanyNetworth resultedCompanyNetworth=new ResultedCompanyNetworth();
                resultedCompanyNetworth.setPeriod(years);
                resultedCompanyNetworth.setPVValue(PV);

                coos.writeObject("SetFinances");
                coos.writeObject(finances);
                coos.writeObject("FindFinances");
                finances=(Finances) cois.readObject();
                evAcounting.setFinances(finances);
                coos.writeObject("SetEVA");
                coos.writeObject(evAcounting);
                coos.writeObject("FindEVA");
                evAcounting=(EVAcounting) cois.readObject();
                resultedCompanyNetworth.setEvacounting(evAcounting);
                coos.writeObject("SetRCN");
                coos.writeObject(resultedCompanyNetworth);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
                try {
                    List<EVAcounting> evaCountings;
                    coos.writeObject("FindEVAAll");//Use-case #9
                    evaCountings =(List<EVAcounting>) cois.readObject();
                    TextFileWriter.writeCalculationsToFile(evaCountings);

                    List<ResultedCompanyNetworth> rcns;
                    coos.writeObject("FindAllRCN"); //Use-case #16
                    rcns =(List<ResultedCompanyNetworth>) cois.readObject();
                    TextFileWriter.writePVtoFile(rcns);

                    List<Finances> financesFile;
                    coos.writeObject("FindEVAperiodAll"); //Use-case #15
                    financesFile =(List<Finances>) cois.readObject();
                    TextFileWriter.writeEVAtoFile(financesFile);

                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                    }//Use-case #9
                }
            }
    private class SetPeriod implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DateWindow dateWindow=new DateWindow(coos,cois,startPeriod,endPeriod,user);
            setVisible(false);
            System.out.println(startPeriod);
            System.out.println(endPeriod);
        }
    }
}
