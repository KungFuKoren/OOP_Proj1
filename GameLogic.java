import java.util.*;
import java.util.Comparator;

public class GameLogic implements PlayableLogic {
    public static ConcretePiece[][] GameBoard;
    private ConcretePlayer Player1; //Defender
    private ConcretePlayer Player2; //Attacker
    public boolean Turn = false;
    public boolean KingDead = false;
    public Stack<Position[]> gamePlay;

    public ArrayList<Pawn> allPawns;
    public ArrayList<ConcretePiece> allPieces;

    public HashMap<String, HashSet<ConcretePiece>> posMap;
    public ArrayList<Position> posArr;


    public GameLogic() {
        this.Player1 = new ConcretePlayer(true);
        this.Player2 = new ConcretePlayer(false);
        reset();
    }

    @Override
    public boolean move(Position a, Position b) { // check if piece can move, kill, win, and move
        if (this.getPieceAtPosition(a) == null || this.getPieceAtPosition(b) != null) {
            return false; // check if chosen square isn't empty and destination is clear
        }
        if ((!this.Turn && this.getPieceAtPosition(a).getOwner().isPlayerOne()) ||
                (this.Turn && !this.getPieceAtPosition(a).getOwner().isPlayerOne())) {
            return false; // if turn = false its Player2 turn same vice versa
        }

        if (!isPathClear(a, b)) {
            return false;
        }

        int aX = a.getX(), aY = a.getY(), bX = b.getX(), bY = b.getY();
        boolean currentPieceIsKing = isKing(a);

        if (((bX == 0 && bY == 0) || (bX == 10 && bY == 0) || (bX == 0 && bY == 10) ||
                (bX == 10 && bY == 10)) && (!currentPieceIsKing)) {
            return false;
        }

        GameBoard[aX][aY].hasBeen.add(b);
        GameBoard[bX][bY] = GameBoard[aX][aY];
        GameBoard[aX][aY] = null;

        posMap.get((bX + "," + bY)).add(GameBoard[bX][bY]);
        posArr.get((bX * 11) + bY).pieceVisited = posMap.get((bX + "," + bY)).size();
        if (!isKing(b)) checkKill(b);

        this.Turn = !this.Turn;
        Position[] posArr = new Position[2];
        posArr[0] = a;
        posArr[1] = b;
        this.gamePlay.add(posArr);
        isGameFinished();
        return true;
    }

