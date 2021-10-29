package gameenginepack;

public class Ray2D {
    public Vector2 Origin, Direction;
    public double Length;

    public Ray2D(Vector2 Origin, Vector2 Direction,double length) {
        this.Origin = Origin;
        this.Direction = Direction;
        this.Length = length;
    }

    public void cast(Vector2 a, Vector2 b) {
        double x1 = a.x,
                y1 = a.y,
                x2 = b.x,
                y2 = b.y;
        double x3 = this.Origin.x,
                y3 = this.Origin.y,
                x4 = this.Origin.add(this.Direction.mul(this.Length)).x,
                y4 = this.Origin.add(this.Direction.mul(this.Length)).y;

        double den = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);
        if (den == 0) {
        }
        double t = ((x1 - x3) * (y3 - y4) - (y1 - y3) * (x3 - x4)) / den;
        double u = -((x1 - x2) * (y1 - y3) - (y1 - y2) * (x1 - x3)) / den;
        if (t >0 && t < 1 && u > 0) {
            System.out.println("Collision Detected");
        } else {
            //System.out.println("Collision not found");
        }
    }

}
