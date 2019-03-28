 
#include <iostream>
#include <chrono>
#include <ctime>
#include <thread>
#include <mutex>
#include <atomic>
#include <iomanip>
using namespace std;

struct ContAtomic{
	ContAtomic():n(0){}
	atomic<int> n;
	void inc(){
		n++;
	}

};

struct ContMutex{
	ContMutex():n(0){}
	int n;
	mutex c;
	void inc(){
		c.lock();
		n++;
		c.unlock();
	}
};

void cerrojo(int nIter){
	ContMutex n;
	thread hilos[1000];
	for(int i=0;i<1000;i++){
		hilos[i]=thread([&n,nIter](){
			for(int j=0;j<nIter;j++){
				n.inc();
			}
		});
	}
	for(int i=0;i<1000;i++){
		hilos[i].join();
	}
}

void atomico(int nIter){
	ContAtomic n;
	thread hilos[1000];
	for(int i=0;i<1000;i++){
		hilos[i]=thread([&n,nIter](){
			for(int j=0;j<nIter;j++){
				n.inc();
			}
		});
	}
	for(int i=0;i<1000;i++){
		hilos[i].join();
	}
}


int main(int argc, char const *argv[])
{
	chrono::time_point<chrono::system_clock>start1,end1,start2,end2;
	cout<<"Iteraciones\t\t"<<"mutex\t\t"<<"atomic"<<endl;
	for(int i=20000;i>0;i-=1000){
		start1=chrono::system_clock::now();
		cerrojo(i);
		end1=chrono::system_clock::now();
		start2=chrono::system_clock::now();
		atomico(i);
		end2=chrono::system_clock::now();
		chrono::duration<double>t1=end1-start1;
		chrono::duration<double>t2=end2-start2;
		cout<<i<<"\t\t\t";
		cout<<fixed<<setprecision(3)<<t1.count();
		cout<<"\t\t";
		cout<<fixed<<setprecision(3)<<t2.count();
		cout<<endl;

	}
	return 0;
}