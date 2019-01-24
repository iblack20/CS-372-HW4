import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
public class Randomdice implements Runnable {

    private JLabel _label;

    /**
     * Randomdice class holds a JLabel and implements Runnable
     * Sets JLabel to initial die face upon creation
     * @param label dynamically created label
     */
    public Randomdice(JLabel label){

        _label = label;
        _label.setIcon(getIcon(0));
    }

    /**
     * run function generates a random number with a range of 6 and sets
     * the icon of each die image to a random face 10 times. Thread is set to sleep for 300 milliseconds between each
     * change of icon.
     * @author Iain Black
     */
    public void run() {
        Random num = new Random();
        int a = num.nextInt(6);

            for (int i = 0; i < 10; i++) {
                _label.setIcon(getIcon(a));
                try {
                    Thread.sleep(300);
                } catch (Exception ex) {
                    System.out.println("Fail");
                }
            }

    }

    /**
     * genIcon takes and int that sets the image icon to the image with the int as the title,
     * the images have been named 0-5 in the resources file to make this work.
     * @param i name of image file
     * @return
     */
    public ImageIcon getIcon(int i){
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        URL dieURL = getClass().getResource("/Resources/" + i + ".png");
        Image dieimg = toolkit.getImage(dieURL);
        dieimg = dieimg.getScaledInstance(100,100,Image.SCALE_SMOOTH);
        ImageIcon dieIcon = new ImageIcon(dieimg);

        return dieIcon;
    }

}
