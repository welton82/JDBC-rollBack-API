package model.dao;

import entity.Department;
import entity.Seller;
import exception.ExceptionIntegrity;
import exception.Excessao;
import util.Conexao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DaoSellerDepartment {

    PreparedStatement prds = null;
    Statement st = null;
    Connection cnn = null;
    ResultSet rs = null;

    public DaoSellerDepartment() {
        cnn = Conexao.getConnection();
    }

    //LISTAR
    public List<Seller> getSeller(){
        List<Seller>adiciona = new ArrayList<>();
        try{
            String sql = "SELECT *FROM seller;";
            st = cnn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                Seller sel = new Seller();
                sel.setName(rs.getString("name"));
                sel.setId(rs.getInt("id"));
                sel.setBaseSalary(rs.getDouble("basesalary"));
                sel.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("birthdate")));
                sel.setDepartmentId(rs.getInt("departmentid"));
                sel.setEmail(rs.getString("email"));
                adiciona.add(sel);
            }
            return adiciona;

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }
    public void getDepartment(){
        try{
            String sql = "SELECT *FROM department;";
            st = cnn.createStatement();
            rs = st.executeQuery(sql);

            while(rs.next()){
                System.out.println("Id: " + rs.getInt("id") + " Department: " + rs.getString("name"));

            }
            System.out.println("----------------------------");

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }

    //INSERIR
    public void insereDepartment(Department dp){
        try{
            String sql = "INSERT INTO department (name,);";
            prds = cnn.prepareStatement(sql);
            prds.setString(1, dp.getName());
            prds.executeUpdate();
        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }
    public void insereSeller(Seller sl){
        try{
           String sql = "INERT INTO seller (name, basesalary, birthdate, departmentid, email)" +
                   " VALUES (?,?,?,?,?);";
           prds.setString(1, sl.getName());
           prds.setDouble(2, sl.getBaseSalary());
           prds.setDate(3, new java.sql.Date(sl.getBirthdate().getTime()));
           prds.setInt(4, sl.getDepartmentId());
           prds.setString(5, sl.getEmail());
           prds.executeUpdate();

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }

    //CONSULTAR
    public Department consultarDepartment(Integer id){
        Department dp = new Department();
        try{
            String sql = "SELECT *FROM department WHERE id = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);

            rs = prds.executeQuery();
            if(rs.next()){
                dp.setId(rs.getInt("id"));
                dp.setName(rs.getString("name"));
            }

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
        return dp;
    }
    public Seller consultarSeller(Integer id){
        Seller sel = new Seller();
        try{
            String sql = "SELECT *FROM seller WHERE id=?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);
            rs = prds.executeQuery();

            if(rs.next()){
                sel.setId(rs.getInt("id"));
                sel.setName(rs.getString("name"));
                sel.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").format(rs.getDate("birthdate")));
                sel.setBaseSalary(rs.getDouble("baseSalary"));
                sel.setDepartmentId(rs.getInt("departmentid"));
                sel.setEmail(rs.getString("email"));
                System.out.println();
            }

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
        return sel;
    }

    //DELETAR
    public void deletarDepartment(Integer id){
        try{
            String sql = "DELETE FROM department WHERE id = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, id);
            prds.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionIntegrity("****Departmamento possui Relacionamento ativo com Seller\n" + e.getMessage());
        }
    }
    public void deletarSeller(Integer id){
        try{
            String sql = "DELETE FROM seller WHERE id = ?;";
            prds = cnn.prepareStatement(sql);
            prds.executeUpdate();
        } catch (SQLException e) {
            throw new ExceptionIntegrity(e.getMessage());
        }
    }

    //ALTERAR
    public void alterarDepartment(Department dp, Integer id){

        try{
            String sql = "UPDATE department set id = ?, name = ? WHERE id = ?;";

            prds = cnn.prepareStatement(sql);
            prds.setInt(1,dp.getId());
            prds.setString(2, dp.getName());
            prds.setInt(3, id);
            prds.executeUpdate();

        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }
    public void alterarSeller(Seller sel, Integer id){
        try{
            String sql = "UPDATE seller SET id = ?, name = ?, birthdate = ?, email = ?, departmentid = ?," +
                    " basesalary = ? WHERE id = ?;";
            prds = cnn.prepareStatement(sql);
            prds.setInt(1, sel.getId());
            prds.setString(2, sel.getName());
            prds.setDate(3, sel.getBirthdate());
            prds.setString(4, sel.getEmail());
            prds.setInt(5, sel.getDepartmentId());
            prds.setDouble(6, sel.getBaseSalary());
            prds.executeUpdate();
        } catch (SQLException e) {
            throw new Excessao(e.getMessage());
        }
    }


    /*API:
    *    setAutoCommit(false)
    *    commit()
    *    rollBack()*/
    //o rollback irá garantir a não alteração dos dados caso haja erro
    public void alterarSalarioAutomaticamente1(Integer id, Integer id2, Double sal1, Double sal2){

        try{
            cnn.setAutoCommit(false);
            String sql1 = "UPDATE seller SET basesalary = ? WHERE departmentid = ?;";
            prds = cnn.prepareStatement(sql1);
            prds.setDouble(1, sal1);
            prds.setInt(2, id);
            int lin1 = prds.executeUpdate();

            int x = 1;
            //if(x < 2){
           //     throw new SQLException("ERRO FALSE");
           // }

            String sql2 = "UPDATE seller SET basesalary = ? WHERE departmentid = ?;";
            prds =  cnn.prepareStatement(sql2);
            prds.setDouble(1, sal2);
            prds.setInt(2, id2);
            int lin2 = prds.executeUpdate();
            cnn.commit();

        } catch (SQLException e) {
            try {
                cnn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new Excessao(e.getMessage());

        }

    }

}
