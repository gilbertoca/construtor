GFI - Gestão Financeira e Inventário
--------------------------------------------------------------------------------
Há dois módulos nesse projeto. O primeiro é "core" e pretende conter Services 
(incluindo mecanismo de persistência). O segundo é "web" e qualquer arquivo
relacionado á camada web. Usar um projeto modular é recomendado quando se planeja
usar o módulo "core" em múltiplas aplicações, ou se planeja ter múltiplos clientes
para o mesmo backend (core).

Para iniciar, por favor complete os seguintes passos:


1. Download e instale o banco de dados Derby http://db.apache.org/derby/, depois 
inicie o banco em modo cliente/servidor (/bin/startNetworkServer.sh linux, /bin/startNetworkServer.bat win32)

2. Em uma área/diretório destinada ao desenvolvimento (/home/usuario/dev - linux, C:\Dev - win32),
obtenha o projeto através de um cliente para o servidor subversion, exemplo:
svn checkout http://construtor.googlecode.com/svn/trunk/gfi gfi

3. No prompt de comando, cd dentro diretório gfi/core e execute "mvn sql:execute". A execução
desse goal (objetivo), irá criar o banco com os objetos preliminares e só deverá ser executado
somente para inicialização do projeto.

4. Ainda no prompt de comando, dentro diretório gfi/core, execute "mvn test". Obtendo confirmação
de êxito na execução, proceda com a instalação do artefato (jar): mvn install.

5. No prompt de comando, cd dentro diretório gfi/web e execute "mvn jetty:run". Agora você poderá
acessar a aplicação através do endereço http://localhost:8080/gfi-web/, mais especificamente 
http://localhost:8080/gfi-web/table-unidade-medida.htm

4. Mais informações podem ser encontrada em:

    http://code.google.com/p/construtor/

