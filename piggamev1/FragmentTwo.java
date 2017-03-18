package meshari.uoregon.piggamev1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.media.MediaPlayer;

public class FragmentTwo extends AppCompatActivity {

    RelativeLayout rLayout;
    static EditText player1Name;
    static EditText player2Name;
    TextView player1Score;
    TextView player2Score;
    static TextView turn;
    TextView turnLablel;
    ImageView DieImage, newDieImage;
    TextView points;
    static public boolean playerOneturn = true;
    static public boolean playerTwoturn = false;
    Players plyrOne = Players.PlayerOne;
    Players plyrTwo = Players.PlayerTwo;
    Piggame game = new Piggame();
    MediaPlayer backMusic;
    MediaPlayer clickSound;
    MediaPlayer booSound;
    private SharedPreferences savedValues;
    String name1, name2, turn_, sum1, sum2, point;
    int newSum1, newSum2, newCounter1, newCounter2, newPoint, newId;
    final int MAX_SCORE = 100;
    int imageNum;
    private boolean musicOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rLayout = (RelativeLayout) findViewById(R.id.Rlayout);
        DieImage = (ImageView) findViewById(R.id.imageViewDie);
        //player1Name = (EditText)findViewById(R.id.player1Name);
        //player2Name = (EditText)findViewById(R.id.player2Name);
        player1Score = (TextView) findViewById(R.id.player1Score);
        player2Score = (TextView) findViewById(R.id.player2Score);
        turnLablel = (TextView) findViewById(R.id.turnLablel);
        turn = (TextView) findViewById(R.id.turn);
        backMusic = MediaPlayer.create(FragmentTwo.this, R.raw.background);
        points = (TextView) findViewById(R.id.points);
        savedValues = getSharedPreferences("localStorage", MODE_PRIVATE);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

        // get default SharedPreferences object
        savedValues = PreferenceManager.getDefaultSharedPreferences(this);
        onTouchMethod();
    }

    @Override
    protected void onPause() {

        super.onPause();
        //DieImage = (ImageView)findViewById(R.id.imageViewDie);
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("name1", name1 = player1Name.getText().toString());
        editor.putString("name2", name2 = player2Name.getText().toString());
        editor.putString("turn", turn_ = turn.getText().toString());
        editor.putString("sum1", sum1 = player1Score.getText().toString());
        editor.putString("sum2", sum2 = player2Score.getText().toString());
        editor.putString("point", point = points.getText().toString());
        editor.putInt("newSum1", newSum1 = game.getSum1());
        editor.putInt("newSum2", newSum2 = game.getSum2());
        editor.putInt("newCounter1", newCounter1 = game.getCounter1());
        editor.putInt("newCounter2", newCounter2 = game.getCounter2());
        editor.putInt("newId", newId = game.getId());
        editor.commit();
        if (savedValues.getBoolean("pref_backGroundmusic", true)) {
            backMusic.stop();
            //backMusic.release();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        );
        if (savedValues.getBoolean("pref_backGroundmusic", true)) {
            backMusic = MediaPlayer.create(FragmentTwo.this, R.raw.background);
            backMusic.setLooping(true);
            backMusic.start();
            musicOn = true;
        }

        player1Name.setText(savedValues.getString("name1", name1));
        player2Name.setText(savedValues.getString("name2", name2));
        turn.setText(savedValues.getString("turn", turn_));
        points.setText(savedValues.getString("point", point));
        player1Score.setText(savedValues.getString("sum1", sum1));
        player2Score.setText(savedValues.getString("sum2", sum2));
        game.setSum1(savedValues.getInt("newSum1", newSum1));
        game.setSum2(savedValues.getInt("newSum2", newSum2));
        game.setCounter1(savedValues.getInt("newCounter1", newCounter1));
        game.setCounter2(savedValues.getInt("newCounter2", newCounter2));
        DieImage.setImageResource(savedValues.getInt("newId", newId));
        game.setId(savedValues.getInt("newId", newId));

        if (savedValues.getBoolean("pref_maxScore", true)) {
            game.setMaxScore(Integer.parseInt(savedValues.getString("PREF_EDITTEXT", "0")));
        } else {
            game.setMaxScore(MAX_SCORE);
        }

        if (!savedValues.getBoolean("pref_backGround", false)) {
            rLayout.setBackgroundResource(0);
        } else {
            imageNum = Integer.parseInt(savedValues.getString("pref_image", "1"));
            if (imageNum == 1) {
                rLayout.setBackgroundResource(R.drawable.backg1);
            } else if (imageNum == 2) {
                rLayout.setBackgroundResource(R.drawable.backg2);
            } else {
                rLayout.setBackgroundResource(R.drawable.backg3);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                return true;
            case R.id.action_settings:
                startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                return true;
            default:
                super.onOptionsItemSelected(item);
        }

        return true;
    }

    protected void clickRolldie(View v) {

        //clickSound.start();
        game.getImage(game.rolls());

        if (playerOneturn) {
            turn.setText(player1Name.getText());
            game.getImage(game.rolls());
            game.play(plyrOne);
            DieImage.setImageResource(game.getId());
            points.setText(Integer.toString(game.getCounter1()));
        } else {
            turn.setText(player2Name.getText());
            game.getImage(game.rolls());
            game.play(plyrTwo);
            DieImage.setImageResource(game.getId());
            points.setText(Integer.toString(game.getCounter2()));
        }

        /*if(game.getPoint() == 0){
            booSound.start();
        }*/
    }

   /* protected void clickEndturn(View v) {

        //clickSound.start();
        points.setText("0");

        if (playerOneturn) {
            game.setSum1(game.getSum1() + game.getCounter1());
            player1Score.setText(Integer.toString(game.getSum1()));
            game.setCounter1(0);
            playerOneturn = false;
            playerTwoturn = true;
            turn.setText(player2Name.getText());
        } else if (playerTwoturn) {
            game.setSum2(game.getSum2() + game.getCounter2());
            player2Score.setText(Integer.toString(game.getSum2()));
            game.setCounter2(0);
            playerTwoturn = false;
            playerOneturn = true;
            turn.setText(player1Name.getText());
        }
    }*/


    /*onTouchMethod: hide the soft keyboard when you click outside of editbox on screen*/
    public void onTouchMethod() {
        rLayout.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                return false;
            }
        });
    }

    /*Set the turn text to Playertwo's turn if Player one got die1 which is = 0 and vice versa*/
    public static void setText() {
        if (playerOneturn) {
            turn.setText(player1Name.getText());
        } else {
            turn.setText(player2Name.getText());
        }
    }

}