import java.util.ArrayList;
import java.util.Iterator;

public abstract class ConcretePiece implements Piece {
    private ConcretePlayer owner;
    private final String type;

    public final String name;
    public final int ID;

    public ArrayList<Position> hasBeen = new ArrayList<>();
    public int distance = 0;

    public ConcretePiece(ConcretePlayer owner, String type, int id) {
        this.owner = owner;
        this.type = type;
        if (id == 0) {
            this.ID = 0;
            this.name = "King";
            return;
        }
        this.name = (owner.isPlayerOne() ? "D" : "A") + id;
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

    public void calcSquaresMoved() {
        int dist = 0;
        if (hasBeen.size() == 1) return;
        else {
            for (int i = 0; i < hasBeen.size() - 1; i++) {
                int xA = hasBeen.get(i).getX();
                int yA = hasBeen.get(i).getY();
                int xB = hasBeen.get(i + 1).getX();
                int yB = hasBeen.get(i + 1).getY();
                if (xA == xB && yA != yB) {
                    dist += Math.abs(yA - yB);
                } else dist += Math.abs(xA - xB);
            }
        }
        this.distance = dist;
    }

    public int getSquaresMoved() {
        return distance;
    }
}
