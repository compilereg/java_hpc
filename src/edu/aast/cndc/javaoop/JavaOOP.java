package edu.aast.cndc.javaoop;


public class JavaOOP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DenseMatrix a,b,c;
		System.out.println("Start .. ");
		a=new DenseMatrix(1500);
		b=new DenseMatrix(1500);
		c=new DenseMatrix(1500);
		a.InitMatrix(1);
		b.InitMatrix(2);
/*
		System.out.println("A matrix : ");
		a.PrintMatrix();
		System.out.println("B matrix : ");
		b.PrintMatrix();
		*/
		c=a.MultiplySeq(b);
/*		
		System.out.println("C After : ");
		c.PrintMatrix();
*/
		System.out.println("Tooks times: " + c.getElapsedTime() + " seconds");
		


	}

}
