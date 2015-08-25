public class Matrix {

	/**
	 * Returns a random m-by-n matrix with values between 0 and 1
	 *
	 * @param m
	 *            dimension
	 * @param n
	 *            dimension
	 */
	public static double[][] random(int m, int n) {
		double[][] matrix = new double[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				matrix[i][j] = Math.random();
		return matrix;
	}

	/**
	 * Returns n-by-n identity matrix I
	 *
	 * @param n
	 *            dimension
	 */
	public static double[][] identity(int n) {
		double[][] I = new double[n][n];
		for (int i = 0; i < n; i++)
			I[i][i] = 1;
		return I;
	}

	/**
	 * Returns transpose(x) . y
	 *
	 * @param x
	 *            vector
	 * @param y
	 *            vector
	 */
	public static double dot(double[] x, double[] y) {
		if (x.length != y.length)
			throw new RuntimeException("Illegal vector dimensions.");
		double sum = 0.0;
		for (int i = 0; i < x.length; i++)
			sum += x[i] * y[i];
		return sum;
	}

	/**
	 * Returns transpose(A)
	 *
	 * @param A
	 *            matrix
	 */
	public static double[][] transpose(double[][] A) {
		int m = A.length;
		int n = A[0].length;
		double[][] C = new double[n][m];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				C[j][i] = A[i][j];
		return C;
	}

	/**
	 * Returns A + B
	 *
	 * @param A
	 *            matrix
	 * @param B
	 *            matrix
	 */
	public static double[][] add(double[][] A, double[][] B) {
		int m = A.length;
		int n = A[0].length;
		double[][] C = new double[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] + B[i][j];
		return C;
	}

	/**
	 * Returns A - B
	 *
	 * @param A
	 *            matrix
	 * @param B
	 *            matrix
	 */
	public static double[][] subtract(double[][] A, double[][] B) {
		int m = A.length;
		int n = A[0].length;
		double[][] C = new double[m][n];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				C[i][j] = A[i][j] - B[i][j];
		return C;
	}

	/**
	 * Returns A * B
	 *
	 * @param A
	 *            matrix
	 * @param B
	 *            matrix
	 */
	public static double[][] multiply(double[][] A, double[][] B) {
		int mA = A.length;
		int nA = A[0].length;
		int mB = B.length;
		int nB = B[0].length;
		if (nA != mB)
			throw new RuntimeException("Illegal matrix dimensions.");
		double[][] C = new double[mA][nB];
		for (int i = 0; i < mA; i++)
			for (int j = 0; j < nB; j++)
				for (int k = 0; k < nA; k++)
					C[i][j] += A[i][k] * B[k][j];
		return C;
	}

	/**
	 * Returns A * x
	 *
	 * @param A
	 *            matrix
	 * @param x
	 *            vector
	 */
	public static double[] multiply(double[][] A, double[] x) {
		int m = A.length;
		int n = A[0].length;
		if (x.length != n)
			throw new RuntimeException("Illegal matrix dimensions.");
		double[] y = new double[m];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				y[i] += A[i][j] * x[j];
		return y;
	}

	/**
	 * Returns x * A
	 *
	 * @param A
	 *            matrix
	 * @param x
	 *            vector
	 */
	public static double[] multiply(double[] x, double[][] A) {
		int m = A.length;
		int n = A[0].length;
		if (x.length != m)
			throw new RuntimeException("Illegal matrix dimensions.");
		double[] y = new double[n];
		for (int j = 0; j < n; j++)
			for (int i = 0; i < m; i++)
				y[j] += A[i][j] * x[i];
		return y;
	}
	
	/**
	 * Returns A ^ n
	 *
	 * @param A
	 *            matrix
	 * @param n
	 *            power
	 */
	public static double[][] power(double[][] A, int n) {
	    if(n == 1) {
	    	return A;
	    } else if(n % 2 == 1) {
	    	return multiply(A, power(A, n - 1));
	    } else {
	    	double[][] B = power(A, n / 2);
		    return multiply(B, B);
	    }
	}
}
