.class public MonteCarloPi
.super java/lang/Object


.method public static main([Ljava/lang/String;)V
.limit locals 5
.limit stack 5
bipush 34
istore_1
bipush 5
bipush 3
imul
bipush 4
bipush 4
idiv
isub
bipush 1
bipush 32
if_icmpgt Label16
bipush 33
istore_2
Label16: 
iload 1
invokestatic io/println(I)V
return
.end method
