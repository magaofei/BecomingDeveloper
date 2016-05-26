#include <stdio.h>
int main()
{
	int A[3][3] = {{1,2,3},{4,5,6},{7,8,9}};
	int B[3][4] = {{1,2,3,4},{4,5,6,7},{7,8,9,1}};
	int C[3][4];
	int m,i,j,k;
	for( i=0;i<3;i++)
	{
	for( j=0;j<4;j++)
	{
		 m=0;
	for( k=0;k<3;k++)
	{
     
	m= m+A[i][k]*B[k][j];
	}
	C[i][j] =m;
	}
	}
for( i=0;i<3;i++)
{
for( j=0;j<4;j++)
{
printf("%d  ",C[i][j]);
if(j==3)
{
printf("\n");
}

}
}

}