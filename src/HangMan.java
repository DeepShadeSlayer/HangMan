import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import java.util.Random;

public class HangMan extends GraphicsProgram {

    private String[] wordsArr = {"bunny", "bus", "sublime"};
    private Random rand;
    public String targetWord;
    private GRect blank;

    @Override
    public void init() {
        rand = new Random();
        targetWord = wordsArr[rand.nextInt(3)];

        for (int i = 0; i < targetWord.length(); i++) {
            blank = new GRect(50,5);
            double blankX = (getWidth()/2 - (blank.getWidth()+15)*(targetWord.length()/2)) + (blank.getWidth()+15)*i;
            blank.setFilled(true);
            add(blank, blankX, 300);
        }
    }

    @Override
    public void run() {
        System.out.println(targetWord);

    }

    public static void main(String[] args) {
        HangMan x = new HangMan();
        x.start();
    }
}
