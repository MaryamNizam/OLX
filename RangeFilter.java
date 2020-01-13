package olx;

public class RangeFilter<T extends Comparable<T>> extends Filter<T>{

	//BY DEFAULT INCLUSIVE RANGE FILTER
	T up;
	T low;
	public RangeFilter(String name, T low, T up) {
		super(name);
		if(low.compareTo(up) > 0) {
			throw new IllegalArgumentException();
		}
		this.low = low;
		this.up = up;
	}
	
	
	public boolean check(T value) {
		
		return value.compareTo(low) >=0 && value.compareTo(up) <= 0;
	}
	
}
