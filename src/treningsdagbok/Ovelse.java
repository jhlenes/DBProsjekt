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

    public String getNavn()
    {
        return navn;
    }

    public String getBeskrivelse()
    {
        return beskrivelse;
    }

    @Override
    public String toString()
    {
        if (getBeskrivelse().equals(""))
        {
            return getNavn();
        } else
        {
            return String.format("%s: %s", getNavn(), getBeskrivelse());
        }
    }

}
