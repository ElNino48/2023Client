package UI;

import Diagramm.CompanyAmountDiagramm;
import Diagramm.CompanyCreateDateDiagramm;
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
import java.util.ArrayList;
import java.util.List;

public class AdminControlPanel extends JFrame{
    private JPanel TableCompanies;
    private JTable tableUsers;
    private DefaultTableModel tableModel;
    private JButton AddButton1;
    private JButton DeleteButton3;
    private JButton RedactButton2;
    private JPanel adminPanel;
    private JButton DiagramButton;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;

        public AdminControlPanel(ObjectOutputStream coos, ObjectInputStream cois) {
            setVisible(true);
            setContentPane(adminPanel);
            setLocationRelativeTo(null);
            setSize(700, 500);
            AddButton1.addActionListener(new Add());
            RedactButton2.addActionListener(new Redact());
            DeleteButton3.addActionListener(new Delete());
            DiagramButton.addActionListener(new Diagram());
            this.addWindowListener(new WindowClosing());
            System.out.println("Admin panel opened!");
            this.cois = cois;
            this.coos = coos;
            ShowData();
        }
        public void ShowData() {
            Object[] columnTitle = {"idUser","Username","employeesNumber","password",
            "login","email","idAdmin"};
            tableModel = new DefaultTableModel(null, columnTitle);
            tableUsers.setModel(tableModel);
            tableModel.getDataVector().removeAllElements();
            System.out.println("Connected in Action_dialog_users");
            try {
                System.out.println("getallusers entering?");
                coos.writeObject("GetAllUsers");//GetAllUsers
                System.out.println("getallusers entered!");
                List<User> users=new ArrayList<User>();
                users = (List<User>)cois.readObject();

                System.out.println("getallusers read");
                System.out.println(users);
                for(User user:users)
                {
                    System.out.println("1");
                    Object[] data = {
                            user.getIdUser(),
                            user.getUsername(),
                            user.getEmployeesNumber(),
                            user.getPassword(),
                            user.getLogin(),
                            user.getEmail(),
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
                RegistrationWindow RegNewUser = new RegistrationWindow(coos,cois,"From AdminControlPanel");
                setVisible(false);
            }
        }
        private class Diagram implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                CompanyAmountDiagramm demo = new CompanyAmountDiagramm("Диаграмма количества компаний пользователей",coos,cois);
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);

                CompanyCreateDateDiagramm сompanyCreateDateDiagramm =new CompanyCreateDateDiagramm("Диаграмма возрастов компаний",coos,cois);
            }
        }
        private class Redact implements ActionListener
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user=new User();
                user.setIdUser(Integer.valueOf(tableModel.getValueAt(tableUsers.getSelectedRow(),0).toString()));
                if(tableModel.getValueAt(tableUsers.getSelectedRow(),1)!=null)
                    user.setUsername(tableModel.getValueAt(tableUsers.getSelectedRow(),1).toString());

                if(tableModel.getValueAt(tableUsers.getSelectedRow(),2)!=null)
                    user.setEmployeesNumber(Integer.valueOf(tableModel.getValueAt(tableUsers.getSelectedRow(),2).toString()));

                if(tableModel.getValueAt(tableUsers.getSelectedRow(),3)!=null)
                    user.setPassword(tableModel.getValueAt(tableUsers.getSelectedRow(),3).toString());

                if(tableModel.getValueAt(tableUsers.getSelectedRow(),4)!=null)
                    user.setLogin(tableModel.getValueAt(tableUsers.getSelectedRow(),4).toString());

                if(tableModel.getValueAt(tableUsers.getSelectedRow(),5)!=null)
                    user.setEmail(tableModel.getValueAt(tableUsers.getSelectedRow(),5).toString());

                //if(tableModel.getValueAt(tableUsers.getSelectedRow(),6)!=null)
                   // user.setId(tableModel.getValueAt(tableUsers.getSelectedRow(),6).toString());
                AdminEditUser EditUser = new AdminEditUser(coos,cois, user);
                setVisible(false);
            }
        }
        private class Delete implements ActionListener
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                User user=new User();
                user.setIdUser(Integer.valueOf(tableModel.getValueAt(tableUsers.getSelectedRow(),0).toString()));
                if(tableModel.getValueAt(tableUsers.getSelectedRow(),1)!=null)
                    user.setUsername(tableModel.getValueAt(tableUsers.getSelectedRow(),1).toString());

                if(tableModel.getValueAt(tableUsers.getSelectedRow(),2)!=null)
                    user.setEmployeesNumber(Integer.valueOf(tableModel.getValueAt(tableUsers.getSelectedRow(),2).toString()));

                if(tableModel.getValueAt(tableUsers.getSelectedRow(),3)!=null)
                    user.setPassword(tableModel.getValueAt(tableUsers.getSelectedRow(),3).toString());

                if(tableModel.getValueAt(tableUsers.getSelectedRow(),4)!=null)
                    user.setLogin(tableModel.getValueAt(tableUsers.getSelectedRow(),4).toString());

                if(tableModel.getValueAt(tableUsers.getSelectedRow(),5)!=null)
                    user.setEmail(tableModel.getValueAt(tableUsers.getSelectedRow(),5).toString());
                try {
                    coos.writeObject("DeleteUser");
                    coos.writeObject(user);
                    setVisible(false);
                    AdminControlPanel adminControlPanel = new AdminControlPanel(coos, cois);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                setVisible(false);
            }
        }
        private class WindowClosing extends WindowAdapter {
            @Override
            public void windowClosing(WindowEvent e){
                setVisible(false);
                System.out.println("Admin edit Table closed!");
                System.exit(0);
            }
        }
    }
