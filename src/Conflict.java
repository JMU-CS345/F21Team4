
public class Conflict {
	boolean conflict = true;
	public Conflict(boolean conflictCurr)
	{
		conflict = conflictCurr;
	}
	
	public void isConflict(boolean conflict) {
		if (!conflict) {
			this.conflict = false;
		} else {
			this.conflict = true;
		}
	}
}
