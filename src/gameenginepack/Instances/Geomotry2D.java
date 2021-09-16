package gameenginepack.Instances;

import gameenginepack.MatrixTwoD;
import gameenginepack.Vector2;

import java.util.ArrayList;

public abstract class Geomotry2D extends Instance {
    public ArrayList<Vector2> Vertices = new ArrayList<>();
    public MatrixTwoD CFrame = new MatrixTwoD();

    public void setPosition() {

    }

    private void UpdateVertices(MatrixTwoD difference) {
        for (Vector2 Vertice : Vertices) {
            Vertice = CFrame.mul(Vertice);
        }
    }

    public void setCFrame() {

    }
}
