package treningsdagbok;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

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
    public String toString() {
        return "Treningsokt:" +
                ", dato=" + dato +
                ", tidspunkt=" + tidspunkt +
                ", varighet=" + varighet +
                ", form=" + form +
                ", prestasjon=" + prestasjon +
                ", luftkvalitet=" + luftkvalitet +
                ", temperatur=" + temperatur +
                ", notat='" + notat + '\'' +
                '}';
    }

    public int getOktNr()
    {
        return oktNr;
    }

    public void setOktNr(int oktNr)
    {
        this.oktNr = oktNr;
    }

    public Date getDato()
    {
        return dato;
    }

    public void setDato(Date dato)
    {
        this.dato = dato;
    }

    public Time getTidspunkt()
    {
        return tidspunkt;
    }

    public void setTidspunkt(Time tidspunkt)
    {
        this.tidspunkt = tidspunkt;
    }

    public int getVarighet()
    {
        return varighet;
    }

    public void setVarighet(int varighet)
    {
        this.varighet = varighet;
    }

    public int getForm()
    {
        return form;
    }

    public void setForm(int form)
    {
        this.form = form;
    }

    public int getPrestasjon()
    {
        return prestasjon;
    }

    public void setPrestasjon(int prestasjon)
    {
        this.prestasjon = prestasjon;
    }

    public String getNotat()
    {
        return notat;
    }

    public void setNotat(String notat)
    {
        this.notat = notat;
    }

    public int getLuftkvalitet()
    {
        return luftkvalitet;
    }

    public void setLuftkvalitet(int luftkvalitet)
    {
        this.luftkvalitet = luftkvalitet;
    }

    public int getTemperatur()
    {
        return temperatur;
    }

    public void setTemperatur(int temperatur)
    {
        this.temperatur = temperatur;
    }



}