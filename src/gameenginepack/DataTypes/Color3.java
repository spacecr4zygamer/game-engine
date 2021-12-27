package gameenginepack.DataTypes;

public class Color3 {

    private short r,g,b;

    private static final String[] Converts = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};
    public static final Color3
            White = new Color3(255,255,255),
            Black = new Color3(0,0,0);

    public static String getHex(short Decimal) {
        /*while (Decimal.shortValue() > 0) {
            float Result = (Decimal.floatValue())/16;
            short meh = (short) Result;
            float Rest = Result-meh;
            sb.append(Converts[(int) (Rest*16)]);
            Decimal=meh;
        }*/
        String resultstring = "";
        String resultone;
        {
            int Rest = Math.floorMod(Decimal,16);
            Decimal=(short) Math.floorDiv(Decimal,16);
        if (Rest>0) {
            //sb.append(Converts[(int) (Rest * 16)]);
            resultone = Converts[Rest];
        } else {
            //sb.append(Converts[0]);
            resultone = "0";}
        }
        {
            int Rest = Math.floorMod(Decimal, 16);
            if (Rest > 0) {
                resultstring = resultstring + Converts[Rest] + resultone;
            } else {
                resultstring = resultstring + "0" + resultone;
            }
        }
        //resultstring = resultstring+""+resultone;
        return resultstring;
    }

    public String encodetoHex() {
        String hexcode = "#"+getHex(this.r)+""+getHex(this.g)+""+getHex(this.b);
        return hexcode;
    }

    private Number clamp(Number num,Number max,Number min) {
        return Math.max(min.shortValue(),Math.min(max.shortValue(),num.shortValue()));
    }

    public Color3(Number r, Number g, Number b) {
        this.r = clamp(r,255,0).shortValue();
        this.g = clamp(g,255,0).shortValue();
        this.b = clamp(b,255,0).shortValue();
    }

    public static Color3 Lerp(Color3 begin, Color3 end, double alpha) {
        short newr = (short) (begin.r+(end.r-begin.r)*alpha);
        short newg = (short) (begin.g+(end.g-begin.g)*alpha);
        short newb = (short) (begin.b+(end.b-begin.b)*alpha);

        return new Color3(newr,newg,newb);
    }

    public Color3 lerp(Color3 other, double alpha) {
        short newr = (short) (this.r+(other.r-this.r)*alpha);
        short newg = (short) (this.g+(other.g-this.g)*alpha);
        short newb = (short) (this.b+(other.b-this.b)*alpha);

        return new Color3(newr,newg,newb);
    }

}
