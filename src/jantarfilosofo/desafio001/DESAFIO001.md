# Desafio 001

Altere o código neste pacote para atender os requisitos abaixo:

1. Cada thread deve entrar em modo de espera utilizando o comando wait() ao invés de sleep();

2. Antes de entrar em espera, cada thread deve notificar a thread a sua direita utilizando o comando notify()

### Perguntas

Q1. Qual a situação final após algum tempo de execução de todas as threads?

Após algum tempo de execução, todas as threads entram em estado de espera (WAITING) e o sistema fica bloqueado, sem progresso na execução. Esse comportamento ocorre devido ao uso incorreto dos mecanismos de sincronização wait() e notify().

As notificações realizadas por meio de notify() podem ser perdidas caso a thread notificada ainda não tenha entrado em estado de espera no momento da chamada. Como não existe uma condição lógica compartilhada que controle quando uma thread pode prosseguir, as threads acabam chamando wait() sem garantia de que alguma outra thread permanecerá ativa para notificá-las posteriormente.

Como resultado, forma-se uma situação em que todas as threads estão bloqueadas simultaneamente, caracterizando um problema clássico de sincronização entre threads causado pela ausência de coordenação adequada e pelo uso direto de wait() e notify() sem controle de estado.

Esse comportamento evidencia a importância do uso de variáveis de condição e do padrão de espera em laço (while) ao trabalhar com wait() e notify() em aplicações concorrentes.
