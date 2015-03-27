package Cube.GameObjects;

import Cube.Draw;
import Cube.GO;
import Cube.Draw;
import Cube.GO;

public class GOWall extends GO {

    public GOWall(int figure, float x,  float y,  float sx,  float sy) {
        this.figure = figure;
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
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
        return "wall";
    }
}
