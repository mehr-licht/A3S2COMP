.class public MonteCarloPi
.super java/lang/Object


.method public static main([Ljava/lang/String;)V
.limit locals 5
.limit stack 5
bipush 31
istore 1
bipush 0
istore 2
bipush 35
istore 3
bipush 32
bipush 31
if_icmpge Label12
bipush 33
istore 1
goto Label11
Label12: 
bipush 36
istore 1
Label11: 
iload 1
invokestatic io/println(I)V
return
.end method
