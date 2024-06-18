public class Blaa extends Resept {
    private double rabatt;
    private int prisAaBetale;

    public Blaa(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit) {
        super(legemiddel, utskrivendeLege, pasientID, reit);
        rabatt = 0.75;
    }

    @Override
    public String farge() {
        return "blaa";
    }

    @Override
    public int prisAaBetale() {
        prisAaBetale = (int) Math.round(legemiddel.hentPris() - (legemiddel.hentPris() * rabatt));
        return prisAaBetale;
    }
}
