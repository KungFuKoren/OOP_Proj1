public abstract class ConcretePiece implements Piece{
    private static ConcretePlayer owner;
    private static String type;

    public ConcretePiece(ConcretePlayer owner, String type) {
        this.owner = owner;
        this.type = type;
    }

    @Override
    public ConcretePlayer getOwner(){
        return this.getOwner();
    }
    @Override
    public String getType(){
        return this.type;
    }


}
