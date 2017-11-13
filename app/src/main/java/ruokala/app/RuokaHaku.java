package ruokala.app;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RuokaHaku extends Thread {

    private static String url = "http://www.amica.fi/api/restaurant/menu/day?date=2017-11-13&language=en&restaurantPageId=66287";
    //"http://www.amica.fi/api/restaurant/menu/day?date=2017-10-3&language=en&restaurantPageId=66287";

    private static final String TAG_LUNCH_MENU = "LunchMenu";
    private static final String TAG_DAY_OF_WEEK = "DayOfWeek";
    private static final String TAG_DATE = "Date";
    private static final String TAG_SET_MENUS = "SetMenus";
    private static final String TAG_MEALS = "Meals";
    private static final String TAG_NAME = "Name";

    String OutputData = "";

    JSONObject json;

    public interface MyInterface
    {
        public void ping(String value);
    }

    MyInterface observer = null;

    public void setUser(MyInterface user){
        this.observer = user;
    }

    public void run(){
        JSONhaku jParser = new JSONhaku();

        // getting JSON string from URL
        json = jParser.getJSONFromUrl(url);

        Log.i("JSON",json.toString());

        try {
            JSONObject lunchMenu = json.getJSONObject(TAG_LUNCH_MENU);

            String dayOfWeek = lunchMenu.getString(TAG_DAY_OF_WEEK);
            String date = lunchMenu.getString(TAG_DATE);

            OutputData += dayOfWeek + " " +date + " \n";
            OutputData += "\n";

            JSONArray setMenus = lunchMenu.getJSONArray(TAG_SET_MENUS);

            for(int i = 0; i < setMenus.length(); i++){
                JSONObject c = setMenus.getJSONObject(i);
                JSONArray meals = c.getJSONArray(TAG_MEALS);

                for(int k = 0; k < meals.length(); k++){
                    JSONObject d = meals.getJSONObject(k);
                    String name = d.getString(TAG_NAME);

                    OutputData += name;
                    OutputData += "\n";
                }
                OutputData += "\n";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        observer.ping(OutputData);
    }
}