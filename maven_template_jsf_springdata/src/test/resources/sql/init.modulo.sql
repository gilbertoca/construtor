--GRUPO
INSERT INTO reca.grupo (id, descricao) VALUES (1, 'Gestor Secad');
INSERT INTO reca.grupo (id, descricao) VALUES (2, 'Operador');
--PERMISSOES
INSERT INTO reca.permissoes (id, token, url) VALUES (1, 'editAcesso', 'Editar os dados de acesso');
INSERT INTO reca.permissoes (id, token, url) VALUES (2, 'editUsuarios', 'Editar qualquer usuário do grupo');
INSERT INTO reca.permissoes (id, token, url) VALUES (3, 'recadastrar', 'Recadastrar servidor');
INSERT INTO reca.permissoes (id, token, url) VALUES (4, 'recadastrarQualquerServidor', 'Recadastrar qualquer servidor');
INSERT INTO reca.permissoes (id, token, url) VALUES (5, 'estatisticas', 'Estatísticas do Recadastramento');
INSERT INTO reca.permissoes (id, token, url) VALUES (6, 'mensagemControle', 'Mensagem de Controle');
INSERT INTO reca.permissoes (id, token, url) VALUES (7, 'cancelarValidacao', 'Cancelar Validação de Cadastro');
--GRUPO_PERMISSOES
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (1, 1);
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (1, 2);
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (1, 3);
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (1, 4);
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (1, 5);
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (1, 6);
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (1, 7);
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (2, 1);
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (2, 3);
INSERT INTO reca.grupo_permissoes (grupo_id, permissoes_id) VALUES (2, 4);
--USUARIO --> senha 12345678
INSERT INTO reca.usuario(id, "login", email, senha, dt_criacao, dt_ultimo_acesso, cpf, nome)
VALUES (1,'10000000000','reca_user@secad.to.gov.br','/KN9DXHo3sd8iK8Mq8ivKoSFLbqJBoVI','2011-05-24 00:00:00',null,'10000000000','Reca User - Usuário administrador do módulo RECA');
--GRUPO_USUARIO
INSERT INTO reca.usuario_grupo (usuario_id, grupo_id) VALUES (1, 1);
--RACAS
INSERT INTO reca.racas (id, descricao, complemento) VALUES (1, 'INDIGENA', 'Para a pessoa que se enquadrar como indigena ou india');
INSERT INTO reca.racas (id, descricao, complemento) VALUES (2, 'BRANCA', 'Para a pessoa que se enquadrar como branca');
INSERT INTO reca.racas (id, descricao, complemento) VALUES (4, 'PRETA', 'Para a pessoa que se enquadrar como preta');
INSERT INTO reca.racas (id, descricao, complemento) VALUES (6, 'AMARELA', 'Para a pessoa que se enquadrar como raça amarela (de origem japonesa, chinesa, coreana, etc.)');
INSERT INTO reca.racas (id, descricao, complemento) VALUES (8, 'PARDA', 'Para a pessoa que se enquadrar como preta ou se declarar como mulata, cabocla, cafuza, mameluca ou mestiça de preto com pessoa de outra cor ou raça');
INSERT INTO reca.racas (id, descricao, complemento) VALUES (9, 'NAO INFORMADO', 'Nao informado');
--NECESSIDADES_ESPECIAIS
INSERT INTO reca.necessidades_especiais (id, descricao) VALUES (1, 'NAO É PORTADOR DE NECESSIDADES ESPECIAIS');
INSERT INTO reca.necessidades_especiais (id, descricao) VALUES (2, 'FÍSICA');
INSERT INTO reca.necessidades_especiais (id, descricao) VALUES (3, 'VISUAL');
INSERT INTO reca.necessidades_especiais (id, descricao) VALUES (4, 'MENTAL');
INSERT INTO reca.necessidades_especiais (id, descricao) VALUES (5, 'MÚLTIPLA');
INSERT INTO reca.necessidades_especiais (id, descricao) VALUES (6, 'REABILITADO');
