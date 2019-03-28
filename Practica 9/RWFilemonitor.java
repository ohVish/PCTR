/**
 * @(#)RWFilemonitor.java
 * @author José Miguel Aragón Jurado
 * @version 1.00 
 */
import java.io.*;

class RWFilemonitor {
  volatile int readers = 0;
  volatile boolean writing = false;
  volatile RandomAccessFile f;

  public RWFilemonitor(){}

  synchronized RandomAccessFile StartRead() {
    while (writing)
      try {
       wait();
     } catch (InterruptedException e) {}
     readers = readers + 1;
     System.out.println("Lector inicia lectura...");
     try{
      f=new RandomAccessFile("fichero.txt","r");
    }catch(FileNotFoundException e){
      System.out.println("No existe el fichero.");
    }
    notifyAll();
    return f;

  }

  synchronized RandomAccessFile EndRead() {
    readers = readers - 1;
    try{
      f.close();
    }catch(IOException e){}
    if (readers == 0) notifyAll();
    System.out.println("Lector finaliza lectura...");
    return f;
  }
  synchronized RandomAccessFile StartWrite() {
    while (writing || (readers != 0)){
      try {
       wait();
     } catch (InterruptedException e) {}
   }
     writing = true;
     try{
       f= new RandomAccessFile("fichero.txt","rw");
     }catch(FileNotFoundException e){
      System.out.println("No existe el fichero.");
    }
    System.out.println("Escritor inicia escritura...");
    return f;

  }
  synchronized RandomAccessFile EndWrite() {
    writing = false;
    try{
      f.close();
    }catch(IOException e){}
    notifyAll();
    System.out.println("Escritor finaliza escritura...");
    return f;
  }
}