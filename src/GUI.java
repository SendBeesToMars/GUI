import javafx.scene.layout.Pane;

import java.awt.*;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GUI extends JPanel{
    private JPanel Panel;
    private JTextArea textArea1;
    private JButton button1;
    private JLabel timeTaken;
    private JCheckBox bufferCheckBox;
    private boolean buffer = false;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().Panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    public GUI() {

        bufferCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buffer = true;
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
                long i = 0, k = 0, timeStart, timeFinish, timeNow, timePrevious, timeElapsed;
                String units;
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(Panel);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    timeStart = System.currentTimeMillis();
                    timePrevious = timeStart;
                    System.out.println("You chose to open this file: " +
                            chooser.getSelectedFile().toString());
                    try{
                        int ch;
                        if(buffer) {
                            InputStream input = new BufferedInputStream( new FileInputStream(chooser.getSelectedFile()));
                            while( (ch = input.read()) != -1) {
                                i++;
                                textArea1.append(Character.toString((char) ch));
                                if(i == 1000000){
                                    k++;
                                    timeNow = System.currentTimeMillis();
                                    System.out.println(k +  "kk" + " " + (
                                            (timeNow - timePrevious) / 1000) + "s since last mill");
                                    i = 0;
                                    timePrevious = timeNow;
                                }
                            }
                            timeFinish = System.currentTimeMillis();
                            if((timeFinish - timeStart) < 10000){
                                timeElapsed = timeFinish - timeStart;
                                units = "ms";
                            }
                            else{
                                timeElapsed = (timeFinish - timeStart) / 1000;
                                units = "s";
                            }
                            timeNow = System.currentTimeMillis();
                            System.out.println("Time Elapsed: " + timeElapsed + units +" , " + ((timeNow - timePrevious) / 1000) + "s since last mill");
                            String time = String.valueOf(timeElapsed);
                            timeTaken.setText(time + units);
                        }
                        else {
                            InputStream input = new FileInputStream(chooser.getSelectedFile());
                            while( (ch = input.read()) != -1) {
                                i++;
                                textArea1.append(Character.toString((char) ch));
                                if(i == 1000000){
                                    k++;
                                    timeNow = System.currentTimeMillis();
                                    System.out.println(k +  "kk" + " " + (
                                            (timeNow - timePrevious) / 1000) + "s since last mill");
                                    i = 0;
                                    timePrevious = timeNow;
                                }
                            }
                            timeFinish = System.currentTimeMillis();
                            if((timeFinish - timeStart) < 1000){
                                timeElapsed = timeFinish - timeStart;
                                units = "ms";
                            }
                            else{
                                timeElapsed = (timeFinish - timeStart) / 1000;
                                units = "s";
                            }
                            timeNow = System.currentTimeMillis();
                            System.out.println("Time Elapsed: " + timeElapsed + units +" , " + ((timeNow - timePrevious) / 1000) + "s since last mill");
                            String time = String.valueOf(timeElapsed);
                            timeTaken.setText(time + units);
                        }
                    }
                    catch (IOException e1){

                    }
                }
            }
        });

    }
}
