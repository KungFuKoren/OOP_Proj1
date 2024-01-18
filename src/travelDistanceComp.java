import java.util.Comparator;

public class travelDistanceComp implements Comparator<ConcretePiece> {
    //    Comparator<ConcretePiece> numOfSqrCom = new Comparator<ConcretePiece>() {
//        @Override
//        public int compare(ConcretePiece p1, ConcretePiece p2) {
//            return Integer.compare(p1.hasBeen.size(), p2.hasBeen.size());
//        }
//    };
    public int compare(ConcretePiece p1, ConcretePiece p2) {
        return Integer.compare(p1.hasBeen.size(), p2.hasBeen.size());
    }
}
