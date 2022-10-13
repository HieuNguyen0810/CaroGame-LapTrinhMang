package view;

import client.Client;
import constants.Constants;
import gamemechanic.StepPosition;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class BoardView extends JFrame implements ActionListener{
    private JButton[][] cellButtons;
    private JPanel boardPanel;
    private JPanel menuPanel;
    private int[][] board;
    private int count;
    private int[] xUndo;
    private int[] yUndo;
    private boolean[][] isClickable;

    //-------------------------------
    private boolean hasNewPosition;
    private String role;
    private boolean myTurn;
    private String myLabel;
    private String clientLabel;
    private User roomMaster;
    private User clientUser;
    private int totalGames;
    //--------------------
    private JLabel myLabelGUI;
    private JLabel clientLabelGUI;
    private JLabel myWinGames;
    private JLabel clientWinGames;

    public void init() {
        this.setTitle("Cờ Caro");
        this.setSize(1200, 1000);
        this.setResizable(false);

        boardPanel.setSize(1000, 1000);
        boardPanel.setLayout(new GridLayout(20, 20));
        boardPanel.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
        for (int i = 0; i < Constants.SIZE; ++i) {
            for (int j = 0; j < Constants.SIZE; ++j) {
                cellButtons[i][j] = new JButton("");
                cellButtons[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                cellButtons[i][j].setActionCommand(i + "-" + j);
                cellButtons[i][j].addActionListener(this);
                cellButtons[i][j].setBackground(Color.WHITE);
                boardPanel.add(cellButtons[i][j]);
            }
        }

        menuPanel.setSize(500, 800);
        menuPanel.setVisible(true);
        menuPanel.setLayout(new GridLayout(4, 2));

//        setSize(300, 400);
        menuPanel.setBounds(800, 100, 500, 800);
//        menuPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

//        setContentPane(contentPane);
//        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Bạn");
        lblNewLabel.setBounds(851, 26, 55, 21);
        menuPanel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Đối thủ");
        lblNewLabel_1.setBounds(900, 29, 49, 14);
        menuPanel.add(lblNewLabel_1);

        myLabelGUI = new JLabel(this.myLabel);
        myLabelGUI.setForeground(Color.RED);
        myLabelGUI.setBounds(851, 54, 49, 14);
        menuPanel.add(myLabelGUI);

        clientLabelGUI = new JLabel(clientLabel);
        clientLabelGUI.setForeground(Color.BLUE);
        clientLabelGUI.setBounds(900, 54, 49, 14);
        menuPanel.add(clientLabelGUI);

        myWinGames = new JLabel("0");
        myWinGames.setForeground(Color.BLUE);
        myWinGames.setBounds(851, 79, 49, 14);
        menuPanel.add(myWinGames);

        clientWinGames = new JLabel("0");
        clientWinGames.setForeground(Color.RED);
        clientWinGames.setBounds(900, 79, 49, 14);
        menuPanel.add(clientWinGames);

        this.setLayout(new BorderLayout());
        this.add(boardPanel, BorderLayout.CENTER);
        this.add(menuPanel, BorderLayout.EAST);


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public BoardView(String role) throws IOException {
        if (role.equals("room master")) {
            this.myLabel = "X";
            this.clientLabel = "O";
            this.totalGames = 0;
            this.myTurn = true;
        } else if (role.equals("client")) {
            this.myLabel = "O";
            this.clientLabel = "X";
            this.totalGames = 0;
            this.myTurn = false;
        }

        this.role = role;
        this.hasNewPosition = false;
        this.board = new int[Constants.SIZE][Constants.SIZE];
        this.cellButtons = new JButton[Constants.SIZE][Constants.SIZE];
        this.boardPanel = new JPanel();
        this.menuPanel = new JPanel();
        this.count = 0;
        this.xUndo = new int[Constants.SIZE * Constants.SIZE];
        this.yUndo = new int[Constants.SIZE * Constants.SIZE];
        this.isClickable = new boolean[Constants.SIZE][Constants.SIZE];
        for (int i = 0; i < Constants.SIZE; ++i) {
            for (int j = 0; j < Constants.SIZE; ++j) {
                this.board[i][j] = 0;
                this.isClickable[i][j] = true;
            }
        }
        init();
    }

    //-------------- Update sau khi 1 game kết thúc ------------------
    public void updateInformation(int user) {
        this.myLabelGUI.setText("O");
        this.clientLabelGUI.setText("X");
        if (user == 1) {
            int myWinGames = Integer.parseInt(this.myWinGames.getText()) + 1;
            this.myWinGames.setText(myWinGames + "");
        }
        else {
            int clientWinGames = Integer.parseInt(this.clientWinGames.getText()) + 1;
            this.clientWinGames.setText(clientWinGames + "");
        }
    }
    //----------------------------------------------------------------

    public void setClientUser(User clientUser) {
        this.clientUser = clientUser;
    }

    public boolean getIsClickable(int x, int y) {
        return this.isClickable[x][y];
    }

    public boolean getHasNewPosition() {
        return this.hasNewPosition;
    }

    public String getNewPosition() {
        return this.xUndo[this.count - 1] + " " + this.yUndo[this.count - 1];
    }

    public void setHasNewPosition(boolean value) {
        this.hasNewPosition = value;
    }

//    public void getMoveSteps() {
//        for (int i = 0; i < xUndo.length; ++i) {
//            if (xUndo[i] != 0 && yUndo[i] != 0)
//            System.out.println(xUndo[i] + ", " + yUndo[i]);
//        }
//    }

    public void clearBoard() {
        for (int i = 0; i < Constants.SIZE; ++i) {
            for (int j = 0; j < Constants.SIZE; ++j) {
                cellButtons[i][j] = new JButton("");
                cellButtons[i][j].setFont(new Font("Comic Sans MS", Font.BOLD, 20));
                cellButtons[i][j].setActionCommand(i + "-" + j);
                cellButtons[i][j].addActionListener(this);
                cellButtons[i][j].setBackground(Color.WHITE);
            }
        }
    }

    public void updatePosition(int x, int y) {
        if (isClickable[x][y]) {
            if (count % 2 == 0) {
                this.cellButtons[x][y].setForeground(Color.RED);
                this.cellButtons[x][y].setText("X");
                this.board[x][y] = Constants.X;
            } else {
                this.cellButtons[x][y].setForeground(Color.blue);
                this.cellButtons[x][y].setText("O");
                this.board[x][y] = Constants.O;
            }
            isClickable[x][y] = false;
            xUndo[count] = x;
            yUndo[count] = y;
            hasNewPosition = true;


            this.cellButtons[x][y].setBackground(new Color(178, 190, 181));
            if (count > 0)
                this.cellButtons[xUndo[count - 1]][yUndo[count - 1]].setBackground(Color.WHITE);

            ++this.count;
        }
    }

    public void updatePosition(String role, int x, int y) {
        if (isClickable[x][y]) {
            if (role.equalsIgnoreCase(this.role)) {
                this.cellButtons[x][y].setForeground(Color.BLUE);
                this.cellButtons[x][y].setText(myLabel);
                this.board[x][y] = myLabel.equals("X") ? Constants.X : Constants.O;
                this.myTurn = false;
            } else {
                this.cellButtons[x][y].setForeground(Color.RED);
                this.cellButtons[x][y].setText(clientLabel);
                this.board[x][y] = myLabel.equals("O") ? Constants.O : Constants.X;
                this.myTurn = true;
            }

            isClickable[x][y] = false;
            xUndo[count] = x;
            yUndo[count] = y;
            hasNewPosition = true;

//        getMoveSteps();

            this.cellButtons[x][y].setBackground(new Color(178, 190, 181));
            if (count > 0)
                this.cellButtons[xUndo[count - 1]][yUndo[count - 1]].setBackground(Color.WHITE);

            ++this.count;
        }
    }

    // ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        String mouseClicked = e.getActionCommand();

        String[] positions = mouseClicked.split("-");
        int x = Integer.parseInt(positions[0]);
        int y = Integer.parseInt(positions[1]);
        if (myTurn) {
            updatePosition(this.role, x, y);
        }

        if (this.getHasNewPosition()) {
            System.out.println(this.getNewPosition());
            try {
                StepPosition stepPosition = new StepPosition(myLabel.toLowerCase(), x, y);
                System.out.println(stepPosition);
                Client.socketHandler.sendObjectToServer(stepPosition);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            this.setHasNewPosition(false);
        }
    }

    public boolean isRoomMasterTurn() {
        return myTurn;
    }

    public void setRoomMasterTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
}
