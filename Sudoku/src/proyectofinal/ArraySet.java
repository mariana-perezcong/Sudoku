/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinal;

/**
 *
 * @author mperezcong
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class ArraySet<T> implements SetADT<T>{
    private static final int DEFAULT_CAPACITY=100;
    private static Random rand=new Random();
    private static final int NOT_FOUND=-1;
    private T[] contents;
    private int count;
    
    public ArraySet(int initialCapacity){
        count=0;
        contents= (T[])(new Object[initialCapacity]);        
    }
    
    public ArraySet(){
        this(DEFAULT_CAPACITY);
    }
    

   

    public void add(T element) {
        if(!contains(element)){
            if(size()==contents.length)
                expandCapacity();
            contents[count]=element;
            count++;
        }
    }
    
    private void expandCapacity(){
        T[] larger = (T[]) (new Object[contents.length*1]);
        for( int index=0;index<contents.length; index++)
            larger[index]= contents [index];
        contents=larger;
    }


    public boolean contains(T target) {
        
        return searchTarget(target)!= NOT_FOUND;
        
    }
    private int searchTarget(T target){
        int search=NOT_FOUND;
        int index=0;
        while(index<count&& search==NOT_FOUND){
            if( contents[index].equals(target))
                search=index;
            index++;
            
        }
        return search;
    }


    public T remove(T element) throws NoSuchElementException {  
        if(isEmpty())
            throw new EmptyCollectionException();
        
        else{
            int search=searchTarget(element);
            if(search==NOT_FOUND)
                throw new NoSuchElementException();
            else{
                T result=contents[search];
                contents[search]=contents[count-1];
                contents[count-1]=null;
                count--;
                return result;
            }
       
        }   
    }

    public T removeRandom() {
        if(isEmpty())
            throw new EmptyCollectionException();
        else{
            int choice =rand.nextInt(count);
            T result=contents[choice];
            contents[choice]=contents[count-1];
            contents[count-1]=null;
            count--;
            return result;
            
        }
    }


    public boolean isEmpty() {
        return count==0;
    }


    public int size() {
        return count;
    }


    public void addAll(SetADT<T> set) {
        for(T element:set)
            add(element);
    }


    public SetADT<T> union(SetADT<T> set) {
        int tam=set.size();
        ArraySet <T> union =new ArraySet();
        for(int i=0; i<count; i++)
            union.add(contents[i]);
        Iterator <T> scan=set.iterator();
        while (scan.hasNext())
            union.add(scan.next());
        return union;
    }
    public SetADT <T> unionRecursiva(Iterator <T> set, int tot, SetADT <T> union, T dato){
        union.add(contents[tot]);
        union.add(dato);        
        if(!set.hasNext())
            return union;    
        else
            return unionRecursiva(set,tot,union,set.next());

    }

    public SetADT<T> intersection(SetADT<T> set) {
        ArraySet <T> intersection=new ArraySet();
        Iterator <T> scan= set.iterator();
        int i=0;
        while( scan.hasNext()){
            T ele=scan.next();
            if(contains(ele))
                intersection.add(ele);
            
        }
        return intersection;
    }

    public SetADT<T> difference(SetADT<T> set) {
        ArraySet <T> inter =(ArraySet)intersection(set);
        ArraySet <T> difference =new ArraySet();        
        int i=0;
        while(i<count){
            if(!inter.contains(contents[i]))
                difference.add(contents[i]);
            i++;
        }
        return difference;
    }

    public boolean equals(SetADT<T> set) {
        boolean result=true;
        int countEquals=0;
        Iterator <T> it=set.iterator();
        if(size()==set.size()){
            while(it.hasNext()&&result){
                if(!contains(it.next()))
                    result=false;
            }     
        }
        return result;       
    }

    public Iterator<T> iterator() {
        return new ArrayIterator <T> (contents, count);
    }
    
    public String toString(){
        StringBuilder result=new StringBuilder();
        for(int index=0; index<count;index++)
            result.append(contents[index].toString()+"\n");
        return result.toString();
    }
}
