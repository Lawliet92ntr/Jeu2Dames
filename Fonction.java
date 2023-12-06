public class Fonction {
    
    public static void main(String[] args) {
        String[][] plateau=creePlateau(10, 10);
        
        afficheTableau(plateau);
    }

    public static String[][] creePlateau(int x, int y){
        String[][] tab=new String[x+1][y+2];

        tab[0][0]="x/y";
        for (int i = 1; i < tab.length; i++) {
            
            tab[0][i]=" "+(i-1);
            for (int j = 1; j < tab[i].length; j++) {
                if((i-1)==0){
                    tab[j][0]=" "+(j-1)+"|";
                }
                if(i!=0 ){
                    tab[i][j]="□ ";
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
}
