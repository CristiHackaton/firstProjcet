package com.app.gui;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;







import com.app.db.gateway.UserGateway;
import com.app.db.model.User;
import com.app.service.operations.ConsultationService;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings({ "unchecked", "unchecked" })
public class DoctorScreen extends JFrame{
	private JScrollPane listPanel;
	private JPanel panelVizualizare;
	private JList list;
	private ConsultationService consServ;
	private User user;
	public DoctorScreen() {
		setTitle("Welcome Doctor!");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 381, 240);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		list.repaint();
		listPanel.repaint();
		panelVizualizare.revalidate();
		panelVizualizare.repaint();
		panelVizualizare.setVisible(true);
		JButton butonViewConsultations = new JButton("View Consultations");
		butonViewConsultations.setBounds(10, 11, 89, 23);
		panel.add(butonViewConsultations);
		
		JList list = new JList();
		list.setBackground(new Color(255, 255, 204));
		list.setBounds(10, 51, 138, 165);
		panel.add(list);
	}
private void afisareVizualizareUseri() {
		
		//list = new JList(new PatientListModel(consServ(user)));//creare lista de pacienti
		 listPanel = new JScrollPane(list);
		listPanel.setBounds(10, 10, 320,500);
		panelVizualizare.add(listPanel);
		getContentPane().add(panelVizualizare);
		panelVizualizare.setLayout(null);
		panelVizualizare.setBounds(10, 32, 1700, 750);
		
		final JPanel panel = new JPanel();
		panel.setBounds(395, 39, 500, 500);
		
		panel.setLayout(null);
		panelVizualizare.add(panel);
		JButton butonStergeUser = new JButton("Sterge User");
		butonStergeUser.setBounds(0, 0, 135, 23);
		butonStergeUser.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent arg0) {
			User u=new User();
			UserGateway usg= new UserGateway();
			usg.removeUser(u.getUserID());
			
			
		
			
			}
		});
}
}
