public class Verden {
    Rutenett rutenett;
    int genNr;

    // konstruktør
    Verden(int rader, int kolonner) {
        this.rutenett = new Rutenett(rader, kolonner);
        this.genNr = 0;
        rutenett.fyllMedTilfeldigeCeller();
        rutenett.kobleAlleCeller();
    }

    void tegn() {
        rutenett.tegnRutenett();
        System.out.println("Generasjon nummer: " + genNr + ".");
        System.out.println("Antall levende celler: " + rutenett.antallLevende() + ".");

    }

    void oppdatering() {
        this.genNr++; // øker generasjonsnr
        // går gjennom rutenettet med to for-løkker og bruker tellLevendeNaboer-metoden
        // på hvert celle-objekt
        for (int r = 0; r < rutenett.antRader; r++) {
            for (int k = 0; k < rutenett.antKolonner; k++) {
                rutenett.hentCelle(r, k).tellLevendeNaboer();
            }
        }
        // går gjennom rutenettet på nytt og oppdaterer status på hvert celle-objekt
        for (int r = 0; r < rutenett.antRader; r++) {
            for (int k = 0; k < rutenett.antKolonner; k++) {
                rutenett.hentCelle(r, k).oppdaterStatus();
            }
        }
    }
}
