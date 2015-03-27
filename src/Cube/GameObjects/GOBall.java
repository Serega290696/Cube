package Cube.GameObjects;

import Cube.Draw;
import Cube.GO;
import Cube.GO;
import Cube.Draw;
import Cube.Main;
import Cube.Physics;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Serega on 12.11.14.
 */
public class GOBall extends GO {


    int j = 0;
    private double xspeed = 7;
    private double yspeed = 9;
    private double xmaxspeed = 18;
    private double ymaxspeed = 18;
    private double loseEnergy = 0.8;
    private float t = 0;
    private float g = (float) -10;

    /*
    public float x = 10;
    public float y = 10;
    public float sx = 10;
    public float sy = 10;  */
    private double y0;

    private int figure = 0;
    ArrayList<GO> objects;
    public float score = 0;
    public float defaultTimeToNextJump = 10000;
    public float timeToNextJump = 0;
    public float timeToShowInfo = 0;
    public float defTimeToShowInfo = 2000;

    public GOBall() {}

    public GOBall(int figure, float x, float y, float sx, ArrayList<GO> objects) {
        this.figure = figure;
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sx;
        this.objects = objects;
        y0 = y;
    }

    @Override
    public String name() {
        return "ball";
    }

    @Override
    public void update() {
        timeToShowInfo-= 10;
        if(timeToShowInfo <= 0) {
            timeToShowInfo = defTimeToShowInfo;
        }

//        System.out.println("<<< Score: " + (int)score + " >>>");
        //System.out.println("Y: " + y + ".\tX: " + x + ".\tYspeed: " + yspeed + ".\tXspeed: " + xspeed);
        score+= 0.05f;

        if(timeToNextJump > 0) {
            timeToNextJump -= Main.delay;
        }
        if(timeToNextJump <= 0) {
            timeToNextJump--;
        }
        if(timeToNextJump <= -50) {
            timeToNextJump = 0;
            System.out.println("<<< Score: " + (int)score + " >>>");
            System.out.println("<<< SUPER JUMP READY >>> ");
        }

        for(GO ob : objects) {


            if (Physics.checkCollisions(this, ob) && this.equals(ob) == false)  {
//                System.out.println("Collisions = TRUE!");
                if(ob.name().equals("bonus")) {
                    Random random = new Random();
                    ob.x = Math.abs(random.nextInt()) % (Display.getWidth() - 100 + 1);
                    random = new Random();
                    ob.y = Math.abs(random.nextInt()) % (Display.getHeight() - 100 + 1);
                    giveBonus();
                    continue;
                }
                else {
                    yspeed *= loseEnergy;
                    xspeed *= loseEnergy;
                    //if(yspeed <= 0.1 / Cube.Main.fps) yspeed = 0;
                    y += yspeed;
                }

                if((this.getX() < ob.getX() + ob.getSX() || this.getX() + this.getSX() > ob.getX())
                   &&(this.getY() < ob.getY() + ob.getSY() || this.getY() + this.getSY() > ob.getY())) {

                    float temp = Math.abs(this.getY() - (ob.getY() + ob.getSY()));
                    if(temp > Math.abs(this.getY() + this.getSY() - ob.getY())) {
                        temp = Math.abs(this.getY() + this.getSY() - ob.getY());
                    }
                    if(temp > (Math.abs(this.getX() - (ob.getX() + ob.getSX())))) {
                        temp = Math.abs(this.getX() - (ob.getX() + ob.getSX()));
                    }
                    if(temp > (Math.abs(this.getX() + this.getSX() - ob.getX()))) {
                        temp = Math.abs(this.getX() + this.getSX() - ob.getX());
                    }


                    if(temp == Math.abs(this.getY() - (ob.getY() + ob.getSY()))) hit(0);
                    if(temp == Math.abs(this.getY() + this.getSY() - ob.getY())) hit(2);
                    if(temp == Math.abs(this.getX() - (ob.getX() + ob.getSX()))) hit(1);
                    if(temp == Math.abs(this.getX() + this.getSX() - ob.getX())) hit(3);
                }
                else {
                    if(this.getY() < ob.getY() + ob.getSY()) hit(0);
                    if(this.getY() + this.getSY() > ob.getY()) hit(2);
                    if(this.getX() < ob.getX() + ob.getSX()) hit(1);
                    if(this.getX() + this.getSX() > ob.getX()) hit(3);
                }


            }
        }


        t += Main.delay;
        yspeed += g / Main.fps;


        if(xmaxspeed <= Math.abs(xspeed)) xspeed = Math.signum(xspeed) * xmaxspeed;
        if(ymaxspeed <= Math.abs(yspeed)) yspeed = Math.signum(yspeed) * ymaxspeed;

        y += yspeed;
        x += xspeed;


        /*if(y <= 0) try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }     */



    }

    @Override
    public void render() {
        Draw.draw(figure, x, y, sx, sy);
    }

    public void jump(int dx, int dy) {
        if(dx == 1)
            xspeed += 0.2;
        else if(dx == -1)
            xspeed += (-0.2);
        else if(dy == 1)
            yspeed = Math.abs(yspeed) + 0.2;
        else if(dy == -1)
            yspeed = -Math.abs(yspeed) - 0.2;
        else
            yspeed = 5 + yspeed;
    }

    public void hit(int side) {
        if(side == 0) yspeed = Math.abs(yspeed) + 0.5;
        else if(side == 1) xspeed = Math.abs(xspeed) + 0.5;
        else if(side == 2) yspeed = -Math.abs(yspeed) - 0.5;
        else if(side == 3) xspeed = -Math.abs(xspeed) - 0.5;
    }

    public void giveBonus() {
        score+=100;
        yspeed *= 3;
        xspeed *= 3;
    }

    public void jump() {
        System.out.println("     SUPER JUMP *** SUPER JUMP *** SUPER JUMP");
        timeToNextJump = defaultTimeToNextJump;
        score-=500;
        xspeed = xmaxspeed;
        yspeed = ymaxspeed;
    }
}
