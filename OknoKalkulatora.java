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
	private String aktualnaLiczba = "0";
	private float aktualnyWynik = 0;
	private boolean czyJuzPoliczono;			// do obslugi powtarzania ostatniego dzialania po wybraniu " = "
	private boolean czyWprowadzicNowaLiczbe;	// informuje, gdy nacisniety zostal znak "=" i chcemy wprowadzac nowa liczbe
	
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
		
		// ----- Obsluga przyciskow z dzialaniami ----
		
		JButton znakDodawania = new JButton("+");
		znakDodawania.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				poprzedniaLiczba.setDzialanie(Mat.Dodawanie);
				aktualnyWynik = poprzedniaLiczba.oblicz(Float.parseFloat(aktualnaLiczba));
				poprzedniaLiczba.setLiczba(aktualnyWynik);
				aktualnaLiczba = "0";
				czyWprowadzicNowaLiczbe = false;	// mamy wynik, ale chcemy do niego jeszcze cos dodac (nie skasuj poprzedniej liczby)
				System.out.println(" + ");
			}
		});
		JButton znakOdejmowania = new JButton("-");
		JButton znakMnozenia = new JButton("*");
		JButton znakDzielenia = new JButton("/");
		
		JButton znakWyniku = new JButton("=");
		znakWyniku.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				aktualnyWynik = poprzedniaLiczba.oblicz(Float.parseFloat(aktualnaLiczba));
				poprzedniaLiczba.setLiczba(aktualnyWynik);
				aktualnaLiczba = "0";			// zeby mozna bylo wpisac nowa liczbe
				czyWprowadzicNowaLiczbe = true;		// mamy wynik, mozna wykonywac nowe obliczenia (skasuj poprzednia liczbe)
				System.out.println("\nWynik = " + aktualnyWynik);
			}
		});
		JButton znakKasuj = new JButton("C");
		znakKasuj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				aktualnaLiczba ="0";
				poprzedniaLiczba.setLiczba(0);
				aktualnyWynik = 0;
				poprzedniaLiczba.setDzialanie(null);
				System.out.println("- clear -");
			}
		});
		
		// ----- Obsluga przyciskow z cyframi ----
		
		ActionListener utworzLiczbe = new ActionListener(){
			public void actionPerformed (ActionEvent event){
				if (czyWprowadzicNowaLiczbe){
					poprzedniaLiczba.setLiczba(0);
					czyWprowadzicNowaLiczbe = false;
				}
				aktualnaLiczba += event.getActionCommand();
				System.out.println("a="+aktualnaLiczba+ "  p="+poprzedniaLiczba.getLiczba());
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
		
		this.getContentPane().add(kalkulator);
		
		pack();
		setVisible(true);
	
	}
	

}
