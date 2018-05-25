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
				bookVo.setTid(resultSet.getInt("tid"));
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
	public int findTotal(String name) {
		Connection connection =null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sql;
		try {
			connection = DsUtil.getConn();
			if (name==null) {
				sql = "select count(*) from t_book";
			}else {
				sql = "select count(*) from t_book where name like '%"+name+"%' or descri like '%\"+name+\"%' or author like '%\"+name+\"%'";
			}
			
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

	@Override
	public boolean del(int id) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DsUtil.getConn();
			String sql = "delete from t_book where id = "+id;
			statement = connection.prepareStatement(sql);
			int ret = statement.executeUpdate();
			if (ret>0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(statement, connection);
		}
		
		return false;
	}

	@Override
	public BookVo findId(int id) {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = DsUtil.getConn();
			statement = connection.createStatement();
			String sql = "select * from t_book where id="+id;
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				BookVo bookVo = new BookVo();
				bookVo.setAuthor(resultSet.getString("author"));
				bookVo.setDescri(resultSet.getString("descri"));
				bookVo.setTid(resultSet.getInt("tid"));
				bookVo.setName(resultSet.getString("name"));
				bookVo.setId(resultSet.getInt("id"));
				bookVo.setPrice(resultSet.getDouble("price"));
				bookVo.setPhoto(resultSet.getString("photo"));
				bookVo.setPubDate(resultSet.getDate("pubDate"));
				return bookVo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(resultSet, statement, connection);
		}
		return null;
	}

	@Override
	public int edit(BookVo bookVo) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DsUtil.getConn();
			if (bookVo.getPhoto()==null||bookVo.getPhoto().equals("")) {
		   		String sql =" update    t_book set  tid=?,name=?,descri=?,price=?,author=?,pubdate=? where id=?";
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setInt(1, bookVo.getTid());
				preparedStatement.setString(2, bookVo.getName());
				preparedStatement.setString(3, bookVo.getDescri());
				preparedStatement.setDouble(4, bookVo.getPrice());
				preparedStatement.setString(5, bookVo.getAuthor());
				// java.util.Date-->java.sql.Date
				preparedStatement.setDate(6, new java.sql.Date(bookVo.getPubDate().getTime()));
				preparedStatement.setInt(7,bookVo.getId());
				int ret = preparedStatement.executeUpdate();
				return ret;
			  }else {
					String sql =" update    t_book set  tid=?,name=?,descri=?,photo=?,price=?,author=?,pubdate=? where id=?";
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setInt(1, bookVo.getTid());
					preparedStatement.setString(2, bookVo.getName());
					preparedStatement.setString(3, bookVo.getDescri());
					preparedStatement.setString(4, bookVo.getPhoto());
					preparedStatement.setDouble(5, bookVo.getPrice());
					preparedStatement.setString(6, bookVo.getAuthor());
					// java.util.Date-->java.sql.Date
					preparedStatement.setDate(7, new java.sql.Date(bookVo.getPubDate().getTime()));
					preparedStatement.setInt(8,bookVo.getId());
					int ret = preparedStatement.executeUpdate();
					return ret;
			  }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(preparedStatement, connection);
		}
		return 0;
	}



}
