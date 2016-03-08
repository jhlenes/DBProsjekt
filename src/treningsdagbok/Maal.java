package treningsdagbok;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by ArntKristoffer on 07.03.2016.
 */
public class Maal {
    private int maalNr;
    private int ovelseNr;
    private Date dato;
    private Time tidspunkt;
    private int sett;
    private int repetisjoner;
    private int belastning;

    public Maal(int maalNr, int ovelseNr, Date dato, Time tidspunkt, int sett, int repetisjoner, int belastning) {
        this.maalNr = maalNr;
        this.ovelseNr = ovelseNr;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.sett = sett;
        this.repetisjoner = repetisjoner;
        this.belastning = belastning;
    }

    public Date getDato() {
        return dato;
    }

    public int getBelastning() {
        return belastning;
    }

    public int getMaalNr() {
        return maalNr;
    }

    public int getOvelseNr() {
        return ovelseNr;
    }

    public int getRepetisjoner() {
        return repetisjoner;
    }

    public int getSett() {
        return sett;
    }

    public void setMaalNr(int maalNr) {
        this.maalNr = maalNr;
    }

    public void setOvelseNr(int ovelseNr) {
        this.ovelseNr = ovelseNr;
    }

    public void setDato(Date dato) {
        this.dato = dato;
    }

    public Time getTidspunkt() {
        return tidspunkt;
    }

    public void setTidspunkt(Time tidspunkt) {
        this.tidspunkt = tidspunkt;
    }

    public void setSett(int sett) {
        this.sett = sett;
    }

    public void setRepetisjoner(int repetisjoner) {
        this.repetisjoner = repetisjoner;
    }

    public void setBelastning(int belastning) {
        this.belastning = belastning;
    }

    @Override
    public String toString()
    {
        return dato + ": " + sett + "x" + repetisjoner + "x" + belastning + "kg";
    }
}
