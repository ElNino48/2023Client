package UI;

import models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminEditUser extends JFrame {
    private JPanel AdminEdit;

    private JTable tableUsers;
    private JLabel RegistrationTextField;
    private JLabel loginLabel;
    private JTextField LoginField1;
    private JLabel EmailLabel;
    private JTextField EmailField2;
    private JLabel passLabel;
    private JPasswordField passwordField1;
    private JButton CommitButton;
    private JButton GoBackDownButton;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;

    private DefaultTableModel tableModel;
    private User user;
    public AdminEditUser(ObjectOutputStream coos, ObjectInputStream cois) {
        setVisible(true);
        setContentPane(AdminEdit);
        setSize(1000,400);
        setLocationRelativeTo(null);
        CommitButton.addActionListener(new Commit());
        GoBackDownButton.addActionListener(new Back());
        this.cois = cois;
        this.coos = coos;
        ShowData();
    }
    public AdminEditUser(ObjectOutputStream coos, ObjectInputStream cois, User user) {
        setVisible(true);
        setContentPane(AdminEdit);
        this.user=user;
        setSize(1000,400);
        setLocationRelativeTo(null);
        CommitButton.addActionListener(new Commit());
        GoBackDownButton.addActionListener(new Back());
        this.cois = cois;
        this.coos = coos;
        ShowData();
    }
    public void ShowData() {
        Object[] columnTitle = {"idUser","Username","employeesNumber","email"};
        tableModel = new DefaultTableModel(null, columnTitle);
        tableUsers.setModel(tableModel);
        tableModel.getDataVector().removeAllElements();
        System.out.println("Connected in Action_dialog_users");
            System.out.println(user);
                System.out.println("Admin call User is shown");
                Object[] data = {
                        user.getIdUser(),
                        user.getLogin(),
                        user.getEmployeesNumber(),
                        user.getEmail(),
                };
                tableModel.addRow(data);
    }

    public void close()
    {
        setVisible(false);
    }
    private class Back implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            AdminControlPanel adminControlPanel = new AdminControlPanel(coos,cois);
            setVisible(false);
            //
        }
    }
    private class Commit implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            user.setLogin(LoginField1.getText());
            user.setUsername(LoginField1.getText());
            user.setEmail(EmailField2.getText());
            user.setPassword(passwordField1.getText());
            try {
                coos.writeObject("EditUser");
                coos.writeObject(user);
                AdminControlPanel adminControlPanel = new AdminControlPanel(coos,cois);
                setVisible(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
