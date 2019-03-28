/**
 * @(#)RWMonitorAN.java
 * @author José Miguel Aragón Jurado
 * @version 1.00 
 */
import java.io.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

class RWMonitorAN {
  private ReentrantLock cerrojo;
  private Condition escritores;
  private Condition lectores;
  private volatile int readers = 0;
  private volatile boolean writing = false;
  private volatile RandomAccessFile f;

  public RWMonitorAN(){
    cerrojo=new ReentrantLock();
    escritores=cerrojo.newCondition();
    lectores=cerrojo.newCondition();
  }

  public RandomAccessFile StartRead() {
    cerrojo.lock();
    try{
      while (writing)
        try {
         lectores.await();
       } catch (InterruptedException e) {}
       readers = readers + 1;
       System.out.println("Lector inicia lectura...");
       try{
        f=new RandomAccessFile("fichero.txt","r");
      }catch(FileNotFoundException e){
        System.out.println("No existe el fichero.");
      }
      lectores.signal();
    }finally{
      cerrojo.unlock();
      return f;
    }  

  }

  public RandomAccessFile EndRead() {
    cerrojo.lock();
    try{
      readers = readers - 1;
      try{
        f.close();
      }catch(IOException e){}
      if (readers == 0) escritores.signal();
      System.out.println("Lector finaliza lectura...");
    }finally{
      cerrojo.unlock();
      return f;
    }
  }
  public RandomAccessFile StartWrite() {
    cerrojo.lock();
    try{
      while (writing || (readers != 0)){
        try {
         escritores.await();
       } catch (InterruptedException e) {}
     }
     writing = true;
     try{
       f= new RandomAccessFile("fichero.txt","rw");
     }catch(FileNotFoundException e){
      System.out.println("No existe el fichero.");
    }
    System.out.println("Escritor inicia escritura...");
  }finally{
    cerrojo.unlock();
    return f;
  }

}
public RandomAccessFile EndWrite() {
  cerrojo.lock();
  try{
    writing = false;
    try{
      f.close();
    }catch(IOException e){}
    if(readers==0) escritores.signal();
    else lectores.signal();
    System.out.println("Escritor finaliza escritura...");
  }finally{
    cerrojo.unlock();
    return f;
  }
}
}