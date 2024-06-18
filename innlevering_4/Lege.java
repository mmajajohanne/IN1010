public class Lege implements Comparable<Lege> {
    protected String navn;
    protected IndeksertListe<Resept> utskrevneResepter;

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    public void leggtilResept(Resept ny) {
        utskrevneResepter.leggTil(0, ny);
    }

    public IndeksertListe<Resept> hentUtskrevneResepter() {
        return utskrevneResepter;
    }

    public Hvit skrivHvitResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            Hvit nyresept = new Hvit(legemiddel, this, pasient, reit);
            leggtilResept(nyresept);
            return nyresept;
        }
    }

    public MilResept skrivMilResept(Legemiddel legemiddel, Pasient pasient) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            MilResept nyresept = new MilResept(legemiddel, this, pasient);
            leggtilResept(nyresept);
            return nyresept;
        }
    }

    public PResept skrivPResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            throw new UlovligUtskrift(this, legemiddel);
        } else {
            PResept nyresept = new PResept(legemiddel, this, pasient, reit);
            leggtilResept(nyresept);
            return nyresept;
        }
    }

    public Blaa skrivBlaaResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        if (legemiddel instanceof Narkotisk) {
            if (this instanceof Spesialist) {
                Blaa nyresept = new Blaa(legemiddel, this, pasient, reit);
                leggtilResept(nyresept);
                return nyresept;
            } else {
                throw new UlovligUtskrift(this, legemiddel);
            }
        } else {
            Blaa nyresept = new Blaa(legemiddel, this, pasient, reit);
            leggtilResept(nyresept);
            return nyresept;
        }
    }

    @Override
    public String toString() {
        return ("Navn: " + hentNavn());
    }

    @Override
    public int compareTo(Lege annen) {
        return this.navn.compareTo(annen.navn); // bruker compareTo metoden til String klassen
    }
}
