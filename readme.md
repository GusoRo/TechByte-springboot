# TechByte

API REST desenvolvida utilizando Java 25, Spring Boot e MySQL, com execução em contêineres através do Podman.

---

## 1. Tecnologias utilizadas

- Java 25
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Maven
- Podman
- IntelliJ IDEA

## 2. Criação do projeto no Spring Initializr

Acesse o site do Spring Initializr:

https://start.spring.io/

Configure o projeto com os seguintes parâmetros:

| Campo | Configuração |
|---|---|
| Project | Maven |
| Language | Java |
| Group | com.TechByte |
| Artifact | TechByte |
| Name | TechByte |
| Package name | com.TechByte.TechByte |
| Packaging | Jar |
| Java | 25 |

### Dependências

Adicione as seguintes dependências:

- Spring Web
- Spring Data JPA
- MySQL Driver
- Spring Boot DevTools

<img width="762" height="856" alt="image" src="https://github.com/user-attachments/assets/c24acf93-0b6d-4e67-ab22-19988922822a" />


![Configuração do Spring Initializr](images/01-spring-initializr.png)



<img width="762" height="856" alt="image" src="https://github.com/user-attachments/assets/110aadf7-e16b-4498-92e4-e6ae050df5b0" />


Após configurar, clique em Generate, extraia o projeto e abra no IntelliJ IDEA.

<img width="1919" height="1029" alt="image" src="https://github.com/user-attachments/assets/33371f0c-0845-4b1a-83be-4b3bbb1b6a8f" />


![Projeto no IntelliJ](images/03-intellij.png)

---

## 3. Estrutura do projeto

O projeto foi organizado da seguinte maneira:

```text
TechByte/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/TechByte/TechByte/
│       │       ├── controller/
│       │       │   └── ProdutoController.java
│       │       ├── model/
│       │       │   └── Produto.java
│       │       └── TechByteApplication.java
│       └── resources/
│           └── application.properties
├── pom.xml
└── Containerfile
```

**Imagem 4 — Estrutura de pastas do projeto**

![Estrutura do projeto](images/04-estrutura.png)

---

<img width="472" height="794" alt="image" src="https://github.com/user-attachments/assets/a85fd4f8-a45e-42c4-97ab-fe1c3ce66693" />


Primeiramente, foi criado o banco de dados responsável por armazenar os produtos.

Comando SQL:

```sql
CREATE DATABASE produto_ti;
```



![Banco de dados MySQL](images/05-mysql-database.png)

### Configuração do application.properties

No arquivo `application.properties`, foram adicionadas as configurações de conexão:

```properties
spring.datasource.url=${DB_URL:jdbc:mysql://localhost:3306/produto_ti}
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:sua_senha}

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Substitua `sua_senha` pela senha configurada no MySQL.

<img width="589" height="178" alt="image" src="https://github.com/user-attachments/assets/5ddf2f7b-14fc-48b3-ba1d-faac73ce5402" />


![Configuração do banco](images/06-application-properties.png)

---

## 5. Criação da entidade Produto

Foi criada a classe `Produto.java` dentro do pacote `model`.

A entidade representa os produtos que serão armazenados no banco de dados.

Atributos utilizados:

- `id`: identificador do produto.
- `nome`: nome do produto.
- `descricao`: descrição do produto.
- `preco`: preço do produto.

O identificador utiliza as anotações `@Id` e `@GeneratedValue`, permitindo a geração automática do ID pelo banco de dados.

<img width="854" height="841" alt="image" src="https://github.com/user-attachments/assets/f7f451e5-fb26-4653-9089-2b5c325ccece" />
<img width="803" height="611" alt="image" src="https://github.com/user-attachments/assets/017ad59b-61f7-4741-9fed-1efaf0679d05" />



![Classe Produto](images/07-model-produto.png)

---

## 6. Criação do Controller

Foi criada a classe `ProdutoController.java` dentro do pacote `controller`.

O Controller é responsável por receber as requisições HTTP e retornar as respostas da API.

O projeto utiliza `@RestController` para identificar a classe como um controlador REST.

As rotas foram organizadas utilizando:

| Anotação | Função |
|---|---|
| `@RestController` | Define um controlador REST |
| `@RequestMapping` | Define o caminho principal |
| `@PostMapping` | Recebe requisições POST |
| `@GetMapping` | Recebe requisições GET |

![Controller](images/08-controller.png)

---

## 7. Executando o projeto localmente

Antes de utilizar o Podman, foi realizada a execução local da aplicação para verificar se a conexão com o banco de dados estava funcionando.

No terminal do IntelliJ, execute:

**Windows:**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux:**

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em:

`http://localhost:8080`

