public class Position {
    public int x;
    public int y;

    public static final int boardSize = 11;


    public Position(int x, int y) {
        if (x > boardSize && x < 0 && y > boardSize && y < 0) {
            this.x = x;
            this.y = y;
        } else {
            // throw new RuntimeException("invalid X or Y");
            this.x = x;
            this.y = y;
        }
    }


   @Override
   public String toString(){
        return String.format("(%s,%s)",x,y);
   }
    public int getX() {
        return x;
    }

    public int getY() {

        return y;
    }


}
