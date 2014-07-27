/* Name : Hardi Chandra */
/* Student ID : 983722 */
/* Project 4 Bucket Sort Combine */
// Project4-BucketSortCombine.cpp : Defines the entry point for the console application.
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
clock_t t,t2,t3,t4,t5;
omp_lock_t L;
omp_lock_t L2[m];

/*function to compare two integers*/
/* returns -1 if p < q, 0 if p = q, 1 if p > q */
int lt(const void *p, const void *q) 
{
	return (*(int *)p - *(int *)q);
}

int _tmain(int argc, _TCHAR* argv[])
{
	int i, j, range, maxval, minval, bnum;

	/************************************/
	/*		sequential code             */
	/************************************/
	/*initialize the list*/	
	t = clock( ); /* time now */
	for ( i = 0; i < n; i++ ) 
		list[i] = rand( );
	for ( i = 0; i < m; i++ ) 
		count[m] = 0;

	/* Distribution phase */
	range = 32767 - 0 + 1;
	for ( i = 0; i < n; i++ ) 
	{
		/* determine destination bucket for list[i] */
		bnum = (int)((float)m * ((float)(list[i]-0)/range));
		bucket[bnum][count[bnum]]=list[i];
		count[bnum]++;
	}

	/* sort each bucket phase */
	for ( i = 0; i < m; i++ ) 
		qsort(bucket[i], count[i], sizeof(int), lt );
	
	/* merging phase */
	int cnt=0;
	for ( i = 0; i < m; i++ )
	{
		for ( j = 0; j < count[i]; j++)
		{
			final[j + cnt]=bucket[i][j];
		}
		cnt=cnt+count[i];
	}
	int seqtime=clock( )-t;
	printf("/************************************/\n");
	printf("/*        sequential code           */\n");
	printf("/************************************/\n");
	printf("Sequential Execution Time: %d \n", seqtime);
	printf("\n");



	/************************************/
	/*	version2 code (Multiple Lock)   */
	/************************************/
	t2 = clock( ); /* time now */
	for ( i = 0; i < n; i++ ) 
		list[i] = rand( );
	for ( i = 0; i < m; i++ ) 
		count[m] = 0;

	/* Distribution phase */
	range = 32767 - 0 + 1;
	
	for ( i = 0; i < m; i++ ) 
		omp_init_lock(&L2[i]);
	#pragma omp parallel for private(j,bnum)
	for ( i = 0; i < m; i++ ) 
	{
		for( j=0; j < n / m; j++ )
		{
			bnum = (int)((float)m * ((float)(list[i * n / m + j]-0)/range));
			bucket[bnum][count[bnum]]=list[i * n / m + j];
			omp_set_lock(&L2[bnum]);
			count[bnum]++;
			omp_unset_lock(&L2[bnum]);
		}
	}
	/* end Distribution phase */

	/* sort each bucket phase */
	#pragma omp parallel for
	for ( i = 0; i < m; i++ ) 
		qsort(bucket[i], count[i], sizeof(int), lt );
	/* end sort each bucket phase */

	/* merging phase */
	#pragma omp parallel for private(j)
	for ( i = 0; i < m; i++ )
	{
		int index=0;
		for( j = 0; j < i ; j++)
			index=index+count[i-j-1];
		for ( j = 0; j < count[i]; j++)
			final[index+j]=bucket[i][j];
	}

	printf("/************************************/\n");
	printf("/*  version2 code (Multiple Lock)   */\n");
	printf("/************************************/\n");
	printf("Parallel Version2 (Multiple Lock) Execution Time: %d \n", (clock( )-t2));
	printf("\n");
	return 0;
}

