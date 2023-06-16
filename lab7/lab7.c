#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc,char * argv[]){
char data[100];
char filename[100];
char name[100];
char number[100];
char date[100];
char stockName[100];
char shares[100];
char price[100];
char Lprice[100];
char Hprice[100];
char Cprice[100];
char h[100];
char temp[100];
char temp1[100];
char temp2[100];
int i = 1;
FILE * ptr;

if(argc<=1){
	printf("%s\n","invalid argument" );
}else{
	strcpy (filename, argv[1]);
	ptr = fopen(filename,"r");
	int sNumber=0;
	float Oprice;
	float Clprice;
	float rev;
	while(fgets(data,sizeof(data),ptr)!= NULL){
		sscanf(data, "%s %s \t %s \t %[^\n]", temp,temp1,temp2,&h);
		if(i==1){
			strcpy(name,h);
		}else if(i==2){
			strcpy(number,h);
		}else if(i==3){
			strcpy(date,h);
		}else if(i==4){
			strcpy(stockName,h);
		}else if(i==5){
			sNumber=atoi(h);
		}else if(i==6){
			Oprice=atof(h);
		}else if(i==7){
			strcpy(Hprice,h);
		}else if(i==8){
			strcpy(Lprice,h);
		}else if(i==9){
			Clprice=atof(h);
		}
		++i;
	}
	rev = (sNumber * Clprice)-(sNumber * Oprice) ;
	if(rev<0){
		rev= -rev;
	}
	printf("Customer: %s\n",name );
	printf("Account: %s\n",number );
	printf("Reporting Date: %s\n",date );
	printf("%-10s", "Stock");
	printf("%-15s", "Open Price");
	printf("%-15s", "Closing Price");
	printf("%s", "Loss");
	printf("\n");
	printf("%-10s", stockName );
	printf("%-15.2f", Oprice);
	printf("%-15.2f", Clprice);
	printf("%0.2f\n", rev);

	if(argv[2]!=NULL){
		FILE * wptr=fopen(argv[2],"w");
		fprintf(wptr,"Customer: %s\n",name );
		fprintf(wptr, "Account: %s\n",number );
		fprintf(wptr,"Reporting Date: %s\n",date );
		fprintf(wptr,"%-10s", "Stock");
		fprintf(wptr,"%-15s", "Open Price");
		fprintf(wptr,"%-15s", "Closing Price");
		fprintf(wptr,"%s\n", "Loss");
		fprintf(wptr,"%-10s", stockName );
		fprintf(wptr,"%-15.2f", Oprice);
		fprintf(wptr,"%-15.2f", Clprice);
		fprintf(wptr,"%0.2f\n", rev);
		/*fputs("Account: ",wptr );
		fputs(number,wptr );
		fputs("\n",wptr );

		fputs("Reporting Date: ",wptr );
		fputs(date,wptr );
		fputs("\n",wptr );*/

		fclose(wptr);
	}
	fclose(ptr);

	return 0;
}
}
