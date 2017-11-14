package ruokala.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PalauteActivity extends AppCompatActivity implements HTTPGetThread.OnRequestDoneInterface
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palaute);

        String url = "https://ruokalareview.herokuapp.com/palaute";
        HTTPGetThread getter = new HTTPGetThread(url, this);
        getter.start();
    }

    @Override
    public void onRequestDone(final String data)
    {
        Log.d("LABRA dataa tulee: ", data);
        PalauteActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView text = (TextView) findViewById(R.id.textView);
                text.setText(data);

                String data2 = data.replaceAll("orava1337", "\n\n");
                text.setText(data2);
            }
        });
    }
}