package treningsdagbok;

import java.util.ArrayList;
import java.util.List;

public class OvelseKategori
{

    private String navn;
    private String beskrivelse;
    private List<Ovelse> ovelser;
    private List<OvelseKategori> underKategorier = new ArrayList<>();

    public OvelseKategori(String navn, String beskrivelse, List<Ovelse> ovelser)
    {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
        this.ovelser = ovelser;
    }

    public boolean addOvelse(Ovelse ovelse)
    {
        return ovelser.add(ovelse);
    }

    public boolean removeOvelse(Ovelse ovelse)
    {
        return ovelser.remove(ovelse);
    }

    public boolean addUnderKategori(OvelseKategori kategori)
    {
        return underKategorier.add(kategori);
    }

    public boolean removeUnderKategori(OvelseKategori kategori)
    {
        return underKategorier.remove(kategori);
    }

}
