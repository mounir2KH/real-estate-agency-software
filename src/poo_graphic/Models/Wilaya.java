package poo_graphic.Models;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Wilaya {
    String nomWilaya;
    double prixMetreCarre;
    private static HashMap<String, Double> prixWilayas = new HashMap<String, Double>();

    public static Wilaya getWilaya(String nomWilaya) {
        try {
            return new Wilaya(nomWilaya);
        } catch (Exception err) {
            return null;
        }
    }
    public static List<String> getWilayas() {
        List<String> resultat = new LinkedList<>();

        prixWilayas.forEach((String key, Double value) -> resultat.add(key));

        return resultat;
    }

    public Wilaya(String nomWilaya) throws WilayaInexistante {
        if (prixWilayas.containsKey(nomWilaya)) {
            this.nomWilaya = nomWilaya;
            this.prixMetreCarre = prixWilayas.get(nomWilaya).doubleValue();
        } else {
            throw new WilayaInexistante();
        }
    }

    public Wilaya(String nomWilaya, double prixMetreCarre) {
        this.nomWilaya = nomWilaya;
        this.prixMetreCarre = prixMetreCarre;
        if (!prixWilayas.containsKey(nomWilaya)) {
            prixWilayas.put(nomWilaya, prixMetreCarre);
        }
    }

    public String getNomWilaya() {
        return nomWilaya;
    }

    public double getPrixMetreCarre() {
        return prixMetreCarre;
    }
}
