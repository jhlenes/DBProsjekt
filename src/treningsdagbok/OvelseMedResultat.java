package treningsdagbok;

public class OvelseMedResultat extends Ovelse
{
    private int oktNr;
    private int sett;
    private int repetisjoner;
    private int belastning;

    public OvelseMedResultat(int oktNr, int ovelseNr, String navn, int sett, int repetisjoner, int belastning)
    {
        super(ovelseNr, navn, "");
        this.oktNr = oktNr;
        this.sett = sett;
        this.repetisjoner = repetisjoner;
        this.belastning = belastning;
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

    @Override
    public String toString()
    {
        return String.format("%s: %d x %d x %d kg", getNavn(), getSett(), getRepetisjoner(), getBelastning());
    }
}
