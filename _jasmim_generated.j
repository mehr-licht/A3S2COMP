.class public MonteCarloPi
.method public estimate_pi(int variavelTeste; int var_TESTE2; )V
bipush 10
ireturn
.end method
.method public static main([Ljava/lang/String;)V
ldc num
bipush 0
ldc num
bipush 1
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
.method public function_def(boolean gaia; int var1; int var2; )V
ldc randou
bipush 2
ldc gerado
bipush 3
bipush 4
ireturn
.end method
