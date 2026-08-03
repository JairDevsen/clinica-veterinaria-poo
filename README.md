# Sistema de Gerenciamento para Clínica Veterinária

Este repositório contém a implementação da primeira etapa do projeto prático para a avaliação da Segunda VA da disciplina de Programação Orientada a Objetos (POO).

---

## Contexto Acadêmico

- **Instituição:** Universidade Federal do Agreste de Pernambuco
- **Curso:** Bacharelado em Ciências da Computação
- **Disciplina:** Programação Orientada a Objetos
- **Professor:** Igor Medeiros Vanderlei
- **Período:** II - Turno Noite
- **Alunos:** Jair Claudino de Melo Filho, Ariel da Silva, Heitor Quitino Brasil Marques e Fábio Alex da Silva Miranda
- **Etapa:** Semana 1 — Modelagem, Persistência e Arquitetura em Camadas

---

## Visão Geral do Projeto

A aplicação consiste em uma API REST desenvolvida em Java com Spring Boot para gerenciar as operações de uma clínica veterinária, incluindo cadastros de clientes, consultas, veterinários e salas de atendimento.

---

## Arquitetura e Decisões de Projeto

A solução segue a arquitetura cliente-servidor orientada a objetos com separação de responsabilidades em camadas:

1. **Camada de Modelo (`model`):**
   - Entidades mapeadas com Spring Data JPA/Hibernate.
   - Herança configurada através da estratégia `JOINED` para suportar as especializações de pessoas e salas.
   - Identificadores numéricos do tipo `Long` e manipulação de datas via `java.time.LocalDate`.

2. **Camada de Repositório (`repository`):**
   - Interfaces herdando de `JpaRepository` para abstração e execução de operações de I/O com o banco de dados.

3. **Camada de Serviço (`service`):**
   - Contém a lógica de negócio isolada dos controladores e da persistência.

4. **Objetos de Transferência de Dados (`dto`):**
   - Estruturas dedicadas para trafegar os dados na API REST, prevenindo problemas de referência circular no Jackson durante a serialização de relacionamentos bidirecionais.

5. **Fachada (`facade`):**
   - Classe `VeterinaryClinicFacade` atuando como ponto único de entrada para os serviços, simplificando o acesso a partir da camada REST.

6. **Camada de Comunicação (`controller`):**
   - Controladores `@RestController` que consomem e retornam dados estritamente no padrão JSON.

---

## Diagrama de Classes

A modelagem de classes revisada e adequada às restrições do ambiente relacional encontra-se na raiz do projeto:

- `diagrama_classes.png`

---

## Execução e Testes

### Pré-requisitos
- JDK 21 ou superior
- PostgreSQL instalado e em execução (para ambiente de desenvolvimento)

### Testes Automatizados
Para executar a suíte de testes unitários e de integração (utilizando o banco H2 em memória):

```bash
./mvnw clean test