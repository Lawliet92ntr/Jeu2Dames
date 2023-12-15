import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Fonction {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] plateau=creePlateau(10, 10);
        plateau=initPions(plateau);
        
        
        for(int boucletest=0 ; boucletest <20 ; boucletest++){    
        
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

        }

        

    }

    public static String[][] creePlateau(int x, int y){
        String[][] tab=new String[x][y];
        
        for (int ligne = 0; ligne < tab.length; ligne++) {
            
            for (int col = 0; col < tab[ligne].length; col++) {
                 
                    if((ligne+col)%2==0){
                        tab[ligne][col]=" □"; /*⛂⛀ */
                    }
                    else{
                        tab[ligne][col]=" ■";
                    }
                
            }
        }
        return tab;
    }

    public static void afficheTableau(String[][] t){
        String ANSI_RESET = "\u001B[0m"; /* Couleur reset */
        String ANSI_RED="\u001B[31m"; /* Couleur rouge */
        String ANSI_BLUE="\u001B[34m";/* Couleur Bleu */
        
        
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
        String ANSI_RESET = "\u001B[0m"; /* Couleur reset */
        String ANSI_RED="\u001B[31m"; /* Couleur rouge */
        String ANSI_BLUE="\u001B[34m";/* Couleur Bleu */
        for (int ligne = 0; ligne < tab.length; ligne++) {
            
            for (int col = 0; col < tab[ligne].length; col++) {
                
                
                    if((ligne+col)%2!=0 && ligne <4){
    
                        tab[ligne][col]=ANSI_RED+" ⛂"+ANSI_RESET; /* ⛂ ⛀ */
                    
                    }
                    else if((ligne+col)%2!=0 && ligne >5){
                        
                        tab[ligne][col]=ANSI_BLUE+" ⛀"+ANSI_RESET;
                    
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




}



