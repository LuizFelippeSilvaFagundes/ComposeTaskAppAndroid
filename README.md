# Aplicação Mobile de Gerenciamento de Tarefas
![Adicionando Tarefa](/Adicionando_a_tarefa.png)



![data da tarefa](/data_tarefa.png)

![home](/home.png)

![login](/login.png)
## Introdução

Este projeto foi desenvolvido como parte do processo seletivo para o cargo de Jr. Developer na Quality Automação Ltda. A aplicação permite aos usuários criar, visualizar e gerenciar uma lista de tarefas, além de fornecer funcionalidades de cadastro e autenticação.

## Como Utilizar
Cadastro e Login:

Após abrir o aplicativo, o usuário será direcionado para a tela de login. Se não tiver uma conta, poderá clicar na opção de registro.
O usuário preenche as informações necessárias e clica em "Cadastrar".
Após um cadastro bem-sucedido, o usuário será redirecionado para a tela de login.
Ao inserir as credenciais corretas e clicar em "Entrar", o usuário será autenticado e direcionado para a tela principal.

Tela Principal (Home):

Na tela principal, o usuário verá duas seções:
Minhas Tarefas: Esta seção exibirá todas as tarefas que ainda não foram concluídas.
Tarefas Finalizadas: Aqui estarão as tarefas que foram marcadas como concluídas.
Ao lado do título "Minhas Tarefas" e "Tarefas Finalizadas", haverá um ícone de seta que, ao ser clicado, expandirá ou colapsará a lista de tarefas correspondentes.

Adicionando uma Tarefa:

O usuário poderá clicar no ícone de "+" localizado na parte inferior direita da tela.
Isso abrirá um diálogo ou uma nova tela onde o usuário poderá inserir os seguintes detalhes da tarefa:
Título: Um campo para o usuário digitar o nome da tarefa.
Descrição: Um campo opcional onde o usuário pode adicionar mais informações sobre a tarefa.
Data e Hora de Término: Um seletor de data e hora que permite ao usuário escolher quando a tarefa deve ser concluída.
Após preencher todos os campos, o usuário clica em "Salvar", e a tarefa será adicionada à lista na tela principal.

Exibição das Tarefas:

As tarefas adicionadas aparecerão imediatamente sob "Minhas Tarefas".
Ao clicar no título de "Minhas Tarefas", a lista de tarefas pendentes será expandida, mostrando cada tarefa com suas informações (título, descrição, data e hora de término).
Para as tarefas finalizadas, o mesmo processo se aplica, mas apenas as tarefas concluídas serão exibidas.

Interação com Tarefas:

O usuário pode marcar uma tarefa como concluída, o que a moverá para a seção "Tarefas Finalizadas".
Também haverá opções para editar ou excluir uma tarefa diretamente da lista.

## Funcionalidades

- Criação, edição e remoção de tarefas
- Marcação de tarefas como concluídas
- Cadastro e autenticação de usuários
- Persistência de dados
- Interface intuitiva e responsiva
- Api para consumo: com frases motivacionais 

## Tecnologias Utilizadas

- Kotlin (Android Nativo)
- Persistência de dados (SQLite, SharedPreferences, etc.)
- Firebase Auth
- Retrofit

## Arquitetura do Projeto
 MVVM (Model-View-ViewModel)

## Estrutura do Projeto

- `Data/`: Onde está toda a parte de persistencia de dados (ROOM)
- `Retrofit/`: Onde está a configuração e interface da API
- `ViewModel/`: Onde está a logica da autenticação de usuario e das tarefas
- `ui.authScreen/`: Onde está as telas 

## Instalação e Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/LuizFelippeSilvaFagundes/ComposeTaskAppAndroid.git

## APIs Externas

AdviceSlip API: API utilizada para obter conselhos motivacionais aleatórios.

### 1. Ambiente de Desenvolvimento
- **Android Studio**: IDE (Ambiente de Desenvolvimento Integrado) utilizado para desenvolver o aplicativo. É a ferramenta oficial para o desenvolvimento de aplicativos Android.

### 2. Dependências

Para funcionar corretamente, o projeto utiliza várias bibliotecas e dependências, que podem ser incluídas no arquivo `build.gradle` do módulo. Aqui estão algumas das principais:

- **Jetpack Compose**: Para a construção da interface do usuário de forma declarativa.
    ```groovy
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.material:material:1.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(platform("com.google.firebase:firebase-bom:33.5.1"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-auth-ktx:22.0.0")
    ```

    **Navegacao**: Para a navegação entre as telas
    ```groovy
        implementation("androidx.navigation:navigation-compose:2.6.0")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1") // Para integração com ViewModel
        implementation("androidx.compose.material:material-icons-extended:1.5.1")
    ```
    **Data e tempo**: Para adicionar a data e o tempo para a tarefa e icone
    ```groovy
            implementation("joda-time:joda-time:2.10.10")
            implementation("androidx.compose.material:material-icons-extended:1.5.1")
    ```

- **Room**: Para a persistência de dados local, utilizando banco de dados SQLite.
    ```groovy
    implementation("androidx.room:room-runtime:2.5.0")
    ksp("androidx.room:room-compiler:2.5.0")
    implementation("androidx.room:room-ktx:2.5.0")  
    ```
- **Retrofit**: Para realizar chamadas de rede e consumir APIs externas.
    ```groovy
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    ```
-
### 3. Configurações Necessárias

- **SDK do Android**: Certifique-se de ter o SDK do Android instalado em seu sistema, assim como as dependências do Kotlin.
- **API Key**: Para consumir a API de conselhos ou qualquer outra API externa, você precisará de uma chave de API (API Key). A chave está configurada no código.

## Instruções para Instalação

1. **Clone o Repositório**: Use o comando abaixo para clonar o repositório em sua máquina local.
   ```bash
   git clone <https://github.com/LuizFelippeSilvaFagundes/ComposeTaskAppAndroid.git>

# Contato
- nome: Luiz Felippe Silva Fagundes
- email: luizfelippefagundes06@gmail.com
