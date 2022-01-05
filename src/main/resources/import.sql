insert into item_inventario (descricao, pontuacao) values ('Arma', 4);
insert into item_inventario (descricao, pontuacao) values ('Munição', 3);
insert into item_inventario (descricao, pontuacao) values ('Água', 2);
insert into item_inventario (descricao, pontuacao) values ('Comida', 1);

insert into rebelde (genero, idade, latitude, longitude, nome, nome_base_galaxia, traidor) values ('MASCULINO', 18, -29.795456,  -51.152127, 'Sandro', 'Confederação de Sistemas Independentes', false);
insert into rebelde (genero, idade, latitude, longitude, nome, nome_base_galaxia, traidor) values ('MASCULINO', 18, -29.795456,  -51.152127, 'André', 'Confederação de Sistemas Independentes', false);
insert into rebelde (genero, idade, latitude, longitude, nome, nome_base_galaxia, traidor) values ('MASCULINO', 18, -29.795456,  -51.152127, 'João', 'Federação Galáctica das Alianças Livres', false);
insert into rebelde (genero, idade, latitude, longitude, nome, nome_base_galaxia, traidor) values ('FEMININO', 40, -29.795456,  -51.152127, 'Marcia', 'Aliança Rebelde', false);
insert into rebelde (genero, idade, latitude, longitude, nome, nome_base_galaxia, traidor) values ('FEMININO', 39, -29.795456,  -51.152127, 'Rosane', 'Império Galáctico', false);
insert into rebelde (genero, idade, latitude, longitude, nome, nome_base_galaxia, traidor) values ('FEMININO', 24, -29.795456,  -51.152127, 'Fabiane', 'República Galáctica', false);
insert into rebelde (genero, idade, latitude, longitude, nome, nome_base_galaxia, traidor) values ('MASCULINO', 16, -29.795456,  -51.152127, 'Sérgio', 'Nova República', true);

insert into rebelde_inventario (acessivel, rebelde_id) values (true, 1);
insert into rebelde_inventario (acessivel, rebelde_id) values (true, 2);
insert into rebelde_inventario (acessivel, rebelde_id) values (true, 3);
insert into rebelde_inventario (acessivel, rebelde_id) values (true, 4);
insert into rebelde_inventario (acessivel, rebelde_id) values (true, 5);
insert into rebelde_inventario (acessivel, rebelde_id) values (true, 6);
insert into rebelde_inventario (acessivel, rebelde_id) values (false, 7);

insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (10, 1, 1);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (1, 2, 1);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (3, 3, 1);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (1, 4, 1);

insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (6, 1, 2);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (2, 2, 2);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (4, 3, 2);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (0, 4, 2);

insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (2, 1, 3);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (8, 2, 3);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (7, 3, 3);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (2, 4, 3);

insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (10, 1, 4);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (1, 2, 4);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (1, 3, 4);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (5, 4, 4);

insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (9, 1, 5);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (3, 2, 5);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (6, 3, 5);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (10, 4, 5);

insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (9, 1, 6);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (3, 2, 6);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (6, 3, 6);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (10, 4, 6);

insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (5, 1, 7);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (7, 2, 7);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (4, 3, 7);
insert into rebelde_item_inventario (quantidade, item_inventario_id, rebelde_inventario_id) values (12, 4, 7);
