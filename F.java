import java.util.Scanner;

public class F {
    public static String ANSI_RESET = "\u001B[0m"; /* Couleur reset */
    public static String ANSI_RED="\u001B[31m"; /* Couleur rouge */
    public static String ANSI_BLUE="\u001B[34m";/* Couleur Bleu */
    public static String PION_ROUGE=ANSI_RED+" ⛂"+ANSI_RESET;
    public static String PION_BLEU=ANSI_BLUE+" ⛂"+ANSI_RESET;
    public static String DAME_ROUGE=ANSI_RED+" ⛁"+ANSI_RESET;
    public static String DAME_BLEU=ANSI_BLUE+" ⛁"+ANSI_RESET;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] plateau=creePlateau(10, 10);
        initPions(plateau);
        afficheTableau(plateau);
        scanner.close();

    }


    public static int[][] creePlateau(int x, int y){
        int[][] tab=new int[x][y];
        
        for (int ligne = 0; ligne < tab.length; ligne++) {
            
            for (int col = 0; col < tab[ligne].length; col++) {
                 
                    
                tab[ligne][col]=0;
                   
                
            }
        }
        return tab;
    }
    public static void initPions(int[][] tab){
        
        for (int ligne = 0; ligne < tab.length; ligne++) {
            
            for (int col = 0; col < tab[ligne].length; col++) {
                
                
                    if((ligne+col)%2!=0 && ligne <4){
    
                        tab[ligne][col]=2; 
                    
                    }
                    else if((ligne+col)%2!=0 && ligne >5){
                        
                        tab[ligne][col]=1;
                    
                    } 
            }
        }
        
    }


    public static void afficheTableau(int[][] t){
        System.out.println("_"+ANSI_RED+"Y"+ANSI_RESET+" 0 1 2 3 4 5 6 7 8 9");
        System.out.println(ANSI_BLUE+"X"+ANSI_RESET+"|---------------------");
        
        for (int ligne = 0; ligne < t.length; ligne++) {

            System.out.print(ligne+"|");

            for (int col = 0; col < t.length; col++) {
                switch (t[ligne][col]) {
                    case 0:
                        if((ligne+col)%2==0){
                            System.out.print(" ■");
                        }
                        else{
                            System.out.print(" □");
                        }
                        break;
                    case 1:
                        System.out.print(PION_BLEU);
                        break;
                    case 2:
                        System.out.print(PION_ROUGE);
                        break;
                    case 11:
                        System.out.print(DAME_BLEU);
                        break;
                    case 22:
                        System.out.print(DAME_ROUGE);
                        break;
                    default:
                        break;
                }

                
            }
            System.out.println();
        }
        
    }
    public static boolean estVide(int[][] plateau, int x, int y){
        boolean estVide=false;
        if(plateau[x][y] == 0){
            estVide=true;
        }
        return estVide;
    }

   /*  public static boolean peutPrendre(int[][] plateau,int joueur,int xPion ,int yPion ,int xPrise,int yPrise){
        boolean peutPrendre=false;

        if (joueur==1) {
            
        }


        return peutPrendre;

    }   
*/
    public static void verifPassageDame(int[][] plateau,int joueur,int x, int y){
        switch (joueur) {
            case 1:
                if (x==0 && plateau[x][y]==1) {
                    plateau[x][y]=11;
                }
                break;
            case 2:
                if (x==9 && plateau[x][y]==2) {
                    plateau[x][y]=22;
                }
                break;
            default:
                break;
        }
    }


    public static void deplacement(int[][] plateau,int joueur,int xDepart,int yDepart, int xArrive , int yArrive){
        int pion = plateau[xDepart][yDepart];
        if(joueur==1){
            if(xArrive<xDepart || pion ==11){ //verifie si le joueur avance du bon coté 
                //verifier que le déplacement est en diagonale de 1
                    //verifier si il y a un pion ennemi
                        //verifier si le pion peut etre pris
                            //faire le déplacement/prise
            }
        }
    }



}
