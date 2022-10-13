package model;

import view.BoardView;

public class Room {
    private User roomMaster;
    private User client;
    private BoardView boardView;
    private int totalGames;

    public Room(User roomMaster) {
        this.roomMaster = roomMaster;
        this.totalGames = 0;
    }













    public User getRoomMaster() {
        return roomMaster;
    }

    public void setRoomMaster(User roomMaster) {
        this.roomMaster = roomMaster;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }
}
