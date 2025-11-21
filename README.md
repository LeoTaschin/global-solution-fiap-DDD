# 📘 Upskilling Platform – Global Solution FIAP 2025

API RESTful desenvolvida com **Java + Spring Boot**, focada em Upskilling e Reskilling para apoiar profissionais nas carreiras do **Futuro do Trabalho (2030+)**.

---

## 👥 Integrantes

- **Leonardo Ceschim Taschin** – RM **554583**
- **Willian Moreira Brito Pinto** – RM **555152**

---

## 🎯 Resumo do Projeto

A aplicação consiste em uma API que gerencia **Usuários** e **Trilhas de Aprendizagem**, permitindo o cadastro, consulta, atualização e remoção desses recursos.  
O objetivo é promover o desenvolvimento contínuo de profissionais por meio de trilhas alinhadas às competências exigidas pelo mercado futuro.

O projeto segue os requisitos da disciplina:
- Arquitetura em camadas (**Controller → Service → Repository**)
- Dois CRUDs completos
- Bean Validation para validação
- Banco **H2** em memória
- Seeds iniciais
- Tratamento centralizado de exceções

Conectado diretamente ao tema **O Futuro do Trabalho** e aos **ODS 4, 8, 9 e 10**.

---

## 🛠 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.3.5**
- **Maven**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**
- **Bean Validation (Jakarta)**

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 21 instalado
- Maven 3.9+ instalado

### Passos para rodar

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação iniciará em:  
`http://localhost:8080`

### Console do H2

`http://localhost:8080/h2-console`

**Credenciais padrão:**
- JDBC URL: `jdbc:h2:mem:upskillingdb`
- Usuário: `sa`
- Senha: *(vazia)*

---

## 📁 Estrutura do Projeto

```
com.fiap.upskilling
 ├── controller
 ├── service
 ├── repository
 ├── domain
 ├── dto
 ├── exception
 └── seed
```

---

## 📌 Entidades Principais

### Usuário
- id
- nome *(obrigatório)*
- email *(obrigatório e único)*
- areaAtuacao
- nivelCarreira (JUNIOR, PLENO, SENIOR, TRANSICAO)
- dataCadastro

### Trilha
- id
- nome *(obrigatório)*
- descricao
- nivel (INICIANTE, INTERMEDIARIO, AVANCADO)
- cargaHoraria *(> 0)*
- focoPrincipal

---

## ✔️ CRUDs Implementados

### Usuários – `/api/usuarios`
- POST – criar
- GET – listar
- GET /{id} – buscar por id
- PUT /{id} – atualizar
- DELETE /{id} – remover

### Trilhas – `/api/trilhas`
Mesmas operações acima.

---

## ⚠️ Tratamento de Erros

A API possui um handler global para:
- Entidade não encontrada (404)
- Erros de validação (400)
- Regras de negócio
- Erros inesperados (500)

---

## 🌱 Seeds automáticos

Ao iniciar o projeto, são carregados usuários e trilhas exemplo:
- Usuários: níveis variados
- Trilhas: IA, Soft Skills, Cloud Native e outras

---

## 🌍 Conexão com o Futuro do Trabalho e ODS

O projeto contribui para:
- **ODS 4**: educação contínua
- **ODS 8**: mais oportunidades profissionais
- **ODS 9**: inovação e tecnologia
- **ODS 10**: redução das desigualdades

Promove trilhas de habilidades técnicas e humanas essenciais para 2030+.
