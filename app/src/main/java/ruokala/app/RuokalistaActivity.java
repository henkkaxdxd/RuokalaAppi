package ruokala.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class RuokalistaActivity extends AppCompatActivity implements RuokaHaku.MyInterface {

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