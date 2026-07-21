-- ======================================================================
--  DADOS FIXOS (rodam a cada inicializacao; o banco H2 e em memoria,
--  entao sempre sobe exatamente igual). Nao precisa mexer aqui.
-- ======================================================================

-- ---------------- CATEGORIAS ----------------
INSERT INTO categoria (id, nome, descricao) VALUES (1, 'Eletronicos', 'Aparelhos e acessorios eletronicos');
INSERT INTO categoria (id, nome, descricao) VALUES (2, 'Livros', 'Livros tecnicos e de literatura');
INSERT INTO categoria (id, nome, descricao) VALUES (3, 'Roupas', 'Vestuario em geral');
INSERT INTO categoria (id, nome, descricao) VALUES (4, 'Alimentos', 'Comidas e bebidas');
INSERT INTO categoria (id, nome, descricao) VALUES (5, 'Brinquedos', 'Brinquedos e jogos');

-- ---------------- PRODUTOS ----------------
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (1,  'Notebook Dell',            3500.00, 10,  TRUE,  '2023-01-15', 1);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (2,  'Mouse Logitech',            120.00, 50,  TRUE,  '2023-02-10', 1);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (3,  'Teclado Mecanico',          350.00, 30,  TRUE,  '2023-03-05', 1);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (4,  'Monitor LG 24',             900.00, 8,   TRUE,  '2023-01-20', 1);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (5,  'Smartphone Samsung',       2200.00, 15,  TRUE,  '2023-04-12', 1);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (6,  'Fone Bluetooth',            250.00, 0,   FALSE, '2022-11-30', 1);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (7,  'Livro Java Efetivo',         90.00, 100, TRUE,  '2023-05-01', 2);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (8,  'Livro Clean Code',          110.00, 60,  TRUE,  '2023-05-15', 2);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (9,  'Livro Spring in Action',    150.00, 5,   TRUE,  '2023-06-01', 2);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (10, 'Livro Padroes de Projeto',  130.00, 0,   FALSE, '2022-09-10', 2);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (11, 'Camiseta Preta',             45.00, 200, TRUE,  '2023-07-01', 3);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (12, 'Calca Jeans',               160.00, 40,  TRUE,  '2023-07-10', 3);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (13, 'Jaqueta Corta-Vento',       300.00, 12,  TRUE,  '2023-08-01', 3);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (14, 'Tenis Esportivo',           400.00, 25,  TRUE,  '2023-08-15', 3);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (15, 'Cafe Especial 1kg',          60.00, 80,  TRUE,  '2023-09-01', 4);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (16, 'Chocolate Belga',            35.00, 150, TRUE,  '2023-09-10', 4);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (17, 'Azeite Extra Virgem',        55.00, 0,   FALSE, '2022-12-20', 4);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (18, 'Quebra-Cabeca 1000pc',       80.00, 20,  TRUE,  '2023-10-01', 5);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (19, 'Lego Classico',             320.00, 18,  TRUE,  '2023-10-15', 5);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (20, 'Boneca Articulada',         140.00, 0,   FALSE, '2022-08-05', 5);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (21, 'Carrinho Controle Remoto',  210.00, 22,  TRUE,  '2023-11-01', 5);
INSERT INTO produto (id, nome, preco, estoque, ativo, data_cadastro, categoria_id) VALUES (22, 'Webcam HD',                 180.00, 35,  TRUE,  '2023-11-20', 1);

-- ---------------- CLIENTES ----------------
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (1,  'Ana Souza',      'ana@email.com',      'Sao Paulo',      'SP', '1990-05-14');
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (2,  'Bruno Lima',     'bruno@email.com',    'Rio de Janeiro', 'RJ', '1985-11-02');
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (3,  'Carla Dias',     'carla@email.com',    'Belo Horizonte', 'MG', '1998-03-25');
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (4,  'Diego Alves',    'diego@email.com',    'Sao Paulo',      'SP', '1979-07-30');
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (5,  'Elaine Costa',   'elaine@email.com',   'Curitiba',       'PR', '1992-12-10');
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (6,  'Felipe Rocha',   'felipe@email.com',   'Porto Alegre',   'RS', '2000-01-05');
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (7,  'Gabriela Nunes', 'gabriela@email.com', 'Recife',         'PE', '1988-09-18');
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (8,  'Henrique Melo',  'henrique@email.com', 'Sao Paulo',      'SP', '1995-06-22');
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (9,  'Isabela Ramos',  'isabela@email.com',  'Florianopolis',  'SC', '1983-04-11');
INSERT INTO cliente (id, nome, email, cidade, estado, data_nascimento) VALUES (10, 'Joao Pedro',     'joao@email.com',     'Rio de Janeiro', 'RJ', '1975-10-08');

-- ---------------- PEDIDOS ----------------
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (1,  1,  '2024-01-10', 'ENTREGUE',  3620.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (2,  1,  '2024-02-15', 'PAGO',       250.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (3,  2,  '2024-01-20', 'ENVIADO',    900.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (4,  3,  '2024-03-01', 'PENDENTE',   240.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (5,  4,  '2024-02-28', 'ENTREGUE',  2200.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (6,  4,  '2024-04-05', 'CANCELADO',  350.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (7,  5,  '2024-03-15', 'PAGO',       470.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (8,  6,  '2024-04-20', 'PENDENTE',    45.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (9,  7,  '2024-01-30', 'ENTREGUE',   550.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (10, 8,  '2024-05-01', 'PAGO',      3500.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (11, 8,  '2024-05-10', 'ENVIADO',    160.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (12, 9,  '2024-02-05', 'ENTREGUE',   730.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (13, 10, '2024-03-20', 'CANCELADO',  320.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (14, 2,  '2024-05-15', 'PAGO',       400.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (15, 1,  '2024-06-01', 'PENDENTE',   180.00);
INSERT INTO pedido (id, cliente_id, data_pedido, status, valor_total) VALUES (16, 5,  '2024-06-10', 'ENTREGUE',   210.00);

-- ---------------- ITENS DE PEDIDO ----------------
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (1,  1,  1,  1, 3500.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (2,  1,  2,  2, 120.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (3,  2,  6,  1, 250.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (4,  3,  4,  1, 900.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (5,  4,  3,  1, 350.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (6,  4,  16, 2, 35.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (7,  5,  5,  1, 2200.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (8,  6,  3,  1, 350.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (9,  7,  14, 1, 400.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (10, 7,  15, 1, 60.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (11, 8,  11, 1, 45.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (12, 9,  9,  2, 150.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (13, 9,  8,  1, 110.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (14, 9,  7,  1, 90.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (15, 10, 1,  1, 3500.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (16, 11, 12, 1, 160.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (17, 12, 13, 1, 300.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (18, 12, 10, 1, 130.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (19, 12, 18, 3, 80.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (20, 13, 19, 1, 320.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (21, 14, 14, 1, 400.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (22, 15, 21, 1, 210.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (23, 16, 21, 1, 210.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (24, 2,  16, 1, 35.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (25, 5,  2,  1, 120.00);
INSERT INTO item_pedido (id, pedido_id, produto_id, quantidade, preco_unitario) VALUES (26, 7,  16, 1, 35.00);
