public class Rutenett {

    int antRader;
    int antKolonner;
    Celle[][] rutene;

    // konstruktør
    Rutenett(int antRader, int antKolonner) {
        this.antRader = antRader;
        this.antKolonner = antKolonner;
        this.rutene = new Celle[antRader][antKolonner];
    }

    void lagCelle(int rad, int kol) {
        // 1/3-sjanse for at cellen blir satt til levende
        Celle c = new Celle();
        if (Math.random() <= 0.3333) {
            c.settLevende();
        }

        // legger til cellen i rutenettet
        rutene[rad][kol] = c;
    }

    void fyllMedTilfeldigeCeller() {
        for (int k = 0; k < antKolonner; k++) {
            for (int r = 0; r < antRader; r++) {
                this.lagCelle(r, k);
            }
        }
    }

    Celle hentCelle(int rad, int kolonne) {
        // sjekker om koordinatene gitt er ugyldige
        if ((rad < 0) || (rad >= this.antRader) || (kolonne < 0) || (kolonne >= this.antKolonner)) {
            return null;
        } else {
            return rutene[rad][kolonne];
        }

    }

    void tegnRutenett() {
        // tegner toppen av rutenettet
        System.out.print("+");
        for (int k = 0; k < antKolonner; k++) {
            System.out.print("-+");
        }
        System.out.println();

        for (int r = 0; r < antRader; r++) {
            System.out.print("|");
            for (int k = 0; k < antKolonner; k++) {
                // tegner hver celle etterfulgt av en vertikal linje
                System.out.print(rutene[r][k].hentStatusTegn() + "|");
            }
            System.out.println();

            // tegner en horisontal linje under hver rad
            System.out.print("+");
            for (int k = 0; k < antKolonner; k++) {
                System.out.print("-+");
            }
            System.out.println();
        }
    }

    void settNaboer(int rad, int kolonne) {
        Celle c = this.hentCelle(rad, kolonne);
        if (c == null) {
            return; // ugyldig celleposisjon
        }

        // går gjennom alle potensielle naboposisjoner
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    // ignorerer den opprinnelige cellen
                    continue;
                }
                int naboRad = rad + i;
                int naboKol = kolonne + j;
                // sjekker om naboposisjonen er gyldig
                if (naboRad >= 0 && naboRad < antRader && naboKol >= 0 && naboKol < antKolonner) {
                    Celle naboCelle = this.hentCelle(naboRad, naboKol);
                    c.leggTilNabo(naboCelle);
                }
            }
        }
    }

    void kobleAlleCeller() {
        // går gjennom rutenettet og bruker sett.Naboer på alle cellene
        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                this.settNaboer(r, k);
            }

        }
    }

    int antallLevende() {
        int teller = 0;
        // går gjennom rutenettet og øker telleren for hver levende celle
        for (int r = 0; r < antRader; r++) {
            for (int k = 0; k < antKolonner; k++) {
                if (rutene[r][k].erLevende()) {
                    teller++;
                }
            }
        }
        return teller;
    }

}
