package WorkOrder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import Emp.EmpDTO;

public class WorkOrderDAO {
	public List<WorkOrderDTO> select(WorkOrderDTO dto) {
		List<WorkOrderDTO> list = new ArrayList();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			String query = "select w.order_num, w.work_order_title, w.WORK_DATE, e.ename, w.status ";
					query += "from work_order w left outer join emp e ";
					query += "on (w.empno = e.empno)";

			ps = conn.prepareStatement(query);

//				ps.setInt(1, dto.getOrder_num());

			rs = ps.executeQuery();

			while (rs.next()) {
				WorkOrderDTO DTO = new WorkOrderDTO();
				int order_num = rs.getInt("order_num");
				Date work_date = rs.getDate("work_date");
				String work_order_title = rs.getString("work_order_title");
				String ename = rs.getString("ename");
				String status = rs.getString("status");

				DTO.setOrder_num(order_num);
				DTO.setWork_date(work_date);
				DTO.setWork_order_title(work_order_title);
				DTO.setEname(ename);
				DTO.setStatus(status);
				list.add(DTO);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
