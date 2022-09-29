import java.sql.*;

public class PartsSuppliers {
	static String bd = "partssuppliers3";
	static String login = "root";
	static String password = "Introducir contraseña";
	static String url = "jdbc:mysql://localhost:3306/" + bd;
	
	
	public static void main(String[] args) throws Exception {
// crear conexión
		
		Connection conn = null; 
		try { 
			//Class.forName("com.mysql.jdbc.Driver"); 
			//conn = DriverManager.getConnection(url, login, password); 
			String sURL = url; 
			conn = DriverManager.getConnection(sURL,login,password); 
			
			if (conn != null) { 
				System.out.println("-Abierta base de datos " + url + " - Ok"); 
			// Crear tabla contacto 
				Statement st = conn.createStatement();
			// Permite comandos SQL 
				st.executeUpdate("CREATE TABLE IF NOT EXISTS PIEZAS (" + 
								  "Codigo INT NOT NULL, " + 
								  "Nombre NVARCHAR(100), " + 
								  "PRIMARY KEY (Codigo))"); 
				System.out.println("\n-Creada tabla (piezas) - Ok");
				
				st.executeUpdate("CREATE TABLE IF NOT EXISTS PROVEEDORES (" + 
						   		"Id char(4) NOT NULL, " + 
						   		"Nombre NVARCHAR(100), " + 
						   		"PRIMARY KEY (Id))"); 
				System.out.println("\n-Creada tabla (proveedores) - Ok");
				
				st.executeUpdate("CREATE TABLE IF NOT EXISTS SUMINISTRA (" + 
				   				"CodigoPieza INT NOT NULL, " + 
				   				"IdProveedor char(4) NOT NULL, " + 
				   				"precio INT UNSIGNED, " + 
				   				"PRIMARY KEY (CodigoPieza, idProveedor), " + 
				   				"FOREIGN KEY (CodigoPieza) REFERENCES PIEZAS (Codigo), " + 
				   				"FOREIGN KEY (IdProveedor) REFERENCES PROVEEDORES (Id))"); 
				System.out.println("\n-Creada tabla (suministra) - Ok");
								
// Insertar datos a la tabla 
				int codigo[] = {7178, 4562, 2346, 1783, 2235}; 
				String nombrePieza[] = { "A", "B", "C", "D", "E" }; 
				for (int i = 0; i < codigo.length; i++) { 
					st.executeUpdate( 
							"INSERT INTO PIEZAS (" + 
							"Codigo, " + 
							"Nombre) " + 
							"VALUES ("+ "'" + 
							codigo[i] + "','" + 
							nombrePieza[i] + "' )"); 
					} 
				System.out.println("\n-Añadir registros a la tabla PIEZAS - Ok"); 
				
				int Id[] = {3126, 1234, 1802, 1082, 4672}; 
				String nombreProveedor[] = { "A", "B", "C", "D", "E" }; 
				for (int i = 0; i < codigo.length; i++) { 
					st.executeUpdate( 
							"INSERT INTO PROVEEDORES (" + 
							"Id, " + 
							"Nombre) " + 
							"VALUES ("+ "'" + 
							Id[i] + "','" + 
							nombreProveedor[i] + "' )"); 
					} 
				System.out.println("\n-Añadir registros a la tabla PROVEEDORES - Ok"); 
				
				int codigoPieza[] = {7178, 4562, 2346, 1783, 2235}; 
				int idProveedor[] = {3126, 1234, 1802, 1082, 4672};
				int precio[] = {50, 100, 150, 200, 250};
				for (int i = 0; i < codigo.length; i++) { 
					st.executeUpdate( 
							"INSERT INTO SUMINISTRA (" + 
							"CodigoPieza, " + 
							"IdProveedor, " + 
							"Precio) " + 
							"VALUES ("+ "'" + 
							codigoPieza[i] + "','" + 
							idProveedor[i] + "','" + 
							precio[i] + "' )"); 
					} 
				System.out.println("\n-Añadir registros a la tabla SUMINISTRA - Ok"); 
				
				
// Consulta de datos 
				System.out.println("\n-Consultar registros tabla PIEZAS:"); 
				ResultSet rs1 = st.executeQuery("select * from piezas"); 
				while (rs1.next()) {
					System.out.println(
							rs1.getString(1) + " " + 
							rs1.getString(2)); 
				}
				
				System.out.println("\n-Consultar registros tabla PROVEEDORES:"); 
				ResultSet rs2 = st.executeQuery("select * from proveedores"); 
				while (rs2.next()) {
					System.out.println(
							rs2.getString(1) + " " + 
							rs2.getString(2)); 
				}
				
				System.out.println("\n-Consultar registros tabla SUMINISTRA:"); 
				ResultSet rs3 = st.executeQuery("select * from suministra"); 
				while (rs3.next()) {
					System.out.println(
							rs3.getString(1) + " " + 
							rs3.getString(2) + " " + 
							rs3.getString(3)); 
				}
				// Borrar tabla st.executeUpdate("DROP TABLE contacto"); 
				//System.out.println("-Borrar tabla contacto - Ok"); 
				conn.close();

				
// Cerrar base de datos 
				
				System.out.println("\n-Cerrar base de datos " + url + " - Ok"); 
				} 
			} catch (SQLException ex) { 
				System.out.println(ex); 
			} 
	  }	 

}


