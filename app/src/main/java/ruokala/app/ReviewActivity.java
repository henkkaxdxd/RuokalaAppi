package ruokala.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ReviewActivity extends Activity{

    EditText editText;

    TextView tvData;

    String message = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewactivity);

        tvData = (TextView) findViewById(R.id.textViewID);

        editText = (EditText) findViewById(R.id.editTextID);
    }

    public void sendBtnClick(View v){
        message = editText.getText().toString();
        message = message.replaceAll(" ", "-");
        message = message.replaceAll("/", " tai ");

        if (message.isEmpty()){
            tvData.setText("Viesti kenttäsi on tyhjä!");
        } else {
            new ReviewActivity.JSONtask().execute("https://ruokalareview.herokuapp.com/laheta/"+message); //ilman www.
            tvData.setText(message);
            editText.setText("");
        }
    }

    public class JSONtask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = " ";
                while ((line = reader.readLine())!= null){
                    buffer.append(line);
                }
                buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            tvData.setText(result);
        }
    }
}
