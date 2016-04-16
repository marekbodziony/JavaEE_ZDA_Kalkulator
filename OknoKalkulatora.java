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
	private double aktualnyWynik = 0;
	private boolean czyWykonacObliczenia;		// informuje czy ma zostac wykonane wybrane dzialanie matematyczne (czy sa juz dwie liczby do obliczen)
	private boolean czyJuzPoliczono;			// do obslugi powtarzania ostatniego dzialania po wybraniu " = "
	private boolean czyNastopilaZmianaDzialania;
	private boolean czyJestAktualnaLiczba;		// czy aktualna liczba zostala wprowadzona
	private boolean czyRozpoczacNoweObliczenia;	// gdy otrzymamy 'wynik' dzialania (nacisniety zostal znak "="), mozemy uzyc 'wyniku' aby kontunuowac obliczenia (wartosc: FALSE) 
												// albo wprowadzic nowa liczby dla nowych obliczen (wartosc: TRUE)
	
	public OknoKalkulatora(){
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// jak ma byc zamkniete okno po kliknieciu "x" 
		setTitle("Kalkulator");
				
		JPanel kalkulator = new JPanel();
		JPanel znakPlusWyswietlacz = new JPanel();
		JPanel znak = new JPanel();
		JPanel wyswietlacz = new JPanel();
		JPanel cyfryPlusDzialania = new JPanel();
		JPanel cyfry = new JPanel();
		JPanel dzialania = new JPanel();
		
		kalkulator.add(znakPlusWyswietlacz);
		kalkulator.add(cyfryPlusDzialania);
		znakPlusWyswietlacz.add(znak);
		znakPlusWyswietlacz.add(wyswietlacz);
		cyfryPlusDzialania.add(cyfry);
		cyfryPlusDzialania.add(dzialania);
				
		kalkulator.setLayout(new BoxLayout(kalkulator,BoxLayout.PAGE_AXIS ));
		znakPlusWyswietlacz.setLayout(new BoxLayout(znakPlusWyswietlacz, BoxLayout.LINE_AXIS));
		cyfryPlusDzialania.setLayout(new BoxLayout(cyfryPlusDzialania, BoxLayout.LINE_AXIS));
		cyfry.setLayout(new GridLayout(4,3));
		dzialania.setLayout(new GridLayout(5,0));
		
		// ----- Obsluga wyswietlacza wyniku ----
		
		JTextField wyswietlaczKalkulatora = new JTextField(13);
		wyswietlaczKalkulatora.setSize(200, 30);
		wyswietlaczKalkulatora.setHorizontalAlignment(JTextField.RIGHT);
		wyswietlaczKalkulatora.setText(aktualnaLiczba);		
		wyswietlaczKalkulatora.setEditable(false);	// nie mozna nic wpisac "recznie" na wyswietlacz kalkulatora (wyswietla on tylko podane liczby lub wynik)
		
		JTextField znakNaWyswietlaczu = new JTextField(1);
		znakNaWyswietlaczu.setHorizontalAlignment(JTextField.CENTER);
		znakNaWyswietlaczu.setEditable(false);
		
		// ----- Obsluga przyciskow z dzialaniami ----
		
		JButton znakDodawania = new JButton("+");
		znakDodawania.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// oblicza poprzednie dzialanie, gdy zmieniono znak (gdy wykonujemy oblicznia, ale nie wyswietlono jeszcze wyniku znakiem "=")
				if((poprzedniaLiczba.getDzialanie() != Mat.Dodawanie) && (poprzedniaLiczba.getDzialanie() != null)){ 	
					poprzedniaLiczba.setLiczba(poprzedniaLiczba.oblicz(Double.parseDouble(aktualnaLiczba),czyJestAktualnaLiczba)); 
					czyNastopilaZmianaDzialania = true; } 
				poprzedniaLiczba.setDzialanie(Mat.Dodawanie);	// ustawiamy działanie na "Dodawanie"
				// jesli nie ma przeszkod do wykonania obliczen (jest aktualna i poprzednia), wykonaj dodawanie
				if(czyWykonacObliczenia) {aktualnyWynik = poprzedniaLiczba.oblicz(Double.parseDouble(aktualnaLiczba),czyJestAktualnaLiczba);}	
				// nic nie obliczaj jesli pierwszy raz nacisnieto znak "+" (mamy aktualna liczbe, wiec zapisz aktualna jako poprzednia)
				if(!czyWykonacObliczenia) { 	
					aktualnyWynik = Double.parseDouble(aktualnaLiczba);
					czyWykonacObliczenia = true; }	
				// kiedy wybrano inne dzialanie (przed wykonaniem poprzedniego znakiem "="), nie ma aktualnej, do wyniku wstaw poprzednia
				if (czyNastopilaZmianaDzialania){ aktualnyWynik = poprzedniaLiczba.getLiczba(); czyNastopilaZmianaDzialania = false;}	
				poprzedniaLiczba.setLiczba(aktualnyWynik);
				czyJestAktualnaLiczba = false;		// usuwamy aktualna liczbe
				aktualnaLiczba = "0";				// zeby mozna bylo wpisac nowa liczbe
				czyRozpoczacNoweObliczenia = false;	// mamy wynik, ale chcemy z nim jeszcze cos zrobic (przy wprowadzaniu nowej liczby nie skasuj poprzedniej)
				znakNaWyswietlaczu.setText("+");
				System.out.println(" + \t a="+aktualnaLiczba + ", p=" + poprzedniaLiczba.getLiczba() + ", w=" + aktualnyWynik + "   dzial=" + poprzedniaLiczba.getDzialanie().name());
			}
		});
		JButton znakOdejmowania = new JButton("-");
		znakOdejmowania.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent event){
				// oblicza poprzednie dzialanie, gdy zmieniono znak (gdy wykonujemy oblicznia, ale nie wyswietlono jeszcze wyniku znakiem "=")
				if((poprzedniaLiczba.getDzialanie() != Mat.Odejmowanie) && (poprzedniaLiczba.getDzialanie() != null)){ 	
					poprzedniaLiczba.setLiczba(poprzedniaLiczba.oblicz(Double.parseDouble(aktualnaLiczba),czyJestAktualnaLiczba)); 
					czyNastopilaZmianaDzialania = true; } 
				poprzedniaLiczba.setDzialanie(Mat.Odejmowanie);	// ustawiamy działanie na "Odejmowanie"
				// jesli nie ma przeszkod do wykonania obliczen (jest aktualna i poprzednia), wykonaj odejmowanie
				if(czyWykonacObliczenia) {aktualnyWynik = poprzedniaLiczba.oblicz(Double.parseDouble(aktualnaLiczba),czyJestAktualnaLiczba);}
				// nic nie obliczaj jesli pierwszy raz nacisnieto znak "-" (mamy aktualna liczbe, wiec zapisz aktualna jako poprzednia)
				if(!czyWykonacObliczenia) { 
					aktualnyWynik = Double.parseDouble(aktualnaLiczba);
					czyWykonacObliczenia = true; }	
				// kiedy wybrano inne dzialanie (przed wykonaniem poprzedniego znakiem "="), nie ma aktualnej, do wyniku wstaw poprzednia
				if (czyNastopilaZmianaDzialania){ aktualnyWynik = poprzedniaLiczba.getLiczba(); czyNastopilaZmianaDzialania = false;}	
				poprzedniaLiczba.setLiczba(aktualnyWynik);
				czyJestAktualnaLiczba = false;		// usuwamy aktualna liczbe
				aktualnaLiczba = "0";				// zeby mozna bylo wpisac nowa liczbe
				czyRozpoczacNoweObliczenia = false;	// mamy wynik, ale chcemy z nim jeszcze cos zrobic (przy wprowadzaniu nowej liczby nie skasuj poprzedniej)
				znakNaWyswietlaczu.setText("-");
				System.out.println(" - \t a="+aktualnaLiczba + ", p=" + poprzedniaLiczba.getLiczba() + ", w=" + aktualnyWynik + "   dzial=" + poprzedniaLiczba.getDzialanie().name());
			}
		});
		JButton znakMnozenia = new JButton("*");
		znakMnozenia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				// oblicza poprzednie dzialanie, gdy zmieniono znak (gdy wykonujemy oblicznia, ale nie wyswietlono jeszcze wyniku znakiem "=")
				if((poprzedniaLiczba.getDzialanie() != Mat.Mnozenie) && (poprzedniaLiczba.getDzialanie() != null)){ 	
					poprzedniaLiczba.setLiczba(poprzedniaLiczba.oblicz(Double.parseDouble(aktualnaLiczba),czyJestAktualnaLiczba)); 
					czyNastopilaZmianaDzialania = true; } 
				poprzedniaLiczba.setDzialanie(Mat.Mnozenie);	// ustawiamy działanie na "Mnozenie"
				// jesli nie ma przeszkod do wykonania obliczen (jest aktualna i poprzednia), wykonaj mnozenie
				if(czyWykonacObliczenia) {aktualnyWynik = poprzedniaLiczba.oblicz(Double.parseDouble(aktualnaLiczba),czyJestAktualnaLiczba);}	
				// nic nie obliczaj jesli pierwszy raz nacisnieto znak "*" (mamy aktualna liczbe, wiec zapisz aktualna jako poprzednia)
				if(!czyWykonacObliczenia) { 	
					aktualnyWynik = Double.parseDouble(aktualnaLiczba);
					czyWykonacObliczenia = true; }	
				// kiedy wybrano inne dzialanie (przed wykonaniem poprzedniego znakiem "="), nie ma aktualnej, do wyniku wstaw poprzednia
				if (czyNastopilaZmianaDzialania){ aktualnyWynik = poprzedniaLiczba.getLiczba(); czyNastopilaZmianaDzialania = false;}	
				poprzedniaLiczba.setLiczba(aktualnyWynik);
				czyJestAktualnaLiczba = false;		// usuwamy aktualna
				aktualnaLiczba = "0";				// zeby mozna bylo wpisac nowa liczbe
				czyRozpoczacNoweObliczenia = false;	// mamy wynik, ale chcemy z nim jeszcze cos zrobic (przy wprowadzaniu nowej liczby nie skasuj poprzedniej)
				znakNaWyswietlaczu.setText("*");
				System.out.println(" * \t a="+aktualnaLiczba + ", p=" + poprzedniaLiczba.getLiczba() + ", w=" + aktualnyWynik + "   dzial=" + poprzedniaLiczba.getDzialanie().name());
			}
		});
		JButton znakDzielenia = new JButton("/");
		znakDzielenia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				// oblicza poprzednie dzialanie, gdy zmieniono znak (gdy wykonujemy oblicznia, ale nie wyswietlono jeszcze wyniku znakiem "=")
				if((poprzedniaLiczba.getDzialanie() != Mat.Dzielenie) && (poprzedniaLiczba.getDzialanie() != null)){ 	
					poprzedniaLiczba.setLiczba(poprzedniaLiczba.oblicz(Double.parseDouble(aktualnaLiczba),czyJestAktualnaLiczba)); 
					czyNastopilaZmianaDzialania = true; } 
				poprzedniaLiczba.setDzialanie(Mat.Dzielenie);	// ustawiamy działanie na "Dzielenie"
				// jesli nie ma przeszkod do wykonania obliczen (jest aktualna i poprzednia), wykonaj dzielenie
				if(czyWykonacObliczenia) {aktualnyWynik = poprzedniaLiczba.oblicz(Double.parseDouble(aktualnaLiczba),czyJestAktualnaLiczba);}
				// nic nie obliczaj jesli pierwszy raz nacisnieto znak "/" (mamy aktualna liczbe, wiec zapisz aktualna jako poprzednia)
				if(!czyWykonacObliczenia) { 	
					aktualnyWynik = Double.parseDouble(aktualnaLiczba);
					czyWykonacObliczenia = true; }
				// kiedy wybrano inne dzialanie (przed wykonaniem poprzedniego znakiem "="), nie ma aktualnej, do wyniku wstaw poprzednia
				if (czyNastopilaZmianaDzialania){ aktualnyWynik = poprzedniaLiczba.getLiczba(); czyNastopilaZmianaDzialania = false;}	
				poprzedniaLiczba.setLiczba(aktualnyWynik);
				czyJestAktualnaLiczba = false;		// usuwamy aktualna liczbe
				aktualnaLiczba = "0";				// zeby mozna bylo wpisac nowa liczbe
				czyRozpoczacNoweObliczenia = false;	// mamy wynik, ale chcemy z nim jeszcze cos zrobic (przy wprowadzaniu nowej liczby nie skasuj poprzedniej)
				znakNaWyswietlaczu.setText("/");
				System.out.println(" / \t a="+aktualnaLiczba + ", p=" + poprzedniaLiczba.getLiczba() + ", w=" + aktualnyWynik + "   dzial=" + poprzedniaLiczba.getDzialanie().name());
			}
		});
		
		JButton znakWyniku = new JButton("=");
		znakWyniku.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				// oblicz wynik wybranego dzialania
				aktualnyWynik = poprzedniaLiczba.oblicz(Double.parseDouble(aktualnaLiczba),czyJestAktualnaLiczba);
				poprzedniaLiczba.setLiczba(aktualnyWynik);
				czyJestAktualnaLiczba = false;		// usuwamy aktualna liczbe
				aktualnaLiczba = "0";				// zeby mozna bylo wpisac nowa liczbe
				czyRozpoczacNoweObliczenia = true;		// mamy wynik, mozna wykonywac nowe obliczenia (przy wprowadzaniu nowej liczby skasuj poprzednia z pamieci)
				wyswietlaczKalkulatora.setText(wyswietlPoprawnieWynikDouble(aktualnyWynik));		// wyswietla aktualny wynik na wyswietlaczu
				znakNaWyswietlaczu.setText("=");
				czyWykonacObliczenia = true;
				System.out.print("=");
				wyswietlAktualneWartosciZmiennych();
			}
		});
		JButton znakKasuj = new JButton("C");
		znakKasuj.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				// usuwamy wszystkie liczby z pamieci oraz wybor dzialania
				czyJestAktualnaLiczba = false;
				aktualnaLiczba ="0";
				poprzedniaLiczba.setLiczba(0);
				aktualnyWynik = 0;
				poprzedniaLiczba.setDzialanie(null);
				czyWykonacObliczenia = false;		// po wybraniu dzialania nic nie obliczaj (nie bedzie drugiej liczby, bo zostala wlasnie usunieta z pamieci)
				wyswietlaczKalkulatora.setText("0");
				znakNaWyswietlaczu.setText("");
				System.out.print("-clear-");
				wyswietlAktualneWartosciZmiennych();
			}
		});
		
		// ----- Obsluga przyciskow z cyframi ----
		
		ActionListener utworzLiczbe = new ActionListener(){
			public void actionPerformed (ActionEvent event){
				// beda wykonywane nowe obliczenia (wyczysc poprzednia liczba oraz dzialanie)
				if (czyRozpoczacNoweObliczenia){			
					poprzedniaLiczba.setLiczba(0);
					poprzedniaLiczba.setDzialanie(null);
					czyRozpoczacNoweObliczenia = false;
					czyWykonacObliczenia = false;
					aktualnaLiczba = "0";
					czyJestAktualnaLiczba = false;
				}
				czyJestAktualnaLiczba = true;
				aktualnaLiczba += event.getActionCommand();
				aktualnaLiczba = wyswietlPoprawnieLiczbeString(aktualnaLiczba);		// usuwa niepotrzebne "0" z poczatku stringu
				wyswietlaczKalkulatora.setText(aktualnaLiczba);		// wyswietla wpisywana liczbe na wyswietlaczu kalkulatora
				wyswietlAktualneWartosciZmiennych();
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
				// jesli nie ma "." w stringu aktualna to go wstaw, jesli juz jest to nie pozwol na wstawienie kolejnego
				if (!aktualnaLiczba.contains(".")){
					aktualnaLiczba += event.getActionCommand();
					wyswietlaczKalkulatora.setText((aktualnaLiczba));
					czyJestAktualnaLiczba = true;
					czyRozpoczacNoweObliczenia = false;
				}
			}
		});
		
		wyswietlacz.add(wyswietlaczKalkulatora);
		znak.add(znakNaWyswietlaczu);
		
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
	
	// pomocnicza metoda do poprawnego wyswietlania liczby typu String, usuwa niepotrzebne "0" z poczatku stringu (np. 0122.0 -> 122.0, 00000.03 -> 0.03)
	private String wyswietlPoprawnieLiczbeString (String liczbaString){
		
		if (liczbaString.substring(0,1).equals("0") && liczbaString.length() > 1){
			if (!liczbaString.substring(1,2).equals(".")){
				liczbaString = liczbaString.substring(1);
			}
		}
		return liczbaString;
	}
	// pomocnicza metoda do poprawnego wyswietlania wyniku typu float (dla calkowitych liczba nie wyswietla ".0", np 35.0 -> 35) 
		private String wyswietlPoprawnieWynikDouble (double wynikDouble){
			
			String wynikString = "" + wynikDouble;
			if (wynikString.substring(wynikString.length()-1).equals("0")){
				wynikString = wynikString.substring(0, wynikString.length()-2);
			}
			return wynikString;
		}
	// pomocnicza metoda do wyswietlania aktualnych wartosc zmiennych
	private void wyswietlAktualneWartosciZmiennych(){
		String aktualneWartosci = "\t a="+aktualnaLiczba + ", p=" + poprzedniaLiczba.getLiczba() + ", w=" + aktualnyWynik + "   dzial=";
		if (poprzedniaLiczba.getDzialanie() == null) {aktualneWartosci += "null";}
		else {aktualneWartosci += poprzedniaLiczba.getDzialanie().name();}
				
		System.out.println(aktualneWartosci);
	}

}
