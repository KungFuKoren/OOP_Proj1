public class PnP {
    private Position pos;
    private Pawn piece;

    public PnP(Position pos, Pawn piece) {
        this.piece = piece;
        this.pos = pos;
    }

    public Position getPos() {
        return this.pos;
    }

    public Pawn getPawn() {
        return this.piece;
    }
}

