package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 *
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /**
     * The maze
     */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /**
     * Wrapper method.
     */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     *
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     * otherwise, false
     * @pre Possible path cells are in BACKGROUND color;
     * barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     * PATH color; all cells that were visited but are
     * not on the path are in the TEMPORARY color.
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || maze.getColor(x, y) != NON_BACKGROUND) {
            return false;
        }
        if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
        }
        maze.recolor(x, y, PATH);
        if (findMazePath(x + 1, y) || findMazePath(x - 1, y) || findMazePath(x, y - 1) || findMazePath(x, y + 1)) {
            return true;
        } else {
            maze.recolor(x, y, TEMPORARY);
            return false;
        }
    }

    // ADD METHOD FOR PROBLEM 2 HERE
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
        ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
        Stack<PairInt> trace = new Stack<>();
        findMazePathStackBased(0, 0, result, trace);
        return result;
    }

    //Helper method for problem 2
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {

        if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows() || maze.getColor(x, y) != NON_BACKGROUND) {
            return;
        }
        if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> found = new ArrayList<>();
            found.addAll(trace);
            result.add(found);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);
        } else {
            trace.push(new PairInt(x, y));
            maze.recolor(x, y, PATH);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
        }
    }

    // ADD METHOD FOR PROBLEM 3 HERE
    public ArrayList<PairInt> findMazePathMin(int x, int y) {

        ArrayList<PairInt> result = new ArrayList<PairInt>();
        ArrayList<ArrayList<PairInt>> allPath = findAllMazePaths(0, 0);
        int allSize = allPath.size();
        if (allSize == 0) {
            return result;
        }
        int[] pathSize = new int[allSize];
        int minIndex = 0;
        for (int j = 0; j < allSize; j++) {
            pathSize[j] = allPath.get(j).size();
        }
        int size0 = pathSize[0];
        for (int i = 1; i < allSize; i++) {
            if(pathSize[i] < size0){
                minIndex = i;
                size0 = pathSize[i];
            }
        }

        result = allPath.get(minIndex);
        return result;
    }

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
