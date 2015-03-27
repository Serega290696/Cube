package Cube;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Serega on 09.11.14.
 */
public class Draw {

    public static final double circleDet = 30;
    public static final double Pi = 3.14;
    public static double Fi = 0;
    public static double R = 0;

    public static void draw(int figure, float x, float y, float sx, float sy) {
        switch(figure) {
            case 0:  drawCircle(x, y, sx, sy);
                break;
            case 1:  drawRect(x, y, sx, sy);
                break;
            default:;

        }
    }

    public static void drawCircle(float x, float y, float sx, float sy) {
        x += sx / 2;
        y += sy / 2;
        R = sx / 2;
        glPushMatrix();
        {
            glTranslatef(x, y, 0);
            glRotatef(0, 0, 0, 1);
            glBegin(GL_POLYGON);
            {
                for(int i = 0; i <= 2*circleDet; i++) {
                    Fi = Pi * (i / circleDet);
                    glVertex2f((int) (Math.cos(Fi) * R), (int)(Math.sin(Fi) * R));
                }
            }
            glEnd();
        }
        glPopMatrix();
        x -= sx / 2;
        y -= sy / 2;
    }

    private static void drawRect(float x, float y, float sx, float sy) {
        glPushMatrix();
        {
            glTranslatef(x, y, 0);
            glRotatef(0, 0, 0, 1);
            glBegin(GL_QUADS);
            {
                glVertex2f(0,0);
                glVertex2f(0,sy);
                glVertex2f(sx,sy);
                glVertex2f(sx,0);
            }
            glEnd();
        }
        glPopMatrix();
    }
}
