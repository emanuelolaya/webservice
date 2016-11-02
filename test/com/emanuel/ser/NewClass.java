/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emanuel.ser;

import com.emanuel.modelos.Empresa;
import com.emanuel.utilidades.Conexion;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emanu
 */
public class NewClass extends Conexion {
    
    public static String registrar(Empresa empresa){
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
            insertados = ps.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                if(getConexion() == null)getConexion().close();
                if(ps != null)ps.close();
            } catch (SQLException ex) {
                System.out.println("errorrrrrrrrrrr" + ex);
                Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public static void main(String[] args) {
        NewClass c = new NewClass();
        Empresa e = new Empresa();
        e.setNombre("exito");
        e.setCiudad("Bogota");
        e.setDireccion("calle 5 # 7-24");
        System.out.println(c.registrar(e));
    }
    
}