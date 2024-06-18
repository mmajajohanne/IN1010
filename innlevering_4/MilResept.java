public class MilResept extends Hvit {
    private int prisAaBetale;
    // private int reit = 3;

    public MilResept(Legemiddel legemiddel, Lege lege, Pasient pasient) {
        super(legemiddel, lege, pasient, 3);
        prisAaBetale = 0;

    }

    @Override
    public int prisAaBetale() {
        return prisAaBetale;
    }
}
