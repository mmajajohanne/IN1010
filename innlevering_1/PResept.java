public class PResept extends Hvit {
    private int rabatt; // i kr
    private int prisAaBetale;

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit) {
        super(legemiddel, utskrivendeLege, pasientID, reit);
        rabatt = 108;
    }

    @Override
    public int prisAaBetale() {
        prisAaBetale = legemiddel.hentPris() - rabatt;
        if (prisAaBetale < 0) {
            prisAaBetale = 0;
        }
        return prisAaBetale;
    }
}
