/**
 * @author Kyler Sood, University of Minnesota, Twin Cities
 * CSCI 4041H Spring 2022 with Dr. James Moen
 * sood0027@umn.edu
 * Honors Project - Graphs, Chromatic Polynomials
 */
public class Graph {
    LinkedList<Integer>[] adjacency;
    public Graph(){}
    public Graph(LinkedList<Integer>[] adjacency){
        this.adjacency = adjacency;
    }
    public Graph(Integer[][] adjacency){
        LinkedList<Integer>[] temp = new LinkedList[adjacency.length];
        for(int i = 0; i < adjacency.length; i++){
            for(int j = 0; j < adjacency.length; j++){
                if(!adjacency[i][j].equals(adjacency[j][i])){
                    System.out.println("Not an adjacency matrix, asymmetric");
                    break;
                }
                else if(temp[i]==null && adjacency[i][j]==1){
                    temp[i] = new LinkedList<>(j);
                }
                else if(adjacency[i][j]==1){
                    temp[i].setStart(new Node<>(j, temp[i].getStart()));
                }
            }
            if(temp[i]!=null){
                temp[i].setStart(new Node<>(i, temp[i].getStart()));
            }
            else{
                temp[i] = new LinkedList<>(i);
            }
        }
        this.adjacency = temp;
    }
    public LinkedList<Integer>[] addVertex(Integer v){
        if(adjacency==null){
            LinkedList<Integer>[] temp = new LinkedList[]{new LinkedList<Integer>(v)};
            return temp;
        }
        LinkedList<Integer>[] temp = new LinkedList[adjacency.length+1];
        for(int i = 0; i < adjacency.length; i++){
            temp[i] = adjacency[i].clone();
        }
        temp[temp.length-1] = new LinkedList<Integer>(v);
        return temp;
    }
    public LinkedList<Integer>[] addVertexes(Integer[] vertexes){
        LinkedList<Integer>[] temp = adjacency;
        for(Integer v:vertexes){
            Graph P = new Graph(temp);
            temp = P.addVertex(v);
        }
        return temp;
    }
    public LinkedList<Integer>[] addEdge(Integer[] e){
        LinkedList<Integer>[] temp = adjacency;
        Integer v1 = e[0];
        Integer v2 = e[1];
        temp[v1] = adjacency[v1].clone();
        temp[v2] = adjacency[v2].clone();
        temp[v1].add(v2);
        temp[v2].add(v1);
        return temp;
    }
    public LinkedList<Integer>[] addEdges(Integer[][] edges){
        LinkedList<Integer>[] temp = adjacency;
        for(Integer[] e:edges){
            Graph P = new Graph(temp);
            temp = P.addEdge(e);
        }
        return temp;
    }
    public void display(){
        for(int i = 0; i < adjacency.length; i++){
            adjacency[i].display();
            System.out.println();
        }
    }
    public Integer[] nextEdge(){
        Node<Integer> v1;
        for(int i = 0; i<adjacency.length; i++){
            v1 = adjacency[i].getStart();
            if(v1==null){
                continue;
            }
            if(v1.getNext()==null){
                continue;
            }
            else{
                return new Integer[]{v1.getValue(), v1.getNext().getValue()};
            }
        }
        return null;
    }
    public LinkedList<Integer>[] delete(Integer[] e){
        Integer v1 = e[0];
        Integer v2 = e[1];
        LinkedList<Integer>[] temp = new LinkedList[adjacency.length];
        for(int i = 0; i < adjacency.length; i++){
            temp[i] = adjacency[i].clone();
        }
        temp[v1].removeAll(v2);
        temp[v2].removeAll(v1);

        return temp;
    }
    public LinkedList<Integer>[] contract(Integer[] e){
        LinkedList<Integer>[] temp = new LinkedList[adjacency.length-1];
        Integer v1 = e[0];
        Integer v2 = e[1];
        Integer x1 = Math.min(v1, v2);
        Integer x2 = Math.max(v1, v2);
        for(int i = 0; i < x2; i++){
            temp[i] = adjacency[i].clone();
        }
        LinkedList<Integer> temp2 = adjacency[x2].clone();
        temp[x1].mergeNoDuplicates(temp2);
        for(int j = x2; j < temp.length; j++){
            temp[j] = adjacency[j+1].clone();
        }
        for(int i = 0; i < temp.length; i++){
            if(i!=x1){
                temp[i].replace(x2, x1);
                temp[i].removeDuplicatesOf(x1);
            }
            else{
                temp[i].removeAll(x2);
            }
        }
        for(int k = x2; k < adjacency.length; k++){
            for(int j = 0; j < temp.length; j++){
                temp[j].replace(k, k-1);
            }
        }
        return temp;
    }
    public Polynomial chromaticPolynomial(){
        if(nextEdge()==null){
            return new Polynomial(1, adjacency.length);
        }
        Graph G1 = new Graph(adjacency);
        Graph G2 = new Graph(adjacency);
        Integer[] y = G1.nextEdge();
        System.out.println("\nEDGE: "+y[0]+" "+y[1]);
        G1.display();
        G1 = new Graph(G1.delete(y));
        System.out.println("Delete");
        G1.display();
        Integer[] x = G2.nextEdge();
        System.out.println("\nEDGE: "+x[0]+" "+x[1]);
        G2.display();
        G2 = new Graph(G2.contract(x));
        System.out.println("Contract");
        G2.display();
        // This is the deletion-contraction recursion in play.
        Polynomial P1 = G1.chromaticPolynomial();
        Polynomial P2 = G2.chromaticPolynomial();
        P1 = P1.subtract(P2);
        return P1;
    }
    public Polynomial chromaticPolynomialNoDisp(){
        if(nextEdge()==null){
            return new Polynomial(1, adjacency.length);
        }
        Graph G1 = new Graph(adjacency);
        Graph G2 = new Graph(adjacency);
        G1 = new Graph(G1.delete(G1.nextEdge()));
        G2 = new Graph(G2.contract(G2.nextEdge()));
        Polynomial P1 = G1.chromaticPolynomialNoDisp();
        Polynomial P2 = G2.chromaticPolynomialNoDisp();
        P1 = P1.subtract(P2);
        return P1;
    }
    public static void GraphDemo1(){
        // Constructing a graph by entering the edges and vertices
        Graph G = new Graph();
        G = new Graph(G.addVertexes(new Integer[]{0, 1, 2, 3, 4}));
        Integer[] e1 = {0,1};
        Integer[] e2 = {1,2};
        Integer[] e3 = {2,3};
        Integer[] e4 = {3,4};
        Integer[] e5 = {4,0};
        Integer[] e6 = {1, 3};
        G.addEdge(e1);
        G.addEdge(e2);
        G.addEdge(e3);
        G.addEdge(e4);
        G.addEdge(e5);
        G.addEdge(e6);
        G.display();
        Polynomial x = G.chromaticPolynomialNoDisp();
        System.out.println("\nThe chromatic polynomial of G is:");
        x.display();
    }
    public static void GraphDemo2(){
        Integer[][] adj = { {0, 1, 0, 0, 1},
                            {1, 0, 1, 1, 0},
                            {0, 1, 0, 1, 0},
                            {0, 1, 1, 0, 1},
                            {1, 0, 0, 1, 0},
                            };
        Graph G = new Graph(adj);
        G.display();
        Polynomial x = G.chromaticPolynomialNoDisp();
        System.out.println("\nThe chromatic polynomial of G is:");
        x.display();
    }
    public static void main(String[] args){
        GraphDemo1();
        System.out.println();
        //GraphDemo2();
        System.out.println("\n\n\n");
        // ENTER YOUR SQUARE ADJACENCY MATRIX BELOW
        // The default graph is the complete graph "K_6"
        // Constructing K_6: Draw 6 vertices and connect as many edges (15) as possible
        // You can input any adjacency matrix for an undirected graph.
        Integer[][] adj = {
                {0, 1, 1, 1, 1, 1},
                {1, 0, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1, 1},
                {1, 1, 1, 1, 0, 1},
                {1, 1, 1, 1, 1, 0},
        };
        Graph G = new Graph(adj);
        /*Integer[][] adj2 = { // Java has limits on integer size, so this graph demonstrates how this is impractical to do in Java
                {0, 1, 0, 1, 1, 0, 1, 0, 1, 0},
                {1, 0, 1, 1, 0, 0, 0, 1, 0, 1},
                {0, 1, 0, 0, 1, 1, 1, 0, 1, 0},
                {1, 1, 0, 0, 1, 0, 0, 1, 1, 0},
                {1, 0, 1, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 0, 0, 0, 1, 1, 0, 0},
                {1, 0, 1, 0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 1, 0, 0, 1, 1, 0},
        };
        G = new Graph(adj2);*/
        System.out.println("The adjacency structure of G is:");
        G.display();
        Polynomial x = G.chromaticPolynomial();
        System.out.println("\nThe chromatic polynomial of G is:");
        x.display();
        for(int i = 0; i < adj.length+4; i++){
            System.out.print("\nThere are "+x.eval(i)+" proper "+i+"-colorings of G");
        }
    }
}