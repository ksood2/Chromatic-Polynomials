public class Polynomial {
    private int[] coef;   // coefficients p(x) = sum { coef[i] * x^i }
    private int degree;   // degree of polynomial (-1 for the zero polynomial)
    public Polynomial(int a, int b) {
        // a*x^b
        coef = new int[b+1];
        coef[b] = a;
        reduce();
    }
    private void reduce() {
        degree = 0;
        for (int i = coef.length - 1; i >= 0; i--) {
            if (coef[i] != 0) {
                degree = i;
                return;
            }
        }
    }
    public int getDegree() {
        return degree;
    }
    public int[] getCoef(){
        return coef;
    }
    public Polynomial add(Polynomial p) {
        Polynomial newP = new Polynomial(0, Math.max(this.degree, p.degree));
        for(int i = 0; i <= this.degree; i++){
            newP.coef[i] += this.coef[i];
        }
        for(int i = 0; i <= p.degree; i++){
            newP.coef[i] += p.coef[i];
        }
        newP.reduce();
        return newP;
    }
    public Polynomial subtract(Polynomial that) {
        Polynomial poly = new Polynomial(0, Math.max(this.degree, that.degree));
        for (int i = 0; i <= this.degree; i++){
            poly.coef[i] += this.coef[i];
        }
        for (int i = 0; i <= that.degree; i++){
            poly.coef[i] -= that.coef[i];
        }
        poly.reduce();
        return poly;
    }
    public int eval(int x) {
        int p = 0;
        for (int i = degree; i >= 0; i--){
            p += coef[i] * Math.pow(x, i);
        }
        return p;
    }
    @Override
    public String toString() {
        String s = "";
        for(int i = 0; i < this.degree+1; i++){
            if(i!=degree){
                s += coef[i] + "x^" + i + " + ";
            }
            else{
                s += coef[i] + "x^" + i;
            }
        }
        return s;
    }
    public void display(){
        System.out.println(this);
    }
}