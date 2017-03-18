package meshari.uoregon.piggamev1;

import java.util.Random;

public class Piggame {

    static Random rand = new Random();
    private int counter1 = 0;
    private int counter2 = 0;
    private int sum1 = 0;
    private int sum2 = 0;
    private int id;
    private int point = 0;
    private int maxScore = 100;

    protected void setCounter1(int c){counter1 = c;}
    protected int getCounter1(){return counter1;}
    protected void setCounter2(int c){counter2 = c;}
    protected int getCounter2(){return counter2;}
    protected void setSum1(int c){sum1 = c;}
    protected int getSum1(){return sum1;}
    protected void setSum2(int c){sum2 = c;}
    protected int getSum2(){return sum2;}
    protected void setPoint(int c){point = c;}
    protected int getPoint(){return point;}
    protected void setId(int c){id = c;}
    protected int getId(){return id;}
    protected void setMaxScore(int c){maxScore = c;}
    protected int getMaxScore(){return maxScore;}

    protected void play(Players p){

        if(p == Players.PlayerOne) {

            if (id == R.drawable.die1){
                counter1 = 0;
                point = 0;
                FragmentTwo.playerOneturn = false;
                FragmentTwo.playerTwoturn = true;
                FragmentTwo.setText();
            }
            if(id == R.drawable.die2){
                counter1 = counter1 + 2;
                point = 2;
                }
            if(id == R.drawable.die3){
                counter1 = counter1 + 3;
                point = 3;
                }
            if(id == R.drawable.die4){
                counter1 = counter1 + 4;
                point = 4;
                }
            if(id == R.drawable.die5){
                counter1 = counter1 + 5;
                point = 5;
                }
            if(id == R.drawable.die6){
                counter1 = counter1 + 6;
                point = 6;
                }
            if(sum1 >= maxScore){
                resetGame();
            }
        }

        if(p == Players.PlayerTwo){

            if (id == R.drawable.die1){
                counter2 = 0;
                point = 0;
                FragmentTwo.playerTwoturn = false;
                FragmentTwo.playerOneturn = true;
                FragmentTwo.setText();
            }
            if(id == R.drawable.die2){
                counter2 = counter2 + 2;
                point = 2;
                }
            if(id == R.drawable.die3){
                counter2 = counter2 + 3;
                point = 3;
                }
            if(id == R.drawable.die4){
                counter2 = counter2 + 4;
                point = 4;
                }
            if(id == R.drawable.die5){
                counter2 = counter2 + 5;
                point = 5;
                }
            if(id == R.drawable.die6){
                counter2 = counter2 + 6;
                point = 6;
                }
            if(sum2 >= maxScore){
                resetGame();
            }
        }
    }

    protected void getImage(Die die) {
        switch (die) {
            case die1:
                id = R.drawable.die1;
                break;
            case die2:
                id = R.drawable.die2;
                break;
            case die3:
                id = R.drawable.die3;
                break;
            case die4:
                id = R.drawable.die4;
                break;
            case die5:
                id = R.drawable.die5;
                break;
            case die6:
                id = R.drawable.die6;
                break;
        }
    }

    protected void resetGame(){
        counter1 = 0;
        counter2 = 0;
        sum1 = 0;
        sum2 = 0;
    }

    public Die rolls(){
        return Die.values()[rand.nextInt(6)];
    }
}
