
package modelo;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class Docente extends Persona {

    private String nit;
    private String codigo_docente;
    private double salario;
    private String fecha_ingreso_laborar;
    private String fecha_ingreso_registro;
    private int id;
    
    Conexion cn;

    public Docente() {
    }

    public Docente(int id, String nit, String codigo_docente, double salario, String fecha_ingreso_laborar, String fecha_ingreso_registro, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.nit = nit;
        this.codigo_docente = codigo_docente;
        this.salario = salario;
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
        this.fecha_ingreso_registro = fecha_ingreso_registro;
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getFecha_ingreso_laborar() {
        return fecha_ingreso_laborar;
    }

    public void setFecha_ingreso_laborar(String fecha_ingreso_laborar) {
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
    }

    public String getFecha_ingreso_registro() {
        return fecha_ingreso_registro;
    }

    public void setFecha_ingreso_registro(String fecha_ingreso_registro) {
        this.fecha_ingreso_registro = fecha_ingreso_registro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        
        try{
            cn = new Conexion();
            cn.abrir_conexion();
        String query;
        query = "Select id_docente as id,nit,nombres,apellidos,direccion,telefono,fecha_nacimiento, codigo_docente, salario, fecha_ingreso_laborar,"
                + "fecha_ingreso_registro from docente;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
        
        String encabezado [] = {"id","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento", "Codigo docente", "Salario", "Fecha de Ingreso", "Fecha del registro"};
        tabla.setColumnIdentifiers(encabezado);
        
        String datos[] = new String[11];
        
        while (consulta.next()){
        
        datos[0] = consulta.getString("id");
        datos[1] = consulta.getString("nit");
        datos[2] = consulta.getString("nombres");
        datos[3] = consulta.getString("apellidos");
        datos[4] = consulta.getString("direccion");
        datos[5] = consulta.getString("telefono");
        datos[6] = consulta.getString("fecha_nacimiento");
        datos[7] = consulta.getString("codigo_docente");
        datos[8] = consulta.getString("salario");
        datos[9] = consulta.getString("fecha_ingreso_laborar");
        datos[10] = consulta.getString("fecha_ingreso_laborar");
        datos[10] = consulta.getString("fecha_ingreso_registro");
        
        tabla.addRow(datos);
        
        }
        cn.cerrar_conexion();
        
        }catch(SQLException ex){
                    cn.cerrar_conexion();
          System.out.println("Error..." + ex.getMessage()); 
        }
        
        return tabla;
    }
    
    public void agregar() {
        try {

            PreparedStatement parametro;
            String query = "INSERT INTO docente (nit,nombres,apellidos,direccion,telefono,fecha_nacimiento,codigo_docente, salario, fecha_ingreso_laborar,fecha_ingreso_registro ) VALUES(?,?,?,?,?,?,?,?,?,?);";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_Nacimiento());
            parametro.setString(7, getCodigo_docente());
            String Salario2 = Double.toString(getSalario());
            parametro.setString(8, Salario2);
            parametro.setString(9, getFecha_ingreso_laborar());
            parametro.setString(10,getFecha_ingreso_registro());

            
            
            
            int executar = parametro.executeUpdate();

            cn.cerrar_conexion();

            JOptionPane.showMessageDialog(null, Integer.toString(executar) + "Registro ingresado", "Agregar", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException ex) {
            System.out.println("Error...." + ex.getMessage());

        }

    }
    
    @Override
    public void actualizar(){
            try {

            PreparedStatement parametro;
            String query = "update docente set nit = ? ,nombres = ? ,apellidos = ? ,direccion = ? ,telefono = ? ,fecha_nacimiento = ? ,codigo_docente = ? , salario = ? , fecha_ingreso_laborar = ? ,fecha_ingreso_registro = ? "
                    + "where id_docente = ?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_Nacimiento());
            parametro.setString(7, getCodigo_docente());
            String Salario2 = Double.toString(getSalario());
            parametro.setString(8, Salario2);
            parametro.setString(9, getFecha_ingreso_laborar());
            parametro.setString(10,getFecha_ingreso_registro());
            parametro.setInt(11, getId());
            
            
            int executar = parametro.executeUpdate();

            cn.cerrar_conexion();

            JOptionPane.showMessageDialog(null, Integer.toString(executar) + "Registro ingresado", "Agregar", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException ex) {
            System.out.println("Error...." + ex.getMessage());

        }

    
 
    }
    @Override
    public void eliminar(){ 
        
         try {

            PreparedStatement parametro;
            String query = "delete from docente where id_docente = ?";
            cn = new Conexion();
            cn.abrir_conexion();
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            
            int executar = parametro.executeUpdate();

            cn.cerrar_conexion();

            JOptionPane.showMessageDialog(null, Integer.toString(executar) + "Registro eliminado", "Eliminar", JOptionPane.INFORMATION_MESSAGE);

        } catch (HeadlessException | SQLException ex) {
            System.out.println("Error...." + ex.getMessage());

        }
    }

}

