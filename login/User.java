package login; //criação do pacote login

import java.sql.Connection;//importações da bliblioteca para a utilização do sql com banco de dados no codigo em java gerenciamento, armazenamento e verificação do sql
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * Classe responsável por gerenciar operações de login de usuários com integração a um banco de dados.
 */
public class User {
    /**
     * Método para estabelecer a conexão com o banco de dados.
     *
     * @return Um objeto {@link Connection} que representa a conexão ativa com o banco de dados,
     * ou {@code null} em caso de falha.
     */
    public Connection conectarBD() {
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

    /**
     * Variável para armazenar nome do usuário.
     */
    public String nome = "";
    /**
     * Variável para indicar o resultado da verificação de login.
     * {@code true} se o login for bem-sucedido, {@code false} caso contrário.
     */
    public boolean result = false;
/**
     * Método para verificar as credenciais do usuário no banco de dados.
     *
     * @param login O login fornecido pelo usuário.
     * @param senha A senha fornecida pelo usuário.
     * @return {@code true} se as credenciais forem válidas e o usuário for encontrado no banco,
     * {@code false} caso contrário.
     */
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
