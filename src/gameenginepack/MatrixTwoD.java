package gameenginepack;

public class MatrixTwoD {

    private double[] components;
    /*
    1,0,0
    0,1,0
    0,0,1
    m11,m12,m13(x)
    m21,m22,m23(y)
     */

    public MatrixTwoD() {
        //components = new double(9);
        components = new double[]{1,0,0,0,1,0,0,0,1};
    }

    public MatrixTwoD(double x, double y) {
        components = new double[]{1,0,x,0,1,y,0,0,1};
    }

    public MatrixTwoD(Vector2 up, Vector2 look) {
        components = new double[]{up.x,up.y,0,look.x,look.y,0,0,0,1};
    }

    public MatrixTwoD(double x,double y, double m11, double m12, double m21, double m22) {
        components = new double[]{m11,m12,x,m21,m22,y,0,0,1};
    }

    public MatrixTwoD mul(MatrixTwoD other) {
        double[] a = this.getComponents();
        double[] b = other.getComponents();

        double ax=a[0],ay=a[1],a11=a[2],a12=a[3],a21=a[4],a22=a[5];
        double bx=b[0],by=b[1],b11=b[2],b12=b[3],b21=b[4],b22=b[5];

        double  m11 = a11*b11+a12*b21,
                m12 = a11*b12+a12*b22,
                x = a11*bx+a12*by+ax,
                m21 = a21*b11+a22*b21,
                m22 = a21*b12+a22*b22,
                y = a21*bx+a22*by+ay;

        return new MatrixTwoD(x,y,m11,m12,m21,m22);
    }

    public static MatrixTwoD Angles(double a) {
        Vector2 upvector = Vector2.Transform(Vector2.UP,a);
        System.out.println(upvector);
        Vector2 lookvector = Vector2.Transform(Vector2.RIGHT,a);
        System.out.println(lookvector);
        return new MatrixTwoD(upvector,lookvector);
    }

    public double[] getComponents() {
        return new double[]{components[2], components[5],components[0],components[3],components[1],components[4]};
    }

    public String toString() {
        double[] a = getComponents();
        return String.format("%s,%s,%s,%s,%s,%s",a[0],a[1],a[2],a[3],a[4],a[5]);
    }


}
