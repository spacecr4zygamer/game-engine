package gameenginepack;

public class MatrixTwoD {

    private double[] components;
    /*
    1,0,0
    0,1,0
    0,0,1
    m11,m12,m13(x)
    m21,m22,m23(y)
    up vector = (m11,m21)
    back vector = (m12,m22)
    position = (m13,m23)
     */

    public MatrixTwoD() {
        //components = new double(9);
        components = new double[]{
                1, 0, 0,
                0, 1, 0,
                //0,0,1
        };
    }

    public MatrixTwoD(double x, double y) {
        components = new double[]{
                1, 0, x,
                0, 1, y,
                //0,0,1
        };
    }

    //public MatrixTwoD(Vector2 up, Vector2 look) {
    //    components = new double[]{up.x,look.x,0,up.y,look.y,0,0,0,1};
    //}

    public MatrixTwoD(double x, double y, double m11, double m12, double m21, double m22) {
        components = new double[]{
                m11, m12, x,
                m21, m22, y,
                //0,0,1
        };
    }

    public MatrixTwoD(double a, double b, double c, double d) {
        components = new double[]{
                a, b, 0,
                c, d, 0,
                //0,0,1
        };
    }

    public MatrixTwoD mul(MatrixTwoD other) {
        double[] a = this.getComponents();
        double[] b = other.getComponents();

        double ax = a[0], ay = a[1], a11 = a[2], a12 = a[3], a21 = a[4], a22 = a[5];
        double bx = b[0], by = b[1], b11 = b[2], b12 = b[3], b21 = b[4], b22 = b[5];

//        double  m11 = a11*b11+a12*b21,
//                m12 = a11*b12+a12*b22,
//                x = a11*bx+a12*by+ax,
//                m21 = a21*b11+a22*b21,
//                m22 = a21*b12+a22*b22,
//                y = a21*bx+a22*by+ay;
        double m11 = a11 * b11 + a12 * b21,
                m12 = a11 * b12 + a12 * b22,
                x = a11 * bx + a12 * by + ax,
                m21 = a21 * b11 + a22 * b21,
                m22 = a21 * b12 + a22 * b22,
                y = a21 * bx + a22 * by + ay;
        //cos = 0/4, sin = 1, -sin = 2
        double acos = a[0], asin = a[1];
        double bcos = b[0], bsin = b[1];

//        double  m11 = acos*bcos-asin*bsin,
//                m12 = acos*bsin+asin*bcos,
//                x = a11*bx+a12*by+ax,
//                m21 = -acos*bsin-asin*bcos,
//                m22 = acos*bcos-asin*bsin,
//                y = a21*bx+a22*by+ay;

        //System.out.println(m11+" "+m12+" "+m21+" "+m22);

        return new MatrixTwoD(x, y, m11, m12, m21, m22);
    }

    public Vector2 mul(Vector2 b) {

        double[] a = this.getComponents();
        double ax = a[0], ay = a[1], a11 = a[2], a12 = a[3], a21 = a[4], a22 = a[5];

        double x = a11 * b.x + a12 * b.y + ax,
                y = a21 * b.x + a22 * b.y + ay;

        //System.out.println(new Vector2(x,y));
        return new Vector2(x, y);
    }

    //private static double ANGLE_90 = Math.PI/2;

    public static MatrixTwoD Angles(double a) {
        double radians = Math.toRadians(a);
        double cos = Math.cos(radians), sin = Math.sin(radians);
        //Vector2 upvector = Vector2.Transform(Vector2.UP,a);
        //double e = Math.cos(radians),b=-Math.sin(radians),c=Math.sin(radians),d=Math.cos(radians);
        //System.out.println(upvector);
        // System.out.println(e+", "+b+",\n"+c+", "+d);
        //Vector2 lookvector = Vector2.Transform(Vector2.RIGHT,a);
        //System.out.println(upvector+", "+lookvector);
        //System.out.println(lookvector);
        return new MatrixTwoD(cos, -sin, sin, cos);
    }

    public double getAngle() {
        return Math.atan2(components[0], components[1]);
    }

    public Vector2 getPosition() {
        return new Vector2(components[2], components[5]);
    }

    public Vector2 getLookVector() {
        return new Vector2(components[0], components[1]);
    }

