import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import svu.csc213.Dialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Locale;
import java.util.Random;

public class HangMan extends GraphicsProgram {

    private String[] wordsArr = {"bunny", "bus", "sublime"};
    private Random rand;
    public String targetWord;
    private Blank blank;

    @Override
    public void init() {
        rand = new Random();
        targetWord = wordsArr[rand.nextInt(3)];

        for (int i = 0; i < targetWord.length(); i++) {

            double blankX = (getWidth()/2 - (blank.WIDTH)+15)*(targetWord.length()/2) + (blank.WIDTH+15)*i;
            blank = new Blank(blankX, 300, targetWord.charAt(i));
            blank.setFilled(true);
            add(blank);
        }


    }

    public char toChar(int letterNumber) {
        char letter = 0;
        switch(letterNumber) {
            case 1:
                letter = 'A';
                break;
            case 2:
                letter = 'B';
                break;
            case 3:
                letter = 'C';
                break;
            case 4:
                letter = 'D';
                break;
            case 5:
                letter = 'E';
                break;
            case 6:
                letter = 'F';
                break;
            case 7:
                letter = 'G';
                break;
            case 8:
                letter = 'H';
                break;
            case 9:
                letter = 'I';
                break;
            case 10:
                letter = 'J';
                break;
            case 11:
                letter = 'K';
                break;
            case 12:
                letter = 'L';
                break;
            case 13:
                letter = 'M';
                break;
            case 14:
                letter = 'N';
                break;
            case 15:
                letter = 'O';
                break;
            case 16:
                letter = 'P';
                break;
            case 17:
                letter = 'Q';
                break;
            case 18:
                letter = 'R';
                break;
            case 19:
                letter = 'S';
                break;
            case 20:
                letter = 'T';
                break;
            case 21:
                letter = 'U';
                break;
            case 22:
                letter = 'V';
                break;
            case 23:
                letter = 'W';
                break;
            case 24:
                letter = 'X';
                break;
            case 25:
                letter = 'Y';
                break;
            case 26:
                letter = 'Z';
                break;
            default:
                break;
        }
        return letter;
    }

    @Override
    public void run() {
        System.out.println(targetWord);

        getGuess();
            for (int i = 0; i < targetWord.length(); i++) {

            }

    }

    private char getGuess() {
        String letter = Dialog.getString("What letter do you guess?");

        if(letter.length() > 1) {
            Dialog.showMessage("Sorry, please enter a single letter");
            getGuess();
        } else {
            return letter;
        }
    }

    public static void main(String[] args) {
        HangMan x = new HangMan();
        x.start();
    }
}
