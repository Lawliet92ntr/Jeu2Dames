public class Fonction {
    
    public static void main(String[] args) {
        String[][] plateau=creePlateau(10, 10);
        plateau=initPions(plateau);
        deplacerPion(plateau,6,0,5,1);
        deplacerPion(plateau, 5, 1, 4, 2);
        deplacerPion(plateau, 4, 2, 3, 1);
        afficheTableau(plateau);
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
        System.out.println("_Y 0 1 2 3 4 5 6 7 8 9");
        System.out.println("X|---------------------");
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
                
                
                    if((ligne+col)%2==0 && ligne <4){
                        tab[ligne][col]=" ⛂"; /*⛂⛀ */
                    }
                    else if((ligne+col)%2==0 && ligne >5){
                        tab[ligne][col]=" ⛀";
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

 public static boolean deplacerPion(String[][] plateau, int xDepart, int yDepart, int xArrivee, int yArrivee) {
    boolean aEteDeplace=false;
    if (coordValides(xDepart, yDepart, plateau) && coordValides(xArrivee, yArrivee, plateau)) {
        if (Math.abs(xArrivee - xDepart) == 1 && Math.abs(yArrivee - yDepart) == 1) {
            if (plateau[xArrivee][yArrivee].equals(" ■")||plateau[xArrivee][yArrivee].equals(" □")) {
                String a=plateau[xArrivee][yArrivee];
                plateau[xArrivee][yArrivee] = " ⛀";
                plateau[xDepart][yDepart] = a;
                aEteDeplace=true;
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

    return aEteDeplace;
}

public static boolean coordValides(int x, int y, String[][] plateau) {
    return x >= 0 && x < plateau.length && y >= 0 && y < plateau[0].length;
}



}
