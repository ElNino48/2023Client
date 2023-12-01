package UI;

import models.Admin;
import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoggingInWindow extends JFrame {
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    public LoggingInWindow(ObjectOutputStream coos, ObjectInputStream cois){
        setVisible(true);
        setContentPane(LogInPanel);
        setLocationRelativeTo(null);
        setSize(350,250);
        ExitButton.addActionListener(new Exit());
        EnterButton.addActionListener(new Enter());
        RegButtonTop.addActionListener(new Register());
        this.cois = cois;
        this.coos = coos;
    }
    private JLabel RegistrationLabel;
    private JTextField LoginField;
    private JButton EnterButton;
    private JButton ExitButton;
    private JPasswordField PasswordField;
    private JLabel LoginLabel;
    private JLabel PasswordLabel;
    private JPanel LogInPanel;
    private JButton RegButtonTop;

    public void close()
    {
        System.exit(0);
    }

    private class Exit implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            close();
        }
    }

    private class Register implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            RegistrationWindow registrationWindow = new RegistrationWindow(coos,cois);
            setVisible(false);
        }
    }

        private class Enter implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user=new User();
            User userDB=new User();
            Admin userADMIN = new Admin();
            Admin mainAdmin = new Admin();
            user.setLogin(LoginField.getText());
            user.setPassword(PasswordField.getText());
            userADMIN.setLogin(LoginField.getText());
            userADMIN.setPassword(PasswordField.getText());
            try {
                coos.writeObject("AdminCheck");
                coos.writeObject(userADMIN);
                mainAdmin =(Admin)cois.readObject();
                if(mainAdmin!=null){
               // if(mainAdmin.getRole().equals("admin")) {
                    AdminControlPanel adminControlPanel = new AdminControlPanel(coos, cois);
                    setVisible(false);
                }
                else {
                    System.out.println("Admin check failed");
                    coos.writeObject("LogIn");
                    coos.writeObject(user);
                    userDB=(User)cois.readObject();
                    if (userDB == null) {
                        JOptionPane.showMessageDialog(null, "Нет такого пользователя. Повторите попытку или зарегистрируйтесь.");
                        PasswordField.setText("");
                    }
                    else {
                            LoggedInWindow loggedInWindow = new LoggedInWindow(coos, cois, userDB);
                            setVisible(false);
                        }
                    }
                        System.out.println("Admin check failed! Opening standart menu.");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
