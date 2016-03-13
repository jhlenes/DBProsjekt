package treningsdagbok;

import java.sql.Date;
import java.sql.Time;

public class Treningsokt
{
    private int oktNr;
    private Date dato;
    private Time tidspunkt;
    private int varighet;
    private int form;
    private int prestasjon;
    private int luftkvalitet;
    private int temperatur;
    private String notat;

    public Treningsokt(int oktNr, Date dato, Time tidspunkt, int varighet, int form, int prestasjon, String notat, int luftkvalitet, int temperatur)
    {
        this.oktNr = oktNr;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.varighet = varighet;
        this.form = form;
        this.prestasjon = prestasjon;
        this.notat = notat;
        this.luftkvalitet = luftkvalitet;
        this.temperatur = temperatur;
    }

    @Override
    public String toString()
    {
        return "Treningsøkt " + getDato() +
                " kl: " + getTidspunkt() +
                ", varighet: " + getVarighet() +
                ", formen: " + getForm() +
                ", prestasjonen: " + getPrestasjon() +
                ", luftkvaliteten: " + getLuftkvalitet() +
                ", temperaturen: " + getTemperatur() +
                ", notat: " + getNotat();
    }

    public int getOktNr()
    {
        return oktNr;
    }

    public Date getDato()
    {
        return dato;
    }

    public Time getTidspunkt()
    {
        return tidspunkt;
    }

    public int getVarighet()
    {
        return varighet;
    }

    public int getForm()
    {
        return form;
    }

    public int getPrestasjon()
    {
        return prestasjon;
    }

    public String getNotat()
    {
        return notat;
    }

    public int getLuftkvalitet()
    {
        return luftkvalitet;
    }

    public int getTemperatur()
    {
        return temperatur;
    }

}