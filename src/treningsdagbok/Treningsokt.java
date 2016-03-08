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

    private List<Ovelse> ovelser = new ArrayList<>();

    public Treningsokt(int oktNr, Date dato, Time tidspunkt, int varighet, int form, int prestasjon, int luftkvalitet, int temperatur, String notat)
    {
        this.oktNr = oktNr;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.varighet = varighet;
        this.form = form;
        this.prestasjon = prestasjon;
        this.luftkvalitet = luftkvalitet;
        this.temperatur = temperatur;
        this.notat = notat;
    }

    public boolean addOvelser(List<Ovelse> ovelser)
    {
        return this.ovelser.addAll(ovelser);
    }

    public boolean addOvelse(Ovelse ovelse)
    {
        return this.ovelser.add(ovelse);
    }

    public boolean removeOvelse(Ovelse ovelse)
    {
        return this.ovelser.remove(ovelse);
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