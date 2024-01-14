import java.util.ArrayList;

public class GameLogic implements PlayableLogic {

    public static ConcretePiece[][] GameBoard;
    private ConcretePlayer Player1; //Defender
    private ConcretePlayer Player2; //Attacker
    public boolean Turn;
    public ArrayList<ConcretePiece[][]> gamePlay;

    public GameLogic() {
        this.Player1 = new ConcretePlayer(true);
        this.Player2 = new ConcretePlayer(false);
        this.Turn = false;
        reset();
    }

    @Override
    public boolean move(Position a, Position b) {
        if (this.getPieceAtPosition(a) == null || this.getPieceAtPosition(b) != null)
            return false; // check if chosen square isn't empty and destination is clear
        if ((!this.Turn && this.getPieceAtPosition(a).getOwner().isPlayerOne() == this.Player2.isPlayerOne()) || (this.Turn && this.getPieceAtPosition(a).getOwner().isPlayerOne() == this.Player1.isPlayerOne())) {
            return false; // if turn = false its Player2s turn same vice versa
        }

        int aX = a.getX(), aY = a.getY(), bX = b.getX(), bY = b.getY();

        if ((bX == 0 && bY == 0) || (bX == 10 && bY == 0) || (bX == 0 && bY == 10) || (bX == 10 && bY == 10) && (!this.getPieceAtPosition(b).getType().equals("♔"))) {
            return false;
        }

        if (!isPathClear(a, b)) {
            return false;
        }

        GameBoard[bX][bY] = GameBoard[aX][aY];
        GameBoard[aX][aY] = null;

        if (!this.getPieceAtPosition(b).getType().equals("♔")) eat(b);
        
        this.Turn = !this.Turn;

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
            for (int i = aY + 1; i <= bY; i++) {
                if (GameBoard[aX][i] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void eat(Position a) {
        ArrayList<Position> neighbours = new ArrayList<>();
//        if(a.getY() == 0 && a.getX() == 10) neighbours.add()
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

        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return !Turn;
    }

    @Override
    public void reset() {
        setBoard();
        ArrayList<ConcretePiece[][]> newGame = new ArrayList<ConcretePiece[][]>();
        this.gamePlay = newGame;
        gamePlay.add(GameBoard);
        this.Turn = true;
        // Player2.isPlayerOne()


    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return 11;
    }

    public void setBoard() {
        ConcretePiece[][] Board = new ConcretePiece[11][11];

        Pawn aPawn1 = new Pawn(this.Player2);
        Pawn aPawn2 = new Pawn(this.Player2);
        Pawn aPawn3 = new Pawn(this.Player2);
        Pawn aPawn4 = new Pawn(this.Player2);
        Pawn aPawn5 = new Pawn(this.Player2);
        Pawn aPawn6 = new Pawn(this.Player2);
        Pawn aPawn7 = new Pawn(this.Player2);
        Pawn aPawn8 = new Pawn(this.Player2);
        Pawn aPawn9 = new Pawn(this.Player2);
        Pawn aPawn10 = new Pawn(this.Player2);
        Pawn aPawn11 = new Pawn(this.Player2);
        Pawn aPawn12 = new Pawn(this.Player2);
        Pawn aPawn13 = new Pawn(this.Player2);
        Pawn aPawn14 = new Pawn(this.Player2);
        Pawn aPawn15 = new Pawn(this.Player2);
        Pawn aPawn16 = new Pawn(this.Player2);
        Pawn aPawn17 = new Pawn(this.Player2);
        Pawn aPawn18 = new Pawn(this.Player2);
        Pawn aPawn19 = new Pawn(this.Player2);
        Pawn aPawn20 = new Pawn(this.Player2);
        Pawn aPawn21 = new Pawn(this.Player2);
        Pawn aPawn22 = new Pawn(this.Player2);
        Pawn aPawn23 = new Pawn(this.Player2);
        Pawn aPawn24 = new Pawn(this.Player2);
        Pawn aPawn25 = new Pawn(this.Player2);
        Pawn dPawn1 = new Pawn(this.Player1);
        Pawn dPawn2 = new Pawn(this.Player1);
        Pawn dPawn3 = new Pawn(this.Player1);
        Pawn dPawn4 = new Pawn(this.Player1);
        Pawn dPawn5 = new Pawn(this.Player1);
        Pawn dPawn6 = new Pawn(this.Player1);
        Pawn dPawn7 = new Pawn(this.Player1);
        Pawn dPawn8 = new Pawn(this.Player1);
        Pawn dPawn9 = new Pawn(this.Player1);
        Pawn dPawn10 = new Pawn(this.Player1);
        Pawn dPawn11 = new Pawn(this.Player1);
        Pawn dPawn12 = new Pawn(this.Player1);
        King dKing = new King(this.Player1);


        Board[3][0] = aPawn1;
        Board[4][0] = aPawn2;
        Board[5][0] = aPawn3;
        Board[6][0] = aPawn4;
        Board[7][0] = aPawn5;
        Board[5][1] = aPawn6;
        Board[0][3] = aPawn7;
        Board[0][4] = aPawn8;
        Board[0][5] = aPawn9;
        Board[0][6] = aPawn10;
        Board[0][7] = aPawn11;
        Board[1][5] = aPawn12;
        Board[9][5] = aPawn13;
        Board[10][3] = aPawn14;
        Board[10][4] = aPawn15;
        Board[10][5] = aPawn16;
        Board[10][6] = aPawn17;
        Board[10][7] = aPawn18;
        Board[3][10] = aPawn19;
        Board[4][10] = aPawn20;
        Board[5][10] = aPawn21;
        Board[6][10] = aPawn22;
        Board[7][10] = aPawn23;
        Board[7][0] = aPawn24;
        Board[5][9] = aPawn25;

        Board[5][3] = dPawn1;
        Board[4][4] = dPawn2;
        Board[5][4] = dPawn3;
        Board[6][4] = dPawn4;
        Board[3][5] = dPawn5;
        Board[4][5] = dPawn6;
        Board[6][5] = dPawn7;
        Board[7][5] = dPawn8;
        Board[4][6] = dPawn9;
        Board[5][6] = dPawn10;
        Board[6][6] = dPawn11;
        Board[5][7] = dPawn12;
        Board[5][5] = dKing;

        GameBoard = Board;

    }
}

