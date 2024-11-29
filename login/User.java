package login; //criação do pacote login

import java.sql.Connection;//importações da bliblioteca para a utilização do sql com banco de dados no codigo em java gerenciamento, armazenamento e verificação do sql
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User { // criação da classe User, com a função de representar o usuario
    public Connection conectarBD() { //conexão com banco de dados
        Connection conn = null;//inicia a conexão com banco de dados
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://127.0.0.1/test?user=lopes&password=123";//url web do servidor com informações de usuario e senha
            conn = DriverManager.getConnection(url);//obtendo conexão
        } catch (Exception e) {
            e.printStackTrace();//responde StackTrace em caso de erro
        }
        return conn;//retorna a conexão em caso de falha
    }

    public String nome = "";//variavel para armazenar nome
    public boolean result = false;// variavel para resultado de verificação de login

    public boolean verificarUsuario(String login, String senha) {//verificação de usuario no banco de dados
        String sql = "";
        Connection conn = conectarBD();//obtendo conexão com banco de dados

        // INSTRUÇÃO SQL
        sql += "select nome from usuarios ";
        sql += "where login = '" + login + "' ";
        sql += "and senha = '" + senha + "'";//formula do sql para seleccionar o usuario desejado para login

        try {
            Statement st = conn.createStatement();//cria um objeto Statement para a consultar
            ResultSet rs = st.executeQuery(sql);//consulta o sql e obtém os resultados.
            if (rs.next()) {// se o usuario foi encontrado
                result = true;//resulta em verdadeiro a verificação
                nome = rs.getString("nome");//guarda o nome do usuario
            }
        } catch (Exception e) {
            e.printStackTrace();//resulta StackTrace em caso de erro
        }
        return result;// retornando resultado se verdadeiro, se não retorna falso
    }
}
// Fim da classe
