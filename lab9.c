#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void iterative_search(char (*maze)[],char [],int ,int, FILE * );


int main(int argc,char * argv[]){
	const char *filename;
	const char *filename1;
	FILE *ptr;
	FILE *p;
	char line[100];
	int height=0;
	int width=0;
	int c=0;
	filename= argv[1];
	filename1= argv[2];

	int Mx=0;
	int My=0;
	int Cx=0;
	int Cy=0;

	if(argc <=2){
		printf("%s\n","Error - two input files were not provided as command line arguments");
	}else if((strcmp(filename,"labyrinth.txt"))!=0){
		printf("%s %s %s\n","Error - the ",filename," file could not be opened");
	}else if((strcmp(filename1,"directions.txt"))!=0){
		printf("%s %s %s\n","Error - the ",filename1," file could not be opened");
	}else{
		ptr = fopen(argv[1],"r");
		while(fgets(line,sizeof(line),ptr)!= NULL){
			if(height == 0){
				while(c<sizeof(line)){
					if(line[c]=='|'){
						width++;
						c++;
					}else{
						c=0;
						break;
					}
				}
			}
			height++;
		}


		char maze[height][width];
		int c=0;
		int cheesecount=0;
		int h=0;
		int w=0;
		FILE *p = fopen(argv[1],"r");
		while(fgets(line,sizeof(line),p)!= NULL){
			while(w<width){
				maze[h][w]=line[w];
				if(line[w]=='M'){
					Mx=h;
					My=w;
				}else if(line[w]=='C'){
						Cx=h;
						Cy=w;
						cheesecount++;
				}
				w++;
			}
			w=0;
			h++;
		}

		int MX=Mx;
		int MY=My;
		FILE * pd;
		pd=fopen(argv[2],"r");
		int chs[cheesecount][2];
		int cc=0;
		char cpymaze[height][width];
		for(int y=0;y<height;y++){
			for(int o=0;o<width;o++){
				cpymaze[y][o]=maze[y][o];
				if(maze[y][o]=='C'){
					chs[cc][0]=y;
					chs[cc][1]=o;
					cc++;
				}
			}
		}


		FILE * pw=fopen("iterative_labryinth.out","w");
		for(int y=0;y<height;y++){
			for(int o=0;o<width;o++){
				fprintf(pw, "%c",maze[y][o]);
			}
			fprintf(pw, "%s","\n");
		}
		while(fgets(line,sizeof(line),pd)!= NULL){
			if(line[0]=='S'){
				if( !((MX+1)>height-1) ) {
					if(cpymaze[MX+1][MY]!='|'){
						MX=MX+1;
						cpymaze[MX+1][MY]='.';
					}

					iterative_search(cpymaze,"south",MX+1,MY+1,pw);
					fprintf(pw, "%s%d%s%d%s\n","Jerry moved south (",MY+1,",",height-MX,")");
				}
			}else if(line[0]=='W'){
				if( !((MY-1)<0) ) {
					if(cpymaze[MX][MY-1]!='|'){
						MY=MY-1;
						cpymaze[MX+1][MY]='.';
					}
					iterative_search(cpymaze,"west",MX,MY+1,pw);
					fprintf(pw, "%s%d%s%d%s\n","Jerry moved west (",MY+1,",",height-MX,")");

				}
			}else if(line[0]=='N'){
				if(!((MX-1)>height-1)){
					if(cpymaze[MX-1][MY]!='|'){
						MX=MX-1;
						cpymaze[MX-1][MY]='.';
					}
					iterative_search(cpymaze,"north",MX+1,MY,pw);
					fprintf(pw, "%s%d%s%d%s\n","Jerry moved north (",MY+1,",",height-MX,")");

				}
			}else if(line[0]=='E'){
				if(!((MY+1)>width-1)){
					if(cpymaze[MX][MY+1]!='|'){
						MY=MY+1;
						cpymaze[MX][MY+1]='.';
					}
					iterative_search(cpymaze,"east",MX,MY+1,pw);
					fprintf(pw, "%s%d%s%d%s\n","Jerry moved east (",MY+1,",",height-MX,")");

				}
			}
			for(int q=0;q<cheesecount;q++){
				if(chs[q][0]==MX && chs[q][1]==MY){
					iterative_search(cpymaze,"f",MX,MY+1,pw);
					fprintf(pw, "%s\n","Jerry found the cheese!");
					printf("%s\n","Success - output file iterative_labryinth.out produced " );
					break;
				}
			}
		}
		/*for(int y=0;y<height;y++){
			for(int o=0;o<width;o++){
				printf("%c",maze[y][o] );
			}
		}*/
		/*printf("%d\n",Mx );
		printf("%d\n", My);
		printf("%d\n", Cx);
		printf("%d\n", Cy);
		printf("%d\n",height);
		printf("%d\n",width);
		for(int i=0;i<3;i++){
			printf("%d\n", chs[i][0] );
			printf("%d\n", chs[i][1] );
		}
		printf("%d %s %d\n",MX,",",MY );*/
	}
	return 0;
}

void iterative_search(char (*maze)[],char direction[],int y,int x,FILE *pw) {

}
