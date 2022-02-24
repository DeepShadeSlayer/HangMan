import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import svu.csc213.Dialog;

import java.util.Locale;
import java.util.Random;
import acm.graphics.GObject;

public class HangMan extends GraphicsProgram {

    private String[] wordsArr = {"bunny", "bus", "sublime"};
    private Random rand;
    public String targetWord;
    public Blank blank;
    private Blank[] blanks;
    private int correctCount, limbsGone;

    private GOval head;
    private GLine torso, lArm, rArm, lLeg, rLeg;

    @Override
    public void init() {
        addStickMan()
        correctCount = 0;
        limbsGone = 0;
        rand = new Random();
        targetWord = wordsArr[rand.nextInt(3)];
        blanks = new Blank[targetWord.length()];

        for (int i = 0; i < targetWord.length(); i++) {

            double blankX = (getWidth()/2) - (blank.WIDTH+15)*(targetWord.length()/2) + (blank.WIDTH + 15)*i;
            blanks[i] = new Blank(blankX, 300);
            blanks[i].setFilled(true);
            add(blanks[i]);
        }


    }

    private void addStickMan() {

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

        while(limbsGone != 10) {
            if(!checkAnswer()) {
                limbsGone++;
                handleLimbLoss;
            }
            if(correctCount == targetWord.length()) {
                Dialog.showMessage("You did it!");
                stop();
            }

        }
        Dialog.showMessage("Oops! You didn't guess it!");
        stop();
    }

    public boolean checkAnswer() {
        boolean correct = false;
        String guess = getGuess();

        for (int i = 0; i < targetWord.length(); i++) {
            if(targetWord.charAt(i) == guess.charAt(0)) {
                GLabel letterLabel = new GLabel(guess);
                double letterLabelX = blanks[i].getX() + blanks[i].getWidth()/2 - letterLabel.getWidth()/2;
                letterLabel.setLocation(letterLabelX,blanks[0].getY() - 10);
                if(this.getElementAt(letterLabel.getX(), letterLabel.getY()) != null) {
                    Dialog.showMessage("You already guessed that.");
                    checkAnswer();
                    correct = true;
                    break;
                }
                add(letterLabel);
                correct  = true;
                correctCount++;
            }
        }

        return correct;
    }

    private String getGuess() {
        String letter = Dialog.getString("What letter do you guess?");

        if(letter.length() > 1 || letter.length() < 1) {
            Dialog.showMessage("Sorry, please enter a single letter");
            getGuess();
        }
        return letter;
    }

    private void handleLimbLoss() {

    }

    public static void main(String[] args) {
        HangMan x = new HangMan();
        x.start();
    }
}
