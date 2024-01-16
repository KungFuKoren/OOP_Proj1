import java.util.*;
import java.util.Comparator;

public class GameLogic implements PlayableLogic {
    public static ConcretePiece[][] GameBoard;
    private ConcretePlayer Player1; //Defender
    private ConcretePlayer Player2; //Attacker
    public boolean Turn = false;
    public boolean KingDead = false;
    public Stack<Position[]> gamePlay;


    ConcretePiece[][] resetBoard = new ConcretePiece[11][11];


    public GameLogic() {
        this.Player1 = new ConcretePlayer(true);
        this.Player2 = new ConcretePlayer(false);
        reset();
    }

    @Override
    public boolean move(Position a, Position b) {
        if (this.getPieceAtPosition(a) == null || this.getPieceAtPosition(b) != null) {
            return false; // check if chosen square isn't empty and destination is clear
        }
        if ((!this.Turn && this.getPieceAtPosition(a).getOwner().isPlayerOne()) ||
                (this.Turn && !this.getPieceAtPosition(a).getOwner().isPlayerOne())) {
            return false; // if turn = false its Player2 turn same vice versa
        }

        int aX = a.getX(), aY = a.getY(), bX = b.getX(), bY = b.getY();
        boolean currentPieceIsKing = getPieceAtPosition(a).getType().equals("♔");

        if (((bX == 0 && bY == 0) || (bX == 10 && bY == 0) || (bX == 0 && bY == 10) ||
                (bX == 10 && bY == 10)) && (!currentPieceIsKing)) {
            return false;
        }

        if (!isPathClear(a, b)) {
            return false;
        }
        GameBoard[aX][aY].hasBeen.add(b);
        GameBoard[bX][bY] = GameBoard[aX][aY];
        GameBoard[aX][aY] = null;
        if (!this.getPieceAtPosition(b).getType().equals("♔")) checkKill(b);
        this.Turn = !this.Turn;
        Position[] posArr = new Position[2];
        posArr[0] = a;
        posArr[1] = b;
        this.gamePlay.add(posArr);

        return true;
    }

    private static boolean isPathClear(Position a, Position b) {
        int aX = a.getX(), aY = a.getY(), bX = b.getX(), bY = b.getY();
        if ((a.getX() != b.getX() && a.getY() != b.getY()) || (a.getY() == b.getY() && a.getX() == b.getX())) {
            return false;
        }

        if (bX > aX) {
            for (int i = aX + 1; i <= bX; i++) {
                if (GameBoard[i][bY] != null) {
                    return false;
                }
            }
        } else if (aX > bX) {
            for (int i = aX + 1; i <= bX; i++) {
                if (GameBoard[i][bY] != null) {
                    return false;
                }
            }
        } else if (bY > aY) {
            for (int i = aY + 1; i <= bY; i++) {
                if (GameBoard[aX][i] != null) {
                    return false;
                }
            }
        } else if (aY > bY) {
            for (int i = aY + 1; i <= bY; i++)
                if (GameBoard[aX][i] != null) {
                    return false;
                }
        }
        return true;
    }

