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
		if (dzialanie == Mat.Dzielenie && czyJestLiczba2)	{
			if (liczba2 == 0) {System.out.println("Blad! Nie mozna dzielic przez \"0\""); return wynik = 666666f;}		// blad, nie mozna dzielic przez "0"
			return wynik = podziel(liczba2);
		}
		if (dzialanie == null) { return wynik = dodaj(liczba2);}	// gdy nie wybrano zadnego dzialania (zwraca poprzednia lub aktualna - ktora bedzie niezerowa)
		return wynik;
	}
	
	// metody pomocnicze (dodawanie, odejmowanie, mnozenie, dzielenie)
	private double dodaj (double liczba2){ return liczba1 + liczba2; }
	private double odejmij (double liczba2){ return liczba1 - liczba2; }
	private double pomnoz (double liczba2){ return liczba1 * liczba2; }
	private double podziel (double liczba2){ return liczba1 / liczba2; }
	
	// getters, setters
	public double getLiczba(){return liczba1;}
	public Mat getDzialanie(){return dzialanie;}
	public void setLiczba(double licz){ liczba1 = licz;}
	public void setDzialanie (Mat dzialMat){dzialanie = dzialMat;};
	
	// typ wyliczeniowy - dostepne dzialania
	public enum Mat {Dodawanie, Odejmowanie, Mnozenie, Dzielenie};
}
