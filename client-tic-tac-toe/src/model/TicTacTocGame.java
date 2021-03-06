package model;

import java.util.stream.IntStream;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.NativeArray;
import view.NewModeBase;
import view.SingleMode;

public class TicTacTocGame {
//game variable

    private String current_player;
    private boolean start_game_flag;
    private int game_arr[];
    private int counter;
    private boolean winFlag;
    SingleMode tcUI;
    private static int Score;
    private static int ComputerScore;
    private static int gameNum;
    private Stage primaryStage;

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public int getComputerScore() {
        return ComputerScore;
    }

    public void setComputerScore(int ComputerScore) {
        this.ComputerScore = ComputerScore;
    }

    public int getGameNum() {
        return gameNum;
    }

    public void setGameNum(int gameNum) {
        this.gameNum = gameNum;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public TicTacTocGame(SingleMode tc, Stage _primaryStage) {
        current_player = "X";
        start_game_flag = false;
        game_arr = new int[9];
        counter = 0;
        tcUI = tc;
        primaryStage = _primaryStage;
    }

    public int[] getGame_arr() {

        return game_arr;
    }

    public boolean setGame_arr(int move) {
        if (game_arr[move] == 0) {
            if (getCurrent_player().equals("X")) {
                game_arr[move] = 1;
            }
            if (getCurrent_player().equals("O")) {
                game_arr[move] = 2;
            }

            setCounter(getCounter() + 1);
            return true;
        }
        return false;
    }

    public String getCurrent_player() {
        return current_player;
    }

    public void setCurrent_player(String current_player) {
        this.current_player = current_player;
    }

    public boolean getStart_game_flag() {
        return start_game_flag;
    }

    public void setStart_game_flag(boolean start_game_flag) {
        this.start_game_flag = start_game_flag;
    }

    //check x or o win
    /*public String checkWiner(int[] game_arr) {
            int x = IntStream.of(game_arr).filter(value -> value == 1).sum();

            if (x % 2 == 1) {
                return "X";
            } else {
                return "O";
            }

        }
     */
    //check if any one win or not
    public boolean checkWining() {
        if ((game_arr[0] == 1 && game_arr[1] == 1 && game_arr[2] == 1)
                || (game_arr[3] == 1 && game_arr[4] == 1 && game_arr[5] == 1)
                || (game_arr[6] == 1 && game_arr[7] == 1 && game_arr[8] == 1)
                || (game_arr[0] == 1 && game_arr[4] == 1 && game_arr[8] == 1)
                || (game_arr[2] == 1 && game_arr[4] == 1 && game_arr[6] == 1)
                || (game_arr[0] == 1 && game_arr[3] == 1 && game_arr[6] == 1)
                || (game_arr[1] == 1 && game_arr[4] == 1 && game_arr[7] == 1)
                || (game_arr[2] == 1 && game_arr[5] == 1 && game_arr[8] == 1)) {
            System.out.println("X is Winning");
            Score += 10;

            winFlag = true;
            gameNum++;
            newGame();
            return true;
        } else {
            if ((game_arr[0] == 2 && game_arr[1] == 2 && game_arr[2] == 2)
                    || (game_arr[3] == 2 && game_arr[4] == 2 && game_arr[5] == 2)
                    || (game_arr[6] == 2 && game_arr[7] == 2 && game_arr[8] == 2)
                    || (game_arr[0] == 2 && game_arr[4] == 2 && game_arr[8] == 2)
                    || (game_arr[2] == 2 && game_arr[4] == 2 && game_arr[6] == 2)
                    || (game_arr[0] == 2 && game_arr[3] == 2 && game_arr[6] == 2)
                    || (game_arr[1] == 2 && game_arr[4] == 2 && game_arr[7] == 2)
                    || (game_arr[2] == 2 && game_arr[5] == 2 && game_arr[8] == 2)) {
                System.out.println("O is Winning");
                winFlag = true;
                ComputerScore += 10;
                gameNum++;

                newGame();
                return true;
            }
        }
        return false;
    }

    //calculate movement of computer
    public int getRandom() {
        int move;
        boolean move_flag = false;
        do {
            move = ((int) Math.floor(Math.random() * 10)) % 9;
            System.out.println(move);
            if (setGame_arr(move)) {
                {
                    move_flag = true;

                    return move;
                }

            }
        } while (move_flag == false);
        return -1;
    }

//get who play and switch player
    public void switchPlayerSymbol() {

        if (current_player.equals("X")) {
            current_player = "O";
        } else {
            current_player = "X";
        }

    }

    //start game
    public boolean play(int move, Label l) {

        if (getCurrent_player() == "X") {
            if (setGame_arr(move)) {
                l.setText(current_player);
                switchPlayerSymbol();
            }
            return true;

        };

        return false;

    }

    public void computerAction(int x) {

        System.out.println(x);

        switchPlayerSymbol();
        switch (x) {
            case 0:
                tcUI.lblCell1.setText("O");
                break;
            case 1:

                tcUI.lblCell2.setText("O");
                break;
            case 2:

                tcUI.lblCell3.setText("O");
                break;
            case 3:

                tcUI.lblCell4.setText("O");
                break;
            case 4:

                tcUI.lblCell5.setText("O");
                break;
            case 5:

                tcUI.lblCell6.setText("O");
                break;
            case 6:

                tcUI.lblCell7.setText("O");
                break;
            case 7:

                tcUI.lblCell8.setText("O");
                break;
            case 8:

                tcUI.lblCell9.setText("O");
                break;
        }

    }

    public void gameStart(Label l, int move) {

        if (!winFlag) {
            if ("X".equals(getCurrent_player())) {
                if (play(move, l)) {
                    checkWining();
                }
                if (!winFlag) {
                    if (getCounter() < 9) {

                        computerAction(getRandom());

                        checkWining();
                    } else {
                        if (counter == 9) {

                            gameNum++;
                            newGame();

                        }
                    }

                }

            }
        }
    }

    public void newGame() {
        int x = JOptionPane.showConfirmDialog(null, "play again");
        System.out.println(x + "");
        if (x == 0) {
            new SingleMode(primaryStage);
        } else if (x == 1) {
            new NewModeBase(primaryStage);
        }
    }
}
