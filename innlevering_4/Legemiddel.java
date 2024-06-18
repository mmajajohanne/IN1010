abstract public class Legemiddel {
    public final String navn;
    public int pris;
    public final double virkestoff;
    public final int ID;
    public static int IDTELLER = 0; // delt teller mellom alle objektene i klassen

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        ID = IDTELLER++;
    }

    public int hentPris() {
        return pris;
    }

    public String hentNavn() {
        return navn;
    }

    public void settNyPris(int nyPris) {
        pris = nyPris;
    }

    @Override
    public String toString() {
        return ("Navn: " + navn + "\n ID: " + ID + "\n Pris: " + pris + "\n Virkestoff: " + virkestoff);
    }

}