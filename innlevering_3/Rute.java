public abstract class Rute {
    private int radnummer;
    private int kolonnenummer;
    private Labyrint lab;
    private Rute naboNord;
    private Rute naboSyd;
    private Rute naboVest;
    private Rute naboOest;

    public Rute(int r, int k, Labyrint l) {
        radnummer = r;
        kolonnenummer = k;
        lab = l;
    }

    // settnabo
    public void settNabo(Rute nabo, Posisjon pos) {
        // invarianter med enum: posisjonen oppgitt kan bare være en av de fire
        if (pos == Posisjon.NORD) {
            naboNord = nabo;
        } else if (pos == Posisjon.OEST) {
            naboOest = nabo;
        } else if (pos == Posisjon.VEST) {
            naboVest = nabo;
        } else {
            naboSyd = nabo;
        }
    }
}
