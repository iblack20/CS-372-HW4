import javax.swing.*;
import java.awt.*;


/**
 * Webspider class dynamically creates webreader objects and threads based on the
 * size of an array holding webpages.
 * @author Iain Black
 *
 * Collaborated with Griffen Marler on this assignment
 */
public class WebSpider extends JFrame {
    private String[] webpages = {
            "https://www.sno.wednet.edu/Domain/9",
            "https://www.sno.wednet.edu/Domain/11",
     "https://stats.nba.com/teams/traditional/?sort=W_PCT&dir=-1",
            "https://www.facebook.com/",
            "https://twitter.com/",


    };

    /**
     * main function creates webspider object and calls the displayemails function
     * @param args arguments
     */
    public static void main(String[] args){
        WebSpider spidey = new WebSpider();
        WebpageReader.displayEmails();
        WebpageReader.getURLs();
    }

    /**
     * constructor for Webspider creates webpagereader objects for each member of webpages passing in the String member,
     * and creates and starts a thread for each member as well. After sleeping each thread for
     * 100 milliseconds each thread is joined using join()
     */
    public WebSpider(){

        WebpageReader[] cs = new WebpageReader[webpages.length];
        for (int i =0; i < cs.length; i++){
            cs[i] = new WebpageReader(webpages[i]);
        }

        Thread[] ts = new Thread[webpages.length];
        for(int i=0;i<ts.length;i++){
            ts[i] = new Thread(cs[i]);
            ts[i].start();
        }
        try{
            Thread.sleep(100);
        }catch (InterruptedException ex){;}

        for (int i=0;i<ts.length;i++){
            try{
                ts[i].join();
            }catch(InterruptedException ex){;}
        }
    }






}
