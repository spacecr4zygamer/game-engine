package gameenginepack;

import java.util.ArrayList;

public class Color3 {

    private short r,g,b;

    private static final String[] Converts = {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"};

    public static String getHex(Number Decimal) {
        StringBuilder sb = new StringBuilder("");
        while (Decimal.shortValue() > 0) {
            float Result = (Decimal.floatValue())/16;
            short meh = (short) Result;
            float Rest = Result-meh;
            sb.append(Converts[(int) (Rest*16)]);
            Decimal=meh;
        }
        sb.reverse();
        return sb.toString();
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

    public Color3 lerp(Color3 other, double alpha) {
        short newr = (short) (this.r+(other.r-this.r)*alpha);
        short newg = (short) (this.g+(other.g-this.g)*alpha);
        short newb = (short) (this.b+(other.b-this.b)*alpha);

        return new Color3(newr,newg,newb);
    }

}
