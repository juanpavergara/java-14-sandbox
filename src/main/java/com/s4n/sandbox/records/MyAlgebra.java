package com.s4n.sandbox.records;

public class MyAlgebra {
  public static MyRecord add(MyRecord mr1, MyRecord mr2){
    return new MyRecord(mr1.a()+mr2.a(), mr1.b()+mr2.b(), mr1.c()+ mr2.c() );
  }
}
