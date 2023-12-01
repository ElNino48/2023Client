package UI;

import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegistrationWindow extends JFrame {
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    public RegistrationWindow(ObjectOutputStream coos, ObjectInputStream cois){
        setVisible(true);
        setContentPane(RegPanel);
        setLocationRelativeTo(null);
        setSize(350,250);
        this.addWindowListener(new WindowClosing());
        RegisterButton.addActionListener(new Register());
        GoBackDownButton.addActionListener(new GoBack());
        this.cois = cois;
        this.coos = coos;
    }
    public RegistrationWindow(ObjectOutputStream coos, ObjectInputStream cois,String adminPanel){
        setVisible(true);
        setContentPane(RegPanel);
        setLocationRelativeTo(null);
        setSize(350,250);
        this.addWindowListener(new WindowClosing());
        RegisterButton.addActionListener(new Register());
        GoBackDownButton.addActionListener(new GoBack2());
        this.cois = cois;
        this.coos = coos;
    }
    private class GoBack2 implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminControlPanel adminControlPanel =new AdminControlPanel(coos,cois);
            setVisible(false);
        }
    }
    private JLabel RegistrationTextField;
    private JTextField LoginField1;
    private JTextField EmailField2;
    private JPasswordField passwordField1;
    private JPasswordField passwordConfirmField2;
    private JLabel loginLabel;
    private JLabel EmailLabel;
    private JLabel passLabel;
    private JLabel passConfirmLabel;
    private JPanel RegPanel;
    private JButton RegisterButton;
    private JButton GoBackDownButton;

    public void close()
    {
        setVisible(false);
    }
    private class GoBack implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            LoggingInWindow loggingInWindow = new LoggingInWindow(coos,cois);
            setVisible(false);
        }
    }

    private class Register implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
        User user = new User();
        user.setLogin(LoginField1.getText());
        user.setEmail(EmailField2.getText());
        user.setPassword(passwordField1.getText());
        user.setUsername(LoginField1.getText());
            if (!passwordConfirmField2.getText().equals(passwordField1.getText())) {
                    JOptionPane.showMessageDialog(null, "Пароли не совпадают.\n Проверьте, не включен ли CAPS LOCK и повторите попытку.");
                    passwordConfirmField2.setText("");
                } else {
                    try {
                    coos.writeObject("SignUp");
                    coos.writeObject(user);
                    if (cois.readObject().toString().equals("there is such user")) {
                        //setEnabled(false);
                        //Message message = new Message("Такой пользователь уже существует." +
                        //      " Попробуйте войти в аккаунт, либо создайте нового.");
                        JOptionPane.showMessageDialog(null, "Такой пользователь уже существует. Попробуйте войти в аккаунт, либо создайте нового.");
                        LoginField1.setText("");
                        EmailField2.setText("");
                        passwordField1.setText("");
                        passwordConfirmField2.setText("");
                    } else {
                        LoggingInWindow logIn = new LoggingInWindow(coos, cois);
                        JOptionPane.showMessageDialog(null, "Вы зарегистрировались успешно!");
                        setVisible(false);
                    }
                } catch(IOException ex){
                    throw new RuntimeException(ex);
                } catch(ClassNotFoundException ex){
                    throw new RuntimeException(ex);
                }
            }
        }
    }
    private class WindowClosing extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            setVisible(false);
            System.out.println("Reg window closed!");
            System.exit(0);
        }
    }

}
