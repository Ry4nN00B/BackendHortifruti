# 🥬 Sistema de Gestão de Hortifruti – Back-End

## 📘 Introdução
Este projeto apresenta o desenvolvimento de um **sistema backend** voltado ao gerenciamento interno de um hortifruti.  
O sistema foi desenvolvido em **Java com Spring Boot** e **MongoDB**, com foco em melhorar o controle de estoque, cadastro de fornecedores, categorias, promoções e vendas.

> Estrutura em camadas (Controller, Service, Repository e Model), uso de **DTOs** e **arquitetura REST** garantem modularidade, segurança e escalabilidade.

---

## 🧱 Arquitetura do Sistema
A aplicação segue o padrão de **arquitetura em camadas**:

- **Controller:** recebe e responde às requisições HTTP (endpoints REST).  
- **Service:** contém as regras de negócio e processamento principal.  
- **Repository:** faz a comunicação com o banco de dados MongoDB.  
- **Model:** define as entidades do sistema.  
- **DTOs:** controlam o tráfego de dados entre as camadas.

O sistema possui também um **serviço automático de monitoramento** (`MonitorService`) que verifica diariamente os produtos próximos do vencimento e emite alertas.

---

## ⚙️ Funcionalidades Principais

- **Cadastro de Categorias:** registro e organização de tipos de produtos.  
- **Cadastro de Fornecedores:** gerenciamento de informações e contatos.  
- **Cadastro de Produtos:** inserção e atualização de dados dos itens.  
- **Controle de Estoque:** acompanhamento em tempo real das quantidades.  
- **Criação de Promoções:** descontos fixos ou percentuais.  
- **Vendas:** registro e acompanhamento de vendas realizadas.  
- **Monitoramento Automático:** verificação diária de validade e estoque.  
- **Relatórios:** geração de relatórios de desempenho, estoque e vendas.

---

## 🌐 Endpoints da API

| Recurso | Descrição |
|----------|------------|
| `/auth` | Autenticação e gerenciamento de usuários. |
| `/categorias` | CRUD de categorias. |
| `/fornecedores` | CRUD de fornecedores. |
| `/produtos` | CRUD de produtos. |
| `/estoque` | Controle de estoque e lotes. |
| `/promocoes` | Gerenciamento de promoções. |
| `/vendas` | Registro e acompanhamento de vendas. |
| `/relatorios` | Relatórios de estoque, vencimento e financeiro. |

---

## 🛠️ Tecnologias Utilizadas
- **Java 17+**  
- **Spring Boot**  
- **MongoDB**  
- **Spring Data**  
- **REST API**  
- **DTO Pattern**

---

## ✅ Conclusão
O projeto atingiu seus objetivos ao implementar um backend funcional e organizado, aplicando boas práticas de arquitetura, banco de dados e API REST.  
Futuras melhorias incluem novas validações, integração com sistemas externos e o desenvolvimento de uma interface visual.

---

## 👨‍💻 Autores
- Caio Mendes Barradas – R090AD6  
- Gabriel Rodrigues Ramos – R101IG0  
- Lucas Ramos Pereira – G083GI8  
- **Ryan Gomes Xavier – R1019F2**  
- Samuel Carvalho Baia – R1981F8  
