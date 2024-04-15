z# Personal Buy - Challenge FIAP 2024

Repositório destinado ao projeto do Challenge de 2024 da FIAP, na matéria de Java Advanced da faculdade.

 A solução proposta é desenvolver uma inteligência artificial (IA) alimentada por algoritmos avançados de aprendizado de máquina e análise de dados. Este sistema será capaz de coletar e analisar grandes volumes de dados de compra, incluindo histórico de compras, preferências de produtos, comportamento de navegação e até mesmo informações demográficas e contextuais.

 Com base nessa análise, o sistema será capaz de gerar ofertas personalizadas para cada cliente, adaptadas às suas preferências individuais e comportamento de compra passado. Isso pode incluir descontos em produtos relevantes, sugestões de produtos complementares ou pacotes promocionais exclusivos. Além disso, o sistema será capaz de ajustar dinamicamente suas recomendações com base em interações em tempo real, como itens adicionados ao carrinho ou pesquisas recentes.

Benefícios Esperados:
- Aumento do faturamento: Oferecer ofertas personalizadas aumentará as taxas de conversão e o valor médio do pedido, resultando em um aumento significativo no faturamento da empresa.

- Melhor experiência do cliente: Os clientes se sentirão mais valorizados e compreendidos, aumentando a fidelidade à marca e a satisfação geral do cliente.

- Eficiência operacional: Automatizar o processo de recomendação de ofertas reduzirá a carga de trabalho manual e liberará recursos para outras áreas estratégicas do negócio.

## Integrantes

Igor Miguel Silva: Desenvolvedor back-end

João Pedro Costa Feitosa: Administrador do banco de dados

Kaue Matheus Santana: Desenvolvedor Machine Learning e QA

Gustavo René Dias Boamorte: Desenvolvedor .NET

Pedro Felipe Barros da Silva: Desenvolvedor Mobile

## Cronograma de Desenvolvimento

| Desenvolvedor | Data |  Tarefa
|--------|------|:-------------:|
Igor | 07/04 | Criação Controller Client
João | 08/04 | Criação Product Model
Kaue | 09/04 | Criação Compra Model
Kaue | 10/04 | Readme - endpoint client
João | 11/04 | Diagrama de entidades
Igor | 13/04 | Refatoração de entidades
Igor | 14/04 | Finalização projeto - sem validação
Igor | 15/04 | Implementação de validação

## Diagramas e arquitetura

<img src="/imgs/img20_page-0001.jpg">

## Video explicativo
[![Video Youtube]](https://www.youtube.com/watch?v=UZNU6DygUj8)

## Requisitos

 - [ ] CRUD de Clientes
 - [ ] CRUD de Produtos
 - [ ] CRUD de Usuários

 ## Documentação


 ### Endpoints

- [Listar clientes](#listar-cliente)
- [Cadastrar clientes](#cadastrar-cliente)
- [Detalhes do cliente](#detalhes-do-cliente)
- [Apagar cliente](#apagar-cliente)
- [Atualizar cliente](#atualizar-cliente)

### Listar Clientes
`GET` /client

Retorna um array com todos clientes cadastradas.

#### Exemplo de resposta
```js
[
    {
        "nome": "Cadernos",
        "icone": "notebook"
    },
    {
        "id": 2,
        "nome": "Canetas",
        "icone": "pen"
    }
]
```

#### Código de Status

| código | descrição
|--------|---------
|200 | Clientes retornadas com sucesso
|401 | Usuário não autenticado. Realize autenticação em /login

### Cadastrar Cliente

`POST` /cadastrar

Cadastrar um novo cliente com os dados enviados no corpo da requisição.

#### Corpo da Requisição

| campos | tipo | obrigatório | descrição
|--------|------|:-------------:|----------
|nome|string|✅| Um nome curto para o cliente com pelo menos 3 caracteres
|icone|string|❌| O nome do ícone conforme Material Icons

#### Exemplo de Resposta

```js
{
    "nome": "Cadernos",
    "icone": "notebook"
}
```

#### Código de Status

| código | descrição
|--------|---------
|200 | Clientes retornados com sucesso
|400 | Validação falhou. Verifique as regras para o corpo da requisição.
|401 | Usuário não autenticado. Realize autenticação em /login

### Detalhes da Cliente
`GET` /client/`{id}`

Retorna os dados detalhados do cliente com o `id` informado no parametro de path.

### Exemplo de Resposta
```js
//requisição para /client/1
{
    "id": 1,
    "nome": "Cadernos",
    "icone": "notebook"
}
```

#### Código de Status

| código | descrição
|--------|----------
|200 | Clientes retornados com sucesso
|401 | Usuário não autenticado. Realize autenticação em /login
|404 | Não existe cliente com o `id` informado. Consulte lista em /categoria

### Apagar Cliente

`DELETE` /client/ `{id}`

Apaga o cliente indicada pelo `id` enviado no parametro de path. 

#### Código de Status

| código | descrição
|--------|----------
|204 | Cliente apagada com sucesso.
|401 | Usuário não autenticado. Realize autenticação em /login
|404 | Não existe cliente com o `id` informado. Consulte lista em /categoria

### Atualizar Cliente

`PUT` /client/`{id}`

Atualizar os dados do client com o `id` informado no path, utilizando os novos dados enviados no corpo da requisição.

#### Corpo da Requisição

| campos | tipo | obrigatório | descrição
|--------|------|:-------------:|----------
|nome|string|✅| Um nome curto para a categoria com pelo menos 3 caracteres
|icone|string|❌| O nome do ícone conforme Material Icons

```js
{
    "nome": "Cadernos",
    "icone": "notebook"
}
```

#### Código de Status

| código | descrição
|--------|----------
|200 | Cliente atualizado com sucesso.
|400 | Validação falhou. Verifique as regras para o corpo da requisição
|401 | Usuário não autenticado. Realize autenticação em /login
|404 | Não existe cliente com o `id` informado. Consulte lista em /categoria

## Como rodar a aplicação:

Clonar o repositório, abri-lo numa IDE apropriada (no VS-code, por exemplo, ter as extensões apropriadas do Java e SpringBoot) e clicar no botão de run java.

