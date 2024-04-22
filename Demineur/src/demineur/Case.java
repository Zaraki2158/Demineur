
package demineur;

/**
 *
 * @author User1
 */
public class Case {
    //attribut
    private int x,y;
    private String type;
    private String valeur;
    private boolean visible;
    
    //accesseurs
    public int getX(){return this.x;}
    public void setX(int x){this.x=x;}
    public int getY(){return this.y;}
    public void setY(int y){this.y=y;}
    public String getType(){return this.type;}
    public void setType(String type){this.type=type;}
    public String getValeur(){return this.valeur;}
    public void setValeur(String valeur){this.valeur=valeur;}
    public boolean getVisible(){return this.visible;}
    public void setVisible(boolean b){this.visible = b;}
    
    //constructeur standard
    public Case(int x,int y,String type,String v){
        setX(x);
        setY(y);
        setType(type);
        setValeur(v);
        setVisible(false);
    }
    
    //méthode pour retourner un entier 1 si la case est une mine et O sinon
    public int regarderMine(){
        if (this.getType().equals("Mine"))
            return 1;
        return 0;
    }
    
    //permet de visualiser la valeur dans la case
    public String toString(){
        return this.valeur;
    }
    
}
