package registration;

public class Result implements Comparable<Result> {
	
	private Integer id;
	private Integer applicant;
	private Integer speciality;
	private Integer score;
	private Integer priority;
	private boolean enrol;
	
	public Result(Integer id, Integer applicant, Integer speciality,
			Integer score, Integer priority) {
		this.id = id;
		this.applicant = applicant;
		this.speciality = speciality;
		this.score = score;
		this.priority = priority;
		this.enrol = false;
	}
	
	@Override
	public int compareTo(Result result) {
        if (result == this) {
            return 0;
        }
        if (score < result.getScore()) {
            return 1;
        } else if (score == result.getScore()) {
            return 0;
        } else {
            return -1;
        }

	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((applicant == null) ? 0 : applicant.hashCode());
		result = prime * result
				+ ((priority == null) ? 0 : priority.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		result = prime * result
				+ ((speciality == null) ? 0 : speciality.hashCode());
		return result;
	}
	

	@Override
	public String toString() {
		return "Result [applicant=" + applicant + ", enrol=" + enrol + ", id="
				+ id + ", priority=" + priority + ", score=" + score
				+ ", speciality=" + speciality + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Result other = (Result) obj;
		if (applicant == null) {
			if (other.applicant != null)
				return false;
		} else if (!applicant.equals(other.applicant))
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
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public Integer getApplicant() {
		return applicant;
	}
	public void setApplicant(Integer applicant) {
		this.applicant = applicant;
	}
	public Integer getSpeciality() {
		return speciality;
	}
	public void setSpeciality(Integer speciality) {
		this.speciality = speciality;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	public boolean isEnrol() {
		return enrol;
	}

	public void setEnrol(boolean enrol) {
		this.enrol = enrol;
	}	

}
