package kalkulator;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kalkulator.Kalkulator.Mat;

public class OknoKalkulatora extends JFrame {
	
	private Kalkulator poprzedniaLiczba = new Kalkulator();
	private String aktualnaLiczba = "";
	private float aktualnyWynik = 0;
	private boolean czyJuzPoliczono;		// konieczne dla obslugi powtarzania dzialania po wybraniu " = "
	
	public OknoKalkulatora(){
	
		JPanel kalkulator = new JPanel();
		JPanel wyswietlacz = new JPanel();
		JPanel cyfryDzialania = new JPanel();
		JPanel cyfry = new JPanel();
		JPanel dzialania = new JPanel();
		
		kalkulator.add(wyswietlacz);
		kalkulator.add(cyfryDzialania);
		cyfryDzialania.add(cyfry);
		cyfryDzialania.add(dzialania);
				
		kalkulator.setLayout(new BoxLayout(kalkulator,BoxLayout.PAGE_AXIS ));
		cyfryDzialania.setLayout(new BoxLayout(cyfryDzialania, BoxLayout.LINE_AXIS));
		cyfry.setLayout(new GridLayout(4,3));
		dzialania.setLayout(new GridLayout(5,0));
		
		
		ActionListener utworzLiczbe = new ActionListener(){
			public void actionPerformed (ActionEvent e){
				System.out.print(e.getActionCommand());
				aktualnaLiczba += e.getActionCommand();
			}
		};
		
		JButton cyfra1 = new JButton("1");
		cyfra1.addActionListener(utworzLiczbe);
		JButton cyfra2 = new JButton("2");
		cyfra2.addActionListener(utworzLiczbe);
		JButton cyfra3 = new JButton("3");
		cyfra3.addActionListener(utworzLiczbe);
		JButton cyfra4 = new JButton("4");
		cyfra4.addActionListener(utworzLiczbe);
		JButton cyfra5 = new JButton("5");
		cyfra5.addActionListener(utworzLiczbe);
		JButton cyfra6 = new JButton("6");
		cyfra6.addActionListener(utworzLiczbe);
		JButton cyfra7 = new JButton("7");
		cyfra7.addActionListener(utworzLiczbe);
		JButton cyfra8 = new JButton("8");
		cyfra8.addActionListener(utworzLiczbe);
		JButton cyfra9 = new JButton("9");
		cyfra9.addActionListener(utworzLiczbe);
		JButton cyfra0 = new JButton("0");
		cyfra0.addActionListener(utworzLiczbe);
		
		JButton znakPrzecinek = new JButton(".");
		znakPrzecinek.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if (!aktualnaLiczba.contains(".")){
					aktualnaLiczba += event.getActionCommand();
					System.out.print(event.getActionCommand());
				}
			}
		});
		
		JButton znakDodawania = new JButton("+");
		znakDodawania.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(aktualnaLiczba.equals("")) return;
				if (czyJuzPoliczono) {aktualnaLiczba = "0";}
				poprzedniaLiczba.setLiczba(poprzedniaLiczba.oblicz(Float.parseFloat(aktualnaLiczba)));
				aktualnaLiczba ="";
				poprzedniaLiczba.setDzialanie(Mat.Dodawanie);
				System.out.println(" + ");
				czyJuzPoliczono = false;
			}
		});
		JButton znakOdejmowania = new JButton("-");
		JButton znakMnozenia = new JButton("*");
		JButton znakDzielenia = new JButton("/");
		JButton znakWyniku = new JButton("=");
		znakWyniku.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(aktualnaLiczba.equals("")) { aktualnaLiczba = "0";};
				aktualnyWynik = poprzedniaLiczba.oblicz(Float.parseFloat(aktualnaLiczba));		// 'aktualnyWynik' mozna usunac zmienna!
				poprzedniaLiczba.setLiczba(aktualnyWynik);
				if (poprzedniaLiczba.getDzialanie() != null) {czyJuzPoliczono = true;}
				System.out.println("\nWynik = " + aktualnyWynik + "  (poprzed = " + poprzedniaLiczba.getLiczba() + ", aktual = " + aktualnaLiczba +")");
			}
		});
		JButton znakKasuj = new JButton("C");
		znakKasuj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				aktualnaLiczba ="";
			}
		});
		
		JTextField wyswietlaczKalkulatora = new JTextField("0");
		wyswietlaczKalkulatora.setSize(300, 10);
		
			
		wyswietlacz.add(wyswietlaczKalkulatora);
		
		cyfry.add(cyfra1);
		cyfry.add(cyfra2);
		cyfry.add(cyfra3);
		cyfry.add(cyfra4);
		cyfry.add(cyfra5);
		cyfry.add(cyfra6);
		cyfry.add(cyfra7);
		cyfry.add(cyfra8);
		cyfry.add(cyfra9);
		cyfry.add(znakPrzecinek);
		cyfry.add(cyfra0);
		cyfry.add(znakKasuj);
		
		dzialania.add(znakDodawania);
		dzialania.add(znakOdejmowania);
		dzialania.add(znakMnozenia);
		dzialania.add(znakDzielenia);
		dzialania.add(znakWyniku);
		
		//this.setLayout(new GridLayout(2,1));
		this.getContentPane().add(kalkulator);
		//this.getContentPane().add(cyfry);
		
		
		pack();
		setVisible(true);
	
	}

}
