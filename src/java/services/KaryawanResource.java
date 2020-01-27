/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import helper.KaryawanHelper;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import pojos.KaryawanTbl;

/**
 * REST Web Service
 *
 * @author Shiroe
 */
@Path("karyawan")
public class KaryawanResource {

    private KaryawanHelper helper;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of KaryawanResource
     */
    public KaryawanResource() {
        helper = new KaryawanHelper();
    }

    /**
     * Retrieves representation of an instance of services.KaryawanResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of KaryawanResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
    
    @GET
    @Path("getKaryawanList")
    @Produces("application/json")
    public String getKaryawanList() {
        Gson gson = new Gson();
        List<KaryawanTbl> l = null;
        try {
            l = new ArrayList(helper.karyawanList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "{\"user\":" + gson.toJson(l) + "}";
    }
    
    @GET
    @Path("getKaryawanById")
    @Produces("application/json")
    public String getKaryawanById(@QueryParam("idKaryawan") String idKaryawan) {
        Gson gson = new Gson();
        KaryawanTbl kt = null;
        
        try {
            List<KaryawanTbl> l = new ArrayList(helper.getKaryawanById(Integer.parseInt(idKaryawan)));
            for (int i = 0; i < l.size(); i++){
                kt = new KaryawanTbl();
                kt.setIdKaryawan(l.get(i).getIdKaryawan());
                kt.setNama(l.get(i).getNama());
                kt.setGender(l.get(i).getGender());
                kt.setAlamat(l.get(i).getAlamat());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return gson.toJson(kt);
    }
    
    @POST
    @Path("saveDataKaryawan")
    @Consumes("application/json")
    public Response saveDataKaryawan (String data) {
        Gson gson = new Gson();
        KaryawanTbl kt = gson.fromJson(data, KaryawanTbl.class);
        
        try {
            helper.saveDataKaryawaan(kt.getIdKaryawan(), kt.getNama(), kt.getGender(), kt.getAlamat());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Response.status(200).entity(kt).build();
    }
    
    @PUT
    @Path("updateKaryawan")
    @Consumes("application/json")
    public void updateKaryawan(@QueryParam("idKaryawan") String idKaryawan, @QueryParam("nama") String nama){
        try {
            helper.updateKaryawan(Integer.parseInt(idKaryawan), nama);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    @DELETE
    @Path("deleteKaryawan")
    @Consumes("application/json")
    public void deleteKaryawan(@QueryParam("idKaryawan") String idKaryawan){
        try {
            helper.deleteKaryawan(Integer.parseInt(idKaryawan));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
