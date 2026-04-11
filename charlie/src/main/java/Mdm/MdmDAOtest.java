package Mdm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fileLibrary.ParentDAO2;
import fileLibrary.TestDTO;

public class MdmDAOtest extends ParentDAO2<MdmDTO, TestDTO> {
	
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
	protected String selectQuery(MdmDTO dto, TestDTO testDTO) {
		
		String query = " select * from ( "
					 + "	 select rownum as rnum, subqry.* from ( "
					 + "	 select " + tableName() + ".* from " + tableName() ;
		
		String where = "";
		
		String orderBy = "" + dto.getMdm_num();
		if ( testDTO.getOrderBy() != null ) orderBy = testDTO.getOrderBy();
		
		if ( dto != null ) {
		
			switch(testDTO.getSelector()) {
		
				// 단일 선택
				case "num": where = " where " + pk_Coulum_Name() + " = '" + dto.getMdm_num() + "'"; break;
				// 전체 검색
				case "search1": where = " where code = " 
										+ "'" + dto.getSearch() + "'"
										+ " or name = '" + dto.getSearch() + "'" 
										+ " or unit = '" + dto.getSearch() + "'"
				/* 컬럼별 검색 */			+ " or type = '" + dto.getSearch() + "'"; break;	
				case "search2" : where = " where code = '" +  dto.getSearch() + "'"; break;
				case "search3" : where = " where name = '" +  dto.getSearch() + "'"; break;
				case "search4" : where = " where unit = '" +  dto.getSearch() + "'"; break;
				case "search5" : where = " where type = '" +  dto.getSearch() + "'"; break;
				default : break;
			}
		}
		// order by 조건 함수로
		query += where;
		query += " order by ? ) subqry )";
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
			dto.setPrice(rs.getInt("price"));
			
		return dto;
	}

	@Override // 고정
	protected PreparedStatement selectPs(PreparedStatement ps, MdmDTO dto, TestDTO testDTO) throws SQLException {
		String orderBy = pk_Coulum_Name();
		if ( testDTO.getOrderBy() != null ) orderBy = testDTO.getOrderBy();
		
		ps.setString(1, orderBy);
		ps.setInt(2, testDTO.getStart());
		ps.setInt(3, testDTO.getEnd());
		return ps;
	}

	




}
