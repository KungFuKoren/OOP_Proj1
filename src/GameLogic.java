public class GameLogic implements PlayableLogic {

    public static ConcretePiece[][] GameBoard;
    private ConcretePlayer Defender;
    private ConcretePlayer Attacker;
    public boolean Turn;

    public GameLogic() {
        this.Attacker = new ConcretePlayer(false);
        this.Defender = new ConcretePlayer(true);
        this.Turn = false;
        reset();
    }

    @Override
    public boolean move(Position a, Position b) {
        System.out.println("a = " + a.getX() + ", " + a.getY());
        System.out.println("b = " + b.getX() + ", " + b.getY());
//        System.out.println(this.getPieceAtPosition(a).getType().equals("pawn"));
        if (this.getPieceAtPosition(a) == null || this.getPieceAtPosition(b) != null)
            return false; // check if chosen square isn't empty and destination is clear
        if ((this.Turn && this.getPieceAtPosition(a).getOwner().isPlayerOne() == this.Attacker.isPlayerOne()) || (!this.Turn && this.getPieceAtPosition(a).getOwner().isPlayerOne() == this.Defender.isPlayerOne())) {
            return false; // if turn = false its attackers turn same vice versa
        }
        if (!isPathClear(a, b)) {
            return false;
        }
        GameBoard[b.getX()][b.getY()] = GameBoard[a.getX()][a.getY()];
        GameBoard[a.getX()][a.getY()] = null;

        this.Turn = !this.Turn;

        return true;
    }

    private static boolean isPathClear(Position a, Position b) {
        int aX = a.getX(), aY = a.getY(), bX = b.getX(), bY = b.getY();
        if ((a.getX() != b.getX() && a.getY() != b.getY()) || (a.getY() == b.getY() && a.getX() == b.getX())) {
            return false;
        }

        if (bX > aX) {
            for (int i = aX; i <= bX; i++) {
                if (GameBoard[i][bY] != null) {
                    return false;
                }
            }
        } else if (aX > bX) {
            for (int i = bX; i <= aX; i++) {
                if (GameBoard[i][bY] != null) {
                    return false;
                }
            }
        } else if (bY > aY) {
            for (int i = aY; i <= bY; i++) {
                if (GameBoard[aX][i] != null) {
                    return false;
                }
            }
        } else if (aY > bY) {
            for (int i = bY; i <= aY; i++) {
                if (GameBoard[aX][i] != null) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public Piece getPieceAtPosition(Position position) {
        return GameBoard[position.getX()][position.getY()];
    }

    @Override
    public Player getFirstPlayer() {

        if (Attacker.isPlayerOne() && !Defender.isPlayerOne()) return Attacker;
        else throw new RuntimeException("Initialize player status again");
    }

    @Override
    public Player getSecondPlayer() {
        if (Attacker.isPlayerOne() && !Defender.isPlayerOne()) return Defender;
        else throw new RuntimeException("Initialize player status again");
    }

    @Override
    public boolean isGameFinished() {
        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() {
        return false;
    }

    @Override
    public void reset() {
        setBoard();
        // Attacker.isPlayerOne()


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

        Pawn aPawn1 = new Pawn(this.Attacker, "♟");
        Pawn aPawn2 = new Pawn(this.Attacker, "♟");
        Pawn aPawn3 = new Pawn(this.Attacker, "♟");
        Pawn aPawn4 = new Pawn(this.Attacker, "♟");
        Pawn aPawn5 = new Pawn(this.Attacker, "♟");
        Pawn aPawn6 = new Pawn(this.Attacker, "♟");
        Pawn aPawn7 = new Pawn(this.Attacker, "♟");
        Pawn aPawn8 = new Pawn(this.Attacker, "♟");
        Pawn aPawn9 = new Pawn(this.Attacker, "♟");
        Pawn aPawn10 = new Pawn(this.Attacker, "♟");
        Pawn aPawn11 = new Pawn(this.Attacker, "♟");
        Pawn aPawn12 = new Pawn(this.Attacker, "♟");
        Pawn aPawn13 = new Pawn(this.Attacker, "♟");
        Pawn aPawn14 = new Pawn(this.Attacker, "♟");
        Pawn aPawn15 = new Pawn(this.Attacker, "♟");
        Pawn aPawn16 = new Pawn(this.Attacker, "♟");
        Pawn aPawn17 = new Pawn(this.Attacker, "♟");
        Pawn aPawn18 = new Pawn(this.Attacker, "♟");
        Pawn aPawn19 = new Pawn(this.Attacker, "♟");
        Pawn aPawn20 = new Pawn(this.Attacker, "♟");
        Pawn aPawn21 = new Pawn(this.Attacker, "♟");
        Pawn aPawn22 = new Pawn(this.Attacker, "♟");
        Pawn aPawn23 = new Pawn(this.Attacker, "♟");
        Pawn aPawn24 = new Pawn(this.Attacker, "♟");
        Pawn dPawn1 = new Pawn(this.Defender, "♙");
        Pawn dPawn2 = new Pawn(this.Defender, "♙");
        Pawn dPawn3 = new Pawn(this.Defender, "♙");
        Pawn dPawn4 = new Pawn(this.Defender, "♙");
        Pawn dPawn5 = new Pawn(this.Defender, "♙");
        Pawn dPawn6 = new Pawn(this.Defender, "♙");
        Pawn dPawn7 = new Pawn(this.Defender, "♙");
        Pawn dPawn8 = new Pawn(this.Defender, "♙");
        Pawn dPawn9 = new Pawn(this.Defender, "♙");
        Pawn dPawn10 = new Pawn(this.Defender, "♙");
        Pawn dPawn11 = new Pawn(this.Defender, "♙");
        Pawn dPawn12 = new Pawn(this.Defender, "♙");
        King dKing = new King(this.Defender, "♔");


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
        Board[7][0] = aPawn23;
        Board[5][9] = aPawn24;

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

        this.GameBoard = Board;

    }
}

