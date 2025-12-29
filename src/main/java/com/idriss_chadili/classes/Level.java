package com.idriss_chadili.classes;

import java.awt.*;
import java.util.Random;


public class Level {
    public static final int GRID_SIZE = 10;
    public static final int CELL_SIZE = 50;
    private Player player;
    private boolean[][] obstacles;
    private int goalX, goalY;

    public Level(Player player) {
        this.player = player;
        this.obstacles = new boolean[GRID_SIZE][GRID_SIZE];
        generateLevel();
    }

    public void generateLevel() {
        Random rand = new Random();

        // Reset grid and obstacles
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                obstacles[i][j] = false; // Clear obstacles
            }
        }

        // Set random goal position (not at player position)
        goalX = rand.nextInt(GRID_SIZE);
        goalY = rand.nextInt(GRID_SIZE);
        while (goalX == 0 && goalY == 0) { // Avoid player position
            goalX = rand.nextInt(GRID_SIZE);
            goalY = rand.nextInt(GRID_SIZE);
        }

        // Place obstacles (randomly)
        int obstacleCount = rand.nextInt(15) + 5; // Random number of obstacles
        for (int i = 0; i < obstacleCount; i++) {
            int x = rand.nextInt(GRID_SIZE);
            int y = rand.nextInt(GRID_SIZE);
            if (!(x == 0 && y == 0) && !(x == goalX && y == goalY)) {
                obstacles[y][x] = true; // Place obstacle
            }
        }

        // Ensure level is solvable
        while (!Pathfinding.isPathExists(0, 0, goalX, goalY, obstacles)) {
            System.out.println("Level not solvable. Regenerating...");
            generateLevel(); // Regenerate level if no path exists
        }
    }

    public void draw(Graphics g) {
        // Draw grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                g.setColor((i + j) % 2 == 0 ? Color.LIGHT_GRAY : Color.GRAY);
                g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);

                // Draw obstacles (red)
                if (obstacles[i][j]) {
                    g.setColor(Color.RED);
                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                }
            }
        }

        // Draw goal (green)
        g.setColor(Color.GREEN);
        g.fillRect(goalX * CELL_SIZE, goalY * CELL_SIZE, CELL_SIZE, CELL_SIZE);

        // Draw player (blue)
        g.setColor(Color.BLUE);
        g.fillRect(player.getX() * CELL_SIZE, player.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
    }

    public void update() {
        // If the player hits an obstacle, reset their position
        if (obstacles[player.getY()][player.getX()]) {
            player.resetPosition();
        }
    }

    public boolean isCollision() {
        return obstacles[player.getY()][player.getX()]; // Check if player is on an obstacle
    }


    public int getGoalX() {
        return goalX;
    }

    public int getGoalY() {
        return goalY;
    }
}
