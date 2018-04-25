import java.util.*;

public class LinkedList {

    public Node first;

    public static class Node {

        int points;
        String nome;
        Node next;

        public Node( int points, String nome ) {
            this.points = points;
            this.nome = nome;
            next = null;
        }

    }

    public class IteratorLigada implements Iterator {
        Node aux;

        public IteratorLigada() { aux = first; }

        public boolean hasNext() {
            return aux != null;
        }

        public String next() {
            if ( hasNext() ) {
                String player = aux.nome + " " + aux.points;
                aux = aux.next;
                return player;
            }
            return null;
        }
    }

    public LinkedList() {
        first = null;
    }

    public Iterator getIterator() { return new IteratorLigada(); }

    public void insere( int points, String nome ) {
        first = insere( first, points, nome );
    }

    private Node insere( Node n, int points, String nome ) {
        if ( n == null ) return new Node( points, nome );
        if ( points > n.points ) {
            Node f = new Node( points, nome );
            f.next = n;
            return f;
        } else {
            n.next = insere( n.next, points, nome );
            return n;
        }
    }

    public void print() {
        print( first );
    }

    private void print( Node n ) {
        if ( n == null ) return;
        System.out.println( n.nome + ": " + n.points );
        print( n.next );
    }
}
