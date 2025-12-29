package com.idriss_chadili.classes;

public class Player {
    private int x, y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public void move(int dx, int dy) {
        if (x + dx >= 0 && x + dx < Level.GRID_SIZE) x += dx;
        if (y + dy >= 0 && y + dy < Level.GRID_SIZE) y += dy;
    }

    public void resetPosition() {
        x = 0;
        y = 0;
    }
}
