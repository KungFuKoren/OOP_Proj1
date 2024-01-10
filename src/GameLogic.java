public class GameLogic implements PlayableLogic {

    public static ConcretePiece[][] GameBoard = new ConcretePiece[11][11];
    private ConcretePlayer Defender;
    private ConcretePlayer Attacker;
    public static boolean Turn;

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
        // Attacker.isPlayerOne()


    }

    @Override
    public void undoLastMove() {

    }

    @Override
    public int getBoardSize() {
        return 11;
    }
}
