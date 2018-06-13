package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	
	public String stringAMD5(String password) {

		String hashtext = null;

		try {
			MessageDigest m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(password.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32
			// chars.
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hashtext;
	}

	public Usuario validaAdmin(String usr, String password){


		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = MiConexion.getConnection();
			ps = con.prepareStatement(
					"  select usuario, password from usuario_gestion where usuario = ?");//***********
			ps.setString(1, usr);
			rs = ps.executeQuery();

			Usuario miAdmin;
			
			if (rs.next()) {
				
				
					if (rs.getString(2).equals(password)) {
					
					    miAdmin = new Usuario();
					
						miAdmin.setUser(rs.getString(1));
						miAdmin.setPassword(rs.getString(2));
						
						return miAdmin;
						
					} else {
						
						return null;
						
					}
				
			}
			
		} catch (SQLException e) {		
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}										
		}
		
		return null;
	}
	
}
