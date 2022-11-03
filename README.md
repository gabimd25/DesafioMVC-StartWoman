<h1>
GFT-MILHAS
</h1>

<p>Projeto desenvolvido para aplicação dos conceitos da arquitetura MVC (Model-View-Controller) durante o programa Starter Woman da 
<strong> <a href="https://www.gft.com/br/pt">GFT Brasil</a></strong> 🧡💛</p>

<p>Aplicação web de gerenciamento de Eventos desenvolvido conforme o enunciado proposto como desafio.</p>

<h2> 🤝 Colaboradores - Grupo 4</h2>

🔹Ariana Russo

🔹Edyane Araujo

🔹Isabela lima

🔹Gabriela Domingues


<h2>Funcionalidades</h2>

<p>Voltado para gestão de eventos e possível: </p>

- [x] Cadastrar e editar usuários do sistema;

- [x] Cadastrar, editar, listar , deletar e pesquisar por nome os eventos;

- [x] Cadastrar, editar, listar, deletar e pesquisar por nome e/ou quatro letras os participantes dos eventos;

- [x] Cadastrar, editar, listar e deletar grupos e atividades dos eventos;

- [x] Registrar as presenças, atrasos e ausências dos participantes dos eventos;

- [x] Registrar o cumprimento das atividades dos eventos, de cada participante dos grupos;

- [x] Visualizar de todos os grupos;

<h2> ⭐️ Tecnologias utilizadas </h2>

- [x] Plataforma: STS - Spring Too Suite;

- [x] Dependências: MySql Driver, Spring Web, Spring Boot Dev Tools, Thymeleaf, Spring Data JPA, Validation, Spring Security;

- [x] Linguagem do Software: Java

- [x] JDK 17

- [x] Banco de Dados: MySQL Workbench

<h2> 👣 Passo-a-Passo</h2>

1. Abrir o projeto na IDE;

2. Abrir a pasta application.properties e trocar username e password do mysql;

3. Executar o Scrip "gft-milhas.sql", para popular banco de dados (arquivo gerado pelo MySQL workbench);

4. Executar o projeto;

5. Clique <strong> <a href="http://localhost:8080/group/ranking"> aqui </a></strong> para ser direcionado a tela do ranking;

6. Ao clicar no botão “Entrar” será direcionado para cadastro de usuário e após para login;

7. Ao acessar o sistema clique em "Eventos/Listar" e terá acesso a dois eventos pré-cadastrados;

<div align="center">
<img src="/uploads/aba01f7a6f8c7525620ca30301840c0f/lista_eventos.png" width="600px"/>
</div>

8. Clique em “Editar” e terá acesso aos grupos com participantes também pré-cadastrados;

<div align="center">
<img src="/uploads/1e4e05a2bd9a6ab28c461e9f8b555549/tela_eventos.png" width="500px"/>
</div>

9. Clique em "Adicionar Presenças" para atribuir presença/atraso/ausente para cada participante do evento;

<div align="center">
<img src="/uploads/a6271ea3f73210c667b22d897d863d27/resgistro_presença.png" width="600px"/>
</div>

10. Clique em “Editar Atividade” para atribuir a realização da mesma aos participantes do evento;

11. Clique em “Criar Grupo” para adicionar um novo grupo ao evento assim como os participantes deste mesmo grupo;

12. Da mesma maneira ao clicar em “Criar Atividade” é possível criar uma nova atividade para este evento;

13. Para criar novos participantes clique em "Participantes/Cadastrar";

14. Ao clicar em "Participantes/Listar" é possível buscar, editar e excluir um participante;

15. Ao clicar em “Ranking” será direcionado para tela de ranking, onde é possível visualizar a classificação de todos os grupos.
