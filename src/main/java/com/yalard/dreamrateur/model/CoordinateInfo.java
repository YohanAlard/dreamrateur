package com.yalard.dreamrateur.model;

import com.yalard.dreamrateur.constants.Direction;
import com.yalard.dreamrateur.constants.CellType;

/**
 * Cell Information and closest cell link
 */
public class CoordinateInfo {
    private CellType cellType;
    private CoordinateInfo upType;
    private CoordinateInfo rightType;
    private CoordinateInfo leftType;
    private CoordinateInfo downType;
    //from direction
    private Direction direction;

    /**
     * constructor
     * @param free cell type
     */
    public CoordinateInfo(boolean free) {
        this.cellType = free ? CellType.SPACE : CellType.WALL;
    }

    /**
     * return true if a neightbour cell is free
     * @return true or false
     */
    public boolean hasFreeSpace() {
        return upType.getCellType() == CellType.SPACE || rightType.getCellType() == CellType.SPACE || leftType.getCellType() == CellType.SPACE || downType.getCellType() == CellType.SPACE;
    }

    public CellType getCellType() {
        return cellType;
    }

    public CoordinateInfo getUpType() {
        return upType;
    }

    public void setUpType(CoordinateInfo upType) {
        this.upType = upType;
    }

    public CoordinateInfo getRightType() {
        return rightType;
    }

    public void setRightType(CoordinateInfo rightType) {
        this.rightType = rightType;
    }

    public CoordinateInfo getLeftType() {
        return leftType;
    }

    public void setLeftType(CoordinateInfo leftType) {
        this.leftType = leftType;
    }

    public CoordinateInfo getDownType() {
        return downType;
    }

    public void setDownType(CoordinateInfo downType) {
        this.downType = downType;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }
}
