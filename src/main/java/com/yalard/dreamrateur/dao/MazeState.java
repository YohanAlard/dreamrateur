package com.yalard.dreamrateur.dao;

import com.yalard.dreamrateur.com.yalard.dreamrateur.services.CommunicationService;
import com.yalard.dreamrateur.exception.TechException;
import com.yalard.dreamrateur.constants.Direction;
import com.yalard.dreamrateur.model.CoordinateInfo;
import com.yalard.dreamrateur.validator.MazeValidator;

import java.util.List;

/**
 * MazeState is a singleton that store the map information
 * this component is the only one to know the maze information
 */
public enum MazeState {
    INSTANCE;

    private CoordinateInfo[][] map;
    private int currentX;
    private int currentY;
    private CommunicationService communicationService = new CommunicationService();

    /**
     * singleton constructor
     **/
    private MazeState() {

    }

    /**
     * add a line to maze
     *
     * @param line line
     * @throws TechException exception is line is not valid
     */
    public void addLine(String line) throws TechException {
        List<String> lines = MazeValidator.INSTANCE.getLines();
        if (!lines.isEmpty() && line.length() != lines.get(0).length()) {
            throw new TechException("the line must contains" + lines.get(0).length() + "chars");
        }
        lines.add(line);
    }

    /**
     * set robot position
     *
     * @param x x position (column)
     * @param y y position (line)
     */
    public void setRobotPosition(int x, int y) {
        List<String> lines = MazeValidator.INSTANCE.getLines();
        map = new CoordinateInfo[lines.size()][lines.get(0).length()];
        int raw = 0;
        for (String line : lines) {
            parseLine(line, raw);
            raw++;
        }
        currentX = x;
        currentY = y;
    }

    /**
     * convert lines to 2 dimensional array
     *
     * @param line line
     * @param raw  raw index
     */
    private void parseLine(String line, int raw) {
        for (int column = 0; column < line.length(); column++) {
            boolean isWhiteSpace = Character.isWhitespace(line.charAt(column));
            CoordinateInfo coordinateInfo = new CoordinateInfo(isWhiteSpace);
            map[raw][column] = coordinateInfo;
        }
    }

    /**
     * robot move up
     *
     * @param saveHistory save previous direction flag
     * @return new point location info
     */
    public CoordinateInfo moveUp(boolean saveHistory) {
        currentY--;
        CoordinateInfo c = logAndScan(saveHistory, Direction.UP);
        return c;
    }

    /**
     * robot move right
     *
     * @param saveHistory save previous direction flag
     * @return new point location info
     */
    public CoordinateInfo moveRight(boolean saveHistory) {
        currentX++;
        CoordinateInfo c = logAndScan(saveHistory, Direction.RIGHT);
        return c;
    }

    /**
     * robot move down
     *
     * @param saveHistory save previous direction flag
     * @return new point location info
     */
    public CoordinateInfo moveDown(boolean saveHistory) {
        currentY++;
        CoordinateInfo c = logAndScan(saveHistory, Direction.DOWN);
        return c;
    }

    private CoordinateInfo logAndScan(boolean saveHistory, Direction direction) {
        communicationService.say("[" + +(currentX+1) + "/" + (currentY+1) + "]");
        CoordinateInfo c = sonar();
        if (saveHistory)
            c.setDirection(direction);
        return c;
    }

    /**
     * robot move left
     *
     * @param saveHistory save previous direction flag
     * @return new point location info
     */
    public CoordinateInfo moveLeft(boolean saveHistory) {
        currentX--;
        return logAndScan(saveHistory, Direction.LEFT);
    }

    /**
     * return current position information
     *
     * @return point location info
     */
    public CoordinateInfo getCurrentPosition() {
        return logAndScan(false, null);
    }

    /**
     * scan around position
     *
     * @return position and neightbour position information
     */
    public CoordinateInfo sonar() {
        CoordinateInfo current = map[currentY][currentX];
        current.setLeftType(map[currentY][currentX - 1]);
        current.setRightType(map[currentY][currentX + 1]);
        current.setUpType(map[currentY - 1][currentX]);
        current.setDownType(map[currentY + 1][currentX]);
        return current;
    }
}
