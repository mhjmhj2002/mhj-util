package br.com.mhj.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class ConectorMysql {
	Connection connection = null;
	
	public void teste() {

		try {

			connection = DriverManager.getConnection("jdbc:mysql://mysql.mhj.kinghost.net/mhj02", "mhj02", "2y244y26");
//			connection = DriverManager.getConnection("jdbc:mysql://mysql07-farm76.kinghost.net/mhj02", "mhj02@10.17.76.12", "2y244y26teste");//mhj02@10.17.76.12

			teste2(connection);
//			acertarSequence(connection, "sqr_razao_chargeback", 1390-476);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	 public void connectOverProxy() {
	        try {
//	            Class.forName("com.mysql.jdbc.Driver");
	            Connection conn = null;
	            Properties info = new Properties();
	            //info.put("proxy_type", "4"); // SSL Tunneling
	            info.put("proxy_host", "proxypac");
	            info.put("proxy_port", "8080");
	            info.put("proxy_user", "t0404mnl");
	            info.put("proxy_password", "Mhj201908");
	            info.put("user", "mhj02");
	            info.put("password", "2y244y26");
	            conn = DriverManager.getConnection("jdbc:mysql://mysql.mhj.kinghost.net/",info);


	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery("Select NOW()");
	            rs.next();
	            System.out.println("Data- " + rs.getString(1));
	            rs.close();
	            stmt.close();
	            conn.close();

	        } catch (SQLException er) {
	            er.printStackTrace();
	        } 

	    }

	private void teste2(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String query = "SELECT * FROM expression";

		ResultSet resultSet = statement.executeQuery(query);

		while (resultSet.next()) {
			System.out.println("id: " +resultSet.getString("id"));
			System.out.println("idioma: " + resultSet.getInt("idioma"));
//			atualizar(resultSet.getString("nome"));
		}
	}

	private void atualizar(String nuPlano) throws SQLException {
		String part1 = nuPlano.substring(0, 15);
		String part2 = nuPlano.substring(15);
		ajustar(nuPlano, part1, part2);
	}

	private void ajustar(String nuPlano, String part1, String part2) throws SQLException {
		Long p1 = Long.valueOf(part1);
		Long p2 = Long.valueOf(part2);
		System.out.println("part1: " + part1);
		System.out.println("part2: " + part2);
		
		String query = "SELECT\r\n" + 
				"    id_case\r\n" + 
				"FROM INTC.tbstcr_case\r\n" + 
				"where nu_plano = " + nuPlano;
		
		Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery(query);
		
		while (resultSet.next()) {
			
			Long idCase = resultSet.getLong("id_case");
			p1++;
			p2++;
			
			String part1At = StringUtils.leftPad(p1.toString(), 15, "0");
			String part2At = StringUtils.leftPad(p2.toString(),  4, "0");
			
			System.out.println("p1: " + part1At);
			System.out.println("p2: " + part2At);
			System.out.println("idCase: " + idCase);
			
			String partes = part1At + part2At;
			
			String sql = "update INTC.tbstcr_case set nu_plano = " + partes + " where id_case = " + idCase;
			
			Statement statement2 = connection.createStatement();
			statement2.executeUpdate(sql);
			
		}
	}

	public void acertarSequence(Connection connection, String sequence, int quant) throws SQLException {
		Statement statement = connection.createStatement();
		
		String query = "select " + sequence + ".nextval from dual";
		
		for (int i = 0; i < quant; i++) {
			ResultSet resultSet = statement.executeQuery(query);
		}
		
	}
}
