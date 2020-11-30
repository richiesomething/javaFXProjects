package labs;

public class Fraction {

    public static double[] vals = {.5, .25, .125, .0625, .03125};


    public static void main(String[] args) {

        int[] a = {0,0,0,0,0};
        int[] b = {0,0,0,0,1};
        int[] c = {0,0,0,1,0};
        int[] d = {0,0,0,1,1};
        int[] e = {0,0,1,0,0};
        int[] f = {0,0,1,0,1};
        int[] g = {0,0,1,1,0};
        int[] h = {0,0,1,1,1};
        int[] i = {0,1,0,0,0};
        int[] j = {0,1,0,0,1};
        int[] k = {0,1,0,1,0};
        int[] l = {0,1,0,1,1};
        int[] m = {0,1,1,0,0};
        int[] n = {0,1,1,0,1};
        int[] o = {0,1,1,1,0};
        int[] p = {0,1,1,1,1};
        int[] q = {1,0,0,0,0};
        int[] r = {1,0,0,0,1};
        int[] s = {1,0,0,1,0};
        int[] t = {1,0,0,1,1};
        int[] u = {1,0,1,0,0};
        int[] v = {1,0,1,0,1};
        int[] w = {1,0,1,1,0};
        int[] x = {1,0,1,1,1};
        int[] y = {1,1,0,0,0};
        int[] z = {1,1,0,0,1};
    int[] two7 =  {1,1,0,1,0};
    int[] two8 =  {1,1,0,1,1};
    int[] two9 =  {1,1,1,0,0};
    int[] three0 ={1,1,1,0,1};
    int[] three1 ={1,1,1,1,0};
    int[] three2 ={1,1,1,1,1};


        System.out.println(m(a));
        System.out.println(m(b));
        System.out.println(m(c));
        System.out.println(m(d));
        System.out.println(m(e));
        System.out.println(m(f));
        System.out.println(m(g));
        System.out.println(m(h));
        System.out.println(m(i));
        System.out.println(m(j));
        System.out.println(m(k));
        System.out.println(m(l));
        System.out.println(m(m));
        System.out.println(m(n));
        System.out.println(m(o));
        System.out.println(m(p));
        System.out.println(m(q));
        System.out.println(m(r));
        System.out.println(m(s));
        System.out.println(m(t));
        System.out.println(m(u));
        System.out.println(m(v));
        System.out.println(m(w));
        System.out.println(m(x));
        System.out.println(m(y));
        System.out.println(m(z));
        System.out.println(m(two7));
        System.out.println(m(two8));
        System.out.println(m(two9));
        System.out.println(m(three0));
        System.out.println(m(three1));
        System.out.println(m(three2));


    }


    public static double m(int[] a) {

        double sum = 0;
        for (int i = 0; i < 5; i++)
            sum += vals[i] * a[i];

        return sum;
    }

}
