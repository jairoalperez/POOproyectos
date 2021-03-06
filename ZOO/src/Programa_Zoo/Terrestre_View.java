package Programa_Zoo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.sun.glass.ui.Window.Level;
import com.sun.istack.internal.logging.Logger;

import java.sql.Statement;

import java.sql.Connection; 
import 	java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import 	java.sql.SQLException;
import javax.swing.JComboBox;

public class Terrestre_View {

	//Attributes
		private JPanel panel;
		private JLabel label;
		public static JComboBox select;
		private JButton atras;
		private JTable tabla;
		private JButton buscar;
		DefaultTableModel modelo;
		
		
		//llenar el select
		public void fillcombobox() {
			try {
				String sql = "select * from animal where habitat = 'Terrestre'";
			DB.pstmt = DB.conn.prepareStatement(sql);
			DB.rs = DB.pstmt.executeQuery();
			while(DB.rs.next()) {
				this.select.addItem(DB.rs.getString("tipo"));
			}
			} catch (SQLException e) {
			e.printStackTrace();
			}
			}
		
		
		//Constructor
		public Terrestre_View(){
			
			this.panel = new JPanel();
			
			this.label = new JLabel("Terrestres");
			this.label.setBounds(200, 25, 100, 50);
			this.panel.add(label);
			
			this.atras = new JButton ("Atras");
			this.atras.setBounds(1, 1, 75, 50);
			this.atras.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent d) {
					Hab_View base = new Hab_View();
		            IG.vista.setContentPane(base.gethab());
		            IG.vista.invalidate();
		        	IG.vista.validate();
		        }
		    });
			this.panel.add(atras);
			
			this.select = new JComboBox();
			this.select.setBounds(50, 100, 250, 40);
			this.select.addItem("seleccione un animal");
			this.panel.add(select);
			
			fillcombobox();
			
			this.buscar = new JButton("Buscar");
			this.buscar.setBounds(310, 100, 80, 40);
			this.buscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent d) {
					
					 String [] titulos= {"Tipo","Reproduccion","Alimentacion","Temperamento","Habitat","Cantidad"};
					   modelo=new  DefaultTableModel(null,titulos);   
					   String datos []= new String[6];
					   String tipo = select.getSelectedItem().toString();
					   String sql="select * from animal where tipo = '"+ tipo +"'";
					        try {
					        	DB.pstmt = DB.conn.prepareStatement(sql);
								DB.rs = DB.pstmt.executeQuery();
					            while(DB.rs.next())
					            {
					                datos[0]= DB.rs.getString("tipo");
					                datos[1]= DB.rs.getString("reproduccion");
					                datos[2]= DB.rs.getString("alimentacion");
					                datos[3]= DB.rs.getString("temperamento");
					                datos[4]= DB.rs.getString("habitat");
					                datos[5]= DB.rs.getString("cantidad");
					               
					                modelo.addRow(datos);
					            }
					            tabla.setModel(modelo);
					        } catch (SQLException ex) {
					          
					        }
				}
			});
			this.panel.add(buscar);
			
			this.tabla = new JTable();
			this.tabla.setBounds(50, 170, 355, 250);
			this.panel.add(tabla);
			
			modelo = new DefaultTableModel();
			
			this.panel.setSize(500, 500);
			this.panel.setLayout(null);
			this.panel.setVisible(true);	
			
		}
	
	public JPanel getterr() {
		return this.panel;
	}
}