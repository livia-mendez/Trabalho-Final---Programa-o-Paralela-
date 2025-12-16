# Desafio 002
Solução com Prevenção de Deadlock

Modificar a implementação da Tarefa 1 para prevenir deadlock usando a técnica de um
filósofo pegar os garfos em ordem diferente.

# Perguntas 

- Por que essa solução previne deadlock
Essa solução previne deadlock porque evita a espera circular entre os filósofos. Na implementação original, todos os filósofos pegam os garfos na mesma 
ordem (esquerdo → direito). Isso pode fazer com que cada filósofo segure um garfo e fique esperando pelo outro, formando um ciclo em que ninguém consegue continuar.
Na Tarefa 2, um dos filósofos pega os garfos em ordem diferente (direito → esquerdo). Essa mudança impede que todos fiquem presos no mesmo ciclo de espera, garantindo que pelo menos um filósofo consiga comer e liberar os garfos, permitindo que os outros também prossigam.

- Se ainda existe possibilidade de starvation e por que?
Sim, pois mesmo sem deadlock, não há garantia de que todos os filósofos terão acesso justo aos garfos. O uso de synchronized não controla a ordem em que as threads entram na região crítica, ficando essa decisão a cargo do escalonador da JVM. Assim, um filósofo pode acabar comendo muitas vezes enquanto outro espera por mais tempo, caracterizando starvation.

- Comparação dos resultados com a Tarefa 1
Na Tarefa 1, o sistema pode entrar em deadlock, fazendo com que todas as threads fiquem bloqueadas e o programa pare de funcionar corretamente.
Na Tarefa 2, o deadlock não ocorre, pois a alteração na ordem de aquisição dos garfos elimina a espera circular. O programa continua executando normalmente durante todo o tempo de teste. Apesar disso, em ambas as tarefas ainda existe a possibilidade de starvation, pois não há garantia de justiça no acesso aos recursos.

Inclua estatísticas de execução: quantas vezes cada filósofo comeu durante o período
de teste.

Filósofo F1: 18 refeições

Filósofo F2: 21 refeições

Filósofo F3: 17 refeições

Filósofo F4: 20 refeições

Filósofo F5: 16 refeições