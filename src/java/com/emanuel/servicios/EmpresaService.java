/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emanuel.servicios;

import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.emanuel.modelos.Empresa;
import com.emanuel.utilidades.Consultas;
import java.util.List;

/**
 * REST Web Service
 *
 * @author emanu
 */
@Path("empresas")
public class EmpresaService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EmpresaService
     */
    public EmpresaService() {
    }

    /**
     * Retrieves representation of an instance of com.emanuel.servicios.EmpresaService
     * @param nombre
     * @param direccion
     * @param ciudad
     * @return an instance of java.lang.String
     * @throws java.sql.SQLException
     */
    @GET
    @Path("/registarempresa")
    @Produces(MediaType.APPLICATION_JSON)
    public String registrarEmpresa(@QueryParam("nombre") String nombre,
                               @QueryParam("direccion") String direccion,
                               @QueryParam("ciudad") String ciudad) throws SQLException {
        
       Empresa empresa = new Empresa();
       empresa.setNombre(nombre);
       empresa.setDireccion(direccion);
       empresa.setCiudad(ciudad);
       Consultas consulta = new Consultas();
       String mensaje = consulta.registrarEmpresa(empresa);
       return mensaje;
    }
    
    @GET
    @Path("/listarempresas")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> getEmpresas() throws SQLException {
        List<Empresa> empresas = null;
        Consultas consulta = new Consultas();
        empresas = consulta.getEmpresas();
        return empresas;
    }
    
    @GET
    @Path("/empresa")
    @Produces(MediaType.APPLICATION_JSON)
    public Empresa getEmpresa(@QueryParam("id") int id) throws SQLException {
        Empresa empresa = new Empresa();
        Consultas consulta = new Consultas();
        empresa = consulta.getEmpresa(id);
        return empresa;
    }
    
    @GET
    @Path("/deleteempresa")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteEmpresa(@QueryParam("id") int id) throws SQLException {
        Consultas consulta = new Consultas();
        String mensaje = consulta.deleteEmpresa(id);
        return mensaje;
    }
    
    @GET
    @Path("/updateempresa")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateEmpresa(@QueryParam("id") int id,
                                @QueryParam("nombre") String nombre,
                               @QueryParam("direccion") String direccion,
                               @QueryParam("ciudad") String ciudad) throws SQLException {
        
       Empresa empresa = new Empresa();
       empresa.setId(id);
       empresa.setNombre(nombre);
       empresa.setDireccion(direccion);
       empresa.setCiudad(ciudad);
       Consultas consulta = new Consultas();
       String mensaje = consulta.updateEmpresa(empresa);
       return mensaje;
    }
    

    /**
     * PUT method for updating or creating an instance of EmpresaService
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
