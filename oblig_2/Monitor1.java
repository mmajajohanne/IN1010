import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor1 {
    private final SubsekvensRegister register;
    private final Lock lock = new ReentrantLock();

    public Monitor1() {
        this.register = new SubsekvensRegister(); // opretter en privat instans av SubsekvensRegister
    }

    // pakker inn settInn metoden i registret med låsing for å gjøre den trådsikker
    public void settInn(HashMap<String, Subsekvens> hm) {
        lock.lock(); // får låsen før den utfører operasjonen
        try {
            register.settInn(hm);
        } finally {
            lock.unlock(); // frigir låsen
        }
    }

    // pakker på samme måte inn taUtHashMap-metoden med locks
    public HashMap<String, Subsekvens> taUtHashMap(int index) {
        lock.lock();
        try {
            return register.taUtHashMap(index);
        } finally {
            lock.unlock();
        }
    }

    // metoden som henter antall hashmaps trenger ikke låses fordi den kun leser
    public int hentAntall() {
        return register.hentAntall();
    }

    // slåSammenHashMaps endrer ikke på delte ressurser, trenger ikke synkroniseres
    public static HashMap<String, Subsekvens> slåSammenHashMaps(
            HashMap<String, Subsekvens> map1, HashMap<String, Subsekvens> map2) {
        return SubsekvensRegister.slåSammenHashMaps(map1, map2);
    }

    // metoden som leser fra fil og lagrer subsekvenser trenger ikke låses fordi den
    // kun leser
    public static HashMap<String, Subsekvens> lesFraFilOgLagreSubsekvenser(String filnavn) {
        return SubsekvensRegister.lesFraFilOgLagreSubsekvenser(filnavn);
    }
}
