/* Name : Hardi Chandra */
/* Student ID : 983722 */
/* Project 4 Bucket Sort Version3 */
// Project4-BucketSortVersion3.cpp : Defines the entry point for the console application.
//

/* includes */
#include "stdafx.h"
#include "omp.h"
#include "stdlib.h"
#include "time.h"

#define n 10000000 /*length of list*/
#define bsize 1000000 /*size of buckets*/
#define m 100 /*number of buckets*/
int list[n]; /*unsorted list of integers*/
int final[n]; /*sorted list of integers*/
int bucket[m][bsize]; /*buckets*/
int count[m]; /*number of items stored in bucket*/
clock_t t;
omp_lock_t L;

/*function to compare two integers*/
/* returns -1 if p < q, 0 if p = q, 1 if p > q */
int lt(const void *p, const void *q) 
{
	return (*(int *)p - *(int *)q);
}

int _tmain(int argc, _TCHAR* argv[])
{
	int i, j, range, maxval=0, minval=32767, bnum;
	/*initialize the list*/	
	t = clock( ); /* time now */
	for ( i = 0; i < n; i++ ) 
		list[i] = rand( );
	for ( i = 0; i < m; i++ ) 
		count[m] = 0;

	/* print the random result
	printf("Randomize = \n");
	for ( i = 0; i < n; i++ ) 
	{
		if(i%10==0)
			printf("\n");
		printf("%6d",list[i]);
	}
	printf("\n\n");
	/* end print the random result */

	/* Distribution phase */
	for ( i = 0; i < n; i++ ) 
	{
		if(list[i] > maxval)
			maxval=list[i];
		if(list[i] < minval)
			minval=list[i];
	}
	range = maxval - minval + 1;

	omp_set_num_threads(omp_get_num_procs( ));
	#pragma omp parallel for private(j, bnum)
	for ( i = 0; i < m; i++ ) 
	{
		for( j=0; j < n / m; j++ )
		{
			bnum = (int)((float)m * ((float)(list[i * n / m + j]-minval)/range));
			bucket[bnum][count[bnum]]=list[i * n / m + j];
			count[bnum]++;
		}
	}
	/* end Distribution phase */

	/* sort each bucket phase */
	omp_set_num_threads(omp_get_num_procs( ));
	#pragma omp parallel for
	for ( i = 0; i < m; i++ ) 
		qsort(bucket[i], count[i], sizeof(int), lt );
	/* end sort each bucket phase */

	/* print sorted result for each bucket 
	for ( i = 0; i < m; i++ ) 
	{
		printf("Bucket %d = %d",i, count[i]);
		for ( j = 0; j < n; j++ ) 
		{
			if(j%10==0)
				printf("\n");
			printf("%6d",bucket[i][j]);
		}
		printf("\n");
	}
	/* end print result */

	/* merging phase */
	omp_set_num_threads(omp_get_num_procs( ));
	#pragma omp parallel for private(j)
	for ( i = 0; i < m; i++ )
	{
		int index=0;
		for( j = 0; j < i ; j++)
			index=index+count[i-j-1];
		for ( j = 0; j < count[i]; j++)
			final[index+j]=bucket[i][j];
	}

	/* print the final sorted result 
	printf("\n\n");
	printf("Final = \n");
	for ( i = 0; i < n; i++ ) 
	{
		if(i%10==0)
			printf("\n");
		printf("%6d",final[i]);
	}
	printf("\n\n");
	/* end print the final sorted result */

	printf("/************************************/\n");
	printf("/*   Parallel Version3 (No Lock)    */\n");
	printf("/************************************/\n");
	printf("Execution Time: %d \n", (clock( )-t));
	getchar();
	return 0;
}

