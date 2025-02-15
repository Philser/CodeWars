import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class EnoughIsEnough {

  public static int[] listToArray(List<Integer> list) {
    int[] result = new int[list.size()];
    for(int i = 0; i < result.length; i++) 
      result[i] = list.get(i);
    
    return result;
  }

	public static int[] deleteNth(int[] elements, int maxOccurrences) {
		HashMap<Integer, Integer> countedOccurrences = new HashMap<>();
    List<Integer> resultList = new ArrayList<>();
    for(int element: elements) {
      // Update occurrence counter
      int currCount = countedOccurrences.getOrDefault(element, 0);
      countedOccurrences.put(element, currCount + 1);
      
      // Add to list if occurrence not at max
      if(currCount + 1 <= maxOccurrences)
        resultList.add(element);
    }
		return listToArray(resultList);
	}

}
