/* Klasse som implementerer en del av Merge Sort. Brukes for aa rekursivt oppdele en array til enkelt-elementer. 
To og to sorteres deretter sammen med Merge. */

import java.util.Arrays;

class MergeSortKlasse {
    // Lager array for aa legge til resultater fra merge-klassen
    long[] miniRes = new long[4];
    MergeKlasse sorter = new MergeKlasse();

    public long[] mergeSort(long[] A) {
        int n = A.length;
        if(n <= 1) {
            return A;
        }
        int i = n/2;
        // Deler arrayen i 2, og kopierer elementene over. Gjentas til det er 1 element i hver array. Sorterer dem saa med Merge.
        long[] A1 = Arrays.copyOfRange(A, 0, i);
        long[] A2 = Arrays.copyOfRange(A, i, n);
        mergeSort(A1);
        mergeSort(A2);
        long [] sortert = sorter.merge(A1, A2, A);

        // Henter resultater fra merge-klassen
        miniRes = sorter.hentMiniRes();
        return sortert;
    }

    public long [] hentMiniRes() {
        return miniRes;
    }

    // Nullstiller variablene fra merge, saa antall sammenligninger og bytter blir korrekt
    public void nullstill() {
        sorter.nullstill();
    }
}