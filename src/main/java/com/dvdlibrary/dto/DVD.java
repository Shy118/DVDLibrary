package com.dvdlibrary.dto;


public class DVD {
	private String dvdId;
	private String title;
	private String releaseDate;
	private String mPAA;
	private String directorName;
	private String studio;
	private String rating;
	
	public DVD(String dvdId) {
		this.dvdId = dvdId;
	}
	
	public String getDvdId() {
		return dvdId;
	}
	
	public void setDvdId(String dvdId) {
		this.dvdId = dvdId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public String getMPAA() {
		return mPAA;
	}
	
	public void setMPAA(String mPAA) {
		this.mPAA = mPAA;
	}
	
	public String getDirectorName() {
		return directorName;
	}
	
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	
	public String getStudio() {
		return studio;
	}
	
	public void setStudio(String studio) {
		this.studio = studio;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "ID: " + dvdId + " | Title: " + title + " | Release Date: " + releaseDate + " | MPAA rating: " + mPAA
				+ " | Director's Name: " + directorName + " | Studio: " + studio + " | Rating: " + rating;
	}

	
	
}

	
	