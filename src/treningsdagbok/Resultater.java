package treningsdagbok;

/**
 * Created by Christian on 10.03.2016.
 */
public class Resultater {
    private int ovelseNr;
    private int belastning;
    private  int sett;
    private int repetisjoner;
    private int oktNr;

    public Resultater(int ovelseNr, int belastning, int sett, int repetisjoner, int oktNr) {
        this.ovelseNr = ovelseNr;
        this.belastning = belastning;
        this.sett = sett;
        this.repetisjoner = repetisjoner;
        this.oktNr = oktNr;
    }


    public int getOktNr() {
        return oktNr;
    }

    public void setOktNr(int oktNr) {
        this.oktNr = oktNr;
    }

    public int getOvelseNr() {
        return ovelseNr;
    }

    public void setOvelseNr(int ovelseNr) {
        this.ovelseNr = ovelseNr;
    }

    public int getBelastning() {
        return belastning;
    }

    public void setBelastning(int belastning) {
        this.belastning = belastning;
    }

    public int getSett() {
        return sett;
    }

    public void setSett(int sett) {
        this.sett = sett;
    }

    public int getRepetisjoner() {
        return repetisjoner;
    }

    public void setRepetisjoner(int repetisjoner) {
        this.repetisjoner = repetisjoner;
    }

    @Override
    public String toString() {
        return sett + " x " + repetisjoner + " x " + belastning + " kg";
    }
}
