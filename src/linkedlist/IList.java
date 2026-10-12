package linkedlist;

import java.util.Iterator;
public interface IList <T> {
    public void append(T elemento) throws ListException;
    public void insert(T elemento, int index) throws ListException;
    public T remove(int index) throws ListException;
    public boolean removeObj (T elemento) throws ListException;
    public int indexOf(T element);
    public T get (int index) throws ListException;
    public void set (T elemento, int index) throws ListException;
    public void clear();
    public boolean empty();
    public int size();
    public Iterator<T> Iterador();

    // Métodos de iteración circular y bidireccional
    public void irInicio();
    public void irFinal();
    public void next();
    public void previous();
    public T getActual();
}