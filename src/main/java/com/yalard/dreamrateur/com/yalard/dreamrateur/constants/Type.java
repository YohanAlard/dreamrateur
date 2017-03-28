package com.yalard.dreamrateur.com.yalard.dreamrateur.constants;

/**
 * Created by yohanalard on 24/03/2017.
 */
public enum Type {
    WALL('M'),
    SPACE(' ');

    Type(char character){
        this.character = character;
    }
    private char character;

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}