**Imagem 9 — Aplicação em execução**

![Aplicação executando](images/09-aplicacao-executando.png)

---

## 8. Testando a API

As rotas disponíveis são:

| Método HTTP | Rota | Função |
|---|---|---|
| POST | /produtos | Cadastrar produto |
| GET | /produtos | Listar produtos |

Para acessar a listagem:

`http://localhost:8080/produtos`

Para cadastrar um produto, utilize uma ferramenta como Postman ou Insomnia e envie uma requisição POST.

**Imagem 10 — Teste de cadastro de produto**

![Teste POST](images/10-teste-post.png)

![Teste GET](images/11-teste-get.png)

---

## 9. Instalação do Podman

O Podman foi utilizado para executar a aplicação e o banco de dados em contêineres separados.

Site oficial:

https://podman.io/

Após a instalação, verifique a versão:

```bash
podman --version
```

No Windows, inicialize a máquina virtual:

```bash
podman machine init
podman machine start
```

**Imagem 12 — Podman instalado**

![Podman instalado](images/12-podman-version.png)

---

## 10. Gerando o arquivo JAR

Antes de construir a imagem da aplicação, foi necessário gerar o arquivo JAR.

**Windows:**

```powershell
.\mvnw.cmd clean package -DskipTests
```

**Linux:**

```bash
./mvnw clean package -DskipTests
```

O arquivo será gerado dentro da pasta `target`.


![Arquivo JAR](images/13-jar.png)

---

## 11. Criando o Containerfile

Na raiz do projeto, foi criado o arquivo `Containerfile`.

```dockerfile
FROM docker.io/library/eclipse-temurin:25-jre

WORKDIR /app

COPY target/TechByte-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

O nome do arquivo JAR deve corresponder ao arquivo gerado pelo Maven.


![Containerfile](images/14-containerfile.png)

---

## 12. Criando a rede do Podman

Foi criada uma rede para permitir a comunicação entre a aplicação e o banco de dados.

```bash
podman network create techbyte-network
```

![Rede Podman](images/15-podman-network.png)

---

## 13. Criando o volume do MySQL

Para preservar os dados do banco mesmo após a remoção do contêiner, foi criado um volume:

```bash
podman volume create techbyte-mysql
```

**Imagem 16 — Volume criado**

![Volume Podman](images/16-podman-volume.png)

---

## 14. Executando o MySQL no Podman

O banco de dados foi executado em um contêiner separado.

```bash
podman run -d --name techbyte-db --network techbyte-network -e MYSQL_ROOT_PASSWORD=senha_root -e MYSQL_DATABASE=produto_ti -e MYSQL_USER=techbyte -e MYSQL_PASSWORD=senha_techbyte -p 3306:3306 -v techbyte-mysql:/var/lib/mysql docker.io/library/mysql:8.4
```

Aguarde a inicialização do MySQL.

Para verificar os logs:

```bash
podman logs techbyte-db
```

**Imagem 17 — Contêiner MySQL em execução**

![MySQL no Podman](images/17-mysql-container.png)

---

## 15. Construindo a imagem da aplicação

Com o arquivo JAR e o Containerfile preparados, foi construída a imagem da aplicação:

```bash
podman build -t techbyte .
```

Para visualizar as imagens disponíveis:

```bash
podman images
```

**Imagem 18 — Imagem TechByte criada**

![Imagem criada](images/18-podman-build.png)

---

## 16. Executando a aplicação no Podman

A aplicação foi executada em outro contêiner, conectado à mesma rede do MySQL.

```bash
podman run -d --name techbyte-app --network techbyte-network -e DB_URL=jdbc:mysql://techbyte-db:3306/produto_ti -e DB_USERNAME=techbyte -e DB_PASSWORD=senha_techbyte -p 8080:8080 techbyte
```

A aplicação estará disponível em:

`http://localhost:8080`

**Imagem 19 — Aplicação executando no Podman**

![Aplicação no Podman](images/19-app-container.png)

---

## 17. Verificando os contêineres

Para visualizar os contêineres em execução:

```bash
podman ps
```

Para visualizar todos os contêineres:

```bash
podman ps -a
```

Para consultar os logs:

```bash
podman logs techbyte-app
```

**Imagem 20 — Contêineres em execução**

![Contêineres Podman](images/20-podman-ps.png)

---

## 18. Teste final da aplicação

Com a aplicação e o MySQL executando no Podman, foi realizado o teste final da API.

Listagem de produtos:

`http://localhost:8080/produto`

Requisição POST para `/produtos`.

**Imagem 21 — Resultado final da API**

![Resultado final](images/21-resultado-final.png
