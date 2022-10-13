package caroboad;


import constants.Constants;

public class State {
    private int[][] cells;// = 0: chưa đánh, = 1: X, -1:O
    private int turn;
    private int totalStep;

    public State(int[][] cells, int turn, int totalStep) {
        this.cells = cells;
        this.turn = turn;
        this.totalStep = totalStep;
    }

    public State(int turn) {
        this.cells = new int[Constants.SIZE][Constants.SIZE];
        for (int i = 0; i < Constants.SIZE; ++i) {
            for (int j = 0; j < Constants.SIZE; ++j) {
                this.cells[i][j] = 0;
            }
        }
        this.turn = turn;
    }

    public boolean isClickable(int x, int y) {
        if (cells[x][y] != 0)
            return false;
        return true;
    }

    public void updateCell(int x, int y, int turn) {
        if (isClickable(x, y)) {
            cells[x][y] = turn;
        }
    }











    public int[][] getCells() {
        return cells;
    }

    public void setCells(int[][] cells) {
        this.cells = cells;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getTotalStep() {
        return totalStep;
    }

    public void setTotalStep(int totalStep) {
        this.totalStep = totalStep;
    }
}
