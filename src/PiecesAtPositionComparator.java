import java.util.Comparator;

public class PiecesAtPositionComparator implements Comparator<Position> {
    public int compare(Position pos1, Position pos2) {
        int piecesLandedComp = -Integer.compare(pos1.pieceVisited, pos2.pieceVisited);

        if (piecesLandedComp == 0) {
            int xComp = Integer.compare(pos1.getX(), pos2.getX());

            if (xComp == 0) return Integer.compare(pos1.getY(), pos2.getY());

            return xComp;
        }
        return piecesLandedComp;
    }
}
