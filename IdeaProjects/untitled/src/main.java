import com.sun.deploy.uitoolkit.impl.awt.ui.SwingConsoleWindow;
import com.sun.deploy.uitoolkit.ui.ConsoleController;
import com.sun.xml.internal.ws.api.server.*;
import com.sun.xml.internal.ws.api.server.Container;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Array;
import javax.swing.*;


//import javafx.scene.control.Alert;
//import javafx.stage.Screen;
//import java.util.EventListener;


/**
 * Created by I863409 on 11/07/2017.
 */
public class main {


    public static void main(String[] args) {
        //System.out.println("ola");

        JFrame frame1 = new JFrame();
        JPanel p1 = new JPanel();
        JButton b1 = new JButton();
        JButton b2 = new JButton();

        JButton arrayButtons[] = {b1, b2};

        setButtonsName(arrayButtons);

        p1.add(b1);
        p1.add(b2);
        frame1.add(p1);


        frame1.setSize(350, 400);
        p1.setSize(200, 500);

        frame1.setLocation(800, 500);
        b1.setHorizontalAlignment(SwingConstants.LEFT);
        b2.setHorizontalAlignment(SwingConstants.RIGHT);

        b1.setVerticalAlignment(SwingConstants.TOP);
        b2.setVerticalAlignment(SwingConstants.BOTTOM);


        frame1.setVisible(true);
        p1.setVisible(true);


        //-------------------------------EVENTS-------------------------------

        //Mouse event attribution to a button
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                JOptionPane.showMessageDialog(null, "Trying to create a pull request of type 1", "",
                        JOptionPane.NO_OPTION);
            }


        });


        //Listener needed to close the frame when the close button is hit
        frame1.addWindowListener(new WindowAdapter() {
                                     public void windowClosing(WindowEvent we) {
                                         frame1.dispose();
                                     }
                                 }
        );


    }


    private static void setButtonsName(JButton[] b1) {
        JOptionPane.showMessageDialog(null, "Number of buttons: " + b1.length, "",
                JOptionPane.NO_OPTION);

        int i = 1;
        for (JButton aB1 : b1) {
            aB1.setText("Create pull request " + i);
            i++;
        }
    }



    /*
    //UNDER DEVELOPMENT
    private static void setChildrenToFather(Object[] objArray) {

        if(objArray.length < 2){
            JOptionPane.showMessageDialog(null,"It is needed at least two elements. At least one child to a father.");
            return;
        }

        Object father = objArray[objArray.length-1];
        objArray[objArray.length-1] = null;

        for (Object obj : objArray) {
            father.add(obj);

        }
    }
    */


}



