package com.yalard.dreamrateur.constants;

/**
 * describe a cell type
 */
public enum CellType {
    WALL('M'),
    SPACE(' '),
    VISITED('V');

    CellType(char character) {
        this.character = character;
    }

    private char character;

    public char getCharacter() {
        return character;
    }

}
