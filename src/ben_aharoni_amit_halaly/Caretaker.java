package ben_aharoni_amit_halaly;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
	private List<StoreMemento> savedStates = new ArrayList<>();
	private int currentState = -1;

	public void saveState(storeFacade storeFacade) throws CloneNotSupportedException {
		while (savedStates.size() > currentState + 1) {
			savedStates.remove(savedStates.size() - 1);
		}
		//savedStates.add(storeFacade.saveMem());
		currentState = savedStates.size() - 1;
	}

	public void resetState(storeFacade storeFacade, int index) {
		if (index >= 0 && index < savedStates.size()) {
			storeFacade.resetMem(savedStates.get(index));
			currentState = index;
		}
	}

}
