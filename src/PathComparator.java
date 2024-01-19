import java.util.Comparator;

public class PathComparator implements Comparator<ConcretePiece> {

    public int compare(ConcretePiece p1 , ConcretePiece p2){
        int wonComp = Boolean.compare(!p1.getOwner().hasWon(),!p2.getOwner().hasWon());
        if(wonComp == 0 )return Integer.compare(p1.getPositions().size(),p2.getPositions().size());
        return wonComp;
    }

}
