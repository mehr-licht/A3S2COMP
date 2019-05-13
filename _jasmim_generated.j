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
ldc testeVar3
bipush 4
bipush 5
iadd
bipush 4
ireturn
.end method
.method public function_def()V
ldc randou
bipush 2
ldc gerado
bipush 3
bipush 4
ireturn
.end method
.method public estimate_pi()V
bipush 10
ireturn
.end method
