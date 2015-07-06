package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import model.vo.User;

public class UserDao {

	private JdbcTemplate jdbcTemplate;

	public UserDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public User selectByEmail(String email) {
		List<User> results = jdbcTemplate.query(
				"select * from user where email = ?",
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User(rs.getString("email"),
								rs.getString("pw"),
								rs.getString("name"),
								rs.getString("phone"),
								rs.getTimestamp("create_time"),
								rs.getTimestamp("update_time"),
								rs.getString("account_num"),
								rs.getString("account_name"));
						user.setUserNo(rs.getLong("user_no"));
						user.setLevel(1);
						user.setStatus(1);
						return user;
					}
				},
				email);

		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(final User user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into user (email, pw, name, phone, level, create_time, update_time, account_num, account_name, status) "+
						"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
						new String[] {"user_no"});
				pstmt.setString(1,  user.getEmail());
				pstmt.setString(2,  user.getPassword());
				pstmt.setString(3,  user.getName());
				pstmt.setString(4,  user.getPhone());
				pstmt.setInt(5, 1);
				pstmt.setTimestamp(6,  new Timestamp(user.getCreateTime().getTime()));
				pstmt.setTimestamp(7,  new Timestamp(user.getUpdateTime().getTime()));
				pstmt.setString(8,  user.getAccountNum());
				pstmt.setString(9,  user.getAccountName());
				pstmt.setInt(10, 1);
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		user.setUserNo(keyValue.longValue());
		user.setLevel(1);
		user.setStatus(1);
	}

	public void update(User user) {
		jdbcTemplate.update("update user set name = ?, pw = ?, update_time = ? where email = ?",
				user.getName(), user.getPassword(), user.getUpdateTime(), user.getEmail());
	}

	public List<User> selectAll() {
		List<User> results = jdbcTemplate.query("select * from USER",
				new RowMapper<User>() {
					@Override
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User(rs.getString("email"),
								rs.getString("pw"),
								rs.getString("name"),
								rs.getString("phone"),
								rs.getTimestamp("create_time"),
								rs.getTimestamp("update_time"),
								rs.getString("account_num"),
								rs.getString("account_name"));
						user.setUserNo(rs.getLong("user_no"));
						user.setLevel(1);
						user.setStatus(1);
						return user;
					}
				});
		return results;
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
		return count;
	}

}
