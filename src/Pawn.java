public class Pawn extends ConcretePiece {
    private int piecesAte = 0;

    public Pawn(ConcretePlayer owner) {
        super(owner, owner.isPlayerOne() ? "♙" : "♟");
    }

//    @Override
//    public String getType() {
//        return super.getType();
//    }
//
//    @Override
//    public ConcretePlayer getOwner() {
//        return super.getOwner();
//    }

    public int getPiecesAte() {
        return piecesAte;
    }

    public void ateAPiece(int piecesAte) {
        this.piecesAte++;
    }
}
