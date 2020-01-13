package olx;
import java.util.*;

public class QueryBuilder {
	List<Filter<Integer>> intFilters = new ArrayList<>();
	List<Filter<String>> stringFilters = new ArrayList<>();

	List<Advertisement> initialResults; //to enable filter removal.
	List<Advertisement> currentResults;
	
	public QueryBuilder() {
		initialResults = new ArrayList<>();
		currentResults = new ArrayList<>();
	}
	public QueryBuilder(List<Advertisement> initialResults) {
		this.initialResults = initialResults;
		currentResults = initialResults; 
		//It may look like copying the list here is necessary but it is not because the filter methods do not modify 
		//this list.
	}
	
	private void apply() {
		List<Advertisement> tempList = new ArrayList<>();
		for(Advertisement ad : currentResults) {
			if(ad.satisfyQuery(this)) {
				tempList.add(ad);
			}
		}
		this.currentResults = tempList;
	}
	public void addIntFilter(Filter<Integer> filter) {
		intFilters.add(filter);
		apply();
	}
	public void addStringFilter(Filter<String> filter) {
		stringFilters.add(filter);
		apply();
	}
	
	public void removeIntFilter(Filter<Integer> filter) {
		intFilters.remove(filter);
		currentResults = initialResults;
	}
	public void removeStringFilter(Filter<String> filter) {
		stringFilters.remove(filter);
		currentResults = initialResults;
	}
	
	public List<Filter<Integer>> getIntFilters() {
		return intFilters;
	}
	public List<Filter<String>> getStringFilters() {
		return stringFilters;
	}
	
	public List<Advertisement> getResults() {
		return currentResults;
	}
	public void sortByPrice() {
		//tobeimplemened.
		//Collections.sort(initialResults, new Sortbyprice());
	}
	
	public void sortByTime() {
		//tobeimplemented.
	}
}
