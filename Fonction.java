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
