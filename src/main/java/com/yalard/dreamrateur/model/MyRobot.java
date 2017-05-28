package com.yalard.dreamrateur.model;

import com.yalard.dreamrateur.dao.MazeState;
import com.yalard.dreamrateur.constants.CellType;
import com.yalard.dreamrateur.com.yalard.dreamrateur.services.CommunicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * My robot navigate on the maze
 * he only know information about current position (and neighbour position)
 * he store his local path
 */
public class MyRobot {
    private List<CoordinateInfo> path = new ArrayList<>();
    private CoordinateInfo current = null;

    /** start walking on the maze */
    public void start() {
        //get current position information
        current = MazeState.INSTANCE.getCurrentPosition();
        //start the path way with the initial position
        path.add(current);
        nextMove();
    }


    /**
     * compute next move
     * simple stagegy : go up right down left order.
     * if no move is allowed, simply go back to previous step and apply stategy again.
     */
    private void nextMove() {
        boolean end = Boolean.FALSE;
        if (current.getUpType().getCellType() == CellType.SPACE) {
            current = MazeState.INSTANCE.moveUp(true);
        } else if (current.getRightType().getCellType() == CellType.SPACE) {
            current = MazeState.INSTANCE.moveRight(true);
        } else if (current.getDownType().getCellType() == CellType.SPACE) {
            current = MazeState.INSTANCE.moveDown(true);
        } else if (current.getLeftType().getCellType() == CellType.SPACE) {
            current = MazeState.INSTANCE.moveLeft(true);
        } else {
            //Dead way
            if (getAvailableFreeSpaceList().isEmpty()) {
                //job done
                end = Boolean.TRUE;
            } else {
                // walk back on our step
                //TODO could compute the best move here // get closes point in getAvailableFreeSpaceList
                switch (current.getDirection()) {
                    case UP:
                        current = MazeState.INSTANCE.moveDown(false);
                        break;
                    case DOWN:
                        current = MazeState.INSTANCE.moveUp(false);
                        break;
                    case LEFT:
                        current = MazeState.INSTANCE.moveRight(false);
                        break;
                    case RIGHT:
                        current = MazeState.INSTANCE.moveLeft(false);
                        break;
                }
            }
        }
        current.setCellType(CellType.VISITED);
        path.add(current);
        if (!end) nextMove();
    }

    /**
     * return a sublist path where move is still possible.
     * @return sublist path
     */
    private List<CoordinateInfo> getAvailableFreeSpaceList() {
        return path.stream().filter(coordinateInfo -> coordinateInfo.hasFreeSpace()).collect(Collectors.toList());
    }

}
