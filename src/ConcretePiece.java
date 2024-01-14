public abstract class ConcretePiece implements Piece{
    private ConcretePlayer owner;
    private String type;

    public ConcretePiece(ConcretePlayer owner, String type) {
        this.owner = owner;
        this.type = type;
    }

    @Override
    public ConcretePlayer getOwner(){
        return this.owner;
    }
    @Override
    public String getType(){
        return this.type;
    }


}
