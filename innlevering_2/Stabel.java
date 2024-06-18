public class Stabel<E> extends Lenkeliste<E> {

    @Override
    public void leggTil(E x) {
        Node ny = new Node(x);
        // skal legge til først i listen
        // hvis listen er tom
        if (storrelse == 0) {
            forste = siste = ny;
        } else {
            // hvis listen har elementer
            forste.forrige = ny;
            ny.neste = forste;
            forste = ny;
        }
        storrelse++;
    }

}
