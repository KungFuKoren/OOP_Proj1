public class Pawn extends ConcretePiece {
    private int piecesAte = 0;

    public Pawn(ConcretePlayer owner,String name) {
        super(owner, owner.isPlayerOne() ? "♙" : "♟" , name);
    }

    public int getPiecesAte() {
        return piecesAte;
    }

    public void ateAPiece() {
        this.piecesAte++;
    }
}