    public Vector2 getUpVector() {
        return new Vector2(components[3], components[4]);
    }

    /**
     * Gets all the Components relevant.
     *
     * @return x, y, m11, m12, m21, m22
     */
    public double[] getComponents() {
        //                      x               y           m11             m21            m12          m22
        //return new double[]{components[2], components[5],components[0],components[3],components[1],components[4]};
        //return new double[]{components[2],components[5],components[1],components[0],components[4],components[3]};
        return new double[]{components[2], components[5], components[0], components[1], components[3], components[4]};
    }

//    public MatrixTwoD(double m11, double m12, double x, double m21, double m22, double y) {
//        components = new double[]{
//                m11, m12, x,
//                m21, m22, y,
//                //0,0,1
//        };
//    }

    public void setPosition(Vector2 Position) {
        this.components[2] = Position.x;
        this.components[5] = Position.y;
    }

    public void setOrientation(double angle) {
        double radians = Math.toRadians(angle);
        double cos = Math.cos(radians), sin = Math.sin(radians);
        this.components[0] = cos;
        this.components[1] = -sin;
        this.components[3] = sin;
        this.components[4] = cos;
    }

    public MatrixTwoD inverse() {
        double[] a = this.getComponents();
        double ax = a[0], ay = a[1], a11 = a[2], a12 = a[3], a21 = a[4], a22 = a[5];

        //double det = a11 * a22 * 1 + a12 * ay * 0 + ax * a21 * 0 - 0 * a22 * ax - 0 * ay * a11 - 1 * a21 * a12;
        double det = a11 * a22 - a21 * a12;

//        System.out.println(det==det2);
//        if (det == 0) {
//            return null;
//        }
        double idet = 1 / det;
        //double a = 11, b = a12, c = ax, d = a21, e = a22, f = ay, g = 0, h = 0, i = 1;
        /*double m11 = e * i - f * h, m12 = c * h - b * i, m13 = b * f - c * e,
                m21 = f * g - d * i, m22 = a * i - e * g, m23 = c * d - a * f,
                m31 = d * h - e * g, m32 = b * g - a * h, m33 = a * e - b * d;
        double m11 = a22 * 1 - ay * 0, m12 = ax * 0 - a12 * 1, m13 = a12 * ay - ax * a22,
                m21 = ay * 0 - a21 * 1, m22 = a11 * 1 - a22 * 0, m23 = ax * a21 - a11 * ay,
                m31 = a21 * 0 - a22 * 0, m32 = a12 * 0 - a11 * 0, m33 = a11 * a22 - a12 * a21;*/
        /*double m11 = idet * (a22 * 1 - ay * 0), m12 = idet * (ax * 0 - a12 * 1), mx = idet * (a12 * ay - ax * a22),
                m21 = idet * (ay * 0 - a21 * 1), m22 = idet * (a11 * 1 - a22 * 0), my = idet * (ax * a21 - a11 * ay),
                m31 = idet * (a21 * 0 - a22 * 0), m32 = idet * (a12 * 0 - a11 * 0), m33 = idet * (a11 * a22 - a12 * a21);*/
        double m11 = idet * a22, m12 = idet * -a12, mx = idet * (a12 * ay - ax * a22),
                m21 = idet * -a21, m22 = idet * a11, my = idet * (ax * a21 - a11 * ay);
        /*m31 = 0, m32 = 0, m33 = idet * (a11 * a22 - a12 * a21);*/
        //System.out.println(m11+" "+m12+" "+m13+"\n"+m21+" "+m22+" "+m23+"\n"+m31+" "+m32+" "+m33);
        //System.out.println("This is : "+m33);
        /*if (m33!=1) {
            System.out.println("ITS NOT 1 REEEEEEEEEEEEEEEEEEE");
        }*/
        //return new MatrixTwoD(mx,my,m11,m12,m21,m22);
        return new MatrixTwoD(mx, my, m11, m12, m21, m22);
    }

    public MatrixTwoD ToObjectSpace(MatrixTwoD other) {
        return this.inverse().mul(other);
    }

    public String toString() {
        return String.format("Pos(X: %s; Y: %s); Look(X: %f; Y: %f); Up(X: %f; Y: %f)",
                components[2], components[5],
                components[0], components[1],
                components[3], components[4]);
    }


}
