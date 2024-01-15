public class ConcretePlayer implements Player {
    private final boolean isPlayer1;
    public int numOfWins = 0;

    public ConcretePlayer(boolean isPlayer1) {
        this.isPlayer1 = isPlayer1;
    }

    @Override
    public boolean isPlayerOne() {
        return this.isPlayer1;
    }

    @Override
    public int getWins() {
        return this.numOfWins;
    }
}