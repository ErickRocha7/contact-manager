# Contact Manager

Uma aplicação de console para gerenciamento de contatos desenvolvida em Java. Este projeto possui caráter estritamente **didático**, projetado para consolidar os pilares fundamentais da Programação Orientada a Objetos (POO) e as melhores práticas de Engenharia de Software.

O objetivo principal foi evoluir o código além da sintaxe básica, focando na integridade do estado dos objetos, gerenciamento seguro de recursos e design arquitetural em camadas.

---

## 🧠 Conceitos e Recursos Abordados

### 1. Programação Orientada a Objetos (POO)
* **Abstração**: Uso de superclasse abstrata (`Person`) para encapsular atributos e comportamentos comuns compartilhados por subclasses.
* **Polimorfismo**: Implementação da interface `Searchable` em diferentes entidades (`Contact` e `Group`), permitindo buscas dinâmicas desacopladas.
* **Encapsulamento Estrito (Invariantes de Objeto)**: Setters e construtores robustos que atuam como defesas do sistema, impedindo que qualquer objeto entre em estado inválido na memória.
* **Tipos Enumerados (Enums)**: Modelagem de categorias com propriedades (`Category`) para garantir Type Safety no domínio do negócio.

### 2. Engenharia e Arquitetura de Software
* **Arquitetura em Camadas (Clean Architecture)**: Divisão clara de responsabilidades entre representação de dados (`model`), tratamento de falhas (`exception`) e lógica de negócio (`service`).
* **Princípio da Inversão de Dependência (DIP)**: O core do domínio (`model`) é completamente isolado e independente de camadas ou serviços externos.
* **Convenção de Nomenclatura de Mercado**: Organização do repositório em *kebab-case*, classes em *PascalCase* e estrutura de pacotes baseada em domínio reverso (`br.com.erickrocha`).

### 3. Recursos Avançados da Linguagem Java
* **Gerenciamento de Recursos (Try-With-Resources)**: Controle automatizado do ciclo de vida do fluxo de entrada (`System.in`), mitigando vazamentos de memória (*resource leaks*).
* **Tratamento Customizado de Exceções**: Criação de exceções checadas (`InvalidContactException`) para regras de negócio estruturadas.
* **Coleções Genéricas e Mapas**: Uso extensivo de `ArrayList` para manipulação dinâmica e `HashMap` para fluxos complexos de agrupamento de dados.
* **Otimização de Expressões Regulares (Regex)**: Compilação estática de padrões (`Pattern.compile`) executada uma única vez na carga da classe para ganho substancial de performance.
* **Manipulação Eficiente de Strings**: Uso de `StringBuilder` para construção dinâmica de interfaces de console e `printf` para saídas formatadas.

---

## 📂 Estrutura Estrutural do Projeto

```text
contact-manager/
└── src/
    └── main/
        └── java/
            └── br/
                └── com/
                    └── erickrocha/
                        └── contactmanager/
                            ├── model/
                            │   ├── Category.java
                            │   ├── Person.java
                            │   ├── Contact.java
                            │   ├── Group.java
                            │   └── Searchable.java
                            ├── exception/
                            │   └── InvalidContactException.java
                            ├── service/
                            │   └── ContactManager.java
                            └── ContactManagerApplication.java
```

---

## 🚀 Como Executar

1. Certifique-se de ter o **JDK 17+** instalado em sua máquina.
2. Abra a pasta raiz `src/main/java` em sua IDE de preferência (como o VS Code).
3. Execute a classe principal: `ContactManagerApplication.java`.
