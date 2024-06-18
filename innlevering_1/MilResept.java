public class MilResept extends Hvit {
    private int prisAaBetale;

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID) {
        super(legemiddel, utskrivendeLege, pasientID, 3);
        prisAaBetale = 0;

    }

    @Override
    public int prisAaBetale() {
        return prisAaBetale;
    }
}