    private void checkKill(Position a) {
        int aX = a.getX(), aY = a.getY();
        ArrayList<Position> neighbours = new ArrayList<>();

        if (aY == 0) {
            neighbours.add(new Position(aX - 1, aY));
            neighbours.add(new Position(aX + 1, aY));
            neighbours.add(new Position(aX, aY + 1));

        } else if (aX == 0) {
            neighbours.add(new Position(aX, aY + 1));
            neighbours.add(new Position(aX + 1, aY));
            neighbours.add(new Position(aX, aY - 1));
        } else if (aY == 10) {
            neighbours.add(new Position(aX - 1, aY));
            neighbours.add(new Position(aX + 1, aY));
            neighbours.add(new Position(aX, aY - 1));
        } else if (aX == 10) {
            neighbours.add(new Position(aX, aY + 1));
            neighbours.add(new Position(aX - 1, aY));
            neighbours.add(new Position(aX, aY - 1));
        } else {
            neighbours.add(new Position(aX, aY + 1));
            neighbours.add(new Position(aX - 1, aY));
            neighbours.add(new Position(aX + 1, aY));
            neighbours.add(new Position(aX, aY - 1));
        }


        for (int i = 0; i < neighbours.size(); i++) {
            Position neighbourPos = neighbours.get(i);
            ConcretePiece neighbour = (ConcretePiece) getPieceAtPosition(neighbourPos);
            if (neighbour == null) continue;
            boolean currentIsP1 = this.getPieceAtPosition(neighbourPos).getOwner().isPlayerOne();
            if ((currentIsP1 && !Turn) || (!currentIsP1 && Turn)) {
                if (this.getPieceAtPosition(neighbourPos).getType().equals("♔")) { // if neighbour is King check if eaten
//                    ArrayList<Position> kingNeighbours = new ArrayList<>();
                    System.out.println("KING");
                } else {
                    boolean currentPIsP1 = getPieceAtPosition(a).getOwner().isPlayerOne();
                    if (neighbourPos.getX() == aX - 1 && neighbourPos.getY() == aY) {
                        if (aX - 2 < 0 || (aX - 2 == 0 && aY == 0) || (aX - 2 == 0 && aY == 10) || (GameBoard[aX - 2][aY] != null && GameBoard[aX - 2][aY].getOwner().isPlayerOne() == currentPIsP1)) {
                            kill(a, neighbourPos);
                        }
                    } else if (neighbourPos.getX() == aX + 1 && neighbourPos.getY() == aY) {
                        if (aX + 2 > 10 || (aX + 2 == 10 && aY == 0) || (aX + 2 == 10 && aY == 10) || (GameBoard[aX + 2][aY] != null && GameBoard[aX + 2][aY].getOwner().isPlayerOne() == currentPIsP1)) {
                            kill(a, neighbourPos);
                        }
                    } else if (neighbourPos.getY() == aY - 1 && neighbourPos.getX() == aX) {
                        if (aY - 2 < 0 || (aX == 0 && aY - 2 == 0) || (aX == 10 && aY - 2 == 0) || (GameBoard[aX][aY - 2] != null && GameBoard[aX][aY - 2].getOwner().isPlayerOne() == currentPIsP1)) {
                            kill(a, neighbourPos);
                        }
                    } else if (neighbourPos.getY() == aY + 1 && neighbourPos.getX() == aX) {
                        if (aY + 2 > 10 || (aX == 0 && aY + 2 == 10) || (aX == 10 && aY + 2 == 10) || (GameBoard[aX][aY + 2] != null && GameBoard[aX][aY + 2].getOwner().isPlayerOne() == currentPIsP1)) {
                            kill(a, neighbourPos);
                        }
                    }
                }
                // Turns = true -> player1 turn
            }
        }
    }

    private void kill(Position killerPos, Position victimPos) {
        Pawn killer = (Pawn) getPieceAtPosition(killerPos);
        killer.ateAPiece();
        GameBoard[victimPos.getX()][victimPos.getY()] = null;
    }

    @Override
    public Piece getPieceAtPosition(Position position) {
        return GameBoard[position.getX()][position.getY()];
    }

    @Override
    public Player getFirstPlayer() {
        if (Player2.isPlayerOne()) return Player2;
        else if (Player1.isPlayerOne()) return Player1;
        else throw new RuntimeException("Initialize player status again");
    }

    @Override
    public Player getSecondPlayer() {
        if (!Player2.isPlayerOne()) return Player2;
        else if (!Player1.isPlayerOne()) return Player1;
        else throw new RuntimeException("Initialize player status again");
    }

