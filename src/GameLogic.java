public class GameLogic implements PlayableLogic {

    public static ConcretePiece[][] GameBoard;
    private ConcretePlayer Defender;
    private ConcretePlayer Attacker;
    public static boolean Turn;

    ConcretePiece[][] resetBoard = new ConcretePiece[11][11];


    public GameLogic() {
        reset();
    }

    @Override
    public boolean move(Position a, Position b) {
        if (!isPathClear(a, b)) {
            return false;
        }
        GameBoard[b.getX()][b.getY()] = GameBoard[a.getX()][a.getY()];
        GameBoard[a.getX()][a.getY()] = null;
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

    public void setBoard(){
        ConcretePiece[][] Board = new ConcretePiece[11][11];

        ConcretePiece aPawn1 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn2 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn3 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn4 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn5 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn6 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn7 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn8 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn9 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn10 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn11 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn12 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn13 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn14 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn15 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn16 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn17 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn18 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn19 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn20 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn21 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn22 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn23 = new ConcretePiece(Attacker , pawn);
        ConcretePiece aPawn24 = new ConcretePiece(Attacker , pawn);
        ConcretePiece dPawn1 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn12 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn2 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn3 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn4 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn5 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn6 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn7 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn8 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn9 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn10 = new ConcretePiece(Defender , pawn);
        ConcretePiece dPawn11 = new ConcretePiece(Defender , pawn);
        ConcretePiece dKing = new ConcretePiece(Defender , king);


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

