/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emanuel.utilidades;

import java.sql.PreparedStatement;
import com.emanuel.modelos.Empresa;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emanu
 */
public class Consultas extends Conexion {
    
    public String registrarEmpresa(Empresa empresa){
        String mensaje = "";
        int insertados=0;
        
        PreparedStatement ps = null;
        try {
            System.out.println(empresa.getNombre() + empresa.getDireccion()+ empresa.getCiudad());
            String sql="insert into empresa (emp_nombre,emp_direccion,emp_ciudad) values(?,?,?)";
            System.out.println(sql);
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, empresa.getNombre());
            ps.setString(2, empresa.getDireccion());
            ps.setString(3, empresa.getCiudad());
            System.out.println("sqllllllllll" + ps.toString());
            insertados = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(getConexion() == null)getConexion().close();
                if(ps != null)ps.close();
            } catch (SQLException ex) {
                System.out.println("errorrrrrrrrrrr" + ex);
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(insertados==1){
            System.out.println(insertados);
            mensaje = "{\"mensaje\":\"Registro OK\"}";
        }else{
             mensaje = "{\"mensaje\":\"Registro Fallido\"}";
        }
        return mensaje;
    }
    
    public List<Empresa> getEmpresas(){
        List<Empresa> empresas = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "select * from empresa";
        
        try {
            ps = getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Empresa empresa = new Empresa();
                empresa.setId(rs.getInt("emp_id"));
                empresa.setNombre(rs.getString("emp_nombre"));
                empresa.setCiudad(rs.getString("emp_ciudad"));
                empresa.setDireccion(rs.getString("emp_direccion"));
                System.out.println(empresa.getId() +empresa.getNombre() + empresa.getDireccion()+ empresa.getCiudad());
                empresas.add(empresa);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(getConexion() == null)getConexion().close();
                if(ps != null)ps.close();
                if(rs != null)rs.close();
            } catch (SQLException ex) {
                System.out.println("errorrrrrrrrrrr" + ex);
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return empresas;
    }
    
    public Empresa getEmpresa(int id){
        Empresa empresa = new Empresa();
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "select * from empresa where emp_id = ?";
        
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                empresa.setId(rs.getInt("emp_id"));
                empresa.setNombre(rs.getString("emp_nombre"));
                empresa.setCiudad(rs.getString("emp_ciudad"));
                empresa.setDireccion(rs.getString("emp_direccion"));
                System.out.println(empresa.getId() +empresa.getNombre() + empresa.getDireccion()+ empresa.getCiudad());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(getConexion() == null)getConexion().close();
                if(ps != null)ps.close();
                if(rs != null)rs.close();
            } catch (SQLException ex) {
                System.out.println("errorrrrrrrrrrr" + ex);
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return empresa;
    }
    public String deleteEmpresa(int id){
        String mensaje = null;
        int afectados = 0;
        PreparedStatement ps = null;
        String sql = "delete from empresa where emp_id = ?";
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            afectados = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(getConexion() == null)getConexion().close();
                if(ps != null)ps.close();
            } catch (SQLException ex) {
                System.out.println("errorrrrrrrrrrr" + ex);
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(afectados==1){
            System.out.println(afectados);
            mensaje = "{\"mensaje\":\"Delete OK\"}";
        }else{
            System.out.println(afectados);
            mensaje = "{\"mensaje\":\"Delete Fallido\"}";
        }
        return mensaje;
    }
    
        public String updateEmpresa(Empresa empresa){
        String mensaje = null;
        int afectados = 0;
        PreparedStatement ps = null;
        String sql = "update empresa set emp_nombre = ?, emp_direccion = ?, emp_ciudad = ? where emp_id = ?";
        try {
            ps = getConexion().prepareStatement(sql);
            ps.setString(1, empresa.getNombre());
            ps.setString(2, empresa.getDireccion());
            ps.setString(3, empresa.getCiudad());
            ps.setInt(4, empresa.getId());
            afectados = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                if(getConexion() == null)getConexion().close();
                if(ps != null)ps.close();
            } catch (SQLException ex) {
                System.out.println("errorrrrrrrrrrr" + ex);
                Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(afectados==1){
            System.out.println(afectados);
            mensaje = "{\"mensaje\":\"update OK\"}";
        }else{
            System.out.println(afectados);
            mensaje = "{\"mensaje\":\"update Fallido\"}";
        }
        return mensaje;
    }
}