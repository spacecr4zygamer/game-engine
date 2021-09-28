package gameenginepack.Instances;

import gameenginepack.MatrixTwoD;
import gameenginepack.Vector2;

import java.util.ArrayList;

public abstract class Geomotry2D extends Instance {
    private ArrayList<Vector2> Vertices = new ArrayList<>();
    private MatrixTwoD CFrame = new MatrixTwoD();
    private ArrayList<Vector2> OriginalVertices = new ArrayList<>();
    private Vector2 Size = new Vector2(4,2);

    protected void UpdateVertices() {
        //System.out.println(OriginalVertices.size());
        if (!OriginalVertices.isEmpty()) {
            int c = 0;
            this.Vertices.clear();
            for (Vector2 Vertice : this.OriginalVertices) {
                this.Vertices.add(c, this.CFrame.mul(Vertice.mul(this.Size.mul(0.5))));
                c++;
            }
        }
    }

    public ArrayList<Vector2> getVertices() {
        return Vertices;
    }

    public Vector2 getSize() {
        return Size;
    }

    public void addVertice(Vector2 Vertice) {
        OriginalVertices.add(Vertice);
        UpdateVertices();
    }

    public void setSize(Vector2 newSize) {
        Size = newSize;
        UpdateVertices();
    }

    public void setOrientation(double angle) {
        CFrame.setOrientation(angle);
        this.UpdateVertices();
    }

    public ArrayList<Vector2> getOriginalVertices() {
        return OriginalVertices;
    }

    public MatrixTwoD getCFrame() {
        return CFrame;
    }

    public Vector2 getPosition() {
        return CFrame.getPosition();
    }

    public double getRotation() {
        return CFrame.getAngle();
    }

    protected void setVertices(ArrayList<Vector2> Vertices) {
        this.OriginalVertices = Vertices;
        this.UpdateVertices();
    }

    public void setPosition(Vector2 Position) {
        CFrame.setPosition(Position);
        UpdateVertices();
    }

    public void setCFrame(MatrixTwoD newCFrame) {
        CFrame = newCFrame;
        UpdateVertices();
    }
}
