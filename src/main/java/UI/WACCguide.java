package UI;

import XmlDao.ReadXML;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WACCguide extends JFrame {
    private JPanel waccPanel;
    private JTextArea WACCtext;
    private String text;
    public WACCguide(){
        setVisible(true);
        setContentPane(waccPanel);
        this.text= ReadXML.ReadXmlFile("wacc");
        this.addWindowListener(new WindowClosing());
        WACCtext.setText(text) ;
        setLocationRelativeTo(null);
        setSize(500,300);
        //GuideText = new JTextArea(text,100,100);
        WACCtext.setEditable(false);


    }
    private class WindowClosing extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e){
            setVisible(false);
            System.out.println("WACC guide text window closed!");
        }
    }

}
