package linkedlist;

public class LinkedList {

    public static void main(String[] args) {
    ListaEnlazada<String> lista = new ListaEnlazada<>();

        lista.append("Fuego");
        lista.append("Hielo");
        lista.append("Tornado");

        System.out.println("Lista:");
        for (String dato : lista) {
            System.out.println(dato);
        }

        lista.insert("Luz", 1);
        System.out.println("Despues de insertar:");
        for (String dato : lista) {
            System.out.println(dato);
        }

        System.out.println("Elemento: " + lista.get(2));
        
        System.out.println("Eliminado: " + lista.remove(1));

        System.out.println("Tamanio: " + lista.size());

        lista.invertir();
        System.out.println("Lista invertida:");
        for (String dato : lista) {
            System.out.println(dato);
        }
        ListaEnlazada<String> lista2 = new ListaEnlazada<>();

        lista2.append("Tierra");
        lista2.append("Agua");

        System.out.println("Lista 2:");
        for (String dato : lista2) {
            System.out.println(dato);
        }

        lista.concatenar(lista2);
        System.out.println("Lista concatenada:");
        for (String dato : lista) {
            System.out.println(dato);
        }

        lista.clear();
        System.out.println("Tamanio despues de clear: " + lista.size());
    }
}
