
package demineur;

/**
 *
 * @author User1
 */
public class Plateau {
    //attributs
    private Case[][] monPlateau;
    private int nbMineRestante; // stock le nombre de mines restantes
    private boolean plateauPlein;   //boolean pour savoir si le jeu est fini
    
    //accesseurs
    public int getTailleX(){return this.monPlateau.length-1;}   // le -1, permet de camoufler les bourdures invisible du plateau
    public int getTailleY(){return this.monPlateau[0].length-1;}     // le -1, permet de camoufler les bourdures invisible du plateau
    public Case getCase(int x, int y){return this.monPlateau[x][y];}
    public void setCase(int x, int y, Case c){this.monPlateau[x][y] = c;}
    public boolean getPlateauPlein(){return this.plateauPlein;}
    public void setPlateauPlein(boolean b){this.plateauPlein = b;}
    public int getNbMineRestante(){return this.nbMineRestante;}
    
    // constructeur avec paramètre
    public Plateau(int l,int c,int niveau){
        this.monPlateau = new Case[l+2][c+2]; // +2 pour les bords camouflés
        // initilase le plateau avec des instances de la classe Vide
        for (int i=0;i<this.monPlateau.length;i++)
            for (int j=0;j<this.monPlateau[0].length;j++)
                this.monPlateau[i][j] = new Vide(i,j);
        poserMine(l,c,niveau);
        // appel de la methode getBombes() pour récuperer le nombre de bombes des cases adjacentes
        Voisinage vc = new Voisinage(this);
        for (int i=1;i<this.getTailleX();i++)
            for (int j=1;j<this.getTailleY();j++)
                if (this.getCase(i, j).getType().equals("Vide"))
                    this.getCase(i, j).setValeur(vc.getBombes(i,j));
        this.plateauPlein = false;  //initialisation de l'attribut false
    }
    
    // pose les mines sur le plateau en fonction de la taille et le niveau du plateau
    private void poserMine(int l,int c,int n){
        int res = ((l*c*n)/10);
        if (res==0) res++;
        this.nbMineRestante = res;
        while (res!=0){
            int x = (int)(Math.random()*getTailleX()-1)+1;
            int y = (int)(Math.random()*getTailleY()-1)+1;
            if (this.getCase(x, y).getType().equals("Vide")){
                setCase(x, y, new Mine(x,y));
                res--;
            }
        }
    }
    
    // permet de récupérer le nombre de case restante sur le plateau
    public int nbCaseRestantes(){
        int res=0;
        for (int i=1;i<this.getTailleX();i++)
            for (int j=1;j<this.getTailleY();j++)
                if (!this.getCase(i, j).getVisible())
                    res++;
        return res;
    }
     
    // affiche le plateau
    public String affichePlateau(){
        System.out.println();
        String res="";
        for (int i=1;i<this.getTailleX();i++){
            res += "   |";
            for (int j=1;j<this.getTailleY();j++)
                if (this.getCase(i, j).getVisible())
                    res +=" "+this.getCase(i,j).toString()+" |"; 
                else
                    res +=this.getCase(i, j).getX()+":"+this.getCase(i, j).getY()+"|";
            res += "\n";
        }
        return res;
    }
}
