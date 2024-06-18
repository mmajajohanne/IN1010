import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Monitor2 {
    private SubsekvensRegister register = new SubsekvensRegister();
    private Lock lock = new ReentrantLock();
    private Condition tilgjengelig = lock.newCondition();
    private Condition ferdigSignal = lock.newCondition();
    private boolean ferdigFlettet = false;

    // legger inn en subsekvens i registeret
    public void leggInn(HashMap<String, Subsekvens> subsekvens) {
        lock.lock();
        try {
            register.settInn(subsekvens);
            tilgjengelig.signal(); // signaliserer at det er nytt innhold
        } finally {
            lock.unlock();
        }
    }

    // tar ut en subsekvens fra registeret basert på indeks
    public HashMap<String, Subsekvens> taUt(int index) {
        lock.lock();
        try {
            while (register.hentAntall() <= index) {
                tilgjengelig.await(); // venter til det er nok innhold
            }
            return register.taUtHashMap(index);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    // henter antall subsekvenser i registeret
    public int antallSubsekvenser() {
        lock.lock();
        try {
            return register.hentAntall();
        } finally {
            lock.unlock();
        }
    }

    // tar ut to subsekvenser for fletting
    public ArrayList<HashMap<String, Subsekvens>> hentToForFletting() {
        ArrayList<HashMap<String, Subsekvens>> subsekvenser = new ArrayList<>();
        subsekvenser.add(taUt(0)); // ta ut første element
        subsekvenser.add(taUt(0)); // ta ut neste element
        return subsekvenser;
    }

    // flagger variabelen for ferdig fletting og signaliserer dette
    public void ferdigFlettet() {
        lock.lock();
        try {
            ferdigFlettet = true;
            ferdigSignal.signalAll(); // vekker alle tråder som venter på at flettingen skal være ferdig
        } finally {
            lock.unlock();
        }
    }
}
