# Star Wars Resistence Social Network API

O império continua sua luta incessante de dominar a galáxia, tentando ao máximo expandir seu território e eliminar os rebeldes.

Essa API REST foi desenvolvolvida visando o compartilhamento de recursos entre os rebeldes.

## Funcionalidades

  - Adicionar rebeldes: inclui os dados do rebelde, sua localização, além de recursos (inventário) em sua posse
  
  - Atualizar localização do rebelde: atualiza a última localização do rebelde
  
  - Reportar o rebelde como um traidor: quando algum rebelde trair a resistência e se aliar ao império, deve ser possível reportar o rebelde como traidor.

    - Um traidor não pode negociar os recursos com os demais rebeldes, não pode manipular seu inventário, nem ser exibido em relatórios
    
    - Um rebelde é marcado como traidor quando, ao menos, três outros rebeldes reportarem a traição.
  
  - Negociar itens: os rebeldes poderão negociar itens entre eles de acordo com as pontuações definidas para cada item.
  
    - Ambos os lados deverão oferecer a mesma quantidade de pontos. 
    
      - Exemplo: 1 arma e 1 água (1 x 4 + 1 x 2) valem 6 comidas (6 x 1) ou 2 munições (2 x 3)

    - A negociação em si não será armazenada, mas os itens deverão ser transferidos de um rebelde a outro.
    
  - Relatórios
  
    - Porcentagem de traidores.
    
    - Porcentagem de rebeldes. 
    
    - Quantidade média de cada tipo de recurso por rebelde (Ex: 2 armas por rebelde)
    
    - Pontos perdidos devido a traidores.          

  - Consulta dos itens do inventário: quais são os itens que os rebeldes podem vir a adquirir através de negociações e respectivas pontuações. 
  
## Exemplos de payloads para utilizar a API

  - Adicionar rebelde com inventário de 10 armas. O id dos itens de inventário pode ser obtido em GET /v1/itens-inventario

    ```json  
    POST /v1/rebeldes
    
    {
      "genero": "FEMININO",
      "idade": 40,
      "itens_inventario": [
      {
       "id": 1,
       "quantidade": 10
      }
     ],
     "latitude": -29.795456,
     "longitude": -51.152127,
     "nome": "Mariana",
     "nomeBaseGalaxia": "República Galáctica"
    } 
    ```       
    
  - Atualizar localização do rebelde com id 1
  
    ```json
    PATCH /v1/rebeldes/1/localizacao
    
    {
     "latitude": 29.925597,
     "longitude": -51.078866,
     "nome_base_galaxia": "Nova República"
    }    
    ```    
     
  - Reportar o rebelde com id 1 como traidor. Quem está reportando a traição é o rebelde com id 2
  
    ```json
    PATCH /v1/rebeldes/1/reporte-traidor
    
    {
     "rebelde_id": 2
    }               
    ```
        
  - Negociação dos itens do rebelde com id 1 com o rebelde de id 2
  
    - O rebelde com id 1 está trocando **2 armas + 1 munição + 1 comida** com o rebelde 2 que está fornecendo **2 munições + 3 águas**
    
    - Cada rebelde fornecendo 12 pontos na troca 

    ```json
    PATCH /v1/rebeldes/1/negociar-itens                
    
    {
      "rebeldeDestinoItems": [   
        {
          "id": 2,
          "quantidade": 2
        },
         {
          "id": 3,
          "quantidade": 3
        }
      ],
      "rebeldeIdDestino": 2,
      "rebeldeOrigemItems": [
        {
          "id": 1,
          "quantidade": 2
        },
        {
          "id": 2,
          "quantidade": 1
        },
         {
          "id": 4,
          "quantidade": 1
        }
      ]
    }               
    ```              
    
## Tecnologias     

  - [Java 11](https://www.oracle.com/java/)

  - [Spring Boot](https://spring.io/projects/spring-boot)

  - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
  
  - [H2 Database](https://www.h2database.com/html/main.html)

  - [Gradle](https://gradle.org/)

  - [Lombok](https://projectlombok.org)
  
  - [Swagger](https://springfox.github.io/springfox/)  

  - [JUnit 5](https://junit.org/junit5/)

  - [Mockito](https://site.mockito.org/)

  - [Hamcrest](http://hamcrest.org/JavaHamcrest/)  
     
## Executar a aplicação      

```
Matar processo rodando na porta 8080
$ fuser -k 8080/tcp   

Configurando e validando a versão do Java com o SDKMan 
$ sdk use java 11.0.8.hs-adpt
$ sdk current java

Rodar a aplicação
$ ./gradlew bootRun

```
## Executar os testes unitários

```
Executa com log
$ ./gradlew clean test

Executa sem log
$ ./gradlew test
```

## Documentação

  - [Swagger](http://localhost:8080)
  
## Banco de Dados

  - [H2](http://localhost:8080/h2)
  
    - Os dados da URL, usuário e senha estão no arquivo application.yml
  
## Referências

  - [SDKMan](https://sdkman.io/usage)
  
  - [A Galáxia](https://starwars.fandom.com/pt/wiki/Legends:A_Gal%C3%A1xia)
  
  - [Swagger 2 + Spring 2.6.2 - Failed to start bean 'documentationPluginsBootstrapper' in spring data rest](https://stackoverflow.com/a/70503395)
  
  - [JPQL Constructor Expressions - SELECT NEW](https://docs.oracle.com/html/E13946_04/ejb3_langref.html#ejb3_langref_constructor)
  
  - [Result Set Mapping: Constructor Result Mappings](https://thorben-janssen.com/result-set-mapping-constructor-result-mappings/)
  
  - [Jackson – Bidirectional Relationships](https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion)
  
    - [Use @JsonManagedReference, @JsonBackReference - recursão infinita](https://www.baeldung.com/jackson-bidirectional-relationships-and-infinite-recursion#managed-back-reference)
    
  - [Logging while testing through Gradle](https://stackoverflow.com/a/37153026)