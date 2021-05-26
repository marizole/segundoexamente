package com.emergente.dao;

import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDAOimpl extends ConexionDB implements UsuarioDAO {
     @Override
    public void insert(Usuario usuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT INTO usuarios (usuario,correo,clave) values (?,?,?)");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getClave());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE usuarios SET usuario = ?, correo = ?, clave = ? where id = ?");
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getClave());
            ps.setInt(4, usuario.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM usuarios WHERE id = ?");
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Usuario getById(int id) throws Exception {
        Usuario nom = new Usuario();
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios WHERE id = ?");
            ps.setInt(1,id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                nom.setId(rs.getInt("id"));
                nom.setUsuario(rs.getString("usuario"));
                nom.setCorreo(rs.getString("correo"));
                nom.setClave(rs.getString("clave"));
            }
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return nom;
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> lista = null;
        try {
            this.conectar();
            
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Usuario>();
            while (rs.next()){
                Usuario nom =  new Usuario();
                
                nom.setId(rs.getInt("id"));
                nom.setUsuario(rs.getString("usuario"));
                nom.setCorreo(rs.getString("correo"));
                nom.setClave(rs.getString("clave"));
                
                lista.add(nom);
            }
            rs.close();
            ps.close();
            
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

}
