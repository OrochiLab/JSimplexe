/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package simplexe;

/**
 *
 * @author Yunho
 */
public strictfp class NumberToFraction {
    
    public static void main(String[] main)
    {
        double number = 0.666;
        convert(number);
    }
    
    public static void convert(double number)
    {
         
// Code below doesn't work for 0 and NaN - just check before

long bits = Double.doubleToLongBits(number);

long sign = bits >>> 63;
long exponent = ((bits >>> 52) ^ (sign << 11)) - 1023;
long fraction = bits << 12; // bits are "reversed" but that's not a problem

long a = 1L;
long b = 1L;

for (int i = 63; i >= 12; i--) {
    a = a * 2 + ((fraction >>> i) & 1);
    b *= 2;
}

if (exponent > 0)
    a *= 1 << exponent;
else
    b *= 1 << -exponent;

if (sign == 1)
    a *= -1;

// Here you have to simplify the fraction

System.out.println("Resultat : "+simplifier(a,b));
    }
    
    public static long gcm(long a, long b) 
    {
    return b == 0 ? a : gcm(b, a % b); 
    }

    public static String simplifier(long a, long b) 
    {
    long gcm = gcm(a, b);
    return (a / gcm) + "/" + (b / gcm);
    }
    
    
}
