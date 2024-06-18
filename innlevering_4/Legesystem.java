import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Legesystem {
    private IndeksertListe<Pasient> pasientOversikt = new IndeksertListe<>();
    private IndeksertListe<Legemiddel> legemiddelOversikt = new IndeksertListe<>();
    private IndeksertListe<Lege> legeOversikt = new IndeksertListe<>();
    private IndeksertListe<Resept> reseptOversikt = new IndeksertListe<>();

    public Legesystem(String filnavn) {
        try {
            this.lesFraFil(filnavn);
        } catch (UgyldigDataException e) {
            System.out.println("Ugyldig data " + e.getMessage());
        } catch (UlovligUtskrift x) {
            System.out.println("Ulovlig utskrift av lege");
        }

    }

    public void lesFraFil(String filnavn) throws UgyldigDataException, UlovligUtskrift {
        try {
            File fil = new File(filnavn);
            Scanner s = new Scanner(fil);
            int hashteller = 0;

            while (s.hasNextLine()) {
                String linje = s.nextLine();
                String[] biter = linje.split(",");
                if (!biter[0].equals("#")) {
                    try {
                        // Pasient
                        if (hashteller == 1) {
                            if (biter.length != 2) {
                                throw new UgyldigDataException("Feil antall felt for pasient");
                            } else {
                                pasientOversikt.leggTil(pasientOversikt.stoerrelse(),
                                        new Pasient(biter[0], biter[1]));

                            }

                            // Legemiddel
                        } else if (hashteller == 2) {
                            if (biter.length < 4 || biter.length > 5) {
                                throw new UgyldigDataException("Feil antall felt for legemiddel");
                            } else {
                                // type: vanlig
                                if (biter[1].equals("vanlig")) {
                                    legemiddelOversikt.leggTil(legemiddelOversikt.stoerrelse(),
                                            new Vanlig(biter[0], Integer.parseInt(biter[2]),
                                                    Integer.parseInt(biter[3])));

                                    // type: vanedannende
                                } else if (biter[1].equals("vandeannende")) {
                                    legemiddelOversikt.leggTil(legemiddelOversikt.stoerrelse(),
                                            new Vanedannende(biter[0], Integer.parseInt(biter[2]),
                                                    Integer.parseInt(biter[3]), Integer.parseInt(biter[4])));

                                    // type: narkotisk
                                } else if (biter[1].equals("narkotisk")) {
                                    legemiddelOversikt.leggTil(legemiddelOversikt.stoerrelse(),
                                            new Narkotisk(biter[0], Integer.parseInt(biter[2]),
                                                    Integer.parseInt(biter[3]), Integer.parseInt(biter[4])));
                                }
                            }

                            // Lege
                        } else if (hashteller == 3) {
                            if (biter.length != 2) {
                                throw new UgyldigDataException("Feil antall felt for lege");
                            } else {
                                if (biter[1].equals("0")) { // ikke spesialist
                                    legeOversikt.leggTil(0, new Lege(biter[0]));
                                } else { // spesialist
                                    legeOversikt.leggTil(0, new Spesialist(biter[0], biter[1]));
                                }
                            }

                            // Resept
                        } else if (hashteller == 4) {
                            if (biter.length < 4 || biter.length > 5) {
                                throw new UgyldigDataException("Feil antall felt for resept");
                            } else {
                                Lege lege = legeOversikt.hent(Integer.parseInt(biter[1]));
                                Legemiddel legemiddel = legemiddelOversikt.hent(Integer.parseInt(biter[0]));
                                Pasient pasient = pasientOversikt.hent(Integer.parseInt(biter[2]));
                                int reit = biter.length > 4 ? Integer.parseInt(biter[4]) : 0; // noen har ikke reit
                                String type = biter[3];
                                for (Lege element : legeOversikt) {
                                    if (element.hentNavn().equals(lege.hentNavn())) {
                                        if (type.equals("hvit")) {
                                            reseptOversikt.leggTil(reseptOversikt.stoerrelse(),
                                                    lege.skrivHvitResept(legemiddel, pasient, reit));
                                        } else if (type.equals("blaa")) {
                                            reseptOversikt.leggTil(reseptOversikt.stoerrelse(),
                                                    lege.skrivBlaaResept(legemiddel, pasient, reit));
                                        } else if (type.equals("militaer")) {
                                            reseptOversikt.leggTil(reseptOversikt.stoerrelse(),
                                                    lege.skrivMilResept(legemiddel, pasient));
                                        } else if (type.equals("p")) {
                                            reseptOversikt.leggTil(reseptOversikt.stoerrelse(),
                                                    lege.skrivPResept(legemiddel, pasient, reit));
                                        }
                                    }
                                }
                            }

                        }
                    } catch (UgyldigDataException e) {
                        System.err.println("Ugyldig data oppdaget i linje: " + linje + " | Feil: " + e.getMessage());
                    }

                } else if (biter[0].equals("#")) {
                    hashteller++;
                }

            }
            s.close();
        } catch (

        FileNotFoundException e) {
            System.out.println("Fil " + filnavn + " ikke funnet.");
        }
    }

}
