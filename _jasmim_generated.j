.class public MonteCarloPi
.super java/lang/Object


.method public static main([Ljava/lang/String;)V
.limit locals 5
.limit stack 5
bipush 34
istore 1
bipush 0
istore 2
bipush 35
istore 3
bipush 33
bipush 32
if_icmpgt Label13
bipush 33
istore 1
Label13: 
bipush 36
istore 1
iload 1
invokestatic io/println(I)V
return
.end method
