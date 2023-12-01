package UI;

import models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;

public class DateWindow extends JFrame{
    private JPanel datePanel;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JButton back;
    private JButton commit;
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    private Date startPeriod;
    private Date endPeriod;
    private User user;
    public DateWindow(ObjectOutputStream coos,ObjectInputStream cois,Date startPeriod,Date endPeriod, User user)
    {
        this.coos=coos;
        this.cois=cois;
        this.startPeriod=startPeriod;
        this.endPeriod=endPeriod;
        this.user= user;
        setVisible(true);
        setContentPane(datePanel);
        setLocationRelativeTo(null);
        setSize(500,300);
        commit.addActionListener(new Commit());
        this.addWindowListener(new WindowClosing());
        back.addActionListener(new back());
        dateYearFromComboBox();
        dateMonthFromComboBox();
        dateYearToComboBox();
        dateMonthToComboBox();

    }
    private class back implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            setVisible(false);
        }
    }
    private class Commit implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            int numberOfMonth=comboBox2.getSelectedIndex()+1;
            switch(numberOfMonth) {
                case 1:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-01-01");
                    break;
                case 2:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-02-01");
                    break;
                case 3:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-03-01");
                    break;
                case 4:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-04-01");
                    break;
                case 5:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-05-01");
                    break;
                case 6:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-06-01");
                    break;
                case 7:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-07-01");
                    break;
                case 8:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-08-01");
                    break;
                case 9:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-09-01");
                    break;
                case 10:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-10-01");
                    break;
                case 11:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-11-01");
                    break;
                case 12:
                    startPeriod=Date.valueOf(comboBox1.getSelectedItem()+"-12-01");
                    break;
            }
            numberOfMonth=comboBox4.getSelectedIndex()+1;
            switch(numberOfMonth) {
                case 1:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-01-31");
                    break;
                case 2:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-02-28");
                    break;
                case 3:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-03-31");
                    break;
                case 4:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-04-30");
                    break;
                case 5:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-05-31");
                    break;
                case 6:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-06-30");
                    break;
                case 7:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-07-31");
                    break;
                case 8:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-08-31");
                    break;
                case 9:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-09-30");
                    break;
                case 10:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-10-31");
                    break;
                case 11:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-11-30");
                    break;
                case 12:
                    endPeriod=Date.valueOf(comboBox3.getSelectedItem()+"-12-31");
                    break;
            }
            System.out.println(startPeriod);
            System.out.println(endPeriod);
            Calculator calculator=new Calculator(coos,cois,startPeriod,endPeriod,user);
            setVisible(false);
        }
    }
    private void dateYearFromComboBox()
    {
        for(int i=1970;i<2024;i++){
            comboBox1.addItem(i);
        }
    }
    private void dateMonthFromComboBox()
    {
        //month
        comboBox2.addItem("1.Январь");
        comboBox2.addItem("2.Февраль");
        comboBox2.addItem("3.Март");
        comboBox2.addItem("4.Апрель");
        comboBox2.addItem("5.Май");
        comboBox2.addItem("6.Июнь");
        comboBox2.addItem("7.Июль");
        comboBox2.addItem("8.Август");
        comboBox2.addItem("9.Сентябрь");
        comboBox2.addItem("10.Октябрь");
        comboBox2.addItem("11.Ноябрь");
        comboBox2.addItem("12.Декабрь");
    }
    private void dateYearToComboBox()
    {
        for(int i=1970;i<2024;i++){
            comboBox3.addItem(i);
        }
    }
    private void dateMonthToComboBox()
    {
        //month
        comboBox4.addItem("1.Январь");
        comboBox4.addItem("2.Февраль");
        comboBox4.addItem("3.Март");
        comboBox4.addItem("4.Апрель");
        comboBox4.addItem("5.Май");
        comboBox4.addItem("6.Июнь");
        comboBox4.addItem("7.Июль");
        comboBox4.addItem("8.Август");
        comboBox4.addItem("9.Сентябрь");
        comboBox4.addItem("10.Октябрь");
        comboBox4.addItem("11.Ноябрь");
        comboBox4.addItem("12.Декабрь");
    }
    private class WindowClosing extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            setVisible(false);
        }
    }
}
