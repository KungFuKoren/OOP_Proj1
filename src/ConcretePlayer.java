public class ConcretePlayer implements Player {
    private static boolean isPlayer1;
    private static int numOfWins = 0;

    @Override
    public boolean isPlayerOne() {
        return this.isPlayer1;
    }

    @Override
    public int getWins() {
        return this.numOfWins;
    }
}

//public void setPlayer1() {
//    this.isPlayer1 = true;
//}

//public void setPlayer2() {
//    this.isPlayer1 = false;
//}
