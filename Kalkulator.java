package kalkulator;

public class Kalkulator {
	
	private double liczba1;
	private Mat dzialanie;
		
	public Kalkulator (){
	}
	
	public double oblicz(double liczba2){
		double wynik = 999999999f;
		if (dzialanie == Mat.Dodawanie)		{return wynik = dodaj(liczba2);}
		if (dzialanie == Mat.Odejmowanie) 	{return wynik = odejmij(liczba2);}
		if (dzialanie == Mat.Mnozenie)		{return wynik = pomnoz(liczba2);}
		if (dzialanie == Mat.Dzielenie)	{
			if (liczba2 == 0) {return liczba1;}
			return wynik = podziel(liczba2);
		}
		if (dzialanie == null) { return wynik = dodaj(liczba2); }	// gdy nie wybrano zadnego dzialania: sumujemy 2 liczby (poprzednia + aktualna), tyle ze jedna z nich 
																	// zawsze bedzie rowna "0", wiec ich suma zawsze zwroci wartosc tej niezerowej
		return wynik;
	}
	
	private double dodaj (double liczba2){ return liczba1 + liczba2; }
	private double odejmij (double liczba2){ return liczba1 - liczba2; }
	private double pomnoz (double liczba2){ return liczba1 * liczba2; }
	private double podziel (double liczba2){ return liczba1 / liczba2; }
	
	public double getLiczba(){return liczba1;}
	public Mat getDzialanie(){return dzialanie;}
	public void setLiczba(double licz){ liczba1 = licz;}
	public void setDzialanie (Mat dzialMat){dzialanie = dzialMat;};
	
	public enum Mat {Dodawanie, Odejmowanie, Mnozenie, Dzielenie};
}
