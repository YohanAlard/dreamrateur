package com.yalard.dreamrateur.com.yalard.dreamrateur.services;

import com.yalard.dreamrateur.com.yalard.dreamrateur.TechException;
import com.yalard.dreamrateur.com.yalard.dreamrateur.constants.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yohanalard on 24/03/2017.
 */
public class MapService {
    private List<String> lines = new ArrayList<>();


    private static final String REGEX_WALL_OR_SPACE = "^[" + Type.WALL.getCharacter() + Type.SPACE.getCharacter() + "]*$";
    private static final String REGEX_ONLY_WALL = "^[" + Type.WALL.getCharacter() + "]*$";

    public void validateLine(String line) throws TechException {
        if (!line.matches(REGEX_WALL_OR_SPACE)) {
            throw new TechException("only M or Space are allowed");
        }

        if (line.toUpperCase().charAt(0) != Type.WALL.getCharacter()) {
            throw new TechException("must start with M");
        }
        if (line.toUpperCase().charAt(line.length() - 1) != Type.WALL.getCharacter()) {
            throw new TechException("must end with M");
        }
        if (lines.isEmpty() && !line.matches(REGEX_ONLY_WALL)) {
            throw new TechException("First line should only contains  M ");
        }
        if (line.length() < 3) {
            throw new TechException(" line must contain at least 3 chars");
        }

    }

    public boolean isLastLine(String line) {
        return lines.size() > 1 && line.matches(REGEX_ONLY_WALL);
    }

    public void addLine(String line) throws TechException {
        if (!lines.isEmpty() && line.length() != lines.get(0).length()) {
            throw new TechException("the line must contains" + lines.get(0).length() + "chars");
        }

        lines.add(line);
    }

    public List<String> getLines(){
        return lines;
    }
}
