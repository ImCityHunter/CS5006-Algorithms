/*
 * 	select.cpp
 *
 *  Created on: Mar 18, 2019
 *      Author: hky
 */

#include <iostream>

#include <cstdio>
#include <cstddef>
#include "CUnit/CUnit.h"
#include "CUnit/Basic.h"
using namespace std;

//int main() {
//	cout << "Hello UPC World" << endl;
//	return 0;
//}


/**
 * A utility function to swap two values.
 * @param v1 the first value
 * @param v2 the second index
 */
void swap (int &v1, int& v2 ) {
    int t = v1;
    v1 = v2;
    v2 = t;
}

/**
 * This function places the last element as pivot at
 * its correct position in sorted array 'a'
 * array, and places all smaller (smaller than pivot)
 * to left of pivot and all greater elements to right
 * of pivot.
 * @param a the array to be partitioned
 * @param lo starting index
 * @param hi ending index
 * @return the partitioning index
 */
int partition (int a[], int lo, int hi) {
    int &pv = a[hi];
    int i = lo-1;

    for (int j = lo; j < hi; j++) {
        if (a[j] <= pv) {
            swap(a[++i], a[j]);
        }
    }
    swap(a[++i], pv);
    return i;
}

/**
 * This function selects the kth smallest itmem from the
 * input array 'a' between the lower index 'lo' and the
 * higher index 'hi'.
 *
 * @param a array to be selected,
 * @param lo lower index
 * @param hi higher index
 * @param k kth smallest item index
 * @return the kth smallest item
 */
int select(int a[], int lo, int hi, int k) {

	//return INT_MIN if k is out of bound
	if(k > hi || k<0) return INT_MIN;

	//if (!base condition)
    while(lo<=hi){
    	 int p = partition(a,lo,hi);
    	 if(k == p) return a[k]; //value is found
    	 else if(p > k)
    		 //if p is too big, decrement hi.
    		 //re-assign parameter
    		 hi = p - 1;
    	 else lo = p + 1; //re-assign parameter


     }

    //if k is not found, then return INT_MIN
     return INT_MIN;



 }

/**
 * This function selects the kth smallest itmem from the
 * input array 'a'.
 *
 * @param a array to be selected,
 * @param n number of items in array
 * @param k kth smallest item index
 * @return the kth smallest item
 */

int select(int a[], int n, int k) {
    return select(a, 0, n-1, k);
}

/*
 * Test Select Iteration
 */
static void testSelect(){
	int arr_01 []= {16, 3, 4, 5, 7};
	int sort_01 []= { 3, 4, 5, 7, 16};
	int arr_01_size = 5;
	int kthNum = 3;
	int test_01 = select(arr_01, 0, arr_01_size-1, kthNum);

	//test function, select
	CU_ASSERT_EQUAL(test_01,7);
	for(int i = 0;i<arr_01_size;i++){
		CU_ASSERT_EQUAL(sort_01[i],arr_01[i]);
	}

	int arr_02 [] = {-1,5,3,4,6};
	int sort_02[] = {-1,3,4,5,6};
	int arr_02_size = 5;
	kthNum = 1;
	int test_02 = select(arr_02,0,arr_02_size-1,kthNum);

	//test function, select
	CU_ASSERT_EQUAL(test_02, 3);
	for(int i = 0;i<arr_02_size;i++){
		CU_ASSERT_EQUAL(sort_02[i],arr_02[i]);
	}

	//test function with kthNum out of bound
	kthNum = 10;
	int test_03 = select(arr_02,0,arr_02_size-1,kthNum);
	CU_ASSERT_EQUAL(test_03, INT_MIN);




}


static CU_ErrorCode test_all() {
	// initialize the CUnit test registry -- only once per application
	CU_initialize_registry();

	// add a suite to the registry with no init or cleanup
	CU_pSuite pSuite = CU_add_suite("function_tests", NULL, NULL);

	// add the tests to the suite
	CU_add_test(pSuite, "test_withCUnit", testSelect);


	// run all test suites using the basic interface
	CU_basic_set_mode(CU_BRM_VERBOSE);
	CU_basic_run_tests();

	// display information on failures that occurred
	CU_basic_show_failures(CU_get_failure_list());

	// Clean up registry and return status
	CU_cleanup_registry();
	return CU_get_error();
}

int main() {
	 //CS_5006::testSelect();
	 CU_ErrorCode code = test_all();
	 return (code == CUE_SUCCESS) ? EXIT_SUCCESS : EXIT_FAILURE;
}