    @Override
    public boolean isGameFinished() {

        if (KingDead) {
            this.Player2.numOfWins++;
            logWhenEnd();
            reset();
            return true;
        }
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(0, 10);
        Position pos3 = new Position(10, 0);
        Position pos4 = new Position(10, 10);
        if ((getPieceAtPosition(pos1) != null && Objects.equals(getPieceAtPosition(pos1).getType(), "♔")) ||
                (getPieceAtPosition(pos2) != null && Objects.equals(getPieceAtPosition(pos2).getType(), "♔")) ||
                (getPieceAtPosition(pos3) != null && Objects.equals(getPieceAtPosition(pos3).getType(), "♔")) ||
                (getPieceAtPosition(pos4) != null && Objects.equals(getPieceAtPosition(pos4).getType(), "♔"))) {
            this.Player1.numOfWins++;
            logWhenEnd();
            reset();
            return true;
        }
        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return !Turn;
    }

    @Override
    public void reset() {
        setBoard();
        Stack<Position[]> newGame = new Stack<Position[]>();
        this.gamePlay = newGame;
        this.Turn = false;
        this.KingDead = false;
    }

    @Override
    public void undoLastMove() { // what if got eaten
        if (!this.gamePlay.empty()) {
            Position[] tempArr = gamePlay.pop();

            int xA = tempArr[0].getX();
            int yA = tempArr[0].getY();
            int xB = tempArr[1].getX();
            int yB = tempArr[1].getY();
            GameBoard[xB][yB].hasBeen.remove(tempArr);

            if (GameBoard[xA][yA] == null) {
                GameBoard[xA][yA] = GameBoard[xB][yB];
                GameBoard[xB][yB] = null;
            }
        }
    }

    @Override
    public int getBoardSize() {
        return 11;
    }

