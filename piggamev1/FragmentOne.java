package meshari.uoregon.piggamev1;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FragmentOne extends Fragment {

    private FragmentTwo activity;
    private boolean twoPaneLayout;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_one, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Get a references from the host activity
        activity = (FragmentTwo) getActivity();
        player1Name = (EditText) activity.findViewById(R.id.player1Name);
        player2Name = (EditText) activity.findViewById(R.id.player2Name);
    }

    protected void clickNewgame(View v) {

        //clickSound.start();

        /*player1Name.setText("");
        player2Name.setText("");*/
        player1Score.setText("0");
        player2Score.setText("0");
        turn.setText("");
        points.setText("0");
        playerOneturn = true;
        playerTwoturn = false;
        Intent intent = new Intent(getActivity(), MainActivityTwo.class);
        intent.putExtra("name1", player1Name.getText().toString());
        intent.putExtra("name2", player2Name.getText().toString());
        startActivity(intent);
    }

}

