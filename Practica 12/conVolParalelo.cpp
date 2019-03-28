/*Convolución de matrices paralelo en c++
	Autor: José Miguel Aragón Jurado
*/
#define N 10000
#include <iostream>
#include <random>
#include <cstdlib>
#include <ctime>
#include <thread>
using namespace std;
int m[N][N];
int r[N][N];
int k1[3][3]={{0,-1,0},
{-1,5,-1},
{0,-1,0}};
int k2[3][3]={{1,1,1},
{1,1,1},
{1,1,1}};
int k3[3][3]={{0,0,1},
{-1,1,0},
{0,0,0}};
int k4[3][3]={{0,1,0},
{1,-4,1},
{0,1,0}};

int k5[3][3]={{-1,0,1},
{-2,0,2},
{-1,0,1}};
int k6[3][3]={{1,-2,1},
{-2,5,-2},
{1,-2,1}};


void nextState(int,int,int[][3]);
void rellenarMatriz();
void mostrarMatriz();
void convolucion(int[][3],int); 
void run(int[][3] ,int ,int);


int main(int argc,char* args[]){
	if(argc==1){
		cout<<"Introduce el numero de hebras"<<endl;
	}
	else{

		rellenarMatriz();
		cout<<"Introduce que tecnica de convolucion desea aplicar:"<<endl;
		cout<<"1. Enfoque."<<endl;
		cout<<"2. Desenfoque."<<endl;
		cout<<"3. Realizar bordes."<<endl;
		cout<<"4. Detección de bordes."<<endl;
		cout<<"5. Sobel."<<endl;
		cout<<"6. Sharpen."<<endl;
		int elec;
		cin>>elec;
		switch(elec){
			case 1:{
				convolucion(k1,atoi(args[1]));
				break;
			}
			case 2:{
				convolucion(k2,atoi(args[1]));
				break;
			}
			case 3:
			{
				convolucion(k3,atoi(args[1]));
				break;
			}
			case 4:{
				convolucion(k4,atoi(args[1]));
				break;
			}
			case 5:{
				convolucion(k5,atoi(args[1]));
				break;
			}
			case 6:{
				convolucion(k6,atoi(args[1]));
				break;
			}
		}
	}

	return 0;
}

void rellenarMatriz(){
	uniform_real_distribution<> random(-20,20);
	default_random_engine seed(time(0));
	for(int i=0;i<N;i++){
		for(int j=0;j<N;j++){
			m[i][j]=round(random(seed));
			r[i][j]=0;
		}
	}
}
void mostrarMatriz(){
	for(int i=0;i<N;i++){
		for(int j=0;j<N;j++){
			cout<<m[i][j]<<",";
		}
		cout<<endl;
	}
	cout<<endl;
}

void convolucion(int k[][3],int nh){
	chrono::time_point<chrono::system_clock> start,end;
	start=chrono::system_clock::now();
	thread hilos[nh];
	int bloque=(int)N/nh;
	for(int i=0;i<nh;i++){
		if(i==nh-1){
			hilos[i]=thread(run,ref(k),i*bloque,N);
		}
		else{
			hilos[i]=thread(run,ref(k),i*bloque,(i+1)*bloque);
		}
	}
	for(int i=0;i<nh;i++){
		hilos[i].join();
	}
	end=chrono::system_clock::now();
	chrono::duration<double> tiempo = end-start;
	cout<<"t= "<<tiempo.count()<<endl;


}

void run(int k[][3],int inicio,int fin){
	for(int i=inicio;i<fin;i++){
		for(int j=0;j<N;j++){
			nextState(i,j,k);
		}
	}
}

void nextState(int a,int b,int k[][3]){
	int z=0;
	for(int i=a-1;i<=a+1;i++){
		int l=0;
		for(int j=b-1;j<=b+1;j++){
			if(i>=N || j>=N|| j<0 || i<0);
			else{
				r[a][b]=r[a][b]+k[z][l]*m[i][j];
			}
			l++;
		}
		z++;
	}
}