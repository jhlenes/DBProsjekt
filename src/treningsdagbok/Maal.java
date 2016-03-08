package treningsdagbok;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by ArntKristoffer on 07.03.2016.
 */
public class Maal {
    private int maalNr;
    private int ovelsesNr;
    private Date dato;
    private Time tidspunkt;
    private int sett;
    private int repetisjoner;
    private int belastning;

    public Maal(int maalNr1, int ovelseNr, Date dato, Time tidspunkt, int sett, int repetisjoner, int belastning) {
        this.maalNr = maalNr;
        this.ovelsesNr = ovelsesNr;
        this.dato = this.dato;
        this.tidspunkt = this.tidspunkt;
        this.sett = this.sett;
        this.repetisjoner = this.repetisjoner;
        this.belastning = this.belastning;
    }

    public int getBelastning() {
        return belastning;
    }

    public int getMaalNr() {
        return maalNr;
    }

    public int getOvelsesNr() {
        return ovelsesNr;
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

    public void setOvelsesNr(int ovelsesNr) {
        this.ovelsesNr = ovelsesNr;
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

}
