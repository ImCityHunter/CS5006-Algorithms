/*
 * problem1.cpp
 *
 * CS 5006 Final Exam
 * Problem 1
 *
 * The function power_nt() below, which is not tail-recursive,
 * computes single-precision X raised to the unsigned int power N.
 *
 * This problem is to:
 *
 * 1) create an equivalent function power_tr that transforms
 * power_nt() to be strictly tail recursive using the technique
 * shown in the module 2 lecture and class notes
 *
 * 2) create an equivalent function power_it that transforms
 * power_tr() to be iterative using the technique shown in the
 * module 2 lecture and class notes.
 *
 * Note: this problem will be graded entirely on the application
 * of the techniques shown in the module2 lecture and class notes.
 * In other words, this problem is all about the techniques. Work
 * it out yourself: a function from the web will not serve the
 * purpose.
 *
 * Include a description in the function headers of how you used
 * the techniques to transform the non tail-recursive to the tail-
 * recursive form and the tail-recursive to the iterative form.
 * Also use internal comments to provide specific details.
 *
 * Do not clean up the code as it will obscure how you applied
 * the techniques.
 */
#include <stdio.h>
#include <stdlib.h>

/**
 * Computes X raised to power N. Tail Recursive
 *
 * @param x the base
 * @param n the exponent
 * @return x raised to the power n
 */
float power_nt(float x, unsigned int n) {
	if (n == 0) {
		// base case
		return 1;
	} else if (n%2) {
		// transform to even exponent
		return x * power_nt(x, n-1);
	} else {
		// reduce by factor of 2 each time
		return power_nt(x*x, n/2);
	}
}

/**
 * Computes X raised to power N. This form is tail recursive.
 *
 * @param x the base
 * @param n the exponent
 * @return x raised to the power n
 */
float power_tr(float x, unsigned int n, float accu=1) {

	/*
	 * In tail recursion, the function makes at most a single recursive call
	 * and will return immediately after the call with no further calculations performed on return.
	 */

	//base case (n==0), if n=0 on first try, then accu = 1;
	if(n==0) return accu;
//	else return power_tr(x,n-1,x*accu);

	//update accu
	else if(n%2) return power_tr(x,n-1,x*accu);

	//reduce by 2, update x
	else return power_tr(x*x,n/2,accu);


}

/**
 * Computes X raised to power N. This is iteration
 *
 * @param x the base
 * @param n the exponent
 * @return x raised to the power n
 */
float power_it(float x, unsigned int n) {
	float accu = 1; //set default accu = 1

	//invert base condition
	while(n!=0){
		accu*=x; //update accu
		n--;
	}
	return accu;
}

/**
 * Exercise the three versions of the power function.
 */
int main() {
	printf("CS 5006 Problem 1\n");
	float x = -5.5;
	float nt=0, tr=0, it=0;
	for (unsigned int n = 0; n < 5; n++) {
		nt = power_nt(x,n);  // non tail-recursive
		tr = power_tr(x,n);  // tail recursive
		it = power_it(x,n);  // iterative

		printf("power(%f,%d): nt=%f tr=%f it=%f\n", x, n, nt, tr, it);
	}
}
