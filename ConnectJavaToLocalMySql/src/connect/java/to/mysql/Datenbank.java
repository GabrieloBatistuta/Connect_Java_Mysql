package connect.java.to.mysql;

import java.sql.*;

public class Datenbank {

	public static void main(String[] args) {

		String url = "jdbc:mysql://localhost:3306/test?useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
		String user = "root";
		String password = "";

		// userEinfuegen(url, user, password);
		userUpdaten(url, user, password);
		tabeleLesen(url, user, password);
	}

	// Tabeleinhalt ausgeben
	public static void tabeleLesen(String url, String user, String password) {
		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			String query = "SELECT * FROM personen ORDER BY personen_id ASC";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			int columns = rs.getMetaData().getColumnCount();

			for (int i = 1; i <= columns; i++) {
				System.out.print(String.format("%-15s", rs.getMetaData().getColumnLabel(i)));
			}

			System.out.println();
			System.out.println("-------------------------------------------------------");

			while (rs.next()) {
				for (int i = 1; i <= columns; i++) {
					System.out.print(String.format("%-15s", rs.getString(i)));
				}
				System.out.println();
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// Info zu Tabelle einfuegen
	public static void userEinfuegen(String url, String user, String password) {

		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			String query = "INSERT INTO personen (personen_id,vorname, nachname, geburtsdatum) VALUES (2,'kojar', 'Khalil','1990-04-11')";
			Statement stmt = conn.createStatement();
			stmt.execute(query);
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	// update user
	public static void userUpdaten(String url, String user, String password) {

		try (Connection conn = DriverManager.getConnection(url, user, password)) {

			String query = "UPDATE personen SET vorname='Jan' ,nachname='Loli'  WHERE personen_id=2";

			Statement stmt = conn.createStatement();
			stmt.execute(query);
			stmt.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

}
