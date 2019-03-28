#include <thread>
#include <iostream>
#include <mutex>
#include <condition_variable>

using namespace std;

class lineaCajas{
public:
	lineaCajas():libres(5){
		for(int i=0;i<5;i++)cajas[i]=true;
	}
	int entrarCaja(){
		unique_lock<mutex> lck(c);
		while(libres==0){
			lleno.wait(lck);
		}
		libres--;
		cout<<"Entrando en la caja"<<endl;
		int i=0;
		while(!cajas[i])i=(i+1)%5;
		cajas[i]=false;
		return i;
	}

	void salirCaja(int id){
		unique_lock<mutex> lck(c);
		cajas[id]=true;
		cout<<"Saliendo de la caja."<<endl;
		libres++;
		lleno.notify_one();
	}

private:
	bool cajas[5];
	int libres;
	mutex c;
	condition_variable lleno;
};


int main(){
	lineaCajas local;
	int i=local.entrarCaja();
	this_thread::sleep_for(1s);
	local.salirCaja(i);
	return 0;
}