# Live chat server

### With given objective of testing websocket layer and auth path interception

## 🔧 <b>Configuration file (application.yaml)</b> 🔧

> [!IMPORTANT]
> Add those files to your .gitignore if you want yo publish the copy of this project

```yaml
spring:
  graphql:
    graphiql:
      enabled: true
  devtools:
    restart:
      enabled: true
    livereload:
      enabled: false
  datasource:
    ...Your datasource configs
  jooq:
   ...Dialect and more
  jpa:
    hibernate:
      ddl-auto: ...
    show-sql: true
provider: "org.hibernate.jpa.HibernatePersistenceProvider"
server:
  port: 3000
properties:
  private_key: $KEY
  serverUrl: $SERVER_URL
```



## 🔨 <b>Packing application</b> 🔨

```bash
    mvn clean package
```

## ▶️  <b>Running the project</b> ▶️

- Direct run process:

```bash
    mvn clean spring-boot:run
```

- Jar version

```bash
    java -jar target/chat-app-3.1.3.jar
```

## 🔍 <b>Testing</b> 🔍

```bash
    mvn test
```