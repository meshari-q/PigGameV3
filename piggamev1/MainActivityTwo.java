package meshari.uoregon.piggamev1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

public class MainActivityTwo extends AppCompatActivity {


    EditText player1Name;
    EditText player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_two);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Get the game state sent from the FirstActivity
        Intent intent = getIntent();
        String name1 = intent.getExtras().getString("name1");
        String name2 = intent.getExtras().getString("name2");
        player1Name.setText(name1);
        player2Name.setText(name2);
    }
}