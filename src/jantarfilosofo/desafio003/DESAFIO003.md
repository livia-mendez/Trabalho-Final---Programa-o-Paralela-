# Desafio 003

Solução com Semáforos

Implementar uma soluçãoo usando semáforos para limitar o número de filósofos que podem
tentar pegar os garfos simultaneamente.

1. Implemente uma nova solução usando Semaphore do Java.
2. Use um semáforo para limitar a 4 o número de filósofos que podem tentar pegar os
garfos ao mesmo tempo.
3. Mantenha os garfos como objetos sincronizados ou use sem´aforos individuais para
cada garfo.
4. Implemente o mesmo sistema de logging das tarefas anteriores.
5. Execute o programa por pelo menos 2 minutos e colete estatísticas.

# Perguntas 
- Como a solução funciona?
Nesta solução, é utilizado um semáforo global para limitar o número de filósofos que podem tentar pegar os garfos simultaneamente.
O semáforo é inicializado com 4 permissões, o que significa que, no máximo, quatro filósofos podem tentar pegar os garfos ao mesmo tempo. Antes de tentar pegar qualquer garfo, o filósofo deve adquirir uma permissão do semáforo. Após terminar de comer, ele libera essa permissão.

- Por que ela previne deadlock?
Essa solução previne deadlock porque elimina a possibilidade de todos os filósofos segurarem um garfo simultaneamente.
No problema, o deadlock ocorre quando os cinco filósofos pegam um garfo cada e ficam esperando indefinidamente pelo outro. Ao limitar o número de filósofos que podem tentar pegar garfos a quatro, sempre haverá pelo menos um filósofo fora da disputa, garantindo que pelo menos um par de garfos permaneça livre.

- Comparação de desempenho com a solução da Tarefa 2
Na Tarefa 2, o deadlock é evitado alterando a ordem de aquisição dos garfos por um dos filósofos. Essa solução é simples e eficiente, mas depende de uma regra específica de ordenação. 
Na Tarefa 3, o uso de semáforos adiciona uma camada extra de controle, limitando explicitamente a concorrência. Isso pode reduzir levemente o paralelismo, já que apenas quatro filósofos podem competir pelos garfos ao mesmo tempo, mas aumenta a previsibilidade e a robustez da solução.

- Vantagens e desvantagens dessa abordagem
Vantagens: Prevenção de deadlock garantida, a solução é simples de entender conceitualmente e é fácil de estender para outros problemas de concorrência

- Desvantagens: Redução do paralelismo máximo do sistema, possibilidade de starvation ainda existe, introduz mais complexidade com o uso de semáforos e pode impactar desempenho em cenários com muitos recursos





