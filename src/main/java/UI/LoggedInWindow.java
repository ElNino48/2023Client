package UI;

import File.TextFileWriter;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class LoggedInWindow extends JFrame {
    private JPanel ToDoPanel;
    private JLabel RegistrationTextField;
    private JLabel loginLabel;
    private JLabel EmailLabel;
    private JLabel passLabel;
    private JLabel passConfirmLabel;
    private JButton AddCompanyButton1;
    private JButton ListCompainesButton2;
    private JButton LearnAboutButton3;
    private JButton DoCountingButton4;
    private JButton EndButton;
    private JButton writeToFileButton;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    private User user;
    private List<User> users;
    public LoggedInWindow(ObjectOutputStream coos, ObjectInputStream cois, User user){
        setVisible(true);
        this.user= user;
        System.out.println("logged in userID = "+ user.getIdUser());
        setContentPane(ToDoPanel);
        setLocationRelativeTo(null);
        setSize(350,250);
        AddCompanyButton1.addActionListener(new AddCompany());
        ListCompainesButton2.addActionListener(new ShowListCompanies());
        LearnAboutButton3.addActionListener(new Learn());
        DoCountingButton4.addActionListener(new DoCounting());
        EndButton.addActionListener(new End());
        writeToFileButton.addActionListener(new File());
        this.cois = cois;
        this.coos = coos;
    }
    public void close()
    {
        setVisible(false);
    }
    private class File implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            try {
                List<User> users;
                coos.writeObject("GetAllUsers");
                users =(List<User>) cois.readObject();
                TextFileWriter.writeAllUsersToFile(users);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    private class End implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoggingInWindow loggingInWindow = new LoggingInWindow(coos,cois);
            setVisible(false);
        }
    }
    private class AddCompany implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            RegistrationAddCompanyInfoWindow RegAddCompany = new RegistrationAddCompanyInfoWindow(coos,cois,user);
            setVisible(false);
        }
    }
    private class ShowListCompanies implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            CompaniesTable companiesTable = new CompaniesTable(coos,cois, user);
            setVisible(false);
        }
    }
    private class Learn implements ActionListener
    {
        //Сделать класс Learn из методички
        @Override
        public void actionPerformed(ActionEvent e) {
            FinanaceGuide guide = new FinanaceGuide(user);
            setVisible(false);
        }
    }
    private class DoCounting implements ActionListener
    {
        //Сделать интерактивные рассчёты
        @Override
        public void actionPerformed(ActionEvent e) {
            Calculator calculator = new Calculator(coos,cois, user);
            setVisible(false);
//            User user = new User();
//            user.setUsername(LoginField1.getText());
//            user.setEmail(EmailField2.getText());
//            user.setPassword(passwordField1.getText());
//            try {
//                coos.writeObject("Registration");
//                coos.writeObject(user);
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
            //
        }
    }
}