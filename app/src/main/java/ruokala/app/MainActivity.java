package ruokala.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.estimote.coresdk.common.requirements.SystemRequirementsChecker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemRequirementsChecker.checkWithDefaultDialogs(this);
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

    public void test2ButtonClick(View v){
        Intent intent = new Intent(this, ReviewActivity.class);
        startActivity(intent);
    }

    public void test3ButtonClick(View v){
        Intent intent = new Intent(this, PalauteActivity.class);
        startActivity(intent);
    }
}