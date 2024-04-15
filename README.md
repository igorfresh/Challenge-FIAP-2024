## Personal Buy - Challenge FIAP 2024

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
Igor | 15/04 | Finalização de validação

## Diagramas e arquitetura

<img src="/imgs/img20_page-0001.jpg">

## Video explicativo
[[Video Pitch]](https://www.youtube.com/watch?v=UZNU6DygUj8)

 ## Documentação

 ### Endpoints

### Client

`GET` /client - Retorna um array com todas os clientes cadastrados.

`POST` /client - Cadastrar um novo usuário com os dados enviados no corpo da requisição.

`GET` /client/`{id}` - Retorna os dados detalhados do client com o `id` informado no parametro de path.

`DELETE` /client/ `{id}` - Apaga o client indicado pelo `id` enviado no parametro de path. 

`PUT` /client/`{id}` - Atualizar os dados do cliente com o `id` informado no path, utilizando os novos dados enviados no corpo da requisição.

```js
{
	"name": "Tamires Alves",
	"cpf": "123.456.789-90",
	"email": "tamialves@gmail.com.br",
	"phone":"(11) 91234-5678",
	"adress": "Rua Pesadelo Java, 777",
	"city": "Sâo Paulo",
	"state": "SP",
	"cep": "12345-678"
}
```
### Product

`GET` /product - Retorna um array com todos os produtos cadastrados.

`POST` /product - Cadastrar um novo produto com os dados enviados no corpo da requisição.

`GET` /product/`{id}` - Retorna os dados detalhados do produto com o `id` informado no parametro de path.

`DELETE` /product/ `{id}` - Apaga o produto indicado pelo `id` enviado no parametro de path. 

`PUT` /product/`{id}` - Atualizar os dados do produto com o `id` informado no path, utilizando os novos dados enviados no corpo da requisição.

```js
{
	"name": "Mouse Gamer",
	"price": 45.99
}
```
### Buy

`GET` /buy - Retorna um array com todos as compras cadastradas.

`POST` /buy - Cadastrar uma nova compra com os dados enviados no corpo da requisição.

`GET` /buy/`{id}` - Retorna os dados detalhados da compra com o `id` informado no parametro de path.

`DELETE` /buy/ `{id}` - Apaga a compra indicado pelo `id` enviado no parametro de path. 

`PUT` /buy/`{id}` - Atualizar os dados da compra com o `id` informado no path, utilizando os novos dados enviados no corpo da requisição.

```js
{
	"idClient": "1",
	"datePurchase": "2023-10-10",
	"purchaseStatus": "Em transito",
	"totalPurchaseValue": 21.99
}
```
### Product Category

`GET` /productCategory - Retorna um array com todos as categorias de produtos cadastradas.

`POST` /productCategory - Cadastrar uma nova categoria de produto com os dados enviados no corpo da requisição.

`GET` /productCategory/`{id}` - Retorna os dados detalhados da categoria do produto com o `id` informado no parametro de path.

`DELETE` /productCategory/ `{id}` - Apaga a categoria do produto indicado pelo `id` enviado no parametro de path. 

`PUT` /productCategory/`{id}` - Atualizar os dados da categoria do produto com o `id` informado no path, utilizando os novos dados enviados no corpo da requisição.

```js
{
	"name": "Mouses"
}
```
### Purchased Itens

`GET` /purchasedItens - Retorna um array com todos os produtos comprados de produtos cadastradas.

`POST` /purchasedItens - Cadastrar novos produtos comprados com os dados enviados no corpo da requisição.

`GET` /purchasedItens/`{id}` - Retorna os dados detalhados dos produtos comprados com o `id` informado no parametro de path.

`DELETE` /purchasedItens/ `{id}` - Apaga os produtos comprados indicado pelo `id` enviado no parametro de path. 

`PUT` /purchasedItens/`{id}` - Atualizar os dados dos produtos comprados com o `id` informado no path, utilizando os novos dados enviados no corpo da requisição.

```js
{
	"idProduct": "1",
	"idBuy": "3",
	"quantityItens": 5,
	"unityPrice": 10.99
}
```

### Como rodar a aplicação:

Clonar o repositório, abri-lo numa IDE apropriada (no VS-code, por exemplo, ter as extensões apropriadas do Java e SpringBoot) e clicar no botão de run java.

