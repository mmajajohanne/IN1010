import java.util.ArrayList;

public class Pasient {
    private String navn, fodselsnr;
    private static int ID = 0;
    private int unikID;
    private ArrayList<Resept> reseptliste = new ArrayList<>();

    public Pasient(String n, String nr) {
        navn = n;
        fodselsnr = nr;
        ID++;
        unikID = ID;
    }

    public void leggTilResept(Resept ny) {
        reseptliste.add(ny);
    }

    public String hentNavn() {
        return navn;
    }

    public int hentID() {
        return unikID;
    }
}
