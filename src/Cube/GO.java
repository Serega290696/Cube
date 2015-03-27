package Cube;

/**
 * Created by Serega on 09.11.14.
 */
public abstract class GO {

    public float x;
    public float y;
    public float sx;
    public float sy;

    public int figure = 0;

    public void draw(int figure) {
        Draw.draw(figure, x, y, sx, sy);
    }


    public abstract String name();
    public abstract void update();
    public abstract void render();

    public float getX() {
        return x;
    }
    public float getSX() {
        return sx;
    }
    public float getY() {
        return y;
    }
    public float getSY() {
        return sy;
    }



}
