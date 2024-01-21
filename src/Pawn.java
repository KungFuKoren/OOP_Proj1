public class Pawn extends ConcretePiece {
    private int piecesAte = 0;

    public Pawn(ConcretePlayer owner, int id) {
        super(owner, owner.isPlayerOne() ? "♙" : "♟", id);
    }

    public int getPiecesAte() {
        return piecesAte;
    }

    public void ateAPiece() {
        this.piecesAte++;
    }
}
