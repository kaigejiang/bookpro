package cn.edu.nyist.jdbcuserman.dao.imp;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import cn.edu.nyist.jdbcuserman.dao.TypeDao;
import cn.edu.nyist.jdbcuserman.util.DsUtil;
import cn.edu.nyist.jdbcuserman.vo.TypeVo;

public class TypeDaoImp implements TypeDao {

	@Override
	public List<TypeVo> findAllTypes() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			connection = DsUtil.getConn();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select * from t_type");
			List<TypeVo> ls = new ArrayList<>();
			while(resultSet.next()) {
				TypeVo typeVo = new TypeVo();
				 typeVo.setId(resultSet.getInt("id"));
				 typeVo.setName(resultSet.getString("name"));
				 ls.add(typeVo);
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DsUtil.free(resultSet, statement, connection);
		}
		return null;
	}

}
