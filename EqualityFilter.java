package olx;

public class EqualityFilter<T extends Comparable<T>> extends Filter<T> {
	T value;
	public EqualityFilter(String name, T value) {
		super(name);
		this.value = value;
	}
	@Override
	public boolean check(T value) {
		return this.value.compareTo(value) == 0;
	}
}