    private static boolean isPathClear(Position a, Position b) { // check if path clear from a to b positions
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

    private void checkKill(Position a) { // check if moved pawn or king can kill
        int aX = a.getX(), aY = a.getY();
        ArrayList<Position> neighbours = getNeighbours(a);

        for (int i = 0; i < neighbours.size(); i++) {
            Position neighbourPos = neighbours.get(i);
            ConcretePiece neighbour = (ConcretePiece) getPieceAtPosition(neighbourPos);
            if (neighbour == null) continue;
            boolean currentIsP1 = this.getPieceAtPosition(neighbourPos).getOwner().isPlayerOne();

            if ((currentIsP1 && Turn) || (!currentIsP1 && !Turn)) continue;

            if (isKing(neighbourPos)) { // if neighbour is King check if eaten
                if (Turn) continue;
                ArrayList<Position> kingNeighbours = getNeighbours(neighbourPos);
                int numOfEnemies = 0;
                for (int j = 0; j < kingNeighbours.size(); j++) {
                    if (getPieceAtPosition(kingNeighbours.get(j)) == null) continue;
                    boolean kingNeighbourIsP1 = getPieceAtPosition(kingNeighbours.get(j)).getOwner().isPlayerOne();
                    if (!kingNeighbourIsP1) numOfEnemies++;
                }

                if (numOfEnemies == kingNeighbours.size()) KingDead = true;
            } else {
                boolean currentPIsP1 = getPieceAtPosition(a).getOwner().isPlayerOne();
                if (neighbourPos.getX() == aX - 1 && neighbourPos.getY() == aY) {
                    if (aX - 2 < 0 || (aX - 2 == 0 && aY == 0) || (aX - 2 == 0 && aY == 10) ||
                            (GameBoard[aX - 2][aY] != null && GameBoard[aX - 2][aY].getOwner().isPlayerOne() == currentPIsP1 &&
                                    !isKing(new Position(aX - 2, aY)))) {
                        kill(a, neighbourPos);
                    }
                } else if (neighbourPos.getX() == aX + 1 && neighbourPos.getY() == aY) {
                    if (aX + 2 > 10 || (aX + 2 == 10 && aY == 0) || (aX + 2 == 10 && aY == 10) ||
                            (GameBoard[aX + 2][aY] != null && GameBoard[aX + 2][aY].getOwner().isPlayerOne() == currentPIsP1 &&
                                    !isKing(new Position(aX + 2, aY)))) {
                        kill(a, neighbourPos);
                    }
                } else if (neighbourPos.getY() == aY - 1 && neighbourPos.getX() == aX) {
                    if (aY - 2 < 0 || (aX == 0 && aY - 2 == 0) || (aX == 10 && aY - 2 == 0) ||
                            (GameBoard[aX][aY - 2] != null && GameBoard[aX][aY - 2].getOwner().isPlayerOne() == currentPIsP1 &&
                                    !isKing(new Position(aX, aY - 2)))) {
                        kill(a, neighbourPos);
                    }
                } else if (neighbourPos.getY() == aY + 1 && neighbourPos.getX() == aX) {
                    if (aY + 2 > 10 || (aX == 0 && aY + 2 == 10) || (aX == 10 && aY + 2 == 10) ||
                            (GameBoard[aX][aY + 2] != null && GameBoard[aX][aY + 2].getOwner().isPlayerOne() == currentPIsP1 &&
                                    !isKing(new Position(aX, aY + 2)))) {
                        kill(a, neighbourPos);
                    }
                }
            }

        }
    }

    private ArrayList<Position> getNeighbours(Position a) { // returns all 4/3 neighbours
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
        return neighbours;
    }

    private boolean isKing(Position a) { // returns true if given a position is a king
        return getPieceAtPosition(a).getType().equals("♔");
    }

    private void kill(Position killerPos, Position victimPos) { // kill using killer and victim
        Pawn killer = (Pawn) getPieceAtPosition(killerPos);
        killer.ateAPiece();
        GameBoard[victimPos.getX()][victimPos.getY()] = null;
        Position[] victimPosArr = {victimPos};
        gamePlay.add(victimPosArr);
    }

    @Override
    public Piece getPieceAtPosition(Position position) { // return piece at given position
        return GameBoard[position.getX()][position.getY()];
    }

    @Override
    public Player getFirstPlayer() { // returns p1
        if (Player2.isPlayerOne()) return Player2;
        else if (Player1.isPlayerOne()) return Player1;
        else throw new RuntimeException("Initialize player status again");
    }

    @Override
    public Player getSecondPlayer() { // returns second player
        if (!Player2.isPlayerOne()) return Player2;
        else if (!Player1.isPlayerOne()) return Player1;
        else throw new RuntimeException("Initialize player status again");
    }

    @Override
    public boolean isGameFinished() { // check if game is finished and who won
        if (KingDead) {
            this.Player2.numOfWins++;
            this.Player2.iWon = true;
            allComp();
            return true;
        }
        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(0, 10);
        Position pos3 = new Position(10, 0);
        Position pos4 = new Position(10, 10);
        if ((getPieceAtPosition(pos1) != null && isKing(pos1)) ||
                (getPieceAtPosition(pos2) != null && isKing(pos2)) ||
                (getPieceAtPosition(pos3) != null && isKing(pos3)) ||
                (getPieceAtPosition(pos4) != null && isKing(pos4))) {
            this.Player1.numOfWins++;
            this.Player1.iWon = true;
            allComp();
            return true;
        }
        return false;
    }

    @Override
    public boolean isSecondPlayerTurn() { // check if the turn of the attacker
        return !Turn;
    }

    @Override
    public void reset() { // reset board to starting position
        this.posMap = new HashMap<>();
        this.posArr = new ArrayList<>();
        setBoard();
        Stack<Position[]> newGame = new Stack<Position[]>();
        this.gamePlay = newGame;
        this.Turn = false;
        this.KingDead = false;
        Player2.iWon = false;
        Player1.iWon = false;
    }

    @Override
    public void undoLastMove() {
        if (this.gamePlay.empty()) return;
        boolean foundRotation = false;
        while (foundRotation) {
            Position[] tempArr = gamePlay.pop();
            if (tempArr.length == 2) {
                foundRotation = true;
                int xA = tempArr[0].getX();
                int yA = tempArr[0].getY();
                int xB = tempArr[1].getX();
                int yB = tempArr[1].getY();
                GameBoard[xB][yB].hasBeen.remove(tempArr);

                if (GameBoard[xA][yA] == null) {
                    GameBoard[xA][yA] = GameBoard[xB][yB];
                    GameBoard[xB][yB] = null;
                }
            } else {
                Position returnPawnPos = new Position(tempArr[0].getX(), tempArr[0].getY());
//                if(getPieceAtPosition(returnPawnPos) == null) GameBoard[tempArr[0].getX()][tempArr[0].getY()] = new Pawn()
            }
        }
    }


    @Override
    public int getBoardSize() { // returns the board size
        return 11;
    }

    public void setBoard() { // puts all pieces at starting position

        ArrayList<Pawn> allPawns = new ArrayList<>();
        ArrayList<ConcretePiece> allPieces = new ArrayList<>();

        ConcretePiece[][] Board = new ConcretePiece[11][11];
        Pawn[] aPawn = new Pawn[25];
        Pawn[] dPawn = new Pawn[12];

        for (int i = 0; i < 25; i++) {
            aPawn[i] = new Pawn(this.Player2, (i + 1));
            allPawns.add(aPawn[i]);
            allPieces.add(aPawn[i]);

        }
        for (int i = 0; i < 13; i++) {
            if (i == 7) continue;
            if (i < 7) {
                dPawn[i] = new Pawn(this.Player1, (i + 1));
                allPawns.add(dPawn[i]);
                allPieces.add(dPawn[i]);
            } else {
                dPawn[i - 1] = new Pawn(this.Player1, (i + 1));
                allPawns.add(dPawn[i - 1]);
                allPieces.add(dPawn[i - 1]);
            }
        }

        this.allPawns = allPawns;

        King dKing = new King(this.Player1);

        allPieces.add(dKing);
        this.allPieces = allPieces;

        Board[3][0] = aPawn[0];
        Board[4][0] = aPawn[1];
        Board[5][0] = aPawn[2];
        Board[6][0] = aPawn[3];
        Board[7][0] = aPawn[4];
        Board[5][1] = aPawn[5];
        Board[0][3] = aPawn[6];
        Board[10][3] = aPawn[7];
        Board[0][4] = aPawn[8];
        Board[10][4] = aPawn[9];
        Board[0][5] = aPawn[10];
        Board[1][5] = aPawn[11];
        Board[9][5] = aPawn[12];
        Board[10][5] = aPawn[13];
        Board[0][6] = aPawn[14];
        Board[10][6] = aPawn[15];
        Board[0][7] = aPawn[16];
        Board[10][7] = aPawn[17];
        Board[5][9] = aPawn[18];
        Board[3][10] = aPawn[19];
        Board[4][10] = aPawn[20];
        Board[5][10] = aPawn[21];
        Board[6][10] = aPawn[22];
        Board[7][10] = aPawn[23];

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
                posMap.put((i + "," + j), new HashSet<>());
                Position currPos = new Position(i, j);
                posArr.add(currPos);
                if (GameBoard[i][j] != null) {
                    Position arrPos = new Position(i, j);
                    GameBoard[i][j].hasBeen.add(arrPos);
                    posMap.get((i + "," + j)).add(GameBoard[i][j]);
                    currPos.pieceVisited = posMap.get((i + "," + j)).size();
                }
            }
        }
    }

    public void allComp() { // when game finished print all comparators

        for (ConcretePiece cp : allPieces) {
            cp.calcSquaresMoved();
        }

        ArrayList<ConcretePiece> pieceOnBoard = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (GameBoard[i][j] != null) {
                    pieceOnBoard.add(GameBoard[i][j]);
                }
            }
        }

        pieceOnBoard.sort(new PathComparator());
        for (ConcretePiece piece : pieceOnBoard) {
            if (piece.getPositions().size() <= 1) continue;
            System.out.println(piece.getName() + ": " + piece.getPositions());
        }

        System.out.println("***************************************************************************");

        allPawns.sort(new KillComparator().reversed());
        for (Pawn pawn : this.allPawns) {
            if (pawn.getPiecesAte() == 0) continue;
            System.out.println(pawn.getName() + ": " + pawn.getPiecesAte() + " kills");
        }
        System.out.println("***************************************************************************");

        for (ConcretePiece piece : allPieces) {
            piece.calcSquaresMoved();
        }
        allPieces.sort(new travelDistanceComp());
        for (ConcretePiece piece : allPieces) {
            int squaresMoved = piece.getSquaresMoved();
            if (squaresMoved != 0)
                System.out.println(piece.getName() + ": " + squaresMoved + " squares");
        }
        System.out.println("***************************************************************************");

        posArr.sort(new PiecesAtPositionComparator());
        for (Position currPos : posArr) {
            if (currPos.pieceVisited > 1) {
                System.out.println("(" + currPos.getX() + ", " + currPos.getY() + ")" + currPos.pieceVisited + " pieces");
            }
        }
        System.out.println("***************************************************************************");
    }
}

