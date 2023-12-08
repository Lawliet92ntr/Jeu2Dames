public class Fonction {
    
    public static void main(String[] args) {
        String[][] plateau=creePlateau(10, 10);
        plateau=initPions(plateau);
        afficheTableau(plateau);
    }

    public static String[][] creePlateau(int x, int y){
        String[][] tab=new String[x+1][y+1];

        tab[0][0]="x/y";
        for (int ligne = 1; ligne < tab.length; ligne++) {
            
            tab[0][ligne]=" "+(ligne-1);
            for (int col = 1; col < tab[ligne].length; col++) {
                if((ligne-1)==0){
                    tab[col][0]=" "+(col-1)+"|";
                }
                if(ligne!=0 ){
                    if((ligne+col)%2==0){
                        tab[ligne][col]=" ■"; /*⛂⛀ */
                    }
                    else{
                        tab[ligne][col]=" □";
                    }
                }
                
                
            }
        }

        return tab;
    }

    public static void afficheTableau(String[][] t){
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t.length; j++) {
                System.out.print(t[i][j]);
            }
            System.out.println();
        }
    }


    public static String[][] initPions(String[][] tab){
        for (int ligne = 1; ligne < tab.length; ligne++) {
            
            for (int col = 1; col < tab[ligne].length; col++) {
                
                if(ligne!=0 ){
                    if((ligne+col)%2==0 && ligne <5){
                        tab[ligne][col]=" ⛂"; /*⛂⛀ */
                    }
                    else if((ligne+col)%2==0 && ligne >6){
                        tab[ligne][col]=" ⛀";
                    }
                }
                
                
            }
        }
        

        return tab;
    }
}


/*
 * Fonction de deplacement : Younes
 * 1: Vérifie si les coordonnées sont valides.
 * 2: Vérifie si le déplacement est en diagonale et d'une case.
 * 3: Vérifie si la case d'arrivée est vide.
 * 4: Effectue le déplacement.
 */

 public static void deplacerPion(String[][] plateau, int xDepart, int yDepart, int xArrivee, int yArrivee) {
    if (coordValides(xDepart, yDepart, plateau) && coordValides(xArrivee, yArrivee, plateau)) {
        if (Math.abs(xArrivee - xDepart) == 1 && Math.abs(yArrivee - yDepart) == 1) {
            if (plateau[xArrivee][yArrivee].equals("0")) {
                plateau[xArrivee][yArrivee] = plateau[xDepart][yDepart];
                plateau[xDepart][yDepart] = "0";
            } 
            else{
                System.out.println("La case n'est pas vide.");
            }
        } 
        else{
            System.out.println("Le déplacement doit être en diagonale et d'une case.");
        }
    } 
    else {
        System.out.println("Coordonnées invalides.");
    }
}

public static boolean coordValides(int x, int y, String[][] plateau) {
    return x >= 0 && x < plateau.length && y >= 0 && y < plateau[0].length;
}

public static void afficherPlateau(String[][] plateau) {
    for (String[] ligne : plateau) {
        for (String casePlateau : ligne) {
            System.out.print(casePlateau + " ");
        }
        System.out.println();
    }
    System.out.println();
}

