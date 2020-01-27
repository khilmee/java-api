/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.KaryawanTbl;
import utility.HibernateUtil;

/**
 *
 * @author Shiroe
 */
public class KaryawanHelper {
    
    public KaryawanHelper(){
    
    }
    
    public void saveDataKaryawaan(Integer idKaryawan, String nama, String gender, String alamat) 
    {
        Session sesi = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesi.beginTransaction();
        
        KaryawanTbl kt = new KaryawanTbl();
        if(idKaryawan != 0){
            kt.setIdKaryawan(idKaryawan);
        }
        kt.setGender(gender);
        kt.setNama(nama);
        kt.setAlamat(alamat);
        
        sesi.saveOrUpdate(kt);
        tx.commit();
    }
    
    public List<KaryawanTbl> karyawanList(){
        Session sesi = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesi.beginTransaction();
        
        List<KaryawanTbl> l = null;
        
        Query q = sesi.createQuery("from KaryawanTbl x");
        l = q.list();
        tx.commit();
        sesi.close();
        return l;
    }
    
    public void updateKaryawan(int idKaryawan, String nama){
        Session sesi = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesi.beginTransaction();
        
        Query q = sesi.createQuery("update from KaryawanTbl x set x.nama ='" + nama + 
                "' where x.idKaryawan = " + idKaryawan);
        
        int i = q.executeUpdate();
        tx.commit();
        sesi.close();
    }
    
    public void deleteKaryawan(int idKaryawan){
        Session sesi = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesi.beginTransaction();
        
        Query q = sesi.createQuery("delete from KaryawanTbl x where x.idKaryawan = " + idKaryawan);
        
        int i = q.executeUpdate();
        tx.commit();
        sesi.close();
    }
    
    public List<KaryawanTbl> getKaryawanById(int idKaryawan){
        Session sesi = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = sesi.beginTransaction();
        
        List<KaryawanTbl> l = null;
        
        Query q = sesi.createQuery("from KaryawanTbl x where x.idKaryawan = '" + idKaryawan);
        l = q.list();
        tx.commit();
        sesi.close();
        return l;
    }
}
