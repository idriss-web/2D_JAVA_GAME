package com.idriss_chadili.classes;

import java.util.*;

public class Pathfinding {

    public static boolean isPathExists(int startX, int startY, int goalX, int goalY, boolean[][] obstacles) {
        boolean[][] visited = new boolean[Level.GRID_SIZE][Level.GRID_SIZE];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX, startY));
        visited[startY][startX] = true;

        int[] directions = {-1, 0, 1, 0, 0, -1, 0, 1};

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;

            if (x == goalX && y == goalY) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + directions[i * 2];
                int ny = y + directions[i * 2 + 1];

                if (nx >= 0 && ny >= 0 && nx < Level.GRID_SIZE && ny < Level.GRID_SIZE && !visited[ny][nx] && !obstacles[ny][nx]) {
                    queue.add(new Point(nx, ny));
                    visited[ny][nx] = true;
                }
            }
        }

        return false;
    }

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
