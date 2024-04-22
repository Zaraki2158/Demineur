
package demineur;

/**
 *
 * @author User1
 */
public class Voisinage {
    // attribut
    private Plateau monP;

    //constructeur standard
    public Voisinage(Plateau p){
        this.monP = p;
    }
    
    public String getBombes(int x, int y){
        /* recupere le nombre de mines dans les cases adjacentes
        par rapport à la case au coordonnées en parametre*/ 
        int res=0;
        for (int i=x-1; i<=x+1; i++) 
            for (int j=y-1; j<=y+1; j++) 
                res+= this.monP.getCase(i, j).regarderMine();  // appel de la méthode regarderMine() pour voir si la case est une mine
        if (res == 0)
            return " ";
        return ""+res;
    }
    
    //méthode pour activer les cartes à dévoiler
    public void devoilementCase(int x, int y){
        Case c = this.monP.getCase(x, y);
        //récurence pour voir si on peut dévoiler les cases adjacentes
        if ((x>0 && x<monP.getTailleX()) && (y>0 && y<monP.getTailleY())){
            if (!monP.getCase(x, y).getType().equals("Mine") && !monP.getCase(x, y).getVisible()){
                monP.getCase(x, y).setVisible(true);
                Voisinage v = new Voisinage(monP);
                v.devoilementCase(x+1,y);
                v.devoilementCase(x,y+1);
                v.devoilementCase(x-1,y);
                v.devoilementCase(x,y-1);
                }
            }
        }
        
    }
  