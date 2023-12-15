import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Fonction {
    public static String ANSI_RESET = "\u001B[0m"; /* Couleur reset */
    public static String ANSI_RED="\u001B[31m"; /* Couleur rouge */
    public static String ANSI_BLUE="\u001B[34m";/* Couleur Bleu */
    public static String PION_ROUGE=ANSI_RED+" ⛂"+ANSI_RESET;
    public static String PION_BLEU=ANSI_BLUE+" ⛂"+ANSI_RESET;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] plateau=creePlateau(10, 10);
        plateau=initPions(plateau);
        
        deplacerPion(plateau, 3, 0, 5, 2);
        deplacerPion(plateau, 3, 2, 4, 3);
        System.out.println(peutPrendre(plateau, 5, 2));
        
        afficheTableau(plateau);

        System.out.println("x1");
        int xDepart = scanner.nextInt();
                System.out.println("y1");
        int yDepart = scanner.nextInt();
                System.out.println("x2"); 
        int xArrivee = scanner.nextInt();
                System.out.println("y2");
        int yArrivee = scanner.nextInt();
        
        deplacerPion(plateau,xDepart,yDepart,xArrivee,yArrivee);
        afficheTableau(plateau);

        

        scanner.close();

    }

    public static String[][] creePlateau(int x, int y){
        String[][] tab=new String[x][y];
        
        for (int ligne = 0; ligne < tab.length; ligne++) {
            
            for (int col = 0; col < tab[ligne].length; col++) {
                 
                    if((ligne+col)%2==0){
                        tab[ligne][col]=" ■"; /*⛂⛀ */
                    }
                    else{
                        tab[ligne][col]=" □";
                    }
                
            }
        }
        return tab;
    }

    public static void afficheTableau(String[][] t){
       
        
        
        System.out.println("_"+ANSI_RED+"Y"+ANSI_RESET+" 0 1 2 3 4 5 6 7 8 9");
        System.out.println(ANSI_BLUE+"X"+ANSI_RESET+"|---------------------");
        
        for (int ligne = 0; ligne < t.length; ligne++) {

            System.out.print(ligne+"|");

            for (int col = 0; col < t.length; col++) {
                System.out.print(t[ligne][col]);
            }
            System.out.println();
        }
        
    }


    public static String[][] initPions(String[][] tab){
        
        for (int ligne = 0; ligne < tab.length; ligne++) {
            
            for (int col = 0; col < tab[ligne].length; col++) {
                
                
                    if((ligne+col)%2!=0 && ligne <4){
    
                        tab[ligne][col]=PION_ROUGE; /* ⛂ ⛀ */
                    
                    }
                    else if((ligne+col)%2!=0 && ligne >5){
                        
                        tab[ligne][col]=PION_BLEU;
                    
                    }
                
                
                
            }
        }
        

        return tab;
    }



/*
 * Fonction de deplacement : Younes
 * 1: Vérifie si les coordonnées sont valides.
 * 2: Vérifie si le déplacement est en diagonale et d'une case.
 * 3: Vérifie si la case d'arrivée est vide.
 * 4: Effectue le déplacement.
 */


    public static void deplacerPion(String[][] plateau, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        

        
            String transi=plateau[xArrivee][yArrivee];
            plateau[xArrivee][yArrivee] = plateau[xDepart][yDepart];
            plateau[xDepart][yDepart] = transi;
        


    }
    public static boolean estVide(String[][] plateau, int x, int y){
        boolean estVide=false;
        if(plateau[x][y] == " □" || plateau[x][y] == " ■"){
            estVide=true;
        }

        return estVide;
    }

    private static boolean deplacementPossible(String[][] plateau, int xDepart, int yDepart, int xArrivee, int yArrivee) {
        boolean peutSeDeplacer=true;
        //faut creer la verif des coords
        int deplacementX = Math.abs(xArrivee - xDepart);
        int deplacementY = Math.abs(yArrivee - yDepart);
        //pas de pion
        if (estVide(plateau, xDepart, yDepart)) {
            peutSeDeplacer= false;
        }

        //case n'est pas vide
        else if (!estVide(plateau, xArrivee, yArrivee)) {
            peutSeDeplacer =false;
        }

        else if (deplacementX != 1 || deplacementY != 1) {
            peutSeDeplacer= false;
        }
        else if (xArrivee!=xDepart-1||(yArrivee!=yDepart+1&&yArrivee!=yDepart-1)){
            peutSeDeplacer=false;
        }

        return peutSeDeplacer;
    }

    

    

    public static boolean peutPrendre(String[][] plateau,int xArrivee, int yArrivee){
        boolean peutPrendre=false;

        if(plateau[xArrivee][yArrivee]== PION_ROUGE && (estVide(plateau, xArrivee-1, yArrivee+1)|| estVide(plateau, xArrivee-1, yArrivee-1))){
            peutPrendre=true;
        }
       
        return peutPrendre;
    }


    public static List<List<int[]>> tousLesDeplacementsPossible(String[][] plateau, int xPion, int yPion){
        List<List<int[]>> depPossible=new ArrayList<List<int[]>>();

        if(estVide(plateau, xPion-1, yPion+1)){
            depPossible.add(xPion-1);
        }
 
    }
    

}



