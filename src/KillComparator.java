import java.util.Comparator;

public class KillComparator implements Comparator<Pawn> {
    public int compare(Pawn p1, Pawn p2) {
        int killCompare = Integer.compare(p1.getPiecesAte(), p2.getPiecesAte());
        if(killCompare == 0) {
            int idCompare = Integer.compare(p1.getID(), p2.getID());
            if (idCompare == 0) return Boolean.compare(p1.getOwner().hasWon(),p2.getOwner().hasWon());
            return idCompare;
        }
        return killCompare;
    }

}
