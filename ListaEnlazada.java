package linkedlist;

import java.util.Iterator;

public class ListaEnlazada<T> implements Ilist<T>, Iterable<T> {

    protected NodoSimple<T> inicio;
    protected int nElementos;

    public ListaEnlazada() {
        inicio = null;
        nElementos = 0;
    }

    private class NodoSimple<T> {

        private T dato;
        private NodoSimple<T> sig;

        public NodoSimple(T dato) {
            this.dato = dato;
            this.sig = null;
        }
    }

    @Override
    public void append(T elemento) throws ListException {
        NodoSimple<T> nodoNuevo = new NodoSimple<>(elemento);

        if (inicio == null) {
            inicio = nodoNuevo;
        } else {

            NodoSimple<T> nodo = inicio;

            while (nodo.sig != null) {
                nodo = nodo.sig;
            }

            nodo.sig = nodoNuevo;
        }

        nElementos++;
    }

    @Override
    public void insert(T elemento, int index) throws ListException {
        if (index < 0 || index > nElementos) {
            throw new ListException("Indice fuera de rango");
        }

        NodoSimple<T> nodoNuevo = new NodoSimple<>(elemento);
        if (index == 0) {

            nodoNuevo.sig = inicio;
            inicio = nodoNuevo;

        } else {

            NodoSimple<T> nodo = inicio;

            for (int i = 0; i < index - 1; i++) {
                nodo = nodo.sig;
            }

            nodoNuevo.sig = nodo.sig;
            nodo.sig = nodoNuevo;
        }

        nElementos++;
    }

    @Override
    public T get(int index) throws ListException {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de rango");
        }

        NodoSimple<T> nodo = inicio;

        for (int i = 0; i < index; i++) {
            nodo = nodo.sig;
        }

        return nodo.dato;
    }

    @Override
    public T remove(int index) throws ListException {
        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de rango");
        }

        NodoSimple<T> eliminado;

        if (index == 0) {

            eliminado = inicio;
            inicio = inicio.sig;

        } else {

            NodoSimple<T> nodo = inicio;

            for (int i = 0; i < index - 1; i++) {
                nodo = nodo.sig;
            }

            eliminado = nodo.sig;
            nodo.sig = eliminado.sig;
        }

        nElementos--;

        return eliminado.dato;
    }

    @Override
    public boolean removeObj(T elemento) throws ListException {

        int index = indexOf(elemento);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
    }

    @Override
    public int indexOf(T elemento) {

        NodoSimple<T> nodo = inicio;
        int index = 0;

        while (nodo != null) {

            if (nodo.dato.equals(elemento)) {
                return index;
            }

            nodo = nodo.sig;
            index++;
        }

        return -1;
    }

    @Override
    public void set(T elemento, int index) throws ListException {

        if (index < 0 || index >= nElementos) {
            throw new ListException("Indice fuera de rango");
        }

        NodoSimple<T> nodo = inicio;

        for (int i = 0; i < index; i++) {
            nodo = nodo.sig;
        }

        nodo.dato = elemento;
    }

    @Override
    public void clear() {

        inicio = null;
        nElementos = 0;
    }

    @Override
    public boolean empty() {

        return nElementos == 0;
    }

    @Override
    public int size() {

        return nElementos;
    }

    @Override
    public Iterator<T> Iterator() {

        return iterator();
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            NodoSimple<T> nodo = inicio;

            @Override
            public boolean hasNext() {
                return nodo != null;
            }

            @Override
            public T next() {
                T dato = nodo.dato;
                nodo = nodo.sig;
                return dato;
            }
        };
    }

    public void invertir() {
        NodoSimple<T> anterior = null;
        NodoSimple<T> actual = inicio;

        while (actual != null) {
            NodoSimple<T> siguiente = actual.sig;

            actual.sig = anterior;
            anterior = actual;
            actual = siguiente;
        }

        inicio = anterior;
    }

    public void concatenar(ListaEnlazada<T> otraLista) {
        NodoSimple<T> nodo = otraLista.inicio;
        while (nodo != null) {
            append(nodo.dato);
            nodo = nodo.sig;
        }
    }
}