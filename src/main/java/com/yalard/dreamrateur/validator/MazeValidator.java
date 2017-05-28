package com.yalard.dreamrateur.validator;

import com.yalard.dreamrateur.constants.CellType;
import com.yalard.dreamrateur.exception.TechException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yohanalard on 05/04/2017.
 */
public enum MazeValidator {
    INSTANCE;

    private MazeValidator(){

    }
    private static final String REGEX_WALL_OR_SPACE = "^[" + CellType.WALL.getCharacter() + CellType.SPACE.getCharacter() + "]*$";
    private static final String REGEX_ONLY_WALL = "^[" + CellType.WALL.getCharacter() + "]*$";
    private List<String> lines = new ArrayList<>();
    public void clearLines() {
        lines.clear();
    }

    public void validateLine(String line) throws TechException {
        if (!line.matches(REGEX_WALL_OR_SPACE)) {
            throw new TechException("only M or Space are allowed");
        }

        if (line.toUpperCase().charAt(0) != CellType.WALL.getCharacter()) {
            throw new TechException("must start with M");
        }
        if (line.toUpperCase().charAt(line.length() - 1) != CellType.WALL.getCharacter()) {
            throw new TechException("must end with M");
        }
        if (lines.isEmpty() && !line.matches(REGEX_ONLY_WALL)) {
            throw new TechException("First line should only contains  M ");
        }
        if (line.length() < 3) {
            throw new TechException(" line must contain at least 3 chars");
        }
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }

    public boolean isLastLine(String line) {
        return lines.size() > 1 && line.matches(REGEX_ONLY_WALL);
    }

}
