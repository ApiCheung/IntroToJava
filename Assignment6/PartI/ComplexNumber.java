package Assignment6.PartI;
/**
 * @author Esmee Zhang
 * @date 10/22/20 5:34 下午
 * @projectName IntroToJava-NYU
 */

public class ComplexNumber extends Number implements Cloneable{
        double a;
        double b;

        public ComplexNumber(double a, double b){
                this.a = a;
                this.b = b;
        }

        public ComplexNumber(double a){
                this.a = a;
                this.b = 0;

        }

        public ComplexNumber(){
                this.a = 0;
                this.b = 0;
        }

        @Override
        public int intValue() {
                return 0;
        }

        @Override
        public long longValue() {
                return 0;
        }

        @Override
        public float floatValue() {
                return 0;
        }

        @Override
        public double doubleValue() {
                return 0;
        }

        public ComplexNumber add(ComplexNumber a){
                ComplexNumber res = new ComplexNumber(0, 0);
                res.setReal(this.getReal() + a.getReal());
                res.setImaginary(this.getImaginary() + a.getImaginary());

                return res;
        }

        public ComplexNumber subtract(ComplexNumber a){
                ComplexNumber res = new ComplexNumber(0, 0);
                res.setReal(this.getReal() - a.getReal());
                res.setImaginary(this.getImaginary() - a.getImaginary());

                return res;
        }

        public ComplexNumber multiply(ComplexNumber a){
                ComplexNumber res = new ComplexNumber(0, 0);
                double a_ = this.getReal();
                double b_ = this.getImaginary();
                double c_ = a.getReal();
                double d_ = a.getImaginary();

                res.setReal(((a_ * c_) - (b_ * d_)));
                res.setImaginary(((b_ * c_) + (a_ * d_)));

                return res;
        }

        public ComplexNumber divide(ComplexNumber a){
                ComplexNumber res = new ComplexNumber(0, 0);
                double a_ = this.getReal();
                double b_ = this.getImaginary();
                double c_ = a.getReal();
                double d_ = a.getImaginary();

                res.setReal((((a_ * c_) + (b_ * d_)) / (Math.pow(c_, 2) + Math.pow(d_, 2))));
                res.setImaginary((((b_ * c_) - (a_ * d_)) / (Math.pow(c_, 2) + Math.pow(d_, 2))));

                return res;
        }

        public double abs(){
               double res = Math.pow((Math.pow(this.getReal(), 2) + Math.pow(this.getImaginary(), 2)), 0.5);

               return res;
        }

        public double getReal() {
                return a;
        }

        public double getImaginary() {
                return b;
        }

        public void setReal(double a) {
                this.a = a;
        }

        public void setImaginary(double b) {
                this.b = b;
        }

        @Override
        public String toString() {

                return b != 0 ?
                        " " + String.format("%.2f",a) + " + " +
                        " " + String.format("%.2f",b) +
                        '!' :
                        " " + String.format("%.2f",a);
        }


        @Override
        protected ComplexNumber clone(){
             ComplexNumber res = new ComplexNumber(0, 0);
             res.setReal(this.getReal());
             res.setImaginary(this.getImaginary());

             return res;
        }
}
