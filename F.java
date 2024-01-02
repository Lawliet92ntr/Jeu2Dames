import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class F {
    public static final String ANSI_RESET = "\u001B[0m"; /* Couleur reset */
    public static final String ANSI_RED="\u001B[31m"; /* Couleur rouge */
    public static final String ANSI_BLUE="\u001B[34m";/* Couleur Bleu */
    public static final String PION_ROUGE=ANSI_RED+" 0"+ANSI_RESET; /*⛂ ⛁*/
    public static final String PION_BLEU=ANSI_BLUE+" 0"+ANSI_RESET;
    public static final String DAME_ROUGE=ANSI_RED+" 2"+ANSI_RESET;
    public static final String DAME_BLEU=ANSI_BLUE+" 2"+ANSI_RESET;
    public static int[] score={20,20};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int[][] plateau=creePlateau(10, 10);
        initPions(plateau);

        
        
        
        afficheTableau(plateau);

        
        scanner.close();

    }

     public static boolean movementValide(int[][]plateau,int x , int y) {
        return x >= 0 && x < plateau.length && y >= 0 && y < plateau[0].length;
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
                            System.out.print(ANSI_BLUE+" ■"+ANSI_RESET);
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

    public static boolean peutPrendre(int[][] plateau,int joueur,int xPion ,int yPion ,int xPrise,int yPrise){
        boolean peutPrendre=false;

        if(joueur==1){
            if((xPion==xPrise+1 && yPion ==yPrise-1) && estVide(plateau, xPrise-1, yPrise+1)){
                peutPrendre=true;
            }
            
            else if((xPion==xPrise+1 && yPion ==yPrise+1) && estVide(plateau, xPrise-1, yPrise-1)){
                peutPrendre=true;
            }
        }
        else{
            if((xPion==xPrise-1 && yPion ==yPrise-1) && estVide(plateau, xPrise+1, yPrise+1)){
                peutPrendre=true;
            }

            else if((xPion==xPrise-1 && yPion ==yPrise+1) && estVide(plateau, xPrise+1, yPrise-1)){
                peutPrendre=true;
            }
        }
        return peutPrendre;

    }   

    public static void verifPassageDame(int[][] plateau,int pion,int x, int y){
        switch (pion) {
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

    public static void deplacer(int[][] plateau, int xDepart , int yDepart,int xArrive,int yArrive){
        int pion = plateau[xDepart][yDepart];
        plateau[xDepart][yDepart]=0;
        plateau[xArrive][yArrive]=pion;
        verifPassageDame(plateau, pion, xArrive, yArrive);
    }

    
   

    

    public static int[] emplacementEnnemi(int[][] plateau,int joueur, int xArrive , int yArrive){
        

        int[] emplacement={-1,-1};
        switch (joueur) {
            
            case 1:
                if(plateau[xArrive+1][yArrive-1]==2 || plateau[xArrive+1][yArrive-1]==22 ){
                    emplacement[0]=xArrive+1;
                    emplacement[1]=yArrive-1;
                    

                }
                if(plateau[xArrive+1][yArrive+1]==2 || plateau[xArrive+1][yArrive+1]==22){
                    emplacement[0]=xArrive+1;
                    emplacement[1]=yArrive+1;
                    
                }
                break;
            case 2:
                if(plateau[xArrive-1][yArrive-1]==2 || plateau[xArrive-1][yArrive-1]==22 ){
                    emplacement[0]=xArrive-1;
                    emplacement[1]=yArrive-1;
                    
                }
                if(plateau[xArrive-1][yArrive+1]==2 || plateau[xArrive-1][yArrive+1]==22){
                    emplacement[0]=xArrive-1;
                    emplacement[1]=yArrive+1;
                    
                }
                break;
            default:
                break;
        }

        return emplacement;
        
    }

    public static List<int[]> listeEnnemie(int[][] plateau,int joueur, int x , int y){
        List<int[]> listeEnnemi=new ArrayList<int[]>(); 
        
        int[][] deplacements={{1,-1},{1,1},{-1,-1},{-1,1}};
        
        
        for (int[] i :deplacements ) {
            if(movementValide(plateau, x+i[0], y+i[1])){
                if(verifEnnemiSimple(plateau, joueur, x+i[0], y+i[1])){
                    if(peutPrendre(plateau, joueur, x, y, x+i[0], y+i[1])){
                        listeEnnemi.add(i);
                    }
                }
            }
        }



        return listeEnnemi;
    }
   
/*Verifie chaque pion et renvoie leurs coordonées si ils peuvent prendre un pion renvoie une liste vide si il n'y pas de prise possible */
    public static List<int[]> listeCoupForce(int[][]plateau,int joueur){
        List<int[]> listeCoup=new ArrayList<int[]>();
        for (int x = 0; x < plateau.length; x++) {
            for (int y = 0; y < plateau[x].length; y++) {
                 if(plateau[x][y]==1 || plateau[x][y]==11 ){
                    if( !listeEnnemie(plateau, joueur, x, y).isEmpty()){
                    
                        int[] coordonnee={x,y};
                        listeCoup.add(coordonnee);

                    }
                }
            }
        
           
        }

        return listeCoup;
    }

    public static boolean verifEnnemiSimple(int[][] plateau,int joueur, int x , int y){
        boolean existeEnnemi=false;
        switch (joueur) {
            
            case 1:
                if(plateau[x][y]==2 || plateau[x][y]==22 ){
                    existeEnnemi=true;
                }
                break;
            case 2:
                if(plateau[x][y]==1 || plateau[x][y]==11 ){
                    existeEnnemi=true; 
                }
                break;
            default:
                break;
        }

        return existeEnnemi;
    }

    public static boolean verifEnnemi(int[][] plateau,int joueur, int xArrive , int yArrive){
        

        boolean existeEnnemi=false;
        switch (joueur) {
            
            case 1:
                if(plateau[xArrive+1][yArrive-1]==2 || plateau[xArrive+1][yArrive-1]==22 ){
                    existeEnnemi=true;
                    

                }
                if(plateau[xArrive+1][yArrive+1]==2 || plateau[xArrive+1][yArrive+1]==22){
                    existeEnnemi=true;
                }
                break;
            case 2:
                if(plateau[xArrive-1][yArrive-1]==2 || plateau[xArrive-1][yArrive-1]==22 ){
                    existeEnnemi=true;
                    
                }
                if(plateau[xArrive-1][yArrive+1]==2 || plateau[xArrive-1][yArrive+1]==22){
                    existeEnnemi=true;
                    
                }
                break;
            default:
                break;
        }

        return existeEnnemi;
        
    }
    
    
    public static void priseEnnemi(int[][] plateau,int joueur,int[] score,int x,int y){
        switch (joueur) {
            case 1:
                score[1]=score[1]-1;
                plateau[x][y]=0;
                break;
            case 2:
                score[0]=score[0]-1;
                plateau[x][y]=0;
            default:
                break;
        }
    }

    public static void deplacement2(int[][] plateau,int joueur,int xDepart,int yDepart, int xArrive , int yArrive){
        int pion = plateau[xDepart][yDepart];
        int nbCase=Math.abs(xArrive - xDepart);
        
        if(joueur==1){

            if(xArrive<xDepart || pion ==11){ //verifie si le joueur avance du bon coté 
                switch (nbCase) {
                    case 1:
                        if(estVide(plateau, xArrive, yArrive)){
                            if ((xArrive==xDepart-1 && yArrive==yDepart-1)||(xArrive==xDepart-1 && yArrive==yDepart+1)) {//verifier que le déplacement est en diagonale de 1

                                deplacer(plateau,xDepart,yDepart,xArrive,yArrive);
                                
                            }
                            else{

                                System.out.println("Le déplacement doit etre de 1 en diagonale !");

                            }
                        }
                        else{
                            System.out.println("Case pas vide");
                        }
                        break;
                    

                    case 2:
                        if(verifEnnemi(plateau, joueur, xArrive, yArrive)){
                            int[] ennemi=emplacementEnnemi(plateau, joueur, xArrive, yArrive);
                            int xEnnemi= ennemi[0] , yEnnemi=ennemi[1];
                            
                            if(peutPrendre(plateau, joueur, xDepart, yDepart,xEnnemi , yEnnemi)){
                                priseEnnemi(plateau, joueur, score, xEnnemi, yEnnemi);
                                deplacer(plateau, xDepart, yDepart, xArrive, yArrive);
                            }
                        }
                        
                        break;
                        
                    default:
                        break;
                }
                
                
                          
            }
            else{

                System.out.println("Le pion ne peut pas aller dans ce sens !");

            }
        
        }
    }

}
