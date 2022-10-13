/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gamemechanic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author giang
 */
public class Game {
    private List<StepPosition> listPosition = new ArrayList<StepPosition>();
    private String role = "x";
    private String rolex = null;
    private String roley = null;
    // them
    private int[][] cells; // = 0: chua danh| =1: X | =-1: O
    private int count;

    public Game() {
        this.cells = new int[20][20];
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                this.cells[i][j] = 0;
            }
        }
        this.count = 0;
    }

    public void addPosition(StepPosition step) {
        this.cells[step.getX()][step.getY()] = (role.equals("x")) ? 1 : -1;

        if(role.equals("x")) {
            role = "o";
        }
        else 
            role = "x";
        listPosition.add(step);
        ++count;
    }

    public synchronized boolean isOver() {
        if (count == 20 * 20) {
            return true;
        }
        return false;
    }

    public synchronized String getRole() {
        return role;
    }
    public synchronized String winner() {
        if (winner("x") != null)
            return winner("x");
        else if (winner("y") != null)
            return winner("y");

        return null;
    }

    public synchronized String winner(String role) {
        int player = 0;
        if (role.equals("x")) {
            player = 1;
        } else
            player = -1;
        // hang
        int row = 0;
        for (int i = 0; i < 15; ++i) {
            for (int j = i ; j < 20; ++j) {
                if (cells[i][j] == player) {
                    ++row;
                    if (row > 4)
                        return role;
                } else {
                    row = 0;
                    ++j;
                }
            }
        }

        //cot
        int column = 0;
        for (int i = 0; i < 20; ++i) {
            for (int j = i; j < 20; ++j) {
                if (cells[j][i] == player) {
                    ++column;
                    if (column > 4) {
                        return role;
                    } else {
                        column = 0;
                        ++j;
                    }
                }
            }
        }

        //cheo chinh
        int cheoChinhTren = 0;
        int cheoChinhDuoi = 0;
        for (int i = 0; i < 20; ++i) {
            for (int j = i; j < 20; ++j) {
                if (cells[j - i][j] == player) {
                    cheoChinhTren++;
                    if (cheoChinhTren > 4)
                        return role;
                } else {
                    cheoChinhTren = 0;
                    ++j;
                }
            }
            for (int k = 0; k + i + 1 < 20; ++k) {
                if (cells[k + i + 1][k] == player) {
                    cheoChinhDuoi++;
                    if (cheoChinhDuoi > 4)
                        return role;
                } else {
                    cheoChinhDuoi = 0;
                    ++k;
                }
            }
        }
        // cheo phu
        int cheoPhuDuoi = 0;
        int cheoPhuTren = 0;
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j + i < 20; ++j) {
                if (cells[j + i][19 - j] == player) {
                    cheoPhuDuoi++;
                    if (cheoPhuDuoi > 4)
                        return role;
                } else {
                    cheoPhuDuoi = 0;
                    ++j;
                }
            }
            for (int k = 0; 19 - k - i >= 0; ++k) {
                if (cells[k][19 - k - i] == player) {
                    ++cheoPhuTren;
                    if (cheoPhuTren > 4) {
                        return role;
                    }
                } else {
                    cheoPhuTren = 0;
                    ++k;
                }
            }
        }

        return null;
    }

    public synchronized String getRolex() {
        return rolex;
    }

    public void setRolex(String rolex) {
        this.rolex = rolex;
    }

    public String getRoley() {
        return roley;
    }

    public void setRoley(String roley) {
        this.roley = roley;
    }
    
}
