package treningsdagbok;

public class Resultat
{
    private int ovelseNr;
    private int belastning;
    private int sett;
    private int repetisjoner;
    private int oktNr;

    public Resultat(int ovelseNr, int belastning, int sett, int repetisjoner, int oktNr)
    {
        this.ovelseNr = ovelseNr;
        this.belastning = belastning;
        this.sett = sett;
        this.repetisjoner = repetisjoner;
        this.oktNr = oktNr;
    }


    public int getOvelseNr()
    {
        return ovelseNr;
    }

    public int getBelastning()
    {
        return belastning;
    }

    public int getSett()
    {
        return sett;
    }

    public int getRepetisjoner()
    {
        return repetisjoner;
    }

    public int getOktNr()
    {
        return oktNr;
    }

    @Override
    public String toString()
    {
        return getSett() + " x " + getRepetisjoner() + " x " + getBelastning() + " kg";
    }

}
