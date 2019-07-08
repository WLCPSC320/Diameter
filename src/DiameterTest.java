import Model.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class DiameterTest {
    private Vertex a;
    private Vertex b;
    private Vertex c;
    private Vertex d;
    private Vertex e;
    private Vertex f;
    private ArrayList<Vertex> vertexList;


    @Before
    public void runBefore() {
        a = new Vertex("a");
        b = new Vertex("b");
        c = new Vertex("c");
        d = new Vertex("d");
        e = new Vertex("e");
        f = new Vertex("f");
        vertexList = new ArrayList<>();
        vertexList.add(a);
        vertexList.add(b);
        vertexList.add(c);
        vertexList.add(d);
        vertexList.add(e);
        vertexList.add(f);
    }

    @Test
    public void testDiameterSimpleTriangle() {
        System.out.println("testDiameterSimpleTriangle");
        a.connectVertex(b);
        b.connectVertex(c);
        c.connectVertex(a);
        vertexList = new ArrayList<>();
        vertexList.add(a);
        vertexList.add(b);
        vertexList.add(c);
        Diameter diameter = new Diameter(vertexList);
        assertEquals(1, diameter.getDiameter());
        diameter.printPath();
    }

    @Test
    public void testDiameterSimpleSquare() {
        System.out.println("testDiameterSimpleSquare");
        a.connectVertex(b);
        b.connectVertex(c);
        c.connectVertex(d);
        d.connectVertex(a);
        vertexList = new ArrayList<>();
        vertexList.add(a);
        vertexList.add(b);
        vertexList.add(c);
        vertexList.add(d);
        Diameter diameter = new Diameter(vertexList);
        assertEquals(2, diameter.getDiameter());
        diameter.printPath();
    }

    @Test
    // Worksheet Example (Page 2)
    public void testDiameterAdvanced() {
        System.out.println("testDiameterAdvanced");
        a.connectVertex(b);
        a.connectVertex(f);
        f.connectVertex(e);
        e.connectVertex(d);
        d.connectVertex(c);
        d.connectVertex(b);
        c.connectVertex(b);
        Diameter diameter = new Diameter(vertexList);
        assertEquals(3, diameter.getDiameter());
        diameter.printPath();
    }

    @Test
    // Worksheet Example (Page 1)
    public void testDiameterEvenMoreAdvanced() {
        System.out.println("testDiameterEvenMoreAdvanced");
        Vertex g = new Vertex("g");
        Vertex h = new Vertex("h");
        a.connectVertex(b);
        a.connectVertex(c);
        c.connectVertex(b);
        b.connectVertex(d);
        b.connectVertex(f);
        b.connectVertex(e);
        f.connectVertex(h);
        e.connectVertex(h);
        d.connectVertex(g);
        vertexList.add(g);
        vertexList.add(h);
        Diameter diameter = new Diameter(vertexList);
        assertEquals(4, diameter.getDiameter());
        diameter.printPath();
    }

    @Test
    // Worksheet Example (Page 1) remove E(b,f) & E(a,b), add E(f,g)
    public void testDiameterEvenMoreAdvancedVariation() {
        System.out.println("testDiameterEvenMoreAdvancedVariation");
        Vertex g = new Vertex("g");
        Vertex h = new Vertex("h");
        a.connectVertex(c);
        c.connectVertex(b);
        b.connectVertex(d);
        f.connectVertex(g);
        b.connectVertex(e);
        f.connectVertex(h);
        e.connectVertex(h);
        d.connectVertex(g);
        vertexList.add(g);
        vertexList.add(h);
        Diameter diameter = new Diameter(vertexList);
        assertEquals(5, diameter.getDiameter());
        diameter.printPath();
    }
}