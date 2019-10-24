
package proyectofinal;

public class Sudoku {

  private ArraySet [] columnas; // arreglo de tipo ArraySet  de todas las columnas (arriba <=>  abajo)
  private ArraySet [] renglones; //arreglo de tipo Array Set de todos los renglones (derecha <=> izquierda)
  private ArraySet [][] cuadros; // arreglo bidimensional de tipo ArraySet de los 9 cuadros de 3x3 
  
  //La razón por que la que se decidió implementar arreglos de ArraySet es para:
  // 1. Poder tener acceso a la posicion del número que se va a agregar al sudoku
  // 2. Cada posición del arreglo tendra un conjunto con los posibles numeros que puede tomar según su renglón, columna y cuadro
  
  int [][] sudoku; // matriz de 9X9 correspondiente a todo el sudoku (las nueve columnas, con nueve renglones)
    
  //imprimeMat solo sirve para ir imprimiendo como se está resolviendo el sudoku
  public void imprimeMat(){
    for(int j=0; j<9; j++){
      for(int i=0; i<9; i++)
        System.out.print(sudoku[j][i] + " ");      
    System.out.print("\n");
    }
  }
    
  
  public Sudoku() {
    sudoku = new int[9][9]; // Se inicializa el sudoku: matriz con 9 columnas y 9 renglones
    columnas = new ArraySet[9]; //Se inicializa el arreglo de columnas  <-- 9 Sets
    renglones = new ArraySet[9]; //Se inicializa el arreglo de renglones  <--9 Sets
    cuadros = new ArraySet[3][3]; //Se inicializa el arreglo bidimensional de cuadros <--9 sets
    for(int i = 0; i<9; i++) {
      columnas[i] = new ArraySet(); // se inicializan los 9 sets de columnas
      renglones[i] = new ArraySet(); // se inicializan los 9 sets de renglones
      cuadros[i%3][i/3] = new ArraySet(); // se inicializan los 9 sets de cuadros
    }       
  }
    
  
  public boolean alta(int ren, int col, int num) {
    boolean res = false;
    if(!columnas[col].contains(num) && !renglones[ren].contains(num) && !cuadros[ren/3][col/3].contains(num)) {
      columnas[col].add(num);
      renglones[ren].add(num);
      cuadros[ren/3][col/3].add(num);
      sudoku[ren][col] = num;
      res = true;
    }
    return res;
  }
    
//
  public boolean resolver() {
    int j = 0; // vertical
    int i = 0; // horizontal
    boolean resuelto = false;
    resuelto = resolver(i,j,1,resuelto);
    if (!resuelto)
      resuelto = resolver (i, j, 2,resuelto);  
    if (!resuelto)
      resuelto = resolver (i, j, 3,resuelto);
    if (!resuelto)
      resuelto = resolver (i, j, 4,resuelto);
    if (!resuelto)
      resuelto = resolver (i, j, 5,resuelto);
    if (!resuelto)
      resuelto = resolver (i, j, 6,resuelto);
    if (!resuelto)
      resuelto = resolver (i, j, 7,resuelto);
    if (!resuelto)
      resuelto = resolver (i, j, 8,resuelto);
    if (!resuelto)
      resuelto = resolver (i, j, 9,resuelto);
            
    return resuelto;  
  }
   
  //Método recursivo de solve
  private boolean resolver (int i ,int j, int num, boolean resuelto) {       
    int aux;
    if(i == 9) {
      if(j==8) 
        resuelto = true; // cuando i=9 y j=8, el sudoku ha sido resulto
      i = 0; 
      j++; // (j++)se recorre el sudoku verticalmente
    }        
    if(!resuelto && sudoku[i][j]!=0 )
      return resolver(i+1, j, num, resuelto); // este es el caso en el que el metodo se encuentre en una casilla con pista
    else {
      if(!resuelto && valida (i, j, num)) { //Esta serie de ifs va intentado meter los numeros del uno al nueve en la posicion [i],[j] hasta que sea posible
        resuelto = resolver(i+1,j,1,resuelto);
        if (!resuelto)
          resuelto = resolver (i+1, j, 2,resuelto);   // (i+1) se recorre el sudoku horizontalmente
        if (!resuelto)
          resuelto = resolver (i+1, j, 3,resuelto);
        if (!resuelto)
          resuelto = resolver (i+1, j, 4,resuelto);
        if (!resuelto)
          resuelto = resolver (i+1, j, 5,resuelto);
        if (!resuelto)
          resuelto = resolver (i+1, j, 6,resuelto);
        if (!resuelto)
          resuelto = resolver (i+1, j, 7,resuelto);
        if (!resuelto)
          resuelto = resolver (i+1, j, 8,resuelto);
        if (!resuelto)
          resuelto = resolver (i+1, j, 9,resuelto);
        if(!resuelto) {
          aux = sudoku[i][j]; // el numero que se habia agregado al sudoku  se tiene que eliminar de todos los conjuntos
          sudoku[i][j]=0;
          columnas[j].remove(aux); 
          renglones[i].remove(aux); 
          cuadros[i/3][j/3].remove(aux); 
        }
      } 
    }
    return resuelto;
  }
    
  //parametros: (rengolnes,columnas, numero a ingresar)
  public boolean valida(int i, int j, int n) { //regresa true si n se puede ingresar en el sudoku
    boolean res = false; 
    int num;
    if(i < 9 && i > -1 && j < 9 && j > -1) { // solo se puede ingresar si n no esta ya en esa columna, renglón o cuadro
      if(!columnas[j].contains(n)) {
        if(!renglones[i].contains(n)) {
          res= !cuadros[i/3][j/3].contains(n);
          
          if(res) { //Se quita el numero que estaba anteriormente para agregar el nuevo
            num = sudoku[i][j];
            renglones[i].add(n);
            columnas[j].add(n);
            cuadros[i/3][j/3].add(n);
                         
            if(num!=0) {
              renglones[i].remove(num); 
              columnas[j].remove(num);               
              cuadros[i/3][j/3].remove(num); 
            }             
            sudoku[i][j]=n;  // se reemplaza en numero en el sudoku           
            imprimeMat();
            System.out.println("\n");
          }
        }
      }
    }
        
      return res;
    }
    
    
    
    public static void main(String[] args) {
        Sudoku s = new Sudoku();
        System.out.println(s.resolver());
        s.imprimeMat();

    }
}
