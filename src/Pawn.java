public class Pawn extends ConcretePiece {


    private int piecesAte = 0;

    public Pawn(ConcretePlayer owner) {
        super(owner, owner.isPlayerOne() ? "♙" : "♟");
    }

    public int getPiecesAte() {
        return piecesAte;
    }

    public void ateAPiece(int piecesAte) {
        this.piecesAte++;
    }
}
