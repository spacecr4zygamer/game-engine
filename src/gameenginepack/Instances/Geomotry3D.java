package gameenginepack.Instances;

import gameenginepack.DataTypes.MatrixTwoD;
import gameenginepack.DataTypes.Vector2;

import java.util.ArrayList;

public abstract class Geomotry3D extends  Instance {
    public ArrayList<Vector2> Vertices = new ArrayList<>();
    public MatrixTwoD CFrame = new MatrixTwoD();

}
