import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WebpageReader class implements runnable, and uses regex phrases to search URLs for emails and other URL
 * links. A Hashmap is created to store URLs found by the webspider and an ArrayList is created to hold all of the
 * email addresses found.
 * @author Iain Black
 */
public class WebpageReader implements Runnable{
    private URL _url;
    private static HashMap<String, Boolean> URLs = new HashMap<>();
    private static Set <String> emails = new HashSet<>();
    private String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
    private String emailRegex =  "\"mailto:(.*?)\"";
    private ArrayList<String> URLlist = new ArrayList<>();

    /**
     * Creates a new URL using the String passed in from webpages
     * @param url String from webpages array
     */
    public WebpageReader(String url){
        try{
            _url = new URL(url);

        }catch(Exception ex) {
            _url = null;
        }}

    /**
     * Run opens the URL and uses a Pattern and Matcher to find the predefined regex expressions
     * within the source code of the URL link. If a URL is found, it is added to the URLs Hashmap
     * and set to false to signify that it has been visited. If an email is found using the
     * emailregex expression it is added to the ArrayList for emails.
     */
    public void run(){

        if (_url == null)
            return;
        String line;
        try{
            System.out.printf("Reading from %s\n", _url.toString());
            BufferedReader rdr = new BufferedReader(new InputStreamReader(_url.openStream()));

            while ((line = rdr.readLine()) != null){
                Pattern website = Pattern.compile(regex);
                Matcher matcher = website.matcher(line);

                while (matcher.find()){
                    URLs.put(matcher.group(0), false);
                }
                Pattern email = Pattern.compile(emailRegex);
                Matcher match = email.matcher(line);
                while(match.find()){
                    emails.add(match.group(1));
                }


            }

        }catch (Exception ex){
            System.out.printf("Oops: %s\n", ex.getCause());
        }
    }

    /**
     * Displays all emails added into the emails ArrayList
     */
    public static void displayEmails(){
        List<String> emailList = new ArrayList<>(emails);
        System.out.println("Emails gathered during search:");
        for (int i=0; i<emailList.size();i++){
            System.out.println(emailList.get(i));
        }
    }
    public static HashMap<String, Boolean> getURLs(){
        return URLs;
    }

}