    public void setBoard() {
        ConcretePiece[][] Board = new ConcretePiece[11][11];
        Pawn[] aPawn = new Pawn[25];
        Pawn[] dPawn = new Pawn[12];

        for (int i = 0; i < 25; i++) {
            aPawn[i] = new Pawn(this.Player2, "A" + (i + 1));
        }
        for (int i = 0; i < 12; i++) {

            dPawn[i] = new Pawn(this.Player1, "D" + (i + 1));
        }

//        Pawn aPawn1 = new Pawn(this.Player2 , "A1");
//        Pawn aPawn2 = new Pawn(this.Player2 , "A2");
//        Pawn aPawn3 = new Pawn(this.Player2 , "A3");
//        Pawn aPawn4 = new Pawn(this.Player2, "A4");
//        Pawn aPawn5 = new Pawn(this.Player2 , "A5");
//        Pawn aPawn6 = new Pawn(this.Player2 , "A6");
//        Pawn aPawn7 = new Pawn(this.Player2 , "A7");
//        Pawn aPawn8 = new Pawn(this.Player2 , "A8");
//        Pawn aPawn9 = new Pawn(this.Player2 , "A9");
//        Pawn aPawn10 = new Pawn(this.Player2 , "A10");
//        Pawn aPawn11 = new Pawn(this.Player2 , "A11");
//        Pawn aPawn12 = new Pawn(this.Player2 , "A12");
//        Pawn aPawn13 = new Pawn(this.Player2 , "A13");
//        Pawn aPawn14 = new Pawn(this.Player2 , "A14");
//        Pawn aPawn15 = new Pawn(this.Player2 , "A15");
//        Pawn aPawn16 = new Pawn(this.Player2 , "A16");
//        Pawn aPawn17 = new Pawn(this.Player2 , "A17");
//        Pawn aPawn18 = new Pawn(this.Player2 , "A18");
//        Pawn aPawn19 = new Pawn(this.Player2 , "A19");
//        Pawn aPawn20 = new Pawn(this.Player2 , "A20");
//        Pawn aPawn21 = new Pawn(this.Player2 , "A21");
//        Pawn aPawn22 = new Pawn(this.Player2 , "A22");
//        Pawn aPawn23 = new Pawn(this.Player2 , "A23");
//        Pawn aPawn24 = new Pawn(this.Player2 , "A24");
//        Pawn aPawn25 = new Pawn(this.Player2 , "A25");
//        Pawn dPawn1 = new Pawn(this.Player1 , "D1");
//        Pawn dPawn2 = new Pawn(this.Player1 , "D2");
//        Pawn dPawn3 = new Pawn(this.Player1 , "D3");
//        Pawn dPawn4 = new Pawn(this.Player1 , "D4");
//        Pawn dPawn5 = new Pawn(this.Player1 , "D5");
//        Pawn dPawn6 = new Pawn(this.Player1 , "D6");
//        Pawn dPawn7 = new Pawn(this.Player1 , "D7");
//        Pawn dPawn8 = new Pawn(this.Player1 , "D8");
//        Pawn dPawn9 = new Pawn(this.Player1 , "D9");
//        Pawn dPawn10 = new Pawn(this.Player1 , "D10");
//        Pawn dPawn11 = new Pawn(this.Player1 , "D11");
//        Pawn dPawn12 = new Pawn(this.Player1 , "D12");
        King dKing = new King(this.Player1, "K");


        Board[3][0] = aPawn[0];
        Board[4][0] = aPawn[1];
        Board[5][0] = aPawn[2];
        Board[6][0] = aPawn[3];
        Board[7][0] = aPawn[4];
        Board[5][1] = aPawn[5];
        Board[0][3] = aPawn[6];
        Board[0][4] = aPawn[7];
        Board[0][5] = aPawn[8];
        Board[0][6] = aPawn[9];
        Board[0][7] = aPawn[10];
        Board[1][5] = aPawn[11];
        Board[9][5] = aPawn[12];
        Board[10][3] = aPawn[13];
        Board[10][4] = aPawn[14];
        Board[10][5] = aPawn[15];
        Board[10][6] = aPawn[16];
        Board[10][7] = aPawn[17];
        Board[3][10] = aPawn[18];
        Board[4][10] = aPawn[19];
        Board[5][10] = aPawn[20];
        Board[6][10] = aPawn[21];
        Board[7][10] = aPawn[22];
        Board[7][0] = aPawn[23];
        Board[5][9] = aPawn[24];

        Board[5][3] = dPawn[0];
        Board[4][4] = dPawn[1];
        Board[5][4] = dPawn[2];
        Board[6][4] = dPawn[3];
        Board[3][5] = dPawn[4];
        Board[4][5] = dPawn[5];
        Board[6][5] = dPawn[6];
        Board[7][5] = dPawn[7];
        Board[4][6] = dPawn[8];
        Board[5][6] = dPawn[9];
        Board[6][6] = dPawn[10];
        Board[5][7] = dPawn[11];
        Board[5][5] = dKing;
        GameBoard = Board;

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (GameBoard[i][j] != null) {
                    Position arrPos = new Position(i, j);
                    GameBoard[i][j].hasBeen.add(arrPos);
                }
            }
        }

    }

    public static void logWhenEnd() {
        ArrayList<ArrayList<Position>> pieceOnBoard = new ArrayList<ArrayList<Position>>();

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (GameBoard[i][j] != null) {
                    pieceOnBoard.add(GameBoard[i][j].hasBeen);
                    Iterator<Position> tempIter = GameBoard[i][j].hasBeen.iterator();
                    System.out.println(GameBoard[i][j].name + ": " + GameBoard[i][j].hasBeen + "\n");
                }
            }
        }
        pieceOnBoard.sort(Comparator.comparingInt(ArrayList<Position>::size));
        for (int i = 0; i < pieceOnBoard.size(); i++) {
            System.out.println(pieceOnBoard.get(i));
        }

//        Iterator<Position> moved = GameBoard[3][0].hasBeen.iterator();
    }
}

