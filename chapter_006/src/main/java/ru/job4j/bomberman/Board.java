package ru.job4j.bomberman;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class shows BomberMan game logic.
 * The program does not use user input. Control of players is carried out automatically
 * @author Igor Antropov
 * @version $Id$
 * @since 0.1
 */
public class Board {
    private final int size;
    private final ReentrantLock[][] board;
    private final int playersAmount;
    private final int monstersAmount;
    private final int obstacleAmount;
    private final int stepsForPlayer;
    private final List<Thread> players = new LinkedList<>();

    /**
     * Constructor by default
     */
    public Board() {
        this(3, 2, 1, 10, 2);
    }

    /**
     *
     * @param size - size of board
     * @param playersAmount - amount of players of the game
     * @param stepsForPlayer - numbers of step for every player
     */
    public Board(int size, int playersAmount, int monsterAmount, int stepsForPlayer, int obstacleAmount) {
        this.size = size;
        this.playersAmount = playersAmount;
        this.monstersAmount = monsterAmount;
        this.obstacleAmount = obstacleAmount;
        this.stepsForPlayer = stepsForPlayer;
        board = new ReentrantLock[size][size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Method gets amount of max steps for player
     */
    public int getStepsForPlayers() {
        return this.stepsForPlayer;
    }


    /**
     * Method moveds player from source to destination
     * @param source
     * @param dest
     * @return
     */
    public boolean move(Cell source, Cell dest, int time) {
        boolean result = false;
        try {
            boolean cellFree;
            System.out.println(String.format(" %s Try to lock and move to Cell [%s][%s]", Thread.currentThread().getName(), dest.getX(), dest.getY()));
            cellFree = board[dest.getX()][dest.getY()].tryLock(time, TimeUnit.MILLISECONDS);
            if (cellFree) {
                System.out.println(String.format(" %s Lock acquired. Moved to new Cell [%s][%s]. Old Cell[%s][%s]", Thread.currentThread().getName(), dest.getX(), dest.getY(), source.getX(), source.getY()));
                if (board[source.getX()][source.getY()].isLocked()) {
                    board[source.getX()][source.getY()].unlock();
                    System.out.println(String.format(" %s: UnLock board[%s][%s] acquired.", Thread.currentThread().getName(), source.getX(), source.getY()));
                }
                result = true;
                Thread.sleep(1000);
            } else {
                System.out.println(String.format("%s Lock was refused...", Thread.currentThread().getName()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method looks up next step for player
     * @param cell
     * @return
     */
    public Cell nextStep(Cell cell) {
        int x = cell.getX();
        int y = cell.getY();
        boolean find = false;
        while (!find) {
            int dir = ((Math.random() * 10) > 5) ? 1 : -1;
            if (dir == -1) {
                x = ((Math.random() * 10) > 5) ? x + 1 : x - 1;
                y = cell.getY();
                if (x >= 0 && x < this.size && x != cell.getX()) {
                    find = true;
                }
            } else {
                y = ((Math.random() * 10) > 5) ? y + 1 : y - 1;
                x = cell.getX();
                if (y >= 0 && y < this.size && y != cell.getY()) {
                    find = true;
                }
            }
        }
        return new Cell(x, y);
    }

    /**
     * Method randomly gets free-unlocked cell
     * @return
     * @throws InterruptedException
     */
    private Cell doLockedCell() throws InterruptedException {
        Cell cell;
        do {
            cell = new Cell((int) (Math.random() * size), (int) (Math.random() * size));
        } while (!board[cell.getX()][cell.getY()].tryLock(50, TimeUnit.MILLISECONDS));
        return cell;
    }

    public void createPlayer(int amount, String type) {
        for (int i = 0; i < amount; i++) {
            players.add(new Thread(
                    () -> {
                        Player player = null;
                        try {
                            player = new Player(this, doLockedCell(), type);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        player.go();
                    }));
        }
    }


    public void createObstacles() throws InterruptedException {
        for (int i = 0; i < obstacleAmount; i++) {
            Cell temp = doLockedCell();
            System.out.println(" Obstacle: [" + temp.getX() + "][" + temp.getY() + "]");
        }
    }

    /**
     * Method initializes the board and players
     */
    public void start() throws InterruptedException {
        createPlayer(playersAmount, "BOMBER");
        createPlayer(monstersAmount, "MONSTER");
        createObstacles();
        for (int i = 0; i < (playersAmount + monstersAmount); i++) {
            players.get(i).start();
        }
        for (int i = 0; i < (playersAmount + monstersAmount); i++) {
            players.get(i).join();
        }
    }
}
