package UI;

import Diagramm.CompanyAmountDiagramm;
import Diagramm.CompanyCreateDateDiagramm;
import Diagramm.EvaDiagramm;
import Diagramm.PvDiagramm;
import models.Company;
import models.User;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class CompaniesTable extends JFrame {
    private DefaultTableModel tableModel;
    private JTable tableCompanies;
    private JButton AddButton1;
    private JButton DeleteButton3;
    private JButton RedButton2;
    private JPanel TableCompanies;
    private JButton evaDiagrButton;
    private JButton pvDiagrButton;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    private  List<Company> companies;
    private User user;
    public CompaniesTable(ObjectOutputStream coos, ObjectInputStream cois, User user) {
        this.user= user;
        setVisible(true);
        setContentPane(TableCompanies);
        setLocationRelativeTo(null);
        setSize(350, 250);
        AddButton1.addActionListener(new Add());
        RedButton2.addActionListener(new Redact());
        DeleteButton3.addActionListener(new Delete());
        evaDiagrButton.addActionListener(new EvaDiagram());
        pvDiagrButton.addActionListener(new PvDiagram());
        this.addWindowListener(new WindowClosing());
        System.out.println("Companies Table opened!");
        this.cois = cois;
        this.coos = coos;
        ShowData();
    }
    private class EvaDiagram implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("EVA diag shown:");
            EvaDiagramm demo = new EvaDiagramm("Диаграмма EVA",coos,cois);
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
        }
    }
    private class PvDiagram implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("PV diag shown:");
            PvDiagramm demo = new PvDiagramm("Диаграмма стоимости компании",coos,cois);
            demo.pack();
            RefineryUtilities.centerFrameOnScreen(demo);
            demo.setVisible(true);
        }
    }
    public void ShowData() {
        Object[] columnTitle = {"idCompany","CompanyName", "Departament", "yearFoundation","adress","idUser","owner","UNP"};
        tableModel = new DefaultTableModel(null, columnTitle);
        tableCompanies.setModel(tableModel);
        tableModel.getDataVector().removeAllElements();
        System.out.println("Connected in Action_dialog_companies");
        try {
            System.out.println("GetCompaniesByUserId entering?");
            coos.writeObject("GetCompaniesByUserId");
            coos.writeObject(user);
            System.out.println("GetCompaniesByUserId entered!");
            companies=(List<Company>)cois.readObject();
            System.out.println("GetCompaniesByUserId read");
            System.out.println(companies);
            for(Company company:companies)
            {
                System.out.println("1");
                Object[] data = {
                        company.getIdCompany(),
                        company.getCompanyName(),
                        company.getDepartament(),
                        company.getYearFoundation(),
                        company.getAdress(),
                        company.getIdUser(),
                        company.getOwner(),
                        company.getUNP(),
                };
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private class Add implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            RegistrationAddCompanyInfoWindow addNewCompany = new RegistrationAddCompanyInfoWindow(coos,cois,user);
            setVisible(false);
        }
    }
    private class Redact implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            RedactCompany redactCompany = new RedactCompany(coos,cois, companies,user);
            setVisible(false);
        }
    }
    private class Delete implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                coos.writeObject("DeleteCompany");
                coos.writeObject(companies.get(tableCompanies.getSelectedRow()));
                // Integer.valueOf(model.getValueAt(tableUsers.getSelectedRow(),0).toString()
                CompaniesTable companiesTable = new CompaniesTable(coos,cois,user);
                setVisible(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
           // RegistrationAddCompanyInfoWindow RegAddCompany = new RegistrationAddCompanyInfoWindow(coos,cois,user);
            setVisible(false);
        }
    }
    private class WindowClosing extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            setVisible(false);
            System.out.println("Companies Table closed!");
            LoggedInWindow loggedInWindow = new LoggedInWindow(coos,cois, user);
        }
    }
}
