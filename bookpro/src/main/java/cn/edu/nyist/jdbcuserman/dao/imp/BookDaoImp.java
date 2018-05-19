package cn.edu.nyist.jdbcuserman.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nyist.jdbcuserman.dao.BookDao;
import cn.edu.nyist.jdbcuserman.util.DsUtil;
import cn.edu.nyist.jdbcuserman.vo.BookVo;
import cn.edu.nyist.jdbcuserman.vo.PageConstant;
import cn.edu.nyist.jdbcuserman.vo.TypeVo;

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
			preparedStatement.setDouble(6, bookVo.getPrice());
			preparedStatement.setString(7, bookVo.getAuthor());
			preparedStatement.setDate(5, new java.sql.Date(bookVo.getPubDate().getTime()));
			
			int ret = preparedStatement.executeUpdate();
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(preparedStatement, connection);
		}
		return 0;
	}

	@Override
	public List<BookVo> findAllBooks(int pageNumber) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = DsUtil.getConn();
			String sql = "select * from t_book limit "+(pageNumber-1)*PageConstant.PAGE_SIZE+","+PageConstant.PAGE_SIZE ;
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			List<BookVo> ls = new ArrayList<>();
			while(resultSet.next()) {
				BookVo bookVo = new BookVo();
				 bookVo.setId(resultSet.getInt("id"));
				 bookVo.setName(resultSet.getString("name"));
				 bookVo.setDescri(resultSet.getString("descri"));
				 bookVo.setPhoto(resultSet.getString("photo"));
				 bookVo.setPrice(resultSet.getDouble("price"));
				 bookVo.setAuthor(resultSet.getString("author"));
				 bookVo.setPubDate(resultSet.getDate("pubDate"));
				 ls.add(bookVo);
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(resultSet, preparedStatement, connection);
		}
		return null;
	}

	@Override
	public int findTotal() {
		Connection connection =null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DsUtil.getConn();
			String sql = "select count(*) from t_book";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DsUtil.free(resultSet, statement, connection);
		}
		
		return 0;
	}



}
