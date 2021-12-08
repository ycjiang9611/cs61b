package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import javax.swing.text.Position;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 80;
    private static final int HEIGHT = 80;
    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);


    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        int x0 = p.x; // start x lower left
        int y0 = p.y; // start y lower left
        int i = 0; // level of the hexagon, start with 0
        while (y0 < p.y + s) {
            for (int pt = x0; pt < x0 + s + 2 * i; pt += 1) {
                world[pt][y0] = t;
            }
            x0 -= 1;
            y0 += 1;
            i += 1;
        }
        int x1 = p.x - s + 1; // start x lower left
        int y1 = p.y + s; // start y lower left
        int j = 0;
        while (y1 < p.y + 2 * s) {
            for (int pt = x1; pt < x1 + 3*s - 2 - 2 * j; pt += 1) {
                world[pt][y1] = t;
            }
            x1 += 1;
            y1 += 1;
            j += 1;
        }
    }


    public static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static Position bottomRightNeighbor(Position p, int s) {
        Position res = new Position(0, 0);
        res.x = p.x + 2*s - 1;
        res.y = p.y - s;
        return res;
    }

    public static Position topRightNeighbor(Position p, int s) {
        Position res = new Position(0, 0);
        res.x = p.x + 2*s - 1;
        res.y = p.y + s;
        return res;
    }

    public static void drawRandomVerticalHexes(TETile[][] world, Position p, int s, int N) {
        // start with the top hexagon
        Position pt = new Position(0, 0);
        pt.x = p.x;
        pt.y = p.y;
        for (int i = 0; i < N; i += 1) {
            addHexagon(world, pt, s, randomTile());
            pt.y = pt.y - 2*s;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.MOUNTAIN;
            default: return Tileset.NOTHING;
        }
    }

    public static void main(String[] args) {
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING; // fill with tile type in Tileset.java
            }
        }

        Position p = new Position(8, 60);
        int s = 4; // hexagon size
        drawRandomVerticalHexes(world, p, s, 3);
        Position p1 = topRightNeighbor(p, s);
        drawRandomVerticalHexes(world, p1, s, 4);
        Position p2 = topRightNeighbor(p1, s);
        drawRandomVerticalHexes(world, p2, s, 5);
        Position p3 = bottomRightNeighbor(p2, s);
        drawRandomVerticalHexes(world, p3, s, 4);
        Position p4 = bottomRightNeighbor(p3, s);
        drawRandomVerticalHexes(world, p4, s, 3);

        ter.renderFrame(world);
    }
}
