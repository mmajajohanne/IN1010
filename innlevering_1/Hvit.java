public class Hvit extends Resept {

    public Hvit(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit) {
        super(legemiddel, utskrivendeLege, pasientID, reit);
    }

    @Override
    public String farge() {
        return "hvit";
    }

    @Override
    public int prisAaBetale() {
        return legemiddel.hentPris();
    }
}
