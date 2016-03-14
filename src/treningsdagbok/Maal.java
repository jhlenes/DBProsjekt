package treningsdagbok;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by ArntKristoffer on 07.03.2016.
 */
public class Maal
{
    private int maalNr;
    private Date dato;
    private Time tidspunkt;
    private int sett;
    private int repetisjoner;
    private int belastning;
    private int ovelseNr;

    public Maal(int maalNr, Date dato, Time tidspunkt, int sett, int repetisjoner, int belastning, int ovelseNr)
    {
        this.maalNr = maalNr;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.sett = sett;
        this.repetisjoner = repetisjoner;
        this.belastning = belastning;
        this.ovelseNr = ovelseNr;
    }

    public int getMaalNr()
    {
        return maalNr;
    }

    public Date getDato()
    {
        return dato;
    }

    public Time getTidspunkt()
    {
        return tidspunkt;
    }

    public int getSett()
    {
        return sett;
    }

    public int getRepetisjoner()
    {
        return repetisjoner;
    }

    public int getBelastning()
    {
        return belastning;
    }

    public int getOvelseNr()
    {
        return ovelseNr;

    }

    @Override
    public String toString()
    {
        return getDato() + ": " + getSett() + " x " + getRepetisjoner() + " x " + getBelastning() + " kg";
    }

}
