
package demineur;
import java.util.Scanner;
/**
 *
 * @author User1
 */
public class Demineur {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nbTour=0;
        int l,L;
        //verifie si la hauteur et largeur n'est pas égale ou inférieur à 0
        do{
            System.out.println("Veuillez choisir la largeur du plateau");
            l = sc.nextInt();
            System.out.println("Veuillez choisir la hauteur du plateau");
            L = sc.nextInt();
            if (l<0 || L<0)
                System.out.println("Les dimensions doivent être positives");
        }while(l<0 || L<0);
        int n;
        //verifie si le niveau est compris entre 1 et 9
        do{
            System.out.println("Veuillez choisir le niveau du jeu entre 1 et 9");
            n = sc.nextInt();
            if (n<1 || n>9)
                System.out.println("Vous n'avez pas rentrer un niveau valide");
        }while(n<1 || n>9);
        
        //initialise le plateau de jeu
        Plateau monP = new Plateau(l, L, n);
        
        //créer une boucle qui vas se répéter tant que le plaeau n'est pas rempli
        while(!monP.getPlateauPlein()){
            nbTour++;
            int nbX,nbY;
            System.out.println();
            System.out.println("Tour "+nbTour);
            System.out.println("Il reste "+monP.getNbMineRestante()+" mines sur le plateau");
            System.out.println(monP.affichePlateau());
            //verifie si les coordonnees sont correct
            do{
                System.out.println("Veuillez choisir votre coordonnees x : ");
                nbX = sc.nextInt();
                System.out.println("Veuillez choisir votre coordonnees y : ");
                nbY = sc.nextInt(); 
                if ((nbX<1 && nbX>monP.getTailleX()) && nbY<1 && nbY>monP.getTailleY())
                    System.out.println("Veuillez rentrer des coordonnées valides");
            }while((nbX<1 && nbX>monP.getTailleX()) && nbY<1 && nbY>monP.getTailleY());
            Voisinage vc =new Voisinage(monP); //création de l'instance de la classe Voisinage
            vc.devoilementCase(nbX, nbY);   //dévoila la case choisi et les cases Vide adjacentes
            
            // verifie si la case est une mine 
            if (monP.getCase(nbX, nbY).getType().equals("Mine")){
                // dévoile tout le plateau
                for (int i=1;i<monP.getTailleX();i++)
                    for (int j=1;j<monP.getTailleY();j++){
                        monP.getCase(nbX, nbY).setValeur("*");
                        monP.getCase(i, j).setVisible(true);
                    }
                System.out.println(monP.affichePlateau());
                System.out.println("Dommage vous avez perdu !");
                monP.setPlateauPlein(true);
            }
            // verifie qu'il y a autant de mine que de case restant
            else if (monP.getNbMineRestante() == monP.nbCaseRestantes()){
                // dévoile tout le plateau
                for (int i=1;i<monP.getTailleX();i++)
                    for (int j=1;j<monP.getTailleY();j++)
                        monP.getCase(i, j).setVisible(true);
                System.out.println(monP.affichePlateau());
                System.out.println("Bravo ! Vous avez gagne en "+nbTour+" tours !");
                monP.setPlateauPlein(true);
            }  
             System.out.println();
        }
    }
    
}
