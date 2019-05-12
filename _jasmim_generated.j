.class public MonteCarloPi
.method public static main([Ljava/lang/String;)V
ldc num_samples
bipush 1
bipush 1
iadd
ldc pi_estimate_times_100
bipush 3
bipush 4
imul
ldc testeVar
bipush 10
bipush 5
idiv
ldc testeVar2
bipush 1
bipush 2
isub
bipush 4
ireturn
.end method
