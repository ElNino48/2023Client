package UI;
import models.Company;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.util.List;

public class RedactCompany extends JFrame {
    private User user;
    private JButton addButton;
    private JButton backButton;
    private JPanel AddEditPanel;
    private JLabel nameOfActionLabel;
    private JPanel changeField;
    private JTextField companyName;
    private JTextField DepartamentField;
    private JTextField dateField;
    private JTextField adressField;
    private JTextField ownerField;
    private JComboBox dropBox;
    private JPanel add;
    private JPanel drop;
    private JTextField unpField;
    private JLabel InvTextField;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    private List<Company> companies;
    private Company company;

    private class ComboBoxListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            companyName.setText(companies.get(dropBox.getSelectedIndex()).getCompanyName());
            DepartamentField.setText(companies.get(dropBox.getSelectedIndex()).getDepartament());
            dateField.setText(String.valueOf(companies.get(dropBox.getSelectedIndex()).getYearFoundation()));
            adressField.setText(companies.get(dropBox.getSelectedIndex()).getAdress());
            ownerField .setText(companies.get(dropBox.getSelectedIndex()).getOwner());
            unpField.setText(companies.get(dropBox.getSelectedIndex()).getUNP());
        }
    }
    private void addCompanyComboBox()
    {
        for(int i=0;i<companies.size();i++)
        {
            dropBox.addItem(companies.get(i).getCompanyName());
        }
    }
    public RedactCompany(ObjectOutputStream coos, ObjectInputStream cois,List<Company> companies,User user)
    {
        this.coos=coos;
        this.cois=cois;
        this.companies=companies;
        this.user= user;
        setVisible(true);
        setContentPane(AddEditPanel);
        setLocationRelativeTo(null);
        setSize(500,300);
        nameOfActionLabel.setText("Редактирование компании");
        addButton.setText("Редактировать");
        addButton.addActionListener(new EditAction());
        this.addWindowListener(new WindowClosing());
        backButton.addActionListener(new backAction());
        addCompanyComboBox();
//        loginField.setText(company.getCompanyName());
//        surnameField.setText(company.getDepartament());
//        nameField.setText(company.getYearFoundation().toString());//Дата!
//        telNumberField.setText(company.getAdress());
    }
    private class EditAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            Company company =companies.get(dropBox.getSelectedIndex());
            //company.setIdCompany(companies.get(dropBox.getSelectedIndex()).getIdCompany());
            company.getUser().setIdUser(companies.get(dropBox.getSelectedIndex()).getIdUser());
            company.setCompanyName(companyName.getText());
            company.setDepartament(DepartamentField.getText());
            company.setYearFoundation(Date.valueOf(dateField.getText()));//Дата
            company.setAdress(adressField.getText());
            company.setOwner(ownerField .getText());
            company.setUNP(unpField.getText());
            try {
                coos.writeObject("EditCompany");
                coos.writeObject(company);
                CompaniesTable companiesTable = new CompaniesTable(coos, cois, user);
                JOptionPane.showMessageDialog(null, "Изменения применены успешно!");
                setVisible(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private class backAction implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
        }
    }
    private class WindowClosing extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            setVisible(false);
            System.out.println("Redact company window closed!");
            LoggedInWindow loggedInWindow = new LoggedInWindow(coos,cois,user);
        }
    }
}