public class IndeksertListe<E> extends Lenkeliste<E> {

    public void leggTil(int pos, E x) throws UgyldigListeindeks {
        // sjekker først om posisjonen oppgitt er gyldig
        if (pos > stoerrelse() || pos < 0) {
            throw new UgyldigListeindeks(pos);
        }

        Node ny = new Node(x);
        // håndterer de ulike tilstandene lista kan ha:
        // hvis lista er tom
        if (stoerrelse() == 0) {
            forste = siste = ny;
            // hvis man skal legge til i starten av en ikke-tom liste
        } else if (pos == 0) {
            ny.neste = forste;
            forste.forrige = ny;
            forste = ny;
            // hvis man skal legge til på slutten av listen
        } else if (pos == stoerrelse()) {
            siste.neste = ny;
            ny.forrige = siste;
            siste = ny;
            // hvis man skal legge til midt i listen
        } else {
            Node peker = forste;
            for (int teller = 1; teller < pos; teller++) {
                peker = peker.neste;
            } // invariant etter for: peker refererer til noden rett før pos
            ny.neste = peker.neste;
            peker.neste.forrige = ny;
            peker.neste = ny;
            ny.forrige = peker;
        }
        storrelse++;
    }

    public void sett(int pos, E x) {
        Node peker = forste;
        // sjekker først om indeksen er gyldig
        if (pos >= stoerrelse() || pos < 0) {
            throw new UgyldigListeindeks(pos);
        } else {
            int teller = 0;
            while (teller < pos) {
                peker = peker.neste;
                teller++;
            }
            // invariant etter while: pekeren refererer til noden på posisjonen oppgitt
            peker.innhold = x; // oppdaterer noden
        }
    }

    public E hent(int pos) throws UgyldigListeindeks {
        // sjekker først om pos er gyldig indeks
        if (pos >= stoerrelse() || pos < 0) {
            throw new UgyldigListeindeks(pos);
        }

        Node peker = forste;
        int teller = 0;
        while (teller < pos) {
            peker = peker.neste;
            teller++;
        } // invariant etter while: pekeren refererer til noden på gitt posisjon pos
        return peker.innhold;

    }

    // Metoden fjern(int pos) skal fjerne elementet i posisjon pos (der
    // 0<=pos<stoerrelse()) og returnere det. Det innebærer at alle elementene
    // lenger ut i listen vil få en lavere indeks
    public E fjern(int pos) throws UgyldigListeindeks {
        // sjekker om listen er tom eller indeksen er ugyldig
        if (stoerrelse() == 0 || pos >= stoerrelse() || pos < 0) {
            throw new UgyldigListeindeks(pos);
        }

        E retur;
        // vurderer de ulike tilfellene:
        // hvis man vil fjerne det første elementet
        if (pos == 0) {
            retur = forste.innhold;
            // hvis det er det eneste elementet i listen
            if (forste == siste) {
                forste = siste = null;
            } else {
                forste = forste.neste;
                forste.forrige = null;
            }
            // hvis man vil fjerne det siste elementet
        } else if (pos == stoerrelse() - 1) {
            retur = siste.innhold;
            siste = siste.forrige;
            siste.neste = null;
            // hvis man vil fjerne et element midt i listen
        } else {
            Node peker = forste;
            int teller = 0;
            while (teller < pos) {
                peker = peker.neste;
                teller++;
            } // invariant: pekeren peker på noden som skal fjernes, og teller er lik pos

            retur = peker.innhold;
            // knytter sammen nodene foran og bak den som skal fjernes
            peker.forrige.neste = peker.neste;
            if (peker.neste != null) { // sjekker for å unngå NullPointerException
                peker.neste.forrige = peker.forrige;
            }
        }

        storrelse--;
        return retur;
    }
}