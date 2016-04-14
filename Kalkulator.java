package kalkulator;

public class Kalkulator {
	
	private float liczba1;
	private Mat dzialanie;
		
	public Kalkulator (){
	}
	
	public float oblicz(float liczba2){
		float wynik = 999999999999999f;
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
	
	private float dodaj (float liczba2){ return liczba1 + liczba2; }
	private float odejmij (float liczba2){ return liczba1 - liczba2; }
	private float pomnoz (float liczba2){ return liczba1 * liczba2; }
	private float podziel (float liczba2){ return liczba1 / liczba2; }
	
	public float getLiczba(){return liczba1;}
	public Mat getDzialanie(){return dzialanie;}
	public void setLiczba(float licz){ liczba1 = licz;}
	public void setDzialanie (Mat dzialMat){dzialanie = dzialMat;};
	
	public enum Mat {Dodawanie, Odejmowanie, Mnozenie, Dzielenie};
}
