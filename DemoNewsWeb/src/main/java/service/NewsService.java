package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnection;
import model.News;

public class NewsService {

	public List<News> getAllNews() {
		List<News> list = new ArrayList<>();
		String sql = "SELECT * FROM News";
		try (Connection conn = DBConnection.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				list.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("content")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public News findById(int id) {
		String sql = "SELECT * FROM News WHERE id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return new News(rs.getInt("id"), rs.getString("title"), rs.getString("content"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void insertNews(String id, String title, String content) {
		String sql = "INSERT INTO News(id,title, content) VALUES(?, ?, ?)";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setString(1, id);
			ps.setString(2, title);
			ps.setString(3, content);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateNews(int id, String title, String content) {
		String sql = "UPDATE News SET title=?, content=? WHERE id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteNews(int id) {
		String sql = "DELETE FROM News WHERE id=?";
		try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
