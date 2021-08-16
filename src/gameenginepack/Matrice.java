package gameenginepack;

public class Matrice {
    private final double[] Inside;
    private final int SizeX,SizeY;

    public Matrice(int SizeX,int SizeY) {
        this.SizeX = SizeX;
        this.SizeY = SizeY;
        this.Inside = new double[SizeX*SizeY];
    }

    public Matrice(int SizeX,int SizeY,double[] Inside) {
        this.SizeX = SizeX;
        this.SizeY = SizeY;
        this.Inside = Inside;
    }

    public int getSizeX() {
        return this.SizeX;
    }

    public int getSizeY() {
        return this.SizeY;
    }

    public double getValue(int X, int Y) {
        return this.Inside[X+Y*SizeX];
    }

    public double getValue(int Pos) {return this.Inside[Pos];}

    public void setValue(int X,int Y,int v) {
        this.Inside[X+Y*SizeX] = v;
    }

    public void setValue(int Pos,double Value) {this.Inside[Pos] = Value;}

    public int getTotalSize() {
        return this.SizeX*this.SizeY;
    }

    public void showMatrix() {
        for (int i=0;i<this.Inside.length;i++) {
            System.out.print(this.Inside[i]+"\t");
            if ((i+1)%this.SizeX==0) {
                System.out.print("\n");
            }
        }
    }

    public Matrice add(Matrice other) {
        if (this.getSizeX()==other.getSizeX()&&this.getSizeY()==other.getSizeY()) {
            int SizeX = this.getSizeX(),SizeY = this.getSizeY();
            Matrice Result = new Matrice(SizeX,SizeY);
            for (int i = 0;i<this.getTotalSize();i++) {
                Result.setValue(i,this.getValue(i)+other.getValue(i));
            }
            return Result;
        } else {
            System.out.println("This is a invalid Size Combination");
            return null;
        }
    }

    public Matrice sub(Matrice other) {
        if (this.getSizeX()==other.getSizeX()&&this.getSizeY()==other.getSizeY()) {
            int SizeX = this.getSizeX(),SizeY = this.getSizeY();
            Matrice Result = new Matrice(SizeX,SizeY);
            for (int i = 0;i<this.getTotalSize();i++) {
                Result.setValue(i,this.getValue(i)-other.getValue(i));
            }
            return Result;
        } else {
            System.out.println("This is a invalid Size Combination");
            return null;
        }
    }

    public Matrice mul(Matrice other) {
        if (this.getSizeX() == other.getSizeY()) {
            int SizeY = this.getSizeY(),SizeX = other.getSizeX();
            Matrice Result = new Matrice(SizeX,SizeY);
            for (int x=0;x<SizeX;x++) {
                for (int y=0;y<SizeY;y++) {
                    int temp = 0;
                    for (int os=0;os<this.getSizeX();os++) {
                        temp += (this.getValue(os,y)*other.getValue(x, os));
                    }
                    Result.setValue(x,y,temp);
                }
            }
            return Result;
        } else {
            System.out.println("This is a invalid Size Combination");
            return null;
        }
    }

}
