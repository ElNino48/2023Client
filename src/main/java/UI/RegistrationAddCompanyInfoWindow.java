package UI;

import File.TextFileWriter;
import models.Company;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.List;

public class RegistrationAddCompanyInfoWindow extends JFrame{
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    private User user;
    public RegistrationAddCompanyInfoWindow(ObjectOutputStream coos, ObjectInputStream cois, User user){
        setVisible(true);
        setContentPane(AddInfoPanelViaRegistration);
        this.user= user;
        setLocationRelativeTo(null);
        setSize(850,700);
        CommitButton.addActionListener(new AddInfoCompany());
        GoBackButton.addActionListener(new Back());
        this.cois = cois;
        this.coos = coos;
    }

    private JLabel InstructionsLabel;
    private JTextField NameField1;
    private JTextField DepartamentField2;
    private JTextField yearFoundation3;
    private JTextField adressField4;
    private JTextField OwnerField5;
    private JTextField UNPField6;
    private JLabel adressLabel;
    private JLabel NameLabel;
    private JLabel DepartamentLabel;
    private JLabel dateLabel;
    private JLabel OwnerLabel;
    private JLabel UNPlabel;
    private JPanel AddInfoPanelViaRegistration;
    private JButton GoBackButton;
    private JButton CommitButton;

    public void close()
    {
        setVisible(false);
    }
    private class Back implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoggedInWindow loggedInWindow = new LoggedInWindow(coos,cois, user);
            setVisible(false);
            //
        }
    }
    private class AddInfoCompany implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            //User user = new User();
            //user.setIdUser(7);
            Company company = new Company();
            company.setCompanyName(NameField1.getText());
            company.setDepartament(DepartamentField2.getText());
            company.setYearFoundation(Date.valueOf(yearFoundation3.getText()));
            company.setAdress(adressField4.getText());
            company.setOwner(OwnerField5.getText());
            company.setUNP(UNPField6.getText());
            company.setUser(user);
            try {
                coos.writeObject("AddCompany");
                coos.writeObject(company);
                LoggedInWindow loggedInWindow = new LoggedInWindow(coos,cois, user);
                setVisible(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            try {
                List<Company> companies;
                coos.writeObject("GetAllCompanies");
                companies =(List<Company>) cois.readObject();
                    TextFileWriter.writeAllCompaniesToFile(companies);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }//use case #8
        }
    }
}
