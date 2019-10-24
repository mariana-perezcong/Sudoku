
package proyectofinal;

/**
 *
 * @author mperezcong
 */
public class prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String [][] n= new String [3][3];
        
        n[0][0]="1";
        n[0][1]="2";
        n[0][2]="3";
        n[1][0]="4";
        n[1][1]="5";
        n[1][2]="-3";
        n[2][0]="7";
        n[2][1]="8";
        n[2][2]="9";
        
        boolean resp= leerDatos(n,0); 
        
        if(!resp)
            System.out.println("numero invalido");
        
        for(int i=0; i<9; i++){
            System.out.println("i: "+i+", con/3: "+i%3+" , con%3: "+i/3);
  
        }
        
        ArraySet [] columnas;
    }
    
    

    private static boolean leerDatos(String[][]m, int con ){
        //(x,y)
        int i=con%3;
        int j=con/3;
        boolean resp=false;
        
        if(m[i][j].equals(""))
            m[i][j]="0";
        int n=Integer.parseInt(m[i][j]);
        if(n>=0 && n<10){
            if(con==8){
                if(n!=0)
                   System.out.println(m[i][j]);                 
                resp=true;
            }else{
                if(n!=0)
                    System.out.println(m[i][j]);
                leerDatos(m,con+1);
            }  
        }
        return resp;
    }
}
