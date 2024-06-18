public class Subsekvens {
    public final String subsekvens;
    private int antall;

    public Subsekvens(String sub, int ant) {
        subsekvens = sub;
        antall = ant;
    }

    public int hentAntallForekomster() {
        return antall;
    }

    public void økAntallForekomster() {
        antall++;
    }

    @Override
    public String toString() {
        return "(" + subsekvens + "," + antall + ")";
    }
}