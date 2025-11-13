# 🥭 Backend System of Hortifruti Mikami  
### 🚧 In Processing...  
#### 📚 Project UNIP – PIM IV  

---

## 🥬 Sobre o Projeto
Este projeto representa o **backend do sistema de gestão do Hortifruti Mikami**, desenvolvido como parte do **Projeto Integrado Multidisciplinar (PIM IV)** da **Universidade Paulista – UNIP**.  

O objetivo é criar uma solução que **automatize e otimize a administração interna** de um hortifruti, abrangendo o controle de estoque, produtos, fornecedores, promoções e vendas.  
O backend foi construído com **Java (Spring Boot)** e **MongoDB**, seguindo o padrão **RESTful API** e **arquitetura em camadas**.  

> 💡 O sistema busca simplificar processos, reduzir erros manuais e oferecer uma base sólida para futuras integrações com um frontend.

---

## 🧱 Arquitetura do Sistema
A aplicação segue o padrão de **arquitetura em camadas**, garantindo modularidade, manutenibilidade e escalabilidade:

- **Controller:** recebe e responde às requisições HTTP (endpoints REST).  
- **Service:** contém as regras de negócio e lógicas de processamento.  
- **Repository:** realiza a comunicação com o banco de dados MongoDB.  
- **Model:** define as entidades e estruturas de dados do sistema.  
- **DTOs:** controlam o tráfego de dados entre as camadas, reforçando segurança e organização.  

O sistema também conta com um **serviço automático de monitoramento (`MonitorService`)**, que verifica diariamente produtos próximos ao vencimento e emite alertas, evitando desperdícios.

---

## ⚙️ Funcionalidades Principais

- 🗂️ **Cadastro de Categorias** – Organização dos tipos de produtos (frutas, verduras, legumes, etc.).  
- 🤝 **Cadastro de Fornecedores** – Controle de informações e parcerias comerciais.  
- 🛒 **Cadastro de Produtos** – Registro completo de produtos com dados detalhados.  
- 📦 **Controle de Estoque** – Atualização automática das quantidades disponíveis.  
- 💸 **Criação de Promoções** – Descontos fixos ou percentuais para produtos selecionados.  
- 🧾 **Gestão de Vendas** – Registro e acompanhamento de transações realizadas.  
- ⏰ **Monitoramento Automático** – Alerta diário de produtos próximos ao vencimento.  
- 📊 **Geração de Relatórios** – Visualização de desempenho, vendas e movimentações.

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
- ☕ **Java 17+**  
- 🌱 **Spring Boot**  
- 🍃 **MongoDB**  
- ⚙️ **Spring Data**  
- 🌐 **REST API**  
- 🧩 **DTO Pattern**

---

## 🚀 Execução do Projeto

1. Clone o repositório:  
   ```bash
   git clone https://github.com/SEU_USUARIO/BackendHortifruti.git
   ```
2. Acesse o diretório do projeto:  
   ```bash
   cd BackendHortifruti
   ```
3. Execute o projeto:  
   ```bash
   mvn spring-boot:run
   ```

> O servidor iniciará em: `http://localhost:8080`  

---

## ✅ Conclusão
O **Backend System of Hortifruti Mikami** demonstrou a aplicação prática de conceitos fundamentais de **engenharia de software, arquitetura em camadas e APIs REST**.  
Além de atender aos objetivos propostos, o sistema fornece uma base sólida para futuras melhorias e integração com o frontend.  

---

## 👨‍💻 Autores
- Caio Mendes Barradas – R090AD6  
- Gabriel Rodrigues Ramos – R101IG0  
- Lucas Ramos Pereira – G083GI8  
- **Ryan Gomes Xavier – R1019F2**  
- Samuel Carvalho Baia – R1981F8  

---

© 2025 – Universidade Paulista (UNIP) | Projeto PIM IV – Sistema de Gestão Hortifruti Mikami
