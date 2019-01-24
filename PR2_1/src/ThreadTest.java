import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


public class ThreadTest extends JComponent {
    private JFrame Frame = new JFrame();
    private JButton startButton = new JButton("Roll");

    /**
     * Thread test class creates JFrame, creates array of Randomdice objects
     * and creates/adds JLabels for each object. Also creates and adds the button
     * for rolling the dice, adding an action listener to the button.
     * @author Iain Black
     */

    public ThreadTest(){
        Frame.setSize(800,150);
        Frame.setLayout(new FlowLayout());
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Randomdice[] rd = new Randomdice[5];
        for(int i =0; i<rd.length; i++){
            JLabel l = new JLabel();
            rd[i] = new Randomdice(l);

            Frame.add(l);
        }
        Frame.add(startButton);
        /**
         * Action listener creates a new thread for each Randomdice created,
         * sleeping for 100 milliseconds before starting each of the threads.
         */
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<rd.length;i++){
                    Thread t = new Thread(rd[i]);
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException ex){;}
                    t.start();

                }
            }
        });

        Frame.setVisible(true);



    }

}
