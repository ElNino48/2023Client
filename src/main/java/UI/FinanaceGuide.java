package UI;

import XmlDao.ReadXML;
import models.User;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FinanaceGuide extends JFrame{
    private ObjectOutputStream coos;
    private ObjectInputStream cois;
    private JPanel panel1;
    private JTextArea GuideText;
    private String text;
    private User user;
    public FinanaceGuide(User user){
        setVisible(true);
        setContentPane(panel1);
        this.user= user;
        this.text=ReadXML.ReadXmlFile("texts");
        this.addWindowListener(new WindowClosing());
        GuideText.setText(text) ;
        setLocationRelativeTo(null);
        setSize(500,300);
        //GuideText = new JTextArea(text,100,100);
        GuideText.setEditable(false);
    }
    private class WindowClosing extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            setVisible(false);
            System.out.println("Guide text window closed!");
            LoggedInWindow loggedInWindow = new LoggedInWindow(coos,cois,user);
        }
    }
}


