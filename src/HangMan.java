import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.program.GraphicsProgram;
import svu.csc213.Dialog;

import java.util.Random;

public class HangMan extends GraphicsProgram {

    private String[] wordsArr = {"bunny", "bus", "sublime", "committee", "biography", "printer", "bench", "pavement", "reptile", "sleep", "transport", "push", "bullet", "dramatic", "welcome", "admire", "dozen", "beef", "fork", "crisis", "cucumber", "stubborn", "philosophy", "take", "walk", "pasture", "install", "minor", "active", "stem", "desk", "debt", "superintendent"};
    private Random rand;
    public String targetWord;
    public Blank blank;
    private Blank[] blanks;
    private int correctCount, limbsGone;

    private GOval head;
    private GLine torso, lArm, rArm, lLeg, rLeg, lEye, rEye, mouth;

    private GLabel wrongGuess;

    @Override
    public void init() {
        addStickMan();
        correctCount = 0;
        limbsGone = 0;
        rand = new Random();
        targetWord = wordsArr[rand.nextInt(wordsArr.length)];
        blanks = new Blank[targetWord.length()];
        wrongGuess = new GLabel("Wrong Guesses: ");
        add(wrongGuess, 10, 75);

        for (int i = 0; i < targetWord.length(); i++) {
            //double blankX = (getWidth()/2) - (blank.WIDTH+15)*(targetWord.length()/2) + (blank.WIDTH + 15)*i;
            double blankX = 100 + (blank.WIDTH+15)*i;
            blanks[i] = new Blank(blankX, 400);
            blanks[i].setFilled(true);
            add(blanks[i]);
        }
    }

    private void addStickMan() {
        head = new GOval(50,50);
        add(head, getWidth()*.75 - head.getWidth()/2, 75);

        torso = new GLine(head.getX() + head.getWidth()/2,head.getY() + head.getHeight(), head.getX() + head.getWidth()/2, head.getY() + head.getHeight()+100);
        add(torso);

        lArm = new GLine(torso.getX(), torso.getY() + 25, torso.getX() - 50, torso.getY() +75);
        rArm = new GLine(torso.getX(), torso.getY()+25, torso.getX() + 50, torso.getY() + 75);
        add(lArm);
        add(rArm);

        lLeg = new GLine(torso.getX(), torso.getY()+100, torso.getX() - 50, torso.getY() + 175);
        rLeg = new GLine(torso.getX(), torso.getY()+100, torso.getX() +50, torso.getY() + 175);
        add(lLeg);
        add(rLeg);

        lEye = new GLine(head.getX() + 15, head.getY()+15, head.getX() + 15, head.getY()+ 25);
        rEye = new GLine(head.getX()+head.getWidth() - 15, head.getY() + 15, head.getX()+head.getWidth() - 15, head.getY() + 25);
        add(lEye);
        add(rEye);

        mouth = new GLine(lEye.getX(), lEye.getY() + 20, rEye.getX(), rEye.getY() + 15);
        add(mouth);
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

        while(limbsGone != 9) {

            if(!checkAnswer()) {
                handleLimbLoss();
            }
            if(correctCount == targetWord.length()) {
                Dialog.showMessage("You did it!");
                stop();
                waitForClick();
                exit();
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
                    correctCount--;
                }
                add(letterLabel);
                correct  = true;
                correctCount++;
                System.out.println(correctCount);
            }
        }
        if(!correct) {
            limbsGone++;
            GLabel wGuess = new GLabel(guess);
            add(wGuess, 10, wrongGuess.getY() + limbsGone*wGuess.getHeight());
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
        switch(limbsGone) {
            case 1:
                lLeg.setVisible(false);
                break;
            case 2:
                rLeg.setVisible(false);
                break;
            case 3:
                lArm.setVisible(false);
                break;
            case 4:
                rArm.setVisible(false);
                break;
            case 5:
                torso.setVisible(false);
                break;
            case 6:
                head.setVisible(false);
                break;
            case 7:
                mouth.setVisible(false);
                break;
            case 8:
                lEye.setVisible(false);
                break;
            case 9:
                rEye.setVisible(false);
                break;
        }
    }

    public static void main(String[] args) {
        HangMan x = new HangMan();
        x.start();
    }
}
