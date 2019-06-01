.class public MonteCarloPi
.super java/lang/Object


.method public static main([Ljava/lang/String;)V
.limit locals 5
.limit stack 5
bipush 3
istore 1
bipush 0
istore 2
bipush 35
istore 3
Label11: 
iload 1
bipush 10
if_icmpge Label12
iload 1
bipush 1
iadd
istore 1
goto Label11
Label12: 
iload 1
invokestatic io/println(I)V
return
.end method
