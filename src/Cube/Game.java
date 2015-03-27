package Cube; /**
 * Created by Serega on 09.11.14.
 */
import Cube.GameObjects.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;

public class Game {

    ArrayList<GO> objects = new ArrayList<GO>();

    private static GOBall ball;
    private static GOPlayer player;
    private static GOBonus bonus1;
    private static GOBonus bonus2;
    private static GOBonus bonus3;
    private static final int kBonus = 1;
    private static GOWall floor;
    private static GOWall ceiling;
    private static GOWall wall1;
    private static GOWall wall2;


    public Game() {

        ball = new GOBall(0, Display.getWidth() / 2 - 5, Display.getHeight() - 220, 15, objects);
        player = new GOPlayer(1, 50, 50, 80, 80);
        //GOBonus[] allBonus = new GOBonus()[3];
        bonus1 = new GOBonus();
        bonus2 = new GOBonus(1, 25, 25);
        bonus3 = new GOBonus(1, 25, 25);

        floor = new GOWall(1, -800, -800, Display.getWidth() + 1600, 810);
        ceiling = new GOWall(1, -800, Display.getHeight() - 10, Display.getWidth() + 1600, 810);
        wall1 = new GOWall(1, -800, 0, 820, Display.getHeight());
        wall2 = new GOWall(1, Display.getWidth() - 20, 0, 820, Display.getHeight());

        objects.add(ball);
        objects.add(player);

        objects.add(bonus1);
        objects.add(bonus2);
        objects.add(bonus3);
        //for(int i = 0; i < kBonus; i++) {
            //objects.add(allBonus[i]);
        //}


        objects.add(floor);
        objects.add(ceiling);
        objects.add(wall1);
        objects.add(wall2);

    }


    public void getInput() {
        player.moveTo(Mouse.getX(), Mouse.getY());

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && ball.score >= 500 && ball.timeToNextJump <= 0){
            ball.jump();
        }
//        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
//            ball.jump(-1, 0);
//        }
//        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ){
//            ball.jump(1, 0);
//        }
//        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
//            ball.jump(0, 1);
//        }
//        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
//            ball.jump(0, -1);
//        }
//        if(Keyboard.isKeyDown(Keyboard.KEY_E) && !Keyboard.isRepeatEvent()){
//            ball.jump(0, 0);
//        }


    }
    public void update() {
        for(GO go : objects) {
            go.update();
        }

    }
    public void render() {
        for(GO go : objects) {
            go.render();
        }

    }
}
