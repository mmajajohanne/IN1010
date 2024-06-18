import java.util.Iterator;
import java.util.NoSuchElementException;

abstract public class Lenkeliste<E> implements Liste<E> {
    protected int storrelse;
    protected Node forste = null, siste = null;

    protected class Node {
        protected Node forrige, neste;
        E innhold;

        public Node(E e) {
            this.innhold = e;
        }
    }

    protected class LenkelisteIterator implements Iterator<E> {
        private Node denne = forste;

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public E next() {
            if (!hasNext()) { // forsikrer at det faktisk finnes en neste
                throw new NoSuchElementException();
            }
            E innhold = denne.innhold;
            denne = denne.neste; // beveger videre til neste
            return innhold;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new LenkelisteIterator();
    }

    @Override
    public int stoerrelse() {
        return storrelse;
    }

    @Override
    public void leggTil(E x) {
        // legger til bakerst i listen
        // hvis listen er tom
        Node ny = new Node(x);
        if (storrelse == 0) {
            forste = siste = ny; // alle pekerne peker på dette elementet
            storrelse++;
            return;
        }
        // hvis det finnes elementer
        // antar at man ikke ønsker å legge til duplikater
        else {
            Node peker = forste;
            while (peker != null && !peker.innhold.equals(x)) {
                peker = peker.neste;
            }
            // invarianten etter while: pekeren er 0 eller lik innholdet til ny node
            // håndterer disse to tilfellene:
            if (peker == null) { // slutten av lista
                siste.neste = ny;
                ny.forrige = siste;
                siste = ny;
                storrelse++;
                return;
            }
            return; // hvis den nye er en duplikat: ikke gjør noe
        }
    }

    @Override
    public E hent() {
        return forste.innhold;
    }

    @Override
    public E fjern() throws UgyldigListeindeks {
        // fjerner det første elementet
        E retur;
        // hvis lista er tom
        if (stoerrelse() == 0) {
            throw new UgyldigListeindeks(-1);
            // return null;
        }
        // hvis det kun er ett element i lista
        Node peker = forste;
        if (stoerrelse() == 1) {
            retur = peker.innhold;
            forste = siste = null;
            storrelse--;
            return retur;
        }
        // hvis det er flere elementer i lista
        retur = forste.innhold;
        forste = forste.neste;
        forste.forrige = null;
        storrelse--;
        return retur;
    }

    @Override
    public String toString() {
        String svar = "";
        Node peker = forste;
        while (peker != null) {
            svar += peker.innhold.toString() + "\n";
            peker = peker.neste;
        }
        return svar;
    }
}
