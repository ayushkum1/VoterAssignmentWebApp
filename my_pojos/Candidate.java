package com.app.my_pojos;

public class Candidate {
	
	private int id;
	private String candidateName, party;
	private int votes;

	public Candidate(String candidateName, String party, int votes) {
		super();
		this.candidateName = candidateName;
		this.party = party;
		this.votes = votes;
	}

	public Candidate(int id, String candidateName, String party, int votes) {
		super();
		this.id = id;
		this.candidateName = candidateName;
		this.party = party;
		this.votes = votes;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Candidate id=" + id + ", Name: " + candidateName + ", party: " + party;
	}
	
}
