package registration;

public class State implements Comparable<State> {
	private Integer id;
	private Integer speciality;
	private Integer priority;
	private Integer score;
	
	public State(Integer id, Integer speciality, Integer priority, Integer score) {
		super();
		this.id = id;
		this.speciality = speciality;
		this.priority = priority;
		this.score = score;
	}	
	
	@Override
	public int compareTo(State state) {
        if (state == this) {
            return 0;
        }
        if (score < state.getScore()) {
            return 1;
        } else if (score == state.getScore()) {
            return 0;
        } else {
            return -1;
        }

	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result
				+ ((speciality == null) ? 0 : speciality.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		if (speciality == null) {
			if (other.speciality != null)
				return false;
		} else if (!speciality.equals(other.speciality))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "State [id=" + id + ", priority=" + priority + ", score="
				+ score + ", speciality=" + speciality + "]";
	}	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSpeciality() {
		return speciality;
	}
	public void setSpeciality(Integer speciality) {
		this.speciality = speciality;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	
}
