import java.util.ArrayList;
import java.util.Iterator;

public abstract class ConcretePiece implements Piece {
    private ConcretePlayer owner;
    private final String type;

    public final String name;
    public final int ID;

    public ArrayList<Position> hasBeen = new ArrayList<>();

    public ConcretePiece(ConcretePlayer owner, String type, int id) {
        this.owner = owner;
        this.type = type;
        if (id == 0) {
            this.ID = 0;
            this.name = "King";
            return;
        }
        this.name = (this.owner.isPlayerOne() ? "D" : "A") + id;
        this.ID = id;
    }

    public ArrayList<Position> getPositions() {
        return this.hasBeen;
    }

    @Override
    public ConcretePlayer getOwner() {
        return this.owner;
    }

    @Override
    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.ID;
    }
}
