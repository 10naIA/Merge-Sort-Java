/* Tidligere hovedprogram for Merge sort-algoritmen, som leser inn tall fra en tekstfil, og sorterer dem. Tallene lagres i en ArrayList, 
og kopieres over til en Array, for at det skal ligne mest mulig paa pseudokoden fra forelesningen. Til slutt skrives den sorterte listen til ny fil.
Programmet oppretter baade csv-fil med resultater, og out-fil med den sorterte arrayen.*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

class MergeSortOppg {
    public void losOppg(String fil) {
        // Leser inn filnavn, og legger elementene inn i array-liste siden antall elementer er ukjent under innlesing.
        ArrayList<Integer> midlListe = new ArrayList<>();
        String stripFil = fil.replace(".txt", "");
        try{
            Scanner les = new Scanner(new File(fil));
            while(les.hasNextLine()) {
                int linje = les.nextInt();
                // Maa lese linjeskift for aa unngaa error!
                les.nextLine();
                midlListe.add(linje);
            }
        }
        catch(FileNotFoundException e) {
            System.out.println("Filen finnes ikke.");
        }
        // Flytter elementer inn i array, i stedet for arrayList.
        long[] A = new long[midlListe.size()];
        for(int i = 0; i < midlListe.size(); i++) {
            A[i] = midlListe.get(i);
        }

        // **Kode for oppg om sammenligninger, bytter og tid - start**
        long[][] resListe = new long[A.length+1][4]; 
        for(int i = 0; i < resListe.length; i++) {
            MergeSortKlasse sorterEl = new MergeSortKlasse();
            long [] miniA = Arrays.copyOfRange(A, 0, i);
            long t = System.nanoTime();
            sorterEl.mergeSort(miniA);
            long time = (System.nanoTime()-t) /1000;
            // Henter resultater fra sorteringsalgoritmen
            resListe[i] = sorterEl.hentMiniRes();
            resListe[i][0] = i;
            resListe[i][3] = time;
            sorterEl.nullstill();
        }

        // Sjekker om resultat-fil allerede finnes. Hvis nei, opprettes den
        try{
            FileWriter skrivFil;
            if(!new File(stripFil + "_results.csv").isFile()) {
                skrivFil = new FileWriter(stripFil + "_results.csv");
                skrivFil.write("n,merge_cmp,merge_swaps,merge_time");
                for(int rad = 0; rad < resListe.length; rad++) {
                    skrivFil.write("\n");
                    for(int kol = 0; kol < resListe[rad].length; kol++) {
                        skrivFil.write(resListe[rad][kol] + ",");
                    }
                }
                skrivFil.close();
            }
            /* Hvis fil finnes fra foer, har den alg1 allerede, saa vi maa lese den inn i ny fil + legge inn alg2-resultater samtidig, saa det 
            kommer paa samme linjer*/
            else{
                Scanner les = new Scanner(new File(stripFil +"_results.csv"));
                FileWriter nyFil = new FileWriter(stripFil + "_resultsNY.csv");
                int linjeNr = 0;
                while(les.hasNext()) {
                    String linje = les.next();
                    nyFil.write(linje);
                    if(linje.endsWith("insert_time") || linje.endsWith("merge_time")) {
                        nyFil.write(",merge_cmp,merge_swaps,merge_time");
                    }
                    else{
                        // Starter paa indeks 1 for aa ikke ta med n
                        for(int i = 1; i < resListe[linjeNr].length; i++) {
                            nyFil.write(resListe[linjeNr][i] + ",");
                        }
                        linjeNr ++;
                    }
                    nyFil.write("\n");
                }
                nyFil.close();
                les.close();

                // Fjerner gammel fil, og gir korrekt navn til ny fil.
                File slettFil = new File(stripFil + "_results.csv");
                slettFil.delete();
                File endreNavn = new File(stripFil + "_resultsNY.csv");
                endreNavn.renameTo(new File(stripFil + "_results.csv"));
            }
        }
        catch(IOException i) {
            System.out.println("Feil. Er filen allerede aapen?");
        }
        // **Kode for oppg om sammenligninger, bytter og tid - slutt**


        // **Kode for oppg om korrekthet - start**
        MergeSortKlasse sorter = new MergeSortKlasse();
        long [] sortertA = sorter.mergeSort(A);
        
        try{
            FileWriter utFil = new FileWriter(stripFil + "_merge.out");
            for(long tall : sortertA) {
                utFil.write(tall + "\n");
            }
            utFil.close();
        }
        catch(IOException i) {
            System.out.println("Filen finnes allerede.");
        }
        // **Kode for oppg om korrekthet -slutt**
    }
}