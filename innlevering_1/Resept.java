abstract public class Resept {
    public final int ID;
    public static int IDTELLER = 0;
    public final Legemiddel legemiddel;
    public final Lege lege;
    public final int pasientID;
    public int reit;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientID, int reit) {
        this.legemiddel = legemiddel;
        lege = utskrivendeLege;
        this.pasientID = pasientID;
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

    public int hentPasientID() {
        return pasientID;
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
                "\nPasientID: " + this.hentPasientID() +
                "\nReit: " + reit;
    }

    abstract public String farge();

    abstract public int prisAaBetale();
}
