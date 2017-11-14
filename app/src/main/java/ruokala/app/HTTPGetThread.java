package ruokala.app;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTPGetThread extends Thread {

    public interface OnRequestDoneInterface
    {
        public void onRequestDone(String data);
    }

    OnRequestDoneInterface uiCallback;

    String urli;

    public HTTPGetThread(String url,OnRequestDoneInterface callbackInterface){

        urli = url;
        this.uiCallback = callbackInterface;
    }

    public void run() {
        Log.d("asd", "run()");
        //Scanner scan = new Scanner(System.in); //Creates a new scanner
        //System.out.println("Type the URL where you want to fetch the data from: \n"); //Asks for the URL
        //String urlString = scan.nextLine(); //Waits for the URL

        //loader.loadStuff();
        HttpURLConnection urlConnection = null;
        try{
            //URL url = new URL(urlString);
            URL url = new URL(urli);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String allData = fromStream(in);
            //System.out.println(allData);
            uiCallback.onRequestDone(allData);
            in.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally{
            if (urlConnection != null)
            {
                urlConnection.disconnect();
            }
        }
    }
    public static String fromStream(InputStream in) throws IOException
    {
        Log.d("myApp", "fromStream");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
            out.append(newLine);
        }
        return out.toString();
    }
}