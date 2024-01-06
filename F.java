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
    public static int[][] deplacements={{1,-1},{1,1},{-1,-1},{-1,1}};
    public static int[] coordonnee=new int[4];
    public static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) {
        
       
        int[][] plateau=creePlateau(10, 10);
        initPions(plateau);
        
        jeu(plateau, score);
        
        
        

    }
    public static void jeu(int[][]plateau, int[] score){
        int Joueur=1;
        int etat=2;
        boolean jeu=true;
        do {
            
            afficheTableau(plateau);
            System.out.println("Joueur :" +Joueur);
            do{
            etat=coupForce(plateau, Joueur);
            
            }while(etat==1);
            

            if (Joueur==1 && etat ==0) {
                Joueur=2;
                
            }
            else{
                Joueur=1;
            }

            if (score[0]==0) {
                System.out.println("Bravo Joueur 2 a gagné !");
                jeu=false;
            }
            else if(score[1]==0){
                System.out.println("Bravo Joueur 1 a gagné !");
                jeu=false;
            }



        } while ( jeu);
    }

    public static int coupForce(int[][]plateau,int Joueur ){
        boolean peutContinuer =true;
        int eta=-5;
        int x =0,y=0,xa=0,ya=0;
        List<int[]> coupForcé=listeCoupForce(plateau, Joueur);
        do{
            System.out.println("X Depart");
            x=sc.nextInt();
            System.out.println("Y Depart");
            y=sc.nextInt();
        }while(!estAllie(plateau, Joueur, x, y));

        if(!coupForcé.isEmpty() ){
            
            for (int i =0;i<coupForcé.size() && peutContinuer;i++) {
                if(coupForcé.get(i)[0]==x && coupForcé.get(i)[1]==y){
                    peutContinuer=false;
                }

            }

            if (peutContinuer) {
                System.out.println("Attention vous devez prendre des pions adverse !");
                eta=coupForce(plateau,Joueur);
            }
            
            
        }

        if(estVide(plateau, x, y) || !estAllie(plateau, Joueur, x, y)){
            System.out.println("Attention vous devez choisir un pion allié !");
            eta=coupForce(plateau,Joueur);
        }
        else{

            do{
                    System.out.println("X Arrivé");
                    xa=sc.nextInt();
                    System.out.println("Y Arrivé");
                    ya=sc.nextInt();
                    
                    eta= deplacementPion(plateau, Joueur, x,y,xa,ya);
                    
            }while((!peutContinuer && coupForcé.isEmpty()) || eta==-2|| eta==-1 || (!peutContinuer && ((x-xa)!=2 && Joueur==1 && eta!=1))||(!peutContinuer && ((x-xa)!=-2 && Joueur==2 && eta!=1)));
        }
        
        System.out.println(eta);
        return eta;
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

    public static boolean peutPrendre(int[][] plateau,int joueur,int xDepart ,int yDepart ,int xPrise,int yPrise){
        boolean peutPrendre=false;
        int x =xPrise+( xPrise-xDepart),y= yPrise+(yPrise-yDepart);
        //6 1 > 5 2 > 4 3
        // -1 1

        if(movementValide(plateau, x, y)){
            if(estVide(plateau, x, y)){
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

    public static void deplacer(int[][] plateau, int xDepart , int yDepart,int xArrive,int yArrive, boolean enDeplacement){
        int pion = plateau[xDepart][yDepart];
        plateau[xDepart][yDepart]=0;
        plateau[xArrive][yArrive]=pion;
        if(!enDeplacement){
            verifPassageDame(plateau, pion, xArrive, yArrive);
        }
        
    }


    

    public static List<int[]> listeEnnemie(int[][] plateau,int joueur, int x , int y){
        List<int[]> listeEnnemi=new ArrayList<int[]>(); 
        int[] a= new int[2];        
        for (int[] i :deplacements ) {
            if(movementValide(plateau, x+i[0], y+i[1])){
                if(verifEnnemiSimple(plateau, joueur, x+i[0], y+i[1])){
                    if(peutPrendre(plateau, joueur, x, y, x+i[0], y+i[1])){
                        a[0]=x+i[0];
                        a[1]=y+i[1];
                        listeEnnemi.add(a);
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
                 if(estAllie(plateau, joueur, x, y) ){
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
        for (int[] i :deplacements ) {
            if(movementValide(plateau, xArrive+i[0], yArrive+i[1])){
                switch (joueur) {
                    
                    case 1:
                        if(plateau[xArrive+i[0]][yArrive+i[1]]==2 || plateau[xArrive+i[0]][yArrive+i[1]]==22 ){
                            existeEnnemi=true;
                            

                        }
                        
                        break;
                    case 2:
                        if(plateau[xArrive+i[0]][yArrive+i[1]]==1 || plateau[xArrive+i[0]][yArrive+i[1]]==11 ){
                            existeEnnemi=true;
                            
                        }
                        
                        break;
                    default:
                        break;
                }
            }
        }
        return existeEnnemi;
        
    }
    
    
    public static void priseEnnemi(int[][] plateau,int joueur,int x,int y){
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

    /*Renvoie 0 si le déplacement est fini, 1 si le déplacement peut continuer (rafle) et -1 si le déplacement est impossible*/
    public static int deplacementPion(int[][] plateau,int joueur,int xDepart,int yDepart, int xArrive , int yArrive){
        
        int nbCase=Math.abs(xArrive - xDepart);
        int etatDeplacement=-2;  
        if(joueur==1){
            if(plateau[xDepart][yDepart]==11){

            }
                switch (nbCase) {
                    case 1:
                        
                        if(estVide(plateau, xArrive, yArrive)){
                            if ((xArrive==xDepart-1 && yArrive==yDepart-1)||(xArrive==xDepart-1 && yArrive==yDepart+1)) {//verifier que le déplacement est en diagonale de 1
                                deplacer(plateau,xDepart,yDepart,xArrive,yArrive,false);
                                etatDeplacement=0;
                            }
                            else{

                                etatDeplacement=-1;

                            }
                        }
                        else{
                            etatDeplacement=-1;
                        }
                        break;
                    

                    case 2:
                        
                        if(verifEnnemi(plateau, joueur, xArrive, yArrive)){
                            
                            int xEnnemi= xDepart+(xArrive-xDepart)/2 , yEnnemi=yDepart+(yArrive-yDepart)/2;
                           
                            if(peutPrendre(plateau, joueur, xDepart, yDepart,xEnnemi , yEnnemi)){
                                priseEnnemi(plateau, joueur, xEnnemi, yEnnemi);
                                deplacer(plateau, xDepart, yDepart, xArrive, yArrive,true);
                                System.out.println(verifEnnemi(plateau, joueur, xArrive, yArrive));
                                if(verifEnnemi(plateau, joueur, xArrive, yArrive)){
                                    etatDeplacement=1;
                                    afficheTableau(plateau);
                                }
                                else{
                                    etatDeplacement=0;
                                }
                            }
                        }
                        
                        break;
                        
                    default:
                        etatDeplacement=-1;
                        break;
                }
                
                
                          
        }
        else if(joueur==2){

            
                    switch (nbCase) {
                        case 1:
                            if(estVide(plateau, xArrive, yArrive)){
                                if ((xArrive==xDepart+1 && yArrive==yDepart-1)||(xArrive==xDepart+1 && yArrive==yDepart+1)) {//verifier que le déplacement est en diagonale de 1
                                    deplacer(plateau,xDepart,yDepart,xArrive,yArrive,false);
                                    etatDeplacement=0;
                                }
                                else{

                                    etatDeplacement=-1;

                                }
                            }
                            else{
                                etatDeplacement=-1;
                            }
                            break;
                        

                        case 2:
                            if(verifEnnemi(plateau, joueur, xArrive, yArrive)){
                            
                                int xEnnemi= xDepart+(xArrive-xDepart)/2 , yEnnemi=yDepart+(yArrive-yDepart)/2;
                                System.out.println(peutPrendre(plateau, joueur, xDepart, yDepart,xEnnemi , yEnnemi));
                                if(peutPrendre(plateau, joueur, xDepart, yDepart,xEnnemi , yEnnemi)){
                                    priseEnnemi(plateau, joueur, xEnnemi, yEnnemi);
                                    deplacer(plateau, xDepart, yDepart, xArrive, yArrive,true);
                                    if(verifEnnemi(plateau, joueur, xArrive, yArrive)){
                                        etatDeplacement=1;
                                        afficheTableau(plateau);
                                    }
                                    else{
                                        etatDeplacement=0;
                                    }
                                }
                            }
                            
                            break;
                            
                        default:
                            etatDeplacement=-1;
                            break;
                    }
                    
                    
                                
                }
        
                
            
        
        System.out.println(etatDeplacement);
        return etatDeplacement;
    }
    public static boolean estAllie(int[][] plateau,int joueur,int x ,int y){
        boolean estAllie=false;
        if(joueur==1){
            if(plateau[x][y]==1 ||plateau[x][y]==11  ){
                estAllie=true;
            }
        }
        else{
            if(plateau[x][y]==2 ||plateau[x][y]==22  ){
                estAllie=true;
            }
        }

        return estAllie;
    }

    

    
}
