package dao;

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

import vo.Member;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select * from user where email = ?",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Member member = new Member(rs.getString("email"),
								rs.getString("pw"),
								rs.getString("name"),
								rs.getString("phone"),
								rs.getTimestamp("create_time"),
								rs.getTimestamp("update_time"),
								rs.getString("account_num"),
								rs.getString("account_name"));
						member.setUserNo(rs.getLong("user_no"));
						member.setLevel(1);
						member.setStatus(1);
						return member;
					}
				},
				email);

		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(final Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) 
					throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(
						"insert into user (email, pw, name, phone, level, create_time, update_time, account_num, account_name, status) "+
						"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
						new String[] {"user_no"});
				pstmt.setString(1,  member.getEmail());
				pstmt.setString(2,  member.getPassword());
				pstmt.setString(3,  member.getName());
				pstmt.setString(4,  member.getPhone());
				pstmt.setInt(5, 1);
				pstmt.setTimestamp(6,  new Timestamp(member.getCreateTime().getTime()));
				pstmt.setTimestamp(7,  new Timestamp(member.getUpdateTime().getTime()));
				pstmt.setString(8,  member.getAccountNum());
				pstmt.setString(9,  member.getAccountName());
				pstmt.setInt(10, 1);
				return pstmt;
			}
		}, keyHolder);
		Number keyValue = keyHolder.getKey();
		member.setUserNo(keyValue.longValue());
		member.setLevel(1);
		member.setStatus(1);
	}

	public void update(Member member) {
		jdbcTemplate.update("update user set name = ?, pw = ?, update_time = ? where email = ?",
				member.getName(), member.getPassword(), member.getUpdateTime(), member.getEmail());
	}

	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query("select * from USER",
				new RowMapper<Member>() {
					@Override
					public Member mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Member member = new Member(rs.getString("email"),
								rs.getString("pw"),
								rs.getString("name"),
								rs.getString("phone"),
								rs.getTimestamp("create_time"),
								rs.getTimestamp("update_time"),
								rs.getString("account_num"),
								rs.getString("account_name"));
						member.setUserNo(rs.getLong("user_no"));
						member.setLevel(1);
						member.setStatus(1);
						return member;
					}
				});
		return results;
	}

	public int count() {
		Integer count = jdbcTemplate.queryForObject("select count(*) from user", Integer.class);
		return count;
	}

}
