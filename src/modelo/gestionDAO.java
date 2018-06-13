package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class gestionDAO {
	
	public List<Gestion> getAllGestiones(){
		List<Gestion> gestiones = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = MiConexion.getConnection();
			ps = con.prepareStatement(
					"select id, nombre_apel, tramite, fecha_y_hora from gestion order by fecha_y_hora");//***********
			rs = ps.executeQuery();

			Gestion gestion= null ;
			
			while (rs.next()) {

				gestion = new Gestion();
			
				gestion.setId(rs.getInt(1));
				gestion.setNom_apel(rs.getString(2));
				gestion.setNom_tramite(rs.getString(3));
				gestion.setFecha_y_hora(rs.getString(4));
				
				gestiones.add(gestion);
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
						
		return gestiones;
		
	}
	
	public int agregarGestion(Gestion gest){
		
		Connection con = MiConexion.getConnection();
		String sql = "insert into gestion values('?','?', GETDATE())";
		
		int r;
		
		try{
			
		 r = 0;
		
	    	PreparedStatement s = con.prepareStatement(sql);
	        s.setString(1, gest.getNom_apel());
	        s.setString(2, gest.getNom_tramite());
	        
	        r = s.executeUpdate();
	        if(r>0){System.out.println("Create exitoso"); }
	    } catch (Exception e)
	    {
	    	 r = 0;
	        e.printStackTrace();
	        System.out.println("Algo ha fallado");
	    } finally {
	    	try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			System.out.println("Algo ha fallado");
			}
	    }
	    return r;
	}
	
	public int borrarGestion(int id){
		
		if(id==0){
			
			return 0;
		}
		
		Connection con = MiConexion.getConnection();
		String sql = "delete from gestion where id = ?";

		int r = 0;
	    try
	    {
	    	PreparedStatement s = con.prepareStatement(sql);
	        s.setInt(1, id);
	        
	        r = s.executeUpdate();
	    } catch (Exception e)
	    {
	    	r = 0;
	        e.printStackTrace();
	        System.out.println("Algo ha fallado");
	    } finally {
	    	try {
				con.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	    }
	    return r;
	}

}
