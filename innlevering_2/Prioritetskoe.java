public class Prioritetskoe<E extends Comparable<E>> extends Lenkeliste<E> {

    @Override
    public void leggTil(E x) {
        Node ny = new Node(x);

        // hvis listen er tom:
        if (forste == null) {
            forste = siste = ny;
        } else {
            Node peker = forste;
            Node forrige = null;

            // går gjennom listen for å finne riktig posisjon basert på sammenligningen
            while (peker != null && peker.innhold.compareTo(x) < 0) {
                forrige = peker;
                peker = peker.neste;
            }

            // hvis forrige node er null, er den nye noden den første i listen.
            if (forrige == null) {
                ny.neste = forste;
                forste.forrige = ny;
                forste = ny;
            } else if (peker == null) { // hvis peker er null, skal den nye noden være sist i listen.
                siste.neste = ny;
                ny.forrige = siste;
                siste = ny;
            } else { // den nye noden skal være mellom forrige og peker.
                ny.neste = peker;
                ny.forrige = forrige;
                forrige.neste = ny;
                peker.forrige = ny;
            }
        }
        storrelse++;
    }
}
