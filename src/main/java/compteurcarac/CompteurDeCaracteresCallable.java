package compteurcarac;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class CompteurDeCaracteresCallable implements Callable<ResultatDuCompte> {

    private final String urlATraiter;


    public CompteurDeCaracteresCallable(String urlATraiter) {
        this.urlATraiter = urlATraiter;
    }

    @Override
    public ResultatDuCompte call() {
        try {
            Instant start = Instant.now();

            // Téléchargement du contenu
            String contenu = "";
            // Formule magique pour lire le contenu d'un URL dans une chaine de caractères
            try (Scanner scanner = new Scanner(new URL(urlATraiter).openStream(), StandardCharsets.UTF_8)) {
                contenu = scanner.useDelimiter("\\A").next();
            }

            int nombreDeCaracteres = contenu.length();
            // Combien de temps ça a pris pour calculer le résultat
            Duration tempsDeCalcul = Duration.between(start, Instant.now());

            System.out.printf("Il y a %d caractères dans l'URL %s (%s ms) %n", nombreDeCaracteres, urlATraiter,
                    tempsDeCalcul.toMillis());

            return new ResultatDuCompte(nombreDeCaracteres, tempsDeCalcul);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
    
        }
    }
}
