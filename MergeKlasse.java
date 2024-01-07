// Klasse som implementerer en del av Merge Sort. Den sorterer elementer fra 2 lister, og returnerer 1 sortert liste.

class MergeKlasse {
    /* Verdiene er statiske saa de ikke skal resette til null hver gang merge kalles paa
    Lager array som lagrer antall sammenligninger og bytter. 4 plasser fordi n og tid skal legges til senere*/
    static int cmpTeller = 0;
    static int antBytter = 0;
    long[] miniResListe = new long[4];
    
    public long[] merge(long[] A1, long[] A2, long[] A) {
        int i = 0;
        int j = 0;
        // Sjekker om element i A1 er mindre enn/lik det i A2. Hvis ja, settes det inn i A. Hvis ikke, settes elementet fra A2 inn i A.
        while(i < A1.length && j < A2.length) {
            if(A1[i] <= A2[j]) {
                A[i+j] = A1[i];
                i++;
            }
            else {
                A[i+j] = A2[j];
                j ++;
            }
            cmpTeller ++;
        }
        // Disse loopene sjekker om det er resterende elementer i arrayene, og legger dem evt til i A.
        while(i < A1.length) {
            A[i+j] = A1[i];
            i++;
        }
        while(j< A2.length) {
            A[i+j] = A2[j];
            j++;
        }

        miniResListe[1] = cmpTeller;
        miniResListe[2] = antBytter;
        return A;
    }

    public long [] hentMiniRes() {
        return miniResListe;
    }

    // Nullstiller variablene. Metoden kalles paa i MergeSort.
    public void nullstill() {
        cmpTeller = 0;
        antBytter = 0;
    }
}