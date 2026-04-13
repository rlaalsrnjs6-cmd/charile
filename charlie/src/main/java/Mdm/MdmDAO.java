package Mdm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fileLibrary.CommonDTO;
import fileLibrary.ParentDAO2;

public class MdmDAO extends ParentDAO2<MdmDTO, CommonDTO> {
	
	// set Table 정보 
	@Override
	protected String tableName() {
		return "mdm";
	}

	@Override
	protected String pk_Coulum_Name() {
		return "mdm_num";
	}

	@Override
	protected int setDTONum(MdmDTO dto) {
		return dto.getMdm_num();
	}
	
	
	@Override
	protected String selectQuery(MdmDTO dto, CommonDTO commonDTO) {
		
		// SET QUERY
		String query = " select * from ( "
					 + "	 select rownum as rnum, subqry.* from ( "
					 + "	 select " + tableName() + ".* from " + tableName() ;
		
		// SET WHERE
		String where = "";
		// SET ORDERBY
		String orderBy = pk_Coulum_Name();
		if ( commonDTO.getOrderBy() != null ) orderBy = commonDTO.getOrderBy();
		orderBy += ", received_date" ;
		
		// 가변 조건
		if ( dto != null ) {
		
			if(commonDTO.getSearch() != "") {
				switch(commonDTO.getSelector()) {
				
				// 전체검색
				case "search_all": where = " where code = " 
										+ "'" + commonDTO.getSearch() + "'"
										+ " or name = '" + commonDTO.getSearch() + "'" 
										+ " or unit = '" + commonDTO.getSearch() + "'"
				/* 컬럼별 검색 */			+ " or type = '" + commonDTO.getSearch() + "'"; break;	
				case "code" : where = " where code = '" +  commonDTO.getSearch() + "'"; break;
				case "name" : where = " where name = '" +  commonDTO.getSearch() + "'"; break;
				case "unit" : where = " where unit = '" +  commonDTO.getSearch() + "'"; break;
				case "type" : where = " where type = '" +  commonDTO.getSearch() + "'"; break;
				default : break;
				}
			}
		}
		// 추가 내용
		query += where;
		query += " order by "+ orderBy +" ) subqry )";
		query += " WHERE rnum >= ? AND rnum <= ?" ;
		return query;
		
	}
	

	@Override
	protected String insertQuery() {
		return "INSERT INTO mdm ( mdm_num, code, name, unit, type, price) " 
				+ " VALUES ( mdm_seq.nextval, ?, ?, ?, ?, ?)";
	}

	@Override
	protected PreparedStatement setPs(PreparedStatement ps, MdmDTO dto, String selector) throws SQLException {

			ps.setString(1, dto.getCode());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getUnit());
			ps.setString(4, dto.getType());
			ps.setInt(5, dto.getPrice());
			if ("update".equals(selector)) { ps.setInt(6, dto.getMdm_num()); }

		return ps;
	}

	@Override
	protected String modifyQuery() {
		return
				"UPDATE mdm SET "
				+ "	code = ?, "
				+ "	name = ?, "
				+ "	unit = ?, "
				+ "	type = ?, "
				+ " price = ? "
				+ " where mdm_num = ? "
			;
	}
	
	@Override
	protected MdmDTO setDTO(ResultSet rs) throws SQLException{
		
		MdmDTO dto = new MdmDTO();

			dto.setMdm_num(rs.getInt("mdm_num"));
			dto.setCode(rs.getString("code"));
			dto.setName(rs.getString("name"));
			dto.setUnit(rs.getString("unit"));
			dto.setType(rs.getString("type"));
			dto.setReceived_date(rs.getDate("received_date"));
			dto.setPrice(rs.getInt("price"));
			
		return dto;
	}

	@Override // 고정 사용 CONST
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}


}
