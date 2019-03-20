package br.com.mhj.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang.StringUtils;

public class Teste {
	Connection connection = null;
	
	public void teste() {

		try {

			connection = DriverManager.getConnection("jdbc:oracle:thin:@10.80.50.153:1521:bdtdintc", "USRINTC",
					"USRINTC");

			teste2(connection);

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

	private void teste2(Connection connection) throws SQLException {
		Statement statement = connection.createStatement();

		String query = "SELECT\r\n" + "    nu_plano, count(nu_plano) as count\r\n" + "FROM tbstcr_case\r\n"
				+ "group by nu_plano\r\n" + "having count(nu_plano) > 1";

		ResultSet resultSet = statement.executeQuery(query);

		while (resultSet.next()) {
			System.out.println("nuPlano: " +resultSet.getString("nu_plano"));
			System.out.println("count: " + resultSet.getInt("count"));
			atualizar(resultSet.getString("nu_plano"));
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
}
