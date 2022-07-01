package com.sudha;

public class Genre {

	private long genreId;
	private String genreName;

	public long getGenreId() {
		return genreId;
	}

	public void setGenreId(long genreId) {
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public Genre() {
		this.genreId = 0;
		this.genreName = "";
	}

	public Genre(long genreId, String genreName) {
		this.genreId = genreId;
		this.genreName = genreName;
	}
}