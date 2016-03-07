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
    private Boolean kondis;
    private int tid;
    private int distanse;

    public Maal(int maalNr, int ovelsesNr, Boolean kondis, Date dato, Time tidspunkt) {
        this.maalNr = maalNr;
        this.ovelsesNr = ovelsesNr;
        this.kondis = kondis;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
    }

    public Boolean getKondis() {
        return kondis;
    }

    public void setKondis(Boolean kondis) {
        this.kondis = kondis;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getDistanse() {
        return distanse;
    }

    public void setDistanse(int distanse) {
        this.distanse = distanse;
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
