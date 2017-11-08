package ruokala.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

public class MainActivity extends AppCompatActivity {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        tvData = (TextView) findViewById(R.id.textView);
    }

    @Override
    protected void onResume(){
        super.onResume();
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }

    public void testButtonClick(View v){
        Intent intent = new Intent(this, RuokalistaActivity.class);
        startActivity(intent);
    }
}