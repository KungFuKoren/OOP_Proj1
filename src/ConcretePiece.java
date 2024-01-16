import java.util.ArrayList;

public abstract class ConcretePiece implements Piece {
    private ConcretePlayer owner;
    private final String type;
    
    public final String name;

    public ArrayList<Position> hasBeen = new ArrayList<>();

    public ConcretePiece(ConcretePlayer owner, String type , String name) {
        this.owner = owner;
        this.type = type;
        this.name = name;
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
    public String getName(){
        return this.name;
    }
}
