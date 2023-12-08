package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

//강의에는 없던 부분
//해당 라인 빼면 run 안됨
//해당 부분 넣고 실행하니 경고 뜸
import org.springframework.jdbc.core.RowMapper;

	@Repository	//bean으로 자동 등록
	public class BoardDAO {

		@Autowired	//rootContext에서 만든거 자동 적용
		JdbcTemplate jdbcTemplate;

		//설명 영상에서는 이 부분 들어가 있었음
//		public void setJdbcTemplate(JdbcTemplate template) {
//			this.template = template;
//		}

		public int insertBoard(BoardVO vo) {
			String sql = "insert into BOARD (title, writer, content, category) values ("
					+ "" + vo.getTitle() + "',"
					+ "" + vo.getWriter() + "',"
					+ "" + vo.getContent() + "',"
					+ "" + vo.getCategory() + "')";
			return jdbcTemplate.update(sql);
		}

		// 글 삭제
		public int deleteBoard(int seq) {
			String sql = "delete from BOARD where seq = " + seq;
			return jdbcTemplate.update(sql);
		}

		public int updateBoard(BoardVO vo) {
			String sql = "update BOARD set title = '" + vo.getTitle() + "',"
					+ " writer = '" + vo.getWriter() + "',"
					+ " content = '" + vo.getContent() + "',"
					+ " category = '" + vo.getCategory() + "' where seq = " + vo.getSeq();

			return jdbcTemplate.update(sql);
		}
//		public int updateBoard(BoardVO vo) {
//			String sql = "update BOARD set"
//					+ "title = '" + vo.getTitle() + "',"
//					+ " writer = '" + vo.getWriter() + "',"
//					+ " content = '" + vo.getContent() + "',"
//					+ " category = '" + vo.getCategory() + "' where seq = " + vo.getSeq();
//
//			return jdbcTemplate.update(sq l);
//		}

		class BoardRowMapper implements RowMapper<BoardVO> {	//interface 상속 받음
			@Override
			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardVO vo = new BoardVO();
				vo.setSeq(rs.getInt("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setWriter(rs.getString("writer"));
				vo.setCategory(rs.getString("category"));
				vo.setRegdate(rs.getDate("regdate"));
				return vo;
			}
		}

		public BoardVO getBoard(int seq) {
			String sql = "select * from BOARD where seq = " + seq;
			return jdbcTemplate.queryForObject(sql, new BoardRowMapper());
		}

		public List<BoardVO> getBoardList() {
			String sql = "select * from BOARD order by regdate desc";
			return jdbcTemplate.query(sql, new BoardRowMapper());
		}
	}

	//	이전 내용
//@Repository
//public class BoardDAO {
//	Connection conn = null;
//	PreparedStatement stmt = null;
//	ResultSet rs = null;
//
//	//crud에 쓰이는 quary
////	private final String BOARD_INSERT = "insert into BOARD (category, title, writer, content) values (?,?,?,?)";
//	private final String BOARD_INSERT = "insert into BOARD (category, title, writer, content) values (?,?,?,?)";
//	private final String BOARD_UPDATE = "update BOARD set category=?, title=?, writer=?, content=? where seq=?";
//	private final String BOARD_DELETE = "delete from BOARD  where seq=?";
//	private final String BOARD_GET = "select * from BOARD  where seq=?";
//	private final String BOARD_LIST = "select * from BOARD order by seq desc";
//
//
//	public int insertBoard(BoardVO vo) {
//		System.out.println("===> JDBC로 insertBoard() 기능");
//		try {
//			conn = JDBCUtil.getConnection();
//			stmt = conn.prepareStatement(BOARD_INSERT);
//			stmt.setString(1, vo.getTitle());
//			stmt.setString(2, vo.getWriter());
//			stmt.setString(3, vo.getContent());
//			stmt.setString(4, vo.getCategory());
//			stmt.executeUpdate();
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//
//	@Repository
//	public class BoardDAO {
//
//		@Autowired
//		JdbcTemplate jdbcTemplate;
//
//		public int insertBoard(BoardVO vo) {
//			String sql = "insert into BOARD (title, writer, content, category) values ("
//					+ "" + vo.getTitle() + "',"
//					+ "" + vo.getWriter() + "',"
//					+ "" + vo.getContent() + "',"
//					+ "" + vo.getCategory() + "')";
//			return jdbcTemplate.update(sql);
//		}
//
//		// 글 삭제
//		public int deleteBoard(int seq) {
//			String sql = "delete from BOARD where seq = " + seq;
//			return jdbcTemplate.update(sql);
//		}
//
//		public int updateBoard(BoardVO vo) {
//			String sql = "update BOARD set title = '" + vo.getTitle() + "',"
//					+ " writer = '" + vo.getWriter() + "',"
//					+ " content = '" + vo.getContent() + "',"
//					+ " category = '" + vo.getCategory() + "' where seq = " + vo.getSeq();
//
//			return jdbcTemplate.update(sql);
//		}
//
//		class BoardRowMapper implements RowMapper<BoardVO> {
//			@Override
//			public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//				BoardVO vo = new BoardVO();
//				vo.setSeq(rs.getInt("seq"));
//				vo.setTitle(rs.getString("title"));
//				vo.setContent(rs.getString("content"));
//				vo.setWriter(rs.getString("writer"));
//				vo.setCategory(rs.getString("category"));
//				vo.setRegdate(rs.getDate("regdate"));
//				return vo;
//			}
//		}
//
//		public BoardVO getBoard(int seq) {
//			String sql = "select * from BOARD where seq = " + seq;
//			return jdbcTemplate.queryForObject(sql, new BoardRowMapper());
//		}
//
//		public List<BoardVO> getBoardList() {
//			String sql = "select * from BOARD order by regdate desc";
//			return jdbcTemplate.query(sql, new BoardRowMapper());
//		}
//	}
//}