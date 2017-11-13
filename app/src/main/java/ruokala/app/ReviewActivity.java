package ruokala.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
            //new JSONtask().execute("https://oven-sausage.herokuapp.com/viesti/"+sendtogroupID+"/"+message+"/"+adminName);
            tvData.setText(message);
            editText.setText("");
        }
    }
}
