# Jogo do Cofre Digital

Uma aplicação cliente-servidor multithread desenvolvida em Java, que simula um jogo de adivinhação do cofre digital. A cada aposta realizada pelos jogadores, o cofre acumula fundos e, caso alguém acerte o número correto sorteado pelo servidor, leva uma porcentagem do prêmio!



## Como Funciona a Aplicação

1. **Servidor (`model.Servidor`)**:
   - Inicializa na porta `12345` e fica aguardando conexões.
   - Gerencia o **fundo acumulado** do cofre de forma segura e sincronizada (`synchronized`).
   - A cada nova conexão de cliente, inicia uma nova thread (`ThreadCliente`) para processar a requisição de forma concorrente.
   - Cada aposta adiciona **R$ 2,00** ao fundo acumulado.
   - Sorteia um número de **0 a 999** para cada rodada.
   - Se o palpite do jogador coincidir com o número sorteado, o cofre é aberto e o jogador ganha **60% do fundo acumulado**. O fundo é então reiniciado.
   - Se errar, o jogador é informado do valor atual acumulado.

2. **Cliente (`program.Cliente`)**:
   - Solicita o nome do jogador e o palpite (número entre 0 e 999).
   - Envia um objeto `Pedido` contendo os dados para o servidor através de sockets.
   - Recebe a resposta do servidor (`Resposta`) e exibe o resultado no console.

---

## Pré-requisitos

- Java Development Kit (JDK) 8 ou superior instalado.
- Variáveis de ambiente do Java configuradas (`java` e `javac` acessíveis pelo terminal).

---

## Como Rodar a Aplicação

Siga o passo a passo simplificado para compilar e executar diretamente da pasta `src`:

### 1. Clonar o Projeto e Acessar a Pasta `src`
Abra o terminal na pasta raiz do projeto clonado e entre na pasta `src`:
```bash
cd src
```

### 2. Compilar os Arquivos Java
Ainda dentro da pasta `src`, execute o comando para compilar todos os arquivos do servidor e do cliente:
```bash
javac model/*.java program/*.java
```

### 3. Iniciar o Servidor
Com os arquivos compilados, execute o servidor na mesma pasta `src`:
```bash
java model.Servidor
```
Você verá a mensagem informando que o servidor foi iniciado na porta `12345` e está aguardando conexões.

### 4. Executar o Cliente (Jogar!)
Abra **outro** terminal, navegue até a pasta `src` do projeto e execute o cliente:
```bash
cd src
java program.Cliente
```
Pronto! Basta digitar seu nome, seu palpite e tentar a sorte para abrir o cofre.

### Um ponto interessante é que você pode observar, no terminal que executa o servidor, os clientes que estão se conectando.
