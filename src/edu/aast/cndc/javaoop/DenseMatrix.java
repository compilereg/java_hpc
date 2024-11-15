package edu.aast.cndc.javaoop;

import java.time.LocalTime;
import java.time.Duration;
import java.util.Random;

public class DenseMatrix {
	private double[][] matrix;
	int rows,cols;
	final double rangeMin,rangeMax;
	
	//Time taken after certain operation. Calculated inside the operation method
	private Duration t2_1;
	private double ElapsedTime=0;
	
	//Default constructor
	DenseMatrix() {
		this(3,3);
	}

	//Overloaded constructore recieves of cols, as long  as rows
	DenseMatrix(int rows,int cols) {
		this.rows = rows;
		this.cols = cols;
		matrix = new double[rows][cols];
		this.rangeMin = 0;
		this.rangeMax = 1000;
		InitMatrix(); 
	}
	
	
	//Overloaded constructore recieves matrix size, rows = cols
	DenseMatrix(int rows) {
		this.rows = rows;
		this.cols = rows;
		matrix = new double[rows][cols];
		this.rangeMin = 0;
		this.rangeMax = 1000;
		InitMatrix(); 
	}
	
	//Initialize the matrix with random value
	public void InitMatrix() {
		int i,j;
		Random r = new Random();
		for(i=0;i<rows;i++)
			for(j=0;j<cols;j++)
				this.matrix[i][j]=rangeMin + (rangeMax - rangeMin) * r.nextDouble();;
	}
	
	//Initialize the matrix with certiain double value
	public void InitMatrix(double v) {
		int i,j;
		for(i=0;i<rows;i++)
			for(j=0;j<cols;j++)
				this.matrix[i][j]=v;
	}
	
	
	//Print the matrix
	public void PrintMatrix() {
		int i,j;
		for(i=0;i<rows;i++)  {
			for(j=0;j<cols;j++) 
				System.out.print(this.matrix[i][j] +  " ");
			System.out.println("");
		}		
	}
	
	//Multiply a matrix in sequential using normal matrix mult
	public DenseMatrix MultiplySeq(DenseMatrix b) {
		int i,j,k;
		DenseMatrix outm;
		outm=new DenseMatrix(this.rows,this.cols);
		outm.InitMatrix(0);
		
		LocalTime t1 = LocalTime.now();
		for(i=0;i<this.rows;i++) 
			for(j=0;j<this.cols;j++)
				for(k=0;k<this.rows;k++)
					outm.matrix[i][j] += this.matrix[i][k] * b.matrix[k][j];	
		LocalTime t2 = LocalTime.now();
		this.t2_1 = Duration.between(t1, t2);
/*		if ( t2_1 == null )
			System.out.println("Null time duration");
		else*/
			this.ElapsedTime=this.t2_1.getSeconds();
		return outm;
	}
	
	public double getElapsedTime() {
		return this.ElapsedTime;
	}
}
