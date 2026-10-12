package linkedlist;

public class LinkedList {
    public static void main(String[] args) {
        ListaEnlazada<String> lista = new ListaEnlazada<>();

        lista.append("Ana");
        lista.append("Victor");
        lista.append("Sofia");
        System.out.println("Tamaño: " + lista.size()); 

        lista.insert("Manuel", 1);
        System.out.println("En el índice 1: " + lista.get(1));   

        System.out.println("Removido: " + lista.remove(0));   

        ListaEnlazada<String> otra = new ListaEnlazada<>();
        otra.append("Luisa");
        otra.append("Samara");
        lista.concatenar(otra);
        lista.invertir();
        
        System.out.println("Lista: ");
        int j = 1;
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(" " + (j++) + ". " + lista.get(i));
        }

        // Pruebas de iteracion circular y anterior/siguiente
        System.out.println("\n--- Pruebas de la lista circular ---");

        lista.irInicio();
        System.out.println("Inicio: " + lista.getActual());

        lista.next();
        System.out.println("Siguiente: " + lista.getActual());

        lista.next();
        System.out.println("Siguiente: " + lista.getActual());

        lista.next();
        System.out.println("Siguiente: " + lista.getActual());

        // Da la vuelta al inicio
        lista.next();
        System.out.println("Siguiente (regresa al inicio): " + lista.getActual());

        // Probar previous desde el inicio (debe ir al ultimo)
        lista.irInicio();
        System.out.println("\nDe vuelta en inicio: " + lista.getActual());
        lista.previous();
        System.out.println("Anterior (va al ultimo): " + lista.getActual());

        lista.previous();
        System.out.println("Anterior: " + lista.getActual());

        // Probar irFinal
        lista.irFinal();
        System.out.println("\nIr al final: " + lista.getActual());

        lista.clear();
        System.out.println("\nTamaño despues de clear: " + lista.size());
    }    
}