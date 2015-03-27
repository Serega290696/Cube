package Cube;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Serega on 08.11.14.
 */
public class Main {

    static Game game;
    public static final int fps = 100;
    public static final int delay = 1000 / (int)fps;

    public static void main(String[] args) {
        initDisplay();
        initGL();
        myMain();
    }
    public static void myMain() {
        initGame();

        GameLoop();

        cleanUp();
    }

    //INIT GRAPH: DISPLAY, GL, GAME ---!!!---
    private static void initDisplay() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
            Display.setVSyncEnabled(true);
            Keyboard.create();
            Mouse.create();
            Mouse.setGrabbed(true);
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }
    private static void initGL() {
        // ??????????????
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
        glMatrixMode(GL_MODELVIEW);

        glClearColor(0, 0, 0, 1);

        glDisable(GL_DEPTH_TEST);
        // ??????????????
    }
    private static void initGame() {
        game = new Game();
    }

    //GAME LOOP ---!!!---
    private static void GameLoop() {

        while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {

            getInput();
            update();
            render();
            if(Keyboard.isKeyDown(Keyboard.KEY_BACK))  {
                Main.myMain();
            }


        }
    }
    private static void getInput() {
        game.getInput();
    }
    private static void update() {
        game.update();
    }
    private static void render() {
        // ??????????????
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();

        game.render();

        Display.update();
        Display.sync(fps);
        // ??????????????
    }


    //CLOSE GAME---!!!---
    private static void cleanUp() {
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }




}
