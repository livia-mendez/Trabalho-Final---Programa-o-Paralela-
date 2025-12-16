# Desafio 004
Solução com Monitores e Garantia de Fairness

Implementar uma solução usando monitores (synchronized blocks/methods) que garanta
fairness e previna tanto deadlock quanto starvation.

Implemente uma solução usando uma classe Mesa que atua como monitor.
2. A classe Mesa deve gerenciar o acesso aos garfos de forma centralizada.
3. Implemente um mecanismo de fila ou prioridade que garanta que todos os filósofos
tenham oportunidade de comer (evitar starvation).
4. Use wait() e notifyAll() para coordenar os filósofos.
5. Mantenha o sistema de logging e estatísticas.
6. Execute o programa por pelo menos 2 minutos.
7. Documente no README.md:

# Perguntas
- Como o monitor garante fairness?
O monitor garante fairness (justiça) por meio de uma fila FIFO (First-In, First-Out) mantida dentro da classe Mesa. Funciona da seguinte forma:
Quando um filósofo deseja comer, ele entra em uma fila de espera. Apenas o filósofo que está na primeira posição da fila pode tentar pegar os garfos.
Mesmo que os garfos estejam livres, filósofos que chegaram depois não podem ultrapassar quem chegou antes.
Esse mecanismo garante que: A ordem de atendimento respeita a chegada e nenhum filósofo é favorecido repetidamente
Sendo assim, todos eventualmente terão acesso aos recursos. 
Assim, o monitor não depende apenas do escalonamento das threads pelo sistema operacional, mas impõe uma política explícita de justiça.

- Como deadlock e starvation são prevenidos?
O deadlock é prevenido porque os garfos não são adquiridos individualmente pelos filósofos.
A Mesa gerencia de forma centralizada a liberação simultânea dos dois garfos necessários, eliminando a espera circular.
A starvation é evitada pelo mecanismo de fila (fairness), que assegura que nenhum filósofo fique esperando indefinidamente.
Quando um filósofo termina de comer, os demais são notificados com notifyAll(), permitindo que o próximo da fila avance.

- Comparação com as soluções anteriores
Na Tarefa 1, o uso direto de wait() e notify() podia causar bloqueio total do sistema devido à perda de notificações.
Na Tarefa 2, a prevenção de deadlock foi feita por assimetria, mas ainda existia a possibilidade de starvation.
Na Tarefa 3, o uso de semáforos evitou deadlock ao limitar o número de filósofos concorrentes, porém não garantia fairness total.
A solução com monitores é mais robusta, pois previne deadlock e starvation ao mesmo tempo, garantindo ordem e justiça no acesso aos recursos.

- Trade-offs entre as diferentes abordagens
A solução com monitores possui maior complexidade de implementação, pois exige controle centralizado e fila de espera.
Em contrapartida, oferece maior segurança e previsibilidade.
Soluções com semáforos são mais simples e eficientes, mas podem não garantir fairness.
Já soluções assimétricas são fáceis de implementar, porém não eliminam completamente a possibilidade de starvation.

