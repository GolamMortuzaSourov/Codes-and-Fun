/* This is the only file you should update and submit. */

/* Fill in your Name and GNumber in the following two comment fields
 * Name: Golam Mortuza Sourov
 * GNumber: 01226584
 */

#include <sys/wait.h>
#include "taskmntr.h"
#include "parse.h"
#include "util.h"

/* Constants */
#define DEBUG 1

 
// uncomment if you want to use any of these:

/*
#define NUM_PATHS 2
#define NUM_INSTRUCTIONS 10


static const char *task_path[] = { "./", "/usr/bin/", NULL };
static const char *instructions[] = { "quit", "help", "list", "delete", "exec", "bg", "kill", "suspend", "resume", "pipe", "history", NULL};

*/

/* The entry of your task controller program */
void handler(int sig){
	if(sig==2){
		log_ctrl_c();
	}else if(sig==20){
		log_ctrl_z();
	}
}

int main() {
    char cmdline[MAXLINE];        /* Command line */
    char *cmd=NULL;

    Instruction arr[20];
    Instruction list[20];
    char cstmcmd[10];
    int pid_ =0;
    int size = 0;
    int listsize = 0;

    /* Intial Prompt and Welcome */
    log_intro();
    log_help();

    /* Shell looping here to accept user command and execute */
    struct sigaction act;
    memset(&act,0,sizeof(struct sigaction));
    struct sigaction old;
    act.sa_handler = handler;
    sigaction(SIGINT,&act,&old);
    sigaction(SIGTSTP,&act,&old);
    while (1) {
        char *argv[MAXARGS+1];        /* Argument list */
        Instruction inst;           /* Instruction structure: check parse.h */

        /* Print prompt */
        log_prompt();

        /* Read a line */
        // note: fgets will keep the ending '\n'
	errno = 0;
        if (fgets(cmdline, MAXLINE, stdin) == NULL) {
            if (errno == EINTR) {
                continue;
            }
            exit(-1);
        }

        if (feof(stdin)) {  /* ctrl-d will exit text processor */
          exit(0);
        }

        /* Parse command line */
        if (strlen(cmdline)==1)   /* empty cmd line will be ignored */
          continue;     

        cmdline[strlen(cmdline) - 1] = '\0';        /* remove trailing '\n' */

        cmd = malloc(strlen(cmdline) + 1);          /* duplicate the command line */
        snprintf(cmd, strlen(cmdline) + 1, "%s", cmdline);

        /* Bail if command is only whitespace */
        if(!is_whitespace(cmd)) {
            initialize_command(&inst, argv);    /* initialize arg lists and instruction */
            parse(cmd, &inst, argv);            /* call provided parse() */

            if (DEBUG) {  /* display parse result, redefine DEBUG to turn it off */
                debug_print_parse(cmd, &inst, argv, "main (after parse)");
	    }

            /* After parsing: your code to continue from here */
            /*================================================*/
		#undef DEBUG
		#define DEBUG 0 
		if(size<20){
			if(strcmp(inst.instruct,"history")>0 || strcmp(inst.instruct,"history")<0){
				arr[size].instruct = inst.instruct;
				arr[size].num = inst.num;
				arr[size].num2 = inst.num2;
				arr[size].infile = inst.infile;
				arr[size].outfile = inst.outfile;
				size++;
			}
		}
		if(strcmp(inst.instruct,"help")==0){
			log_help();
		}else if(strcmp(inst.instruct,"quit")==0){
			log_quit();
			exit(0);
		}else if(strcmp(inst.instruct,"history")==0){
			log_history_info(size);
			int order =1;
			for(int i =0;i<size;i++){
				Instruction gg = (arr[i]);
				printf("%d%s%s\n",order," ",gg.instruct);
				order++;
			}
			if(inst.num <0 || inst.num>= size){
				log_history_error(inst.num);
			}
		}else if(strcmp(inst.instruct,"list")==0){
			log_num_tasks(listsize);
			for(int i=0;i<listsize;i++){
				char* cm;	
				cm = list[i].instruct;
				strcat(cm," ");
				char t[5];
				sprintf(t,"%d\n",list[i].num);
				strcat(cm,t);
				sprintf(t,"%d\n",list[i].num2);
				strcat(cm," ");
				strcat(cm,t);
				const char* cmdl = cm;
				log_task_info(i+1,LOG_STATE_READY,0,0,cmdl);
			}
		}else if(strcmp(inst.instruct,"delete")==0){
			for(int k=0;k<=inst.num;k++){
				if(k==inst.num){
					list[k].instruct="NULL";
				}	
			}
		}else if(strcmp(inst.instruct,"bg")==0){
			if(!(pid_=fork())){
				if(cstmcmd!=NULL){
					system(cstmcmd);
				}
			}
			//log_intro();
			
		}else{
			list[size].instruct = inst.instruct;
			list[size].num = inst.num;
			list[size].num2 = inst.num2;
			list[size].infile = inst.infile;
			list[size].outfile = inst.outfile;
			listsize++;
			char h[10] = "./";
			strcat(h,cmdline);
			strcat(h," ");
			char c[2];
			sprintf(c,"%d",inst.num);
			strcat(h,c);
			if(strcmp(cmdline,"pwd")==0){
				strcpy(cstmcmd,"pwd");
			}else{
				strcpy(cstmcmd,h);
			}
			system(cstmcmd); 
		}

	} // end if(!is_whitespace(cmd))
	#undef DEBUG
	#define DEBUG 0
	free(cmd);
	cmd = NULL;
        free_command(&inst, argv);
    }  // end while(1)

    return 0;
}  // end main()

