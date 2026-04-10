package ProductionManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PMDetailDAO {
	private DataSource dataFactory; // 캡슐화를 위해 private

	PMDetailDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 상세페이지에서 필요한 데이터 리턴
	public List selectPM(int prodNum) {

//		String query = "select * from production_management"
//				+ " where prod_num=?";

		// 상세페이지에서 필요한 정보
		String query = "SELECT \r\n" + "    P.*, " + "    M.NAME AS MDM_NAME," + "    (SELECT NVL(SUM(L.lot_count), 0) "
				+ "     FROM work_order W \r\n" + "     JOIN lot L ON W.order_num = L.order_num "
				+ "     WHERE W.prod_num = P.prod_num) AS current_total,"
				+ "    (P.target_quantity - (SELECT NVL(SUM(L.lot_count), 0) "
				+ "                          FROM work_order W \r\n"
				+ "                          JOIN lot L ON W.order_num = L.order_num "
				+ "                          WHERE W.prod_num = P.prod_num)) AS remain_qty"
				+ " FROM production_management P" + " JOIN mdm M ON P.mdm_num = M.mdm_num" + " WHERE P.prod_num = ? ";
		List list = new ArrayList();
		try (Connection conn = dataFactory.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, prodNum);

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					ProductionManagementDTO dto = new ProductionManagementDTO();
					dto.setProd_num(rs.getInt("prod_num"));
					dto.setTarget_quantity(rs.getInt("target_quantity"));
					dto.setWork_start(rs.getDate("work_start"));
					dto.setWork_end(rs.getDate("work_end"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setMdmNum(rs.getInt("mdm_num"));
					dto.setMdmName(rs.getString("mdm_name"));
					dto.setCurrentCount(rs.getInt("current_total"));
					dto.setRemainCount(rs.getInt("remain_qty"));

					list.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}// selectPM() 닫음

	
	public int updatePM(ProductionManagementDTO dto) {
		
		String query = "update production_management set title=?, target_quantity=?, work_start=?, work_end=?"
				+ "  where prod_num = ?";
		
		try(Connection conn = dataFactory.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
						ps.setString(1, dto.getTitle());
						ps.setInt(2, dto.getTarget_quantity());
						ps.setDate(3, dto.getWork_start());
						ps.setDate(4, dto.getWork_end());
						ps.setInt(5, dto.getProd_num());

						
						int result = ps.executeUpdate();
						return result  ;
				}catch(Exception e) {
					e.printStackTrace();
				}
		System.out.println("디테일DAO updatePM 오류남");
			return 0;
		}//updatePM()닫음
	
	
}/////////////////////////////// 클래스 닫음
