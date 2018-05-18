package cn.edu.nyist.jdbcuserman.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import cn.edu.nyist.jdbcuserman.dao.BookDao;
import cn.edu.nyist.jdbcuserman.util.DsUtil;
import cn.edu.nyist.jdbcuserman.vo.BookVo;

public  class BookDaoImp implements BookDao {

	public BookDaoImp() {
		
	}

	@Override
	public int save(BookVo bookVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DsUtil.getConn();
			String sql = "insert into t_book(tid,name,descri,photo,pubdate,price,author) values(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, bookVo.getTid());
			preparedStatement.setString(2, bookVo.getName());
			preparedStatement.setString(3, bookVo.getDescri());
			preparedStatement.setString(4, bookVo.getPhoto());
			preparedStatement.setDouble(5, bookVo.getPrice());
			preparedStatement.setString(6, bookVo.getAuthor());
			preparedStatement.setDate(7, new java.sql.Date(bookVo.getPubDate().getTime()));
			
			int ret = preparedStatement.executeUpdate();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(preparedStatement, connection);
		}
		return 0;
	}

}
