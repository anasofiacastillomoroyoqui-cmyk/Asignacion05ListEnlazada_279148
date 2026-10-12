package linkedlist;

import java.util.Iterator;

public class ListaEnlazada<T> implements IList<T>, Iterable<T> {

    protected NodoDoble<T> inicio;
    protected NodoDoble<T> fin;
    protected NodoDoble<T> iterador;
    protected int nElementos;

    public ListaEnlazada() {
        this.inicio = null;
        this.fin = null;
        this.iterador = null;
        this.nElementos = 0;
    }

    protected static class NodoDoble<E> {
        E dato;
        NodoDoble<E> sig;
        NodoDoble<E> ant;

        public NodoDoble(E dato) {
            this.dato = dato;
            this.sig = null;
            this.ant = null;
        }
    }

    @Override
    public void append(T elemento) throws ListException {
        NodoDoble<T> nuevo = new NodoDoble<>(elemento);

        if (empty()) {
            inicio = nuevo;
            fin = nuevo;
            inicio.sig = inicio;
            inicio.ant = inicio;
            iterador = inicio;
        } else {
            nuevo.ant = fin;
            nuevo.sig = inicio;
            fin.sig = nuevo;
            inicio.ant = nuevo;
            fin = nuevo;
        }

        nElementos++;
    }

    @Override
    public void insert(T elemento, int index) throws ListException {
        if (index < 0 || index > nElementos) {
            throw new ListException("Índice fuera de rango: " + index);
        }

        if (index == nElementos) {
            append(elemento);
            return;
        }

        NodoDoble<T> nuevo = new NodoDoble<>(elemento);

        if (index == 0) {
            nuevo.sig = inicio;
            nuevo.ant = fin;
            inicio.ant = nuevo;
            fin.sig = nuevo;
            inicio = nuevo;
            if (iterador == null) {
                iterador = inicio;
            }
        } else {
            NodoDoble<T> actual = getNodo(index);
            NodoDoble<T> anterior = actual.ant;

            nuevo.sig = actual;
            nuevo.ant = anterior;
            anterior.sig = nuevo;
            actual.ant = nuevo;
        }

        nElementos++;
    }

    @Override
    public T remove(int index) throws ListException {
        if (empty() || index < 0 || index >= nElementos) {
            throw new ListException("Índice fuera de rango: " + index);
        }

        NodoDoble<T> eliminado;

        if (nElementos == 1) {
            eliminado = inicio;
            inicio = null;
            fin = null;
            iterador = null;
        } else if (index == 0) {
            eliminado = inicio;
            if (iterador == inicio) {
                iterador = inicio.sig;
            }
            inicio = inicio.sig;
            inicio.ant = fin;
            fin.sig = inicio;
        } else if (index == nElementos - 1) {
            eliminado = fin;
            if (iterador == fin) {
                iterador = fin.ant;
            }
            fin = fin.ant;
            fin.sig = inicio;
            inicio.ant = fin;
        } else {
            eliminado = getNodo(index);
            if (iterador == eliminado) {
                iterador = eliminado.sig;
            }
            eliminado.ant.sig = eliminado.sig;
            eliminado.sig.ant = eliminado.ant;
        }

        nElementos--;
        return eliminado.dato;
    }

    @Override
    public boolean removeObj(T elemento) throws ListException {
        int index = indexOf(elemento);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int indexOf(T element) {
        if (empty()) return -1;

        NodoDoble<T> aux = inicio;
        for (int i = 0; i < nElementos; i++) {
            if ((element == null && aux.dato == null) || (element != null && element.equals(aux.dato))) {
                return i;
            }
            aux = aux.sig;
        }
        return -1;
    }

    @Override
    public T get(int index) throws ListException {
        return getNodo(index).dato;
    }

    @Override
    public void set(T elemento, int index) throws ListException {
        getNodo(index).dato = elemento;
    }

    private NodoDoble<T> getNodo(int index) throws ListException {
        if (empty() || index < 0 || index >= nElementos) {
            throw new ListException("Índice fuera de rango: " + index);
        }

        NodoDoble<T> aux;
        if (index < nElementos / 2) {
            aux = inicio;
            for (int i = 0; i < index; i++) {
                aux = aux.sig;
            }
        } else {
            aux = fin;
            for (int i = nElementos - 1; i > index; i--) {
                aux = aux.ant;
            }
        }
        return aux;
    }

    @Override
    public void clear() {
        inicio = null;
        fin = null;
        iterador = null;
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

    // Funciones de iteración
    @Override
    public void irInicio() {
        iterador = inicio;
    }

    @Override
    public void irFinal() {
        iterador = fin;
    }

    @Override
    public void next() {
        if (iterador != null) {
            iterador = iterador.sig;
        }
    }

    @Override
    public void previous() {
        if (iterador != null) {
            iterador = iterador.ant;
        }
    }

    @Override
    public T getActual() {
        return (iterador != null) ? iterador.dato : null;
    }

    public void invertir() {
        if (empty() || nElementos == 1) return;

        NodoDoble<T> actual = inicio;
        NodoDoble<T> temp = null;

        for (int i = 0; i < nElementos; i++) {
            temp = actual.ant;
            actual.ant = actual.sig;
            actual.sig = temp;
            actual = actual.ant;
        }

        temp = inicio;
        inicio = fin;
        fin = temp;

        iterador = inicio;
    }

    public void concatenar(ListaEnlazada<T> otraLista) {
        if (otraLista == null || otraLista.empty()) {
            return;
        }

        NodoDoble<T> aux = otraLista.inicio;
        for (int i = 0; i < otraLista.size(); i++) {
            this.append(aux.dato);
            aux = aux.sig;
        }
    }

    @Override
    public Iterator<T> Iterador() {
        return iterator();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoDoble<T> actual = inicio;
            private int cont = 0;

            @Override
            public boolean hasNext() {
                return cont < nElementos;
            }

            @Override
            public T next() {
                T dato = actual.dato;
                actual = actual.sig;
                cont++;
                return dato;
            }
        };
    }
}