public class Celle {
    // instansvariabler
    boolean levende; // levende (true) eller død (false)
    Celle[] naboer; // array for alle naboene (celle-objekter)
    int antNaboer; // antall naboer
    int antLevendeNaboer; // antall levende naboer

    // konstruktør
    Celle() {
        this.levende = false; // cellen initialiserses som død
        this.naboer = new Celle[8]; // initialiserer array med 8 plasser
        this.antNaboer = 0; // ingen naboer
        this.antLevendeNaboer = 0; // ingen levende naboer
    }

    void settDoed() {
        this.levende = false; // setter status til død
    }

    void settLevende() {
        this.levende = true; // setter status til levende
    }

    boolean erLevende() {
        if (this.levende == true) {// hvis den er levende, returner true
            return true;
        } else {// hvis ikke, returner false
            return false;
        }
    }

    char hentStatusTegn() {
        if (this.erLevende()) { // hvis erLevende-sjekken er true, returner '0'
            return 'O';
        } else {// hvis ikke, returner '.'
            return '.';
        }
    }

    void leggTilNabo(Celle nabo) {
        if (this.antNaboer < naboer.length) { // sjekker at ikke arrayen er full
            naboer[antNaboer] = nabo; // legger til nabo i arrayen
            antNaboer++; // øker antall naboer
        }
    }

    void tellLevendeNaboer() {
        this.antLevendeNaboer = 0;
        int antall = 0;
        for (int i = 0; i < this.naboer.length; i++) {// for løkke som løper gjennom arrayen
            if (this.naboer[i] != null) {// sjekker at elementet i arrayen er null
                if (this.naboer[i].erLevende()) { // hvis objektet i elementet er levende: antall øker med 1
                    antall++;
                }
            }
        }
        this.antLevendeNaboer = antall; // oppdaterer antLevendeNaboer
    }

    void oppdaterStatus() {
        if (this.erLevende()) {
            if ((this.antLevendeNaboer < 2) || this.antLevendeNaboer > 3) {
                this.levende = false;
            } else {
                this.levende = true;
            }
        } else if (this.erLevende() == false) {
            if (this.antLevendeNaboer == 3) {
                this.levende = true;
            } else {
                this.levende = false;
            }
        }
    }
}
