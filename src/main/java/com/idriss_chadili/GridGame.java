package com.idriss_chadili;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.idriss_chadili.classes.Level;
import com.idriss_chadili.classes.Player;

public class GridGame extends JPanel implements KeyListener, ActionListener {

    private Player player;
    private Level level;
    private Timer timer;

    public GridGame() {
        setPreferredSize(new Dimension(Level.GRID_SIZE * Level.CELL_SIZE, Level.GRID_SIZE * Level.CELL_SIZE));
        setBackground(Color.WHITE);
        addKeyListener(this);
        setFocusable(true);

        player = new Player(0, 0);
        level = new Level(player);

        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        level.draw(g);

        if (player.getX() == level.getGoalX() && player.getY() == level.getGoalY()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("You Win!", 150, 250);
            timer.stop();

            new Timer(2000, e -> {
                player.resetPosition();
                level.generateLevel();
                timer.start();
            }).start();
        }

        if (level.isCollision()) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString("Game Over!", 150, 250);
            timer.stop();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int dx = 0, dy = 0;

        switch (keyCode) {
            case KeyEvent.VK_UP:    dy = -1; break;
            case KeyEvent.VK_DOWN:  dy = 1; break;
            case KeyEvent.VK_LEFT:  dx = -1; break;
            case KeyEvent.VK_RIGHT: dx = 1; break;
        }

        player.move(dx, dy);

        if (level.isCollision()) {
            player.move(-dx, -dy);
        }

        if (player.getX() == level.getGoalX() && player.getY() == level.getGoalY()) {
            level.generateLevel();
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Author: Idriss Chadili");
        GridGame gamePanel = new GridGame();
        frame.add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
