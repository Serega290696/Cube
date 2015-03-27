package Cube.GameObjects;

import Cube.Draw;
import Cube.Draw;
import Cube.GO;
import org.lwjgl.input.Mouse;

/**
 * Created by Serega on 09.11.14.
 */
public class GOPlayer extends GO {

                         /*
        public float x = 0;
        public float y = 0;
        public float sx = 50;
        public int sy = 50; */

    public int figure = 0;

    private Cube.GameObjects.GOBall ball;


    public GOPlayer() {
    }

    public GOPlayer(int figure, float x,  float y,  float sx,  float sy) {
        this.x = x;
        this.sx = sx;
        this.y = y;
        this.sy = sy;
        this.figure = figure;
        this.ball = ball;
    }


    @Override
    public void update() {
        moveTo(Mouse.getX(), Mouse.getY());
    }

    @Override
    public void render() {
        Draw.draw(figure, x, y, sx, sy);
    }


    public void moveTo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String name() {
        return "player";
    }

}
