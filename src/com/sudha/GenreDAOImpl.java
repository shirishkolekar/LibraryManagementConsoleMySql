package com.sudha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenreDAOImpl implements GenreDAO {
	static Connection con;
	static PreparedStatement ps;
	static PreparedStatement ps1;
	static ResultSet rs;

	@Override
	public ArrayList<Genre> getAllGenres() {
		ArrayList<Genre> genres = new ArrayList<Genre>();
		Genre g = null;
		try {
			con = DbConnection.getCon();
			ps = con.prepareStatement("select * from Genres");
			rs = ps.executeQuery();
			while (rs.next()) {
				g = new Genre();
				g.setGenreId(rs.getInt("genreId"));
				g.setGenreName(rs.getString("genreName"));
				genres.add(g);
			}
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return genres;
	}
}