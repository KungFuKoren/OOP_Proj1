import java.util.Comparator;

public class KillComparator implements Comparator<Pawn> {
    public int compare(Pawn p1, Pawn p2) {
        return Integer.compare(p1.getPiecesAte(), p2.getPiecesAte());
    }

}
