package proyectofinal;
import java.util.Iterator;

public interface SetADT<T> extends  Iterable <T> {
    public void add(T element); // agregar solo si no existe en el conjunto
    public boolean contains(T target);
    public T remove(T element);
    public T removeRandom();
    public boolean isEmpty();
    public int size(); //cardinalidad
    public void addAll(SetADT <T> set);
    public SetADT <T> union (SetADT <T>  set);
    public SetADT <T> intersection (SetADT <T>  set);
    public SetADT <T> difference (SetADT <T>  set);
    public boolean equals(SetADT <T> set);
    public String toString();
    public  Iterator <T> iterator();
    
    
}