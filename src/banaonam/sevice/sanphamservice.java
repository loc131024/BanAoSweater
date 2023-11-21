/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banaonam.sevice;

import banquanao.model.dangnhap;
import banquanao.model.sanpham;
import banaonam.untility.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class sanphamservice {
    public ArrayList<sanpham> getallsanpham(){
        ArrayList<sanpham> dssp = new ArrayList<>();
       
        Connection cn = DB.getConnection();
        String sql = "SELECT * FROM SANPHAM";
        try {
           PreparedStatement pd = cn.prepareStatement(sql);
           ResultSet rs = pd.executeQuery();
            while (rs.next()) {       
              sanpham sp = new sanpham();   
                sp.setMasp(rs.getString(1));
                sp.setTensp(rs.getString(2));
                sp.setSlSp(rs.getInt(3));
                dssp.add(sp);
            }
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }
        return dssp;
    }
    public String getTenSP(String ma) {
        String sql ="";
        Connection con = null;
        PreparedStatement ps=null;
        ResultSet rs = null;
        sql = "select TENSP from SANPHAM where MASP=?";
        sanpham sp = null;
        try {
            con = DB.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                sp = new sanpham(ma, rs.getString(1), rs.getInt(2));
            }
            return sp.getTensp();
        } catch (Exception e) {
            return null;
        }
    }
    
    public Integer addSanPham(sanpham sp){
        Integer row =null;
        Connection cn = DB.getConnection();
        String sql = "INSERT INTO SANPHAM (MASP,TENSP,SOLUONG) VALUES(?,?,?)";
        try {
           PreparedStatement pd = cn.prepareStatement(sql);
           pd.setString(1, sp.getMasp());
           pd.setString(2, sp.getTensp());
           pd.setInt(3, sp.getSlSp());
           row = pd.executeUpdate();
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }
        return row;
    }
    
     public Integer UpdateSanPham(sanpham sp){
        Integer row =null;
        Connection cn = DB.getConnection();
        String sql = "update sanpham\n" +
"set TENSP = ?,SOLUONG = ?\n" +
"where Masp = ?";
        try {
           PreparedStatement pd = cn.prepareStatement(sql);
           pd.setString(1, sp.getTensp());
           pd.setInt(2, sp.getSlSp());
           pd.setString(3, sp.getMasp());
           
           row = pd.executeUpdate();
        } catch (Exception e) {
            System.out.println("lỗi");
            e.printStackTrace();
        }
        return row;
    }
}
