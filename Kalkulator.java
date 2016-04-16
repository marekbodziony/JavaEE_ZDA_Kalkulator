package kalkulator;

public class Kalkulator {
	
	private double liczba1;		
	private Mat dzialanie;				// przechowuje informacje o wybranym dzialaniu (+,-,*,/)
	private boolean czyJestLiczba2;		// czy jest podana liczba2 (nazwana "aktualna" w kalkulatorze)
		
	// pusty konstruktor
	public Kalkulator (){};
	
	// metoda do wykonywania oblicze (w zaleznosci od wybranego dzialania wykonuje odpowiednie obliczenia)
	public double oblicz(double liczba2, boolean czyJestLiczba2){
		double wynik = liczba1;
		if (dzialanie == Mat.Dodawanie)		{return wynik = dodaj(liczba2);}
		if (dzialanie == Mat.Odejmowanie) 	{return wynik = odejmij(liczba2);}
		if ((dzialanie == Mat.Mnozenie) && czyJestLiczba2)	{return wynik = pomnoz(liczba2);}
		// dzielenie z wylaczeniem wyjatku dzielenia przez "0"
		if (dzialanie == Mat.Dzielenie && czyJestLiczba2)	{
			try{
				wynik = podziel(liczba2);
			}
			catch (DzieleniePrezZeroException wyjatek){
				System.out.println(wyjatek.getMessage()); 
			}
			return wynik;
		}
		// gdy nie wybrano zadnego dzialania (mamy w pamieci poprzednia lub aktualna) zwracam te niezerowa liczbe
		if (dzialanie == null) { return wynik = dodaj(liczba2);}	
		
		return wynik;
	}
	
	// metody pomocnicze (dodawanie, odejmowanie, mnozenie, dzielenie)
	private double dodaj (double liczba2){ return liczba1 + liczba2; }
	private double odejmij (double liczba2){ return liczba1 - liczba2; }
	private double pomnoz (double liczba2){ return liczba1 * liczba2; }
	
	private double podziel (double liczba2) throws DzieleniePrezZeroException { 
		// jesli dzielenie przez "0" zwroc wyjatek DzieleniePrezZeroException
		if (liczba2 == 0) { throw new DzieleniePrezZeroException();}  
		return liczba1 / liczba2; }		
	
	// getters, setters
	public double getLiczba(){return liczba1;}
	public Mat getDzialanie(){return dzialanie;}
	public void setLiczba(double licz){ liczba1 = licz;}
	public void setDzialanie (Mat dzialMat){dzialanie = dzialMat;};
	
	// typ wyliczeniowy - dostepne dzialania
	public enum Mat {Dodawanie, Odejmowanie, Mnozenie, Dzielenie};
	
	//
	public class DzieleniePrezZeroException extends Exception{
		public DzieleniePrezZeroException(){
			super("Blad! Proba dzielenia przez 0");
		}
	}
}
