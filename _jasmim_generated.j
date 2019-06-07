.class public MonteCarloPi
.super java/lang/Object

.method public <init>()V
aload_0
invokespecial java/lang/Object/<init>()V
.end method

.method public estimatePi100(I)I
.limit locals 5
.limit stack 10
aload 0
iload 1
ireturn 
.end method

.method public static main([Ljava/lang/String;)V
.limit locals 5
.limit stack 5

bipush 100
istore 2
invokevirtual MonteCarloPi/estimatePi100(I)I
istore 2
invokestatic ioPlus/printResult(I)V
return
.end method
