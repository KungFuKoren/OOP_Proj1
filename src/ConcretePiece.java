import java.util.ArrayList;

public abstract class ConcretePiece implements Piece {
    private ConcretePlayer owner;
    private final String type;

    public ArrayList<Position> hasBeen;

    public ConcretePiece(ConcretePlayer owner, String type) {
        this.owner = owner;
        this.type = type;
    }

    public ArrayList<Position> getPositions(){return this.hasBeen;}
    @Override
    public ConcretePlayer getOwner() {
        return this.owner;
    }

    @Override
    public String getType() {
        return this.type;
    }
}
