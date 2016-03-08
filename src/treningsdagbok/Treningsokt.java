package treningsdagbok;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Treningsokt
{

    private int oktNr;
    private Date dato;
    private int varighet;
    private boolean innendors;

    // Can be null
    private int form = 0;
    private int prestasjon = 0;
    private String notat = "";
    private int luftkvalitet = 0;
    private String vaertype = "";
    private int temperatur = 0;

    private List<Ovelse> ovelser = new ArrayList<>();

    public Treningsokt(int oktNr, Date dato, int varighet, boolean innendors)
    {
        this.oktNr = oktNr;
        this.dato = dato;
        this.varighet = varighet;
        this.innendors = innendors;
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

    public String getVaertype()
    {
        return vaertype;
    }

    public void setVaertype(String vaertype)
    {
        this.vaertype = vaertype;
    }

    public int getTemperatur()
    {
        return temperatur;
    }

    public void setTemperatur(int temperatur)
    {
        this.temperatur = temperatur;
    }

    public boolean isInnendors()
    {
        return innendors;
    }

    public void setInnendors(boolean innendors)
    {
        this.innendors = innendors;
    }

}