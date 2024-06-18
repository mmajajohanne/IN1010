abstract public class Resept {
    public final int ID;
    public static int IDTELLER = 0;
    public final Legemiddel legemiddel;
    public final Lege lege;
    public final Pasient pasient;
    public int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        lege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        ID = IDTELLER++;
    }

    public int hentID() {
        return ID;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return lege;
    }

    public String hentNavn() {
        return pasient.hentNavn();
    }

    public boolean bruk() {
        if (reit - 1 < 0) {
            return false;
        } else {
            reit = reit - 1;
            return true;
        }
    }

    @Override
    public String toString() {
        return "Resept ID: " + this.hentID() +
                "\nLegemiddel: " + this.hentLegemiddel().toString() + // bruker Legemiddel sin toString()-metode
                "\nPris: " + this.prisAaBetale() +
                "\nLege: " + this.hentLege().toString() +
                "\nPasient: " + this.hentNavn() +
                "\nReit: " + reit;
    }

    abstract public String farge();

    abstract public int prisAaBetale();
}
