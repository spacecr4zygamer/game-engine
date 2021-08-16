package gameenginepack;

public class Vector3 {
    private double x=0,y=0,z=0;

    public Vector3() {}

    public Vector3(double x) {
        this.x = x;
    }

    public Vector3(double x,double y) {
        this.x = x;
        this.y = y;
    }

    public Vector3(double x,double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String tostring() {
        return this.x+","+this.y+","+this.z;
    }

    public static Vector3 fromString(String string) {
        String[] ree = string.split(",");
        return new Vector3(Double.parseDouble(ree[0]),Double.parseDouble(ree[1]),Double.parseDouble(ree[2]));
    }

    public Vector3 mul(Vector3 other) {
        return new Vector3(this.x * other.x, this.y * other.y, this.z * other.z);
    }

    public Vector3 mul(double a) {
        return new Vector3(this.x*a,this.y*a,this.z*a);
    }

    public Vector3 add(Vector3 other) {
        return new Vector3(this.x + other.x, this.y + other.y, this.z + other.z);
    }

    public Vector3 sub(Vector3 other) {
        return new Vector3(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3 div(Vector3 other) {
        return new Vector3(this.x/other.x,this.y/other.y,this.z/other.z);
    }

    public Vector3 div(double a) {
        return new Vector3(this.x/a,this.y/a,this.z/a);
    }

    public Vector3 cross(Vector3 other) {
        Vector3 Result = new Vector3();
        double a_1 = this.x,a_2 = this.y,a_3 = this.z;
        double b_1 = other.x,b_2 = other.y,b_3 = other.z;

        double s_1 = a_2*b_3-a_3*b_2,
                s_2 = a_3*b_1-a_1*b_3,
                s_3 = a_1*b_2-a_2*b_1;

        System.out.println(s_1+" "+s_2+" "+s_3);

        return Result;
    }

    public double dot(Vector3 other) {
        return 0;
    }




}
