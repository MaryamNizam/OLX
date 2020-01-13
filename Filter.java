package olx;

public abstract class Filter<T extends Comparable<T>> {
	private String name;

	public Filter(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public abstract boolean check(T value);
}
