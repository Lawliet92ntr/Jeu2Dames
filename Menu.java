import java.util.Scanner;

class Menu {

    public static void gMenu() {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("===== MENU PRINCIPAL =====");
            System.out.println("1. Nouvelle partie");
            System.out.println("2. Charger une partie");
            System.out.println("3. Aide");
            System.out.println("4. Quitter");

            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.println("Choisissez l'adversaire :");
                    System.out.println("1. Joueur");
                    System.out.println("2. Robot");

                    int adversaire = scanner.nextInt();

                    if (adversaire == 1) {
                        System.out.println("Lancement d'une nouvelle partie contre un joueur...");

                    } else if (adversaire == 2) {
                        System.out.println("Choisissez la difficulté du robot :");
                        System.out.println("1. Facile");
                        System.out.println("2. Moyen");
                        System.out.println("3. Difficile");

                        int difficulte = scanner.nextInt();

                        System.out.println("Lancement d'une nouvelle partie contre un robot de difficulté " + difficulte + "...");

                    } else {
                        System.out.println("Choix invalide. Veuillez réessayer.");
                    }
                    break;

                case 2:
                    System.out.println("Chargement d'une partie");
                    break;

                case 3:
                    System.out.println("Affichage de l'aide...");
                    break;

                case 4:
                    System.out.println("Deja ? :-( ... Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }

        } while (choix != 5);

        scanner.close();
    }

}