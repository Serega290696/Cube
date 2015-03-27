package Cube.GameObjects;

import Cube.Draw;
import Cube.GO;
import Cube.Draw;
import Cube.GO;
import org.lwjgl.opengl.Display;

import java.util.Random;

public class GOBonus extends GO {

    public GOBonus() {
        this.sx = 25;
        this.sy = 25;
        this.figure = 1;

        Random random = new Random();
        this.x = Math.abs(random.nextInt()) % (Display.getWidth() - 100 + 1);
        random = new Random();
        this.y = Math.abs(random.nextInt()) % (Display.getHeight() - 100 + 1);
    }

    public GOBonus(int figure, float sx, float sy) {
        this.sx = sx;
        this.sy = sy;
        this.figure = figure;

        Random random = new Random();
        this.x = Math.abs(random.nextInt()) % (Display.getWidth() - 100 + 1);
        random = new Random();
        this.y = Math.abs(random.nextInt()) % (Display.getHeight() - 100 + 1);
    }



    @Override
    public void update() {

    }

    @Override
    public void render() {

        Draw.draw(figure, x, y, sx, sy);
    }
    @Override
    public String name() {
        return "bonus";
    }

}
