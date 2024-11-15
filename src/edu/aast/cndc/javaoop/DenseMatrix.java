package edu.aast.cndc.javaoop;


import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DenseMatrix {
	private double[][] matrix;
	int rows,cols;
	final double rangeMin,rangeMax;
	
	//Time taken after certain operation. Calculated inside the operation method
	
	private double ElapsedTime;
	
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
		this.ElapsedTime = 0;
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
		Date startTime,endTime;
		DenseMatrix outm;
		outm=new DenseMatrix(this.rows,this.cols);
		outm.InitMatrix(0);
		startTime=Calendar.getInstance().getTime();
		for(i=0;i<this.rows;i++) 
			for(j=0;j<this.cols;j++)
				for(k=0;k<this.rows;k++)
					outm.matrix[i][j] += this.matrix[i][k] * b.matrix[k][j];	
		endTime=Calendar.getInstance().getTime();
		outm.ElapsedTime=( endTime.getTime() - startTime.getTime() ) / 1000 % 60;
		return outm;
	}
	
	//Multiply a matrix in sequential using blocked matrix mult
	public DenseMatrix MultiplySeqBlocked(DenseMatrix b,int bs) {
		int i,j,k;
		int ii,jj,kk;
		Date startTime,endTime;
		DenseMatrix outm;
		outm=new DenseMatrix(this.rows,this.cols);
		outm.InitMatrix(0);
		startTime=Calendar.getInstance().getTime();
		for(ii=0;ii<this.rows;ii+=bs)
			for(jj=0;jj<this.cols;jj+=bs)
				for(kk=0;kk<this.rows;kk+=bs)
					for(i=ii;i<(ii+bs);i++)
						for(j=jj;j<(jj+bs);j++)
							for(k=kk;k<(kk+bs);k++)
								outm.matrix[i][j]+=this.matrix[i][k]*b.matrix[k][j];
		endTime=Calendar.getInstance().getTime();
		outm.ElapsedTime=( endTime.getTime() - startTime.getTime() ) / 1000 % 60;
		return outm;
	}

	
	public double getElapsedTime() {
		return this.ElapsedTime;
	}
}
