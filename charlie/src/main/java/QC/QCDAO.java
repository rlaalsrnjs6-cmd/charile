package QC;

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

import Lot.LotDTO;
import fileLibrary.CommonDTO;

public class QCDAO   {
	public List<QCDTO> select(QCDTO dto, CommonDTO pageing) {
		List<QCDTO> list = new ArrayList();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context ctx = new InitialContext();

			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query =
					"SELECT * from ( "
					+ "SELECT rownum as rnum, subqry.* from ( "
					+ "select qc_num, q.lot_num, qc_date, q.empno, e.ename, "
					+ "l.qc_chk, l.lot_count "
					+ "from qc q "
					+ "left outer join lot l "
					+ "on (q.lot_num = l.lot_num) "
					+ "left outer join emp e "
					+ "on (q.empno = e.empno) ";
					
			
			System.out.println("QCDAOmod: "+dto.getMod());
			System.out.println("QCDAOqc_num: "+dto.getQc_num());
			if(dto.getQc_num()!=-1 || "up".equals(dto.getMod())) {
				query += "where qc_num = ?";
			}
			//�쐞�뿉 �썾�뼱媛� 諛묒쑝濡� �삤硫� �븞�릺�꽌
			query += "ORDER BY q.qc_num asc, q.qc_date asc "
					+ ") subqry) "
					+ "WHERE rnum >= ? AND rnum <= ?";
			
			ps = conn.prepareStatement(query);
			int idx = 1;
			if(dto.getQc_num()!=-1 || "up".equals(dto.getMod())) {
				ps.setInt(idx++,dto.getQc_num());
			}
			
			ps.setInt(idx++, pageing.getStart());
		       ps.setInt(idx++, pageing.getEnd());
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				QCDTO DTO = new QCDTO();
				int qc_num = rs.getInt("qc_num");
				int lot_num = rs.getInt("lot_num");
				Date qc_date = rs.getDate("qc_date");
				int empno = rs.getInt("empno");
				int lot_count = rs.getInt("lot_count");
				String qc_chk = rs.getString("qc_chk");
				String ename = rs.getString("ename");
				
				DTO.setQc_num(qc_num);
				DTO.setLot_num(lot_num);
				DTO.setQc_date(qc_date);
				DTO.setEmpno(empno);
				DTO.setQc_chk(qc_chk);
				DTO.setLot_count(lot_count);
				DTO.setEname(ename);
				
				list.add(DTO);
			}
//			System.out.println("DAOlist:"+list);
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
	
	public List<QCDTO> selectall(QCDTO dto) {
		List<QCDTO> list = new ArrayList();
		System.out.println("올실행");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
			conn = dataFactory.getConnection();
			String query = "SELECT * from qc ";
			if(dto.getQc_num()!=-1) {
				query += "where qc_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if(dto.getQc_num()!=-1) {
				ps.setInt(1,dto.getQc_num());
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				QCDTO DTO = new QCDTO();
				int qc_num = rs.getInt("qc_num");
				Date qc_date = rs.getDate("qc_date");
				int empno = rs.getInt("empno");
				int lot_num = rs.getInt("lot_num");
				
				DTO.setQc_num(qc_num);
				DTO.setQc_date(qc_date);
				DTO.setEmpno(empno);
				DTO.setLot_num(lot_num);
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
	
public int qcDAO(QCDTO dto) {
		
		int result = -1;
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context ctx = new InitialContext();
			
			DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//			System.out.println("DAOMODselect:"+dto.getMod());
			conn = dataFactory.getConnection();
			
			String query = "";
			System.out.println("QCDAOmod:"+dto.getMod());
			// �뾽�뜲�씠�듃
			if("up".equals(dto.getMod())) {
				  query = "UPDATE qc "
						+ "SET qc_num = ?, "
						+ "empno = ?, "
						+ "lot_num = ?, "
						+ "qc_date = SYSDATE "
						+ "where qc_num = ?";
			}
			// �씤�꽌�듃
			if("add".equals(dto.getMod())) {
				 query = "INSERT INTO qc "//�븘吏곸븞留뚮벉
					   + "(qc_num, lot_num, qc_date, empno) "
					   + "VALUES ((SELECT NVL(MAX(qc_num), 0) + 1 FROM qc), ?, ?, ?)";
			}
			// �뵜由ы듃
			if("delete".equals(dto.getMod())) { //留뚮뱶�뒗以�
				query = "DELETE FROM qc "
					  + "WHERE qc_num = ?";
			}
			ps = conn.prepareStatement(query);
			
			if("up".equals(dto.getMod())) {
				System.out.println("upps");
				ps.setInt(1, dto.getQc_num());
				ps.setInt(2, dto.getEmpno());
				ps.setInt(3, dto.getLot_num());
				ps.setInt(4, dto.getQc_num());
				
			}
			
			if("add".equals(dto.getMod())) {
				System.out.println("addps");
				ps.setInt(1, dto.getLot_num());
				ps.setDate(2, dto.getQc_date());
				ps.setInt(3, dto.getEmpno());
				
			}
			
			if("delete".equals(dto.getMod())) {
				System.out.println("deleteps");
				ps.setInt(1, dto.getQc_num());
			}
			result = ps.executeUpdate();
			System.out.println("qc由ъ넄�듃 �쟾");
			
			System.out.println("qcDAO由ъ넄�듃:"+result);
			
			
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
		return result;
	}

//Use paging �닔�젙
public int getTotalCount() {

    int total = 0;

    try {
        //�옄�썝�쓣 媛�吏��윭 媛�湲� �쐞�빐 臾몄쓣 �뿴怨�
        Context ctx = new InitialContext();
        //�뿴�뼱�몦 臾몄쓣 �넻�빐 �뼱�뵒濡� 媛덉� 寃쎈줈瑜� �젙�븿
        DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");

        String query ="select count(*) from qc"; 

        try(Connection conn = dataFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
                ResultSet rs = ps.executeQuery()){

            if(rs.next()) { // count 1以� return
                total = rs.getInt(1);
            }
        }
    }catch (Exception e) {
        e.printStackTrace();
    }
    return total;
}

}
