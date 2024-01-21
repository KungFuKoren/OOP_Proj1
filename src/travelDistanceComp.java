import java.util.Comparator;

public class travelDistanceComp implements Comparator<ConcretePiece> {
    public int compare(ConcretePiece p1, ConcretePiece p2) {
        int distanceComp = -Integer.compare(p1.getSquaresMoved(), p2.getSquaresMoved());
        if (distanceComp == 0) {
            int idComp = Integer.compare(p1.getID(), p2.getID());
            if (idComp == 0) return Boolean.compare(!p1.getOwner().hasWon(), !p2.getOwner().hasWon());
            return idComp;
        }
        return distanceComp;
    }
}
