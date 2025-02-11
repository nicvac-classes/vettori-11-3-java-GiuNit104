import java.util.Scanner;

public class Esercizio {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        String c;
        int n, i, e;

        System.out.println("Inserisci la dimensione del vettore: ");
        n = Integer.parseInt( input.nextLine() );

        int[] v = new int[n * 10];

        azzeraVettore(v, n);

        do {
            c = leggiComando();
            if (c.equals("I")) {
                System.out.println("Valore da inserire");
                e = Integer.parseInt( input.nextLine() );
                do {
                    System.out.println("In quale posizione?");
                    i = Integer.parseInt( input.nextLine() );
                } while (i < 0 || i > n);

                n = inserisciInVettoreFast(v, n, e, i);
            }
            if ( c.equals("E") ) {
                if (n > 1) {
                    do {
                        System.out.println("Quale cella del vettore eliminare? (indice)");
                        i = Integer.parseInt( input.nextLine() );
                    } while (i < 0 || i >= n);

                    n = eliminaDaVettoreFast(v, n, i);
                } else {
                    System.out.println("Non posso eliminare delle celle da un vettore formato da un solo elemento");
                }
            }
            if (c.equals("R")) {
                System.out.println("Valore da cercare");
                e = Integer.parseInt(input.nextLine());
                i = ricercaNelVettore(v, n, e);
                if (i >= 0) {
                    System.out.println("elemento " + e + " trovato in posizione " + i);
                } else {
                    System.out.println("Elemento " + e + " non trovato.");
                }
            }
            if (c.equals("D")) {
                System.out.println("Rimozione dei duplicati");

                n = rimuoviDuplicatiFunz(v, n);
            }
            if (c.equals("V")) {
                visualizzaVettore(v, n);
            }
        } while (!c.equals("X"));

        input.close();
    }

    public static void azzeraVettore(int[] v, int n) {
        int i;

        for (i = 0; i <= n - 1; i++) {
            v[i] = 0;
        }
    }
    
    public static int eliminaDaVettore(int[] v, int n, int ie) {
        int i, n2;

        n2 = n - 1;
        int[] w = new int[n2];

        i = 0;
        while (i <= ie - 1 && i < n) {
            w[i] = v[i];
            i = i + 1;
        }
        i = ie + 1;
        while (i < n) {
            w[i - 1] = v[i];
            i = i + 1;
        }

        i = 0;
        while (i < n2) {
            v[i] = w[i];
            i = i + 1;
        }
        
        return n2;
    }
    
    public static int eliminaDaVettoreFast(int[] v, int n, int ie) {
        int i, n2;

        n2 = n - 1;
        i = ie;
        while (i <= n - 2) {
            v[i] = v[i + 1];
            i = i + 1;
        }
        
        return n2;
    }
    
    public static int inserisciInVettore(int[] v, int n, int e, int ie) {
        int i, n2;

        n2 = n + 1;
        int[] w = new int[n2];

        i = 0;
        while (i <= ie - 1 && i < n) {
            w[i] = v[i];
            i = i + 1;
        }
        w[ie] = e;
        i = ie + 1;
        while (i < n2) {
            w[i] = v[i - 1];
            i = i + 1;
        }

        i = 0;
        while (i < n2) {
            v[i] = w[i];
            i = i + 1;
        }
        
        return n2;
    }
    
    public static int inserisciInVettoreFast(int[] v, int n, int e, int ie) {
        int i, n2;

        n2 = n + 1;
        i = n2 - 1;
        while (i >= ie + 1) {
            v[i] = v[i - 1];
            i = i - 1;
        }
        v[ie] = e;
        
        return n2;
    }
    
    public static String leggiComando() {
        String c;

        do {
            System.out.println("1. [I]nserisci elemento " + (char) 10 + "2. [E]limina elemento " + (char) 10 + "3. [R]icerca valore " + (char) 10 + "4. [D] Elimina Duplicati " + (char) 10 + "5. [V]isualizza vettore " + (char) 10 + "6. [X] Esci");
            c = input.nextLine();
        } while (!c.equals("I") && !c.equals("E") && !c.equals("R") && !c.equals("D") && !c.equals("V") && !c.equals("X"));
        
        return c;
    }
    
    public static int ricercaNelVettore(int[] v, int n, int valore) {
        int i, iTrovato;

        i = 0;
        iTrovato = -1;
        while (i < n && iTrovato == -1) {
            if (v[i] == valore) {
                iTrovato = i;
            }
            i = i + 1;
        }
        
        return iTrovato;
    }
    
    public static int rimuoviDuplicati(int[] v, int n) {
        boolean duplicato;
        int i, j, k;

        k = 0;
        int[] w = new int[n];

        i = 0;
        while (i <= n - 1) {
            duplicato = false;
            j = i + 1;
            while (j <= n - 1 && duplicato == false) {
                if (v[i] == v[j]) {
                    duplicato = true;
                }
                j = j + 1;
            }
            if (duplicato == false) {
                w[k] = v[i];
                k = k + 1;
            } else {

            }
            i = i + 1;
        }

        i = 0;
        while (i < k) {
            v[i] = w[i];
            i = i + 1;
        }
        
        return k;
    }
    
    public static int rimuoviDuplicatiFunz(int[] v, int n) {
        int i, j;

        i = 0;
        while (i <= n - 2) {
            j = i + 1;
            while (j <= n - 1) {
                if (v[i] == v[j]) {
                    System.out.println("Elimino " + v[j] + " da cella " + j);
                    n = eliminaDaVettoreFast(v, n, j);
                } else {
                    j = j + 1;
                }
            }
            i = i + 1;
        }
        
        return n;
    }
    
    public static void visualizzaVettore(int[] v, int n) {
        int i;

        System.out.println("-------");

        i = 0;
        while (i < n) {
            System.out.println(Integer.toString(i) + ": " + v[i]);
            i = i + 1;
        }

        System.out.println("-------");
    }
}