package gameenginepack;

import org.jetbrains.annotations.Nullable;

public class CFrame {
    public Vector3 Position = new Vector3(0,0,0);
    public Vector3 BackVector = new Vector3(1,0,0);
    public Vector3 RightVector = new Vector3(0,0,1);
    public Vector3 UpVector = new Vector3(0,1,0);

    public CFrame(double x, double y, double z, double m11, double m12, double m13, double m21, double m22, double m23, double m31, double m32, double m33) {
        //System.out.println("This one???"+x+" "+y+" "+z);
        this.Position = new Vector3(x, y, z);
        this.RightVector = new Vector3(m11, m21, m31).normalize();
        this.UpVector = new Vector3(m12, m22, m32).normalize();
        this.BackVector = new Vector3(m13, m23, m33).normalize();
    }

    public CFrame() {}

    public CFrame(double x,double y,double z) {
        this.Position = new Vector3(x,y,z);
    }

    public CFrame (Vector3 a, Vector3 b) {
        /*
        local eye, look = t[1], t[2];
		local eyeIsVector = type(eye) == "table" and eye.__type and eye.__type == "vector3";
		local lookIsVector = type(look) == "table" and look.__type and look.__type == "vector3";
		if (not eyeIsVector and not lookIsVector) then
			local t = type(eye);
			local cust = t == "table" and eye.__type or t;
			error("bad argument #1 to 'new' (Vector3 expected, got" .. cust .. ")");
		end;

		local zaxis = (eye - look).unit;
		local xaxis = top:Cross(zaxis).unit;
		local yaxis = zaxis:Cross(xaxis).unit;
		if (xaxis.magnitude == 0) then -- edge cases
			if zaxis.y < 0 then
				xaxis = Vector3.new(0, 0, -1);
				yaxis = Vector3.new(1, 0, 0);
				zaxis = Vector3.new(0, -1, 0);
			else
				xaxis = Vector3.new(0, 0, 1);
				yaxis = Vector3.new(1, 0, 0);
				zaxis = Vector3.new(0, 1, 0);
			end;
		end;
		self.proxy.p = Vector3.new(eye.x, eye.y, eye.z);
		self.proxy.m11, self.proxy.m12, self.proxy.m13 = xaxis.x, yaxis.x, zaxis.x;
		self.proxy.m21, self.proxy.m22, self.proxy.m23 = xaxis.y, yaxis.y, zaxis.y;
		self.proxy.m31, self.proxy.m32, self.proxy.m33 = xaxis.z, yaxis.z, zaxis.z;
         */
        Vector3 zaxis = (a.sub(b)).normalize(),
                xaxis = new Vector3(0,1,0).cross(zaxis).normalize(),
                yaxis = zaxis.cross((xaxis)).normalize();
        if (xaxis.magnitude() == 0 ) {
            if (zaxis.y == 0) {
                xaxis = new Vector3(0, 0, -1);
                yaxis = new Vector3(1, 0, 0);
                zaxis = new Vector3(0, -1, 0);
            } else {
                xaxis = new Vector3(0, 0, 1);
                yaxis = new Vector3(1, 0, 0);
                zaxis = new Vector3(0, 1, 0);
            }
        }
        //local right = Vector3.new(m11, m21, m31) -- This is the same as cf.rightVector
        //local up = Vector3.new(m12, m22, m32) -- This is the same as cf.upVector
        //local back = Vector3.new(m13, m23, m33) -- This is the same as -cf.lookVector
        this.Position = new Vector3(a.x,a.y,a.z);
        this.RightVector = new Vector3(xaxis.x,xaxis.y, xaxis.z);
        this.UpVector = new Vector3(yaxis.x,yaxis.y, yaxis.z);
        this.BackVector = new Vector3(zaxis.x,zaxis.y, zaxis.z);
    }

    public CFrame mul(CFrame other) {
        double[] a = this.getComponents();
        double[] b = other.getComponents();
        double ax=a[0],ay=a[1],az=a[2],a11=a[3],a12=a[4],a13=a[5],a21=a[6],a22=a[7],a23=a[8],a31=a[9],a32=a[10],a33=a[11];
        double bx=b[0],by=b[1],bz=b[2],b11=b[3],b12=b[4],b13=b[5],b21=b[6],b22=b[7],b23=b[8],b31=b[9],b32=b[10],b33=b[11];
        //x,y,z,m11,m12,m13,m21,m22,m23,m31,m32,m33
        //0,1,2,3   ,4  ,5  ,6  ,7  ,8  ,9  ,10 ,11
        //double m11 = a[3]*b[3]+a[4]
        /*double m11 = a[3] * b[3] + a[4] * b[6] + a[5] * b[9];
        double m12 = a[3] * b[4] + a[4] * b[7] + a[5] * b[10];
        double m13 = a[3] * b[5] + a[4] * b[8] + a[5] * b[11];
        double x = a[3] * b[0] + a[4] * b[1] + a[5] * b[2] + a[0];
        double m21 = a[6] * b[3] + a[7] * b[6] + a[8] * b[9];
        double m22 = a[6] * b[4] + a[7] * b[7] + a[8] * b[10];
        double m23 = a[6] * b[5] + a[7] * b[8] + a[8] * b[11];
        double y = a[6] * b[0] + a[7] * b[1] + a[8] * b[2] + a[1];
        double m31 = a[9] * b[3] + a[10] * b[6] + a[11] * b[9];
        double m32 = a[9] * b[4] + a[10] * b[7] + a[11] * b[10];
        double m33 = a[9] * b[5] + a[10] * b[8] + a[11] * b[11];
        double z = a[9] * b[0] + a[10] * b[1] + a[11] * b[2] + a[2];
        double m11 = a[3]*b[3]+a[4]*b[6]+a[5]*b[9];
        double m12 = a[3]*b[4]+a[4]*b[7]+a[5]*b[10];
        double m13 = a[3]*b[5]+a[4]*b[8]+a[5]*b[11];
        double x = a[3]*b[0]+a[4]*b[1]+a[5]*b[2]+a[0];
        double m21 = a[6]*b[3]+a[7]*b[6]+a[8]*b[9];
        double m22 = a[6]*b[4]+a[7]*b[7]+a[8]*b[10];
        double m23 = a[6]*b[5]+a[7]*b[8]+a[8]*b[11];
        double y = a[6]*b[0]+a[7]*b[1]+a[8]*b[2]+a[1];
        double m31 = a[9]*b[3]+a[10]*b[6]+a[11]*b[9];
        double m32 = a[9]*b[4]+a[10]*b[7]+a[11]*b[10];
        double m33 = a[9]*b[5]+a[10]*b[8]+a[11]*b[11];
        double z = a[9]*b[0]+a[10]*b[1]+a[11]*b[2]+a[2];*/
        double m11 = a11*b11+a12*b21+a13*b31;
        double m12 = a11*b12+a12*b22+a13*b32;
        double m13 = a11*b13+a12*b23+a13*b33;
        double x = a11*bx+a12*by+a13*bz+ax;
        //System.out.println(a11*bx+" "+a12*bx+" "+a13*bz);
        double m21 = a21*b11+a22*b21+a23*b31;
        double m22 = a21*b12+a22*b22+a23*b32;
        double m23 = a21*b13+a22*b23+a23*b33;
        double y = a21*bx+a22*by+a23*bz+ay;
        double m31 = a31*b11+a32*b21+a33*b31;
        double m32 = a31*b12+a32*b22+a33*b32;
        double m33 = a31*b13+a32*b23+a33*b33;
        double z = a31*bx+a32*by+a33*bz+az;
        //System.out.println(ax+" "+ay+" "+az+" "+a11+" "+a12+" "+a13+" "+a21+" "+a22+" "+a23+" "+a31+" "+a32+" "+a33);
        //System.out.println(bx+" "+by+" "+bz+" "+b11+" "+b12+" "+b13+" "+b21+" "+b22+" "+b23+" "+b31+" "+b32+" "+b33);
       // System.out.println(x+" "+y+" "+z+" "+m11+" "+m12+" "+m13+" "+m21+" "+m22+" "+m23+" "+m31+" "+m32+" "+m33);

        return new CFrame(x, y, z, m11, m12, m13, m21, m22, m23, m31, m32, m33);
    }

    public Vector3 mul(Vector3 other) {
        double[] a = this.getComponents();
        double nx = a[3] * other.x + a[4] * other.y + a[5] * other.x + a[0];
        double ny = a[6] * other.x + a[7] * other.y + a[8] * other.x + a[1];
        double nz = a[9] * other.x + a[10] * other.y + a[11] * other.x + a[2];
        return new Vector3(nx, ny, nz);
    }


    public double[] getComponents() {
        //-- m11, m12, m13,
        //-- m21, m22, m23,
        //-- m31, m32, m33
        //
        //local right = Vector3.new(m11, m21, m31) -- This is the same as cf.rightVector
        //local up = Vector3.new(m12, m22, m32) -- This is the same as cf.upVector
        //local back = Vector3.new(m13, m23, m33) -- This is the same as -cf.lookVector
        return new double[]{this.Position.x, this.Position.y, this.Position.z,
                this.BackVector.x, this.UpVector.x,this.RightVector.x,
                this.BackVector.y, this.UpVector.y,this.RightVector.y,
                this.BackVector.z, this.UpVector.z,this.RightVector.z};
    }
}
