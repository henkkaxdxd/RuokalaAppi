package ruokala.app;

//import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RuokalistaActivity extends AppCompatActivity implements RuokaHaku.MyInterface {

    private static String url = "http://www.amica.fi/api/restaurant/menu/day?date=2017-11-9&language=en&restaurantPageId=66287";

    private static final String TAG_LUNCH_MENU = "LunchMenu";
    private static final String TAG_DAY_OF_WEEK = "DayOfWeek";
    private static final String TAG_DATE = "Date";
    private static final String TAG_SET_MENUS = "SetMenus";
    private static final String TAG_MEALS = "Meals";
    private static final String TAG_NAME = "Name";

    //String OutputData = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruokalista);
        Log.i("JSON","@onCreate");

        RuokaHaku loader = new RuokaHaku();
        loader.setUser(this);
        loader.start();
    }

    @Override
    public void ping(final String value){
        RuokalistaActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView text = (TextView) findViewById(R.id.textView);
                text.append(value);
            }
        });
    }
}