#include <stdio.h>
#include <stdlib.h>

typedef struct Item {
	int itemId;
	char itemName[50];
	int quantity;
	double pricePerItem;
}Item;

int N=5;
void insertItem(struct Item *, struct Item);
void updateItem(struct Item *, int, int);
void searchItem(struct Item *, int );
void printData(struct Item *);

int main(){
	Item * itemInventory = malloc(N*sizeof(Item));
	for(int i=0;i<N;i++){
		itemInventory[i].itemId=-1;
	}
	char input;
	char buf[20];
	int elem=0;
	for(int i =0; i<1; i++){
		printf("%s\n","Enter your choice :" );
		printf("%s\n","'i' - insert an item" );
		printf("%s\n","'u' - update the database" );
		printf("%s\n","'s' - search the database" );
		printf("%s\n","'d' - display the database" );
		printf("%s\n","'q' - quit the program" );
		fgets(buf,20,stdin);
		sscanf(buf,"%c",&input);

		if(input=='i' || input=='I'){
			Item it;
			insertItem(itemInventory, it);
		}else if(input=='u' || input=='U'){
			int id;
			printf("%s\n","Enter the id that you are looking for: " );
			fgets(buf,10,stdin);
			sscanf(buf,"%d",&id);
			int q;
			printf("%s\n","Enter the updated quantity: " );
			fgets(buf,10,stdin);
			sscanf(buf,"%d",&q);
			updateItem(itemInventory, id, q);
		}else if(input=='s' || input=='S'){
			int id;
			printf("%s\n","Enter the id that you are looking for: " );
			fgets(buf,10,stdin);
			sscanf(buf,"%d",&id);
			searchItem(itemInventory, id);
		}else if(input=='d' || input=='D'){
			printData(itemInventory);
		}else if(input=='q' || input=='Q'){
			free(itemInventory);
			exit(0);
		}
		--i;
	}
	/*printf("%d\n",itemInventory[0].itemId);
	printf("%s\n",itemInventory[0].itemName);
	printf("%d\n",itemInventory[0].quantity);
	printf("%.2lf\n",itemInventory[0].pricePerItem);*/
}

void insertItem(struct Item *itemInventory, struct Item item){
	char buf[10];
	printf("%s\n","Enter item ID: " );
	fgets(buf,10,stdin);
	sscanf(buf,"%d",&item.itemId);
	for(int i=0;i<N;i++){
		if(item.itemId==itemInventory[i].itemId){
			printf("%s\n","Error - item already exists, try again" );
			fgets(buf,10,stdin);
			sscanf(buf,"%d",&item.itemId);
			i=-1;
		}
	}

	printf("%s\n","Enter item Name: " );
	fgets(buf,10,stdin);
	sscanf(buf,"%s",&item.itemName);

	printf("%s\n","Enter Quantity: " );
	fgets(buf,10,stdin);
	sscanf(buf,"%d",&item.quantity);

	printf("%s\n","Enter Price per Item: " );
	fgets(buf,10,stdin);
	sscanf(buf,"%lf",&item.pricePerItem);

	for(int i=0;i<N;i++){
		if(itemInventory[i].itemId==-1){
			itemInventory[i]=item;
			if(i==N-1){
				itemInventory=realloc(itemInventory,N*2);
				for(int q=N;q<N*2;q++){
					itemInventory[q].itemId=-1;
				}
				N=N*2;
			}
			break;
		}
	}
	//itemInventory[0]=item;
}

void updateItem(Item *itemInventory, int itemID, int quantity){
	for(int i=0;i<N;i++){
		if(itemInventory[i].itemId==itemID){
			itemInventory[i].quantity=quantity;
			return;
		}
	}
	printf("%s\n","Item not found" );
}

void searchItem(Item *itemInventory, int itemID){
	for(int i=0;i<N;i++){
		if(itemInventory[i].itemId == itemID){
			printf("%s %d\n","Item ID: ",itemInventory[i].itemId );
			printf("%s %s\n", "Item Name: ",itemInventory[i].itemName );
			printf("%s %d\n", "Item Quantity: ",itemInventory[i].quantity );
			printf("%s %.2lf\n","Price Per Item: ",itemInventory[i].pricePerItem );
			return;
		}
	}
	printf("%s\n","Item not found" );
}

void printData(Item *itemInventory){
	printf("%-13s %-15s %-15s %s\n","ID", "Name", "Quantity", "Item Price" );
	for(int i=0;i<N;i++){
		if(itemInventory[i].itemId!=-1){
			printf("%-14d",itemInventory[i].itemId );
			printf("%-17s",itemInventory[i].itemName );
			printf("%-15d",itemInventory[i].quantity );
			printf("%.2lf\n",itemInventory[i].pricePerItem );
		}
	}
}
