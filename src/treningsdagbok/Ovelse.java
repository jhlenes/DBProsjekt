package treningsdagbok;

public class Ovelse
{
    private int ovelseNr;
    private String navn;
    private String beskrivelse;

    public Ovelse(int ovelseNr, String navn, String beskrivelse)
    {
        this.ovelseNr = ovelseNr;
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    public int getOvelseNr()
    {
        return ovelseNr;
    }

    public void setOvelseNr(int ovelseNr)
    {
        this.ovelseNr = ovelseNr;
    }

    public String getNavn()
    {
        return navn;
    }

    public void setNavn(String navn)
    {
        this.navn = navn;
    }

    public String getBeskrivelse()
    {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse)
    {
        this.beskrivelse = beskrivelse;
    }

    @Override
    public String toString()
    {
        if (getBeskrivelse().equals(""))
        {
            return getNavn();
        } else
        {
            return getNavn() + ": " + getBeskrivelse();
        }
    }
}
