public class Bil3 {
    String bilNr;

    Bil3(String bilNr) {
        this.bilNr = bilNr;
    }

    void skrivUt() {
        System.out.println("bilnummer:" + this.bilNr);
    }

    String hentNummer() {
        return this.bilNr;
    }
}
