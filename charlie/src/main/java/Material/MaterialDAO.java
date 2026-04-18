package Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import fileLibrary.CommonDTO;
import fileLibrary.ParentDAO3;

public class MaterialDAO extends ParentDAO3<MaterialDTO, CommonDTO> {

	@Override
	protected String tableName() {
		return "material";
	}

	@Override
	protected String pk_Coulum_Name() {
		return "material_num";
	}
	@Override
	protected int setDTONum(MaterialDTO dto) {
		return dto.getMaterial_num();
	}


	@Override
	protected MaterialDTO setDTO(ResultSet rs) {
		MaterialDTO dto = new MaterialDTO();
		try {
			
			// material
			dto.setMaterial_num(rs.getInt("material_num"));
			//dto.setArea_quantity(rs.getInt("area_quantity"));
			dto.setWarehouse_num(rs.getInt("warehouse_num"));
			dto.setMdm_num(rs.getInt("mdm_num"));
			
			// mdm
			dto.setCode(rs.getString("code"));
			dto.setName(rs.getString("name"));
			dto.setUnit(rs.getString("unit"));
			
			dto.setTotal_price(rs.getLong("total_price")); // Alias와 매칭
			dto.setTotal_quantity(rs.getLong("total_quantity")); // Alias와 매칭

			// warehouse
			dto.setWh_section(rs.getString("wh_section"));
			dto.setFloor_level(rs.getString("floor_level"));
			
		} catch (SQLException e) {
			System.out.println("MaterialDAO setDTO 에러: " + e.getMessage());
		}
		return dto;
	}




	@Override
	protected String selectQuery(MaterialDTO dto, CommonDTO commonDTO) {
		// 고정
		String query =
		   "   SELECT * from (   "
		 + "   SELECT rownum as rnum, subqry.* from (   "
		 + ""
		 + " SELECT tableA.material_num, "
		 + " tableB.mdm_num, "
		 + " tableB.code, tableB.name, tableB.unit, "
		 + " SUM(tableB.quantity) AS total_quantity, "
		 + " SUM(tableB.price * tableB.quantity) AS total_price, "
		 + " tableC.warehouse_num, "
		 + " tableC.wh_section, tableC.floor_level "
		 + " FROM material tableA"
		 + ""
		 + " JOIN mdm tableB ON tableA.mdm_num = tableB.mdm_num "
		 + " JOIN warehouse tableC ON tableA.warehouse_num = tableC.warehouse_num ";

				    
		   // 고정
	    String where = commonDTO.getWhere();
	    if("".equals(where)) where = "";  

		String orderBy = commonDTO.getOrderBy();
		if(("".equals(commonDTO.getOrderBy()))) orderBy = pk_Coulum_Name();  
		   
		// 추가 조건 붙일 때
		query += where ;
		
		query += " GROUP BY "
				+ "tableA.material_num, "
				+ "tableB.mdm_num, "
				+ "tableB.code, tableB.name, tableB.unit, "
				+ "tableC.warehouse_num, "
				+ "tableC.wh_section, tableC.floor_level ";
		
		query += " order by " + orderBy + " ) subqry )"
		 	  + " WHERE rnum >= ? AND rnum <= ?";
	    return query;
	}
	
	@Override
	protected PreparedStatement selectPs(PreparedStatement ps, CommonDTO commonDTO) throws SQLException {
		ps.setInt(1, commonDTO.getStart());
		ps.setInt(2, commonDTO.getEnd());
		return ps;
	}
	
	
	
	@Override
	protected String selectAllQuery() {
	    return "SELECT mdm_num, code, name FROM mdm " +
	           "WHERE type IN ('assemble', 'product', 'material') AND canuse = 'Y'";
	}

	@Override
	protected MaterialDTO setJoinDTO(ResultSet rs) throws SQLException {
		
		MaterialDTO dto = new MaterialDTO();
		
		dto.setMdm_num(rs.getInt("mdm_num"));
		dto.setCode(rs.getString("code"));
		dto.setName(rs.getString("name"));
		
		return dto;
	}
		@Override // INSERT QUERY
	protected String insertQuery() {
		return "INSERT INTO material (material_num, warehouse_num, mdm_num) "
				+ " VALUES ( material_seq.nextval, ?, ?)" ; 
	}

	@Override
	protected String modifyQuery() {
		return "UPDATE " + tableName() + " SET "
			 + " warehouse_num = ?, "
			 + " mdm_num = ? "
			 + " WHERE " + pk_Coulum_Name() + " = ?";
	}

	protected PreparedStatement setPs(PreparedStatement ps, MaterialDTO dto, String selector) throws SQLException {
	    try {
	        ps.setInt(1, dto.getWarehouse_num());
	        ps.setInt(2, dto.getMdm_num());
	        if ("update".equals(selector)) {
	            ps.setInt(3, dto.getMaterial_num());
	        }
	    } catch (SQLException e) {
	        System.out.println("MaterialDAO setPs 에러 발생: " + e.getMessage());
	        throw e; 
	    }
	    return ps;
	}
	
	//////////민권쓰
	public List<MaterialDTO> selectall(MaterialDTO dto) {
        List<MaterialDTO> list = new ArrayList();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Context ctx = new InitialContext();

            DataSource dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
//            System.out.println("DAOMODselect:"+dto.getMod());
            conn = dataFactory.getConnection();
            String query ="SELECT * from material";


            ps = conn.prepareStatement(query);



            rs = ps.executeQuery();
            while(rs.next()) {
                MaterialDTO DTO = new MaterialDTO();
                int total_quantity = rs.getInt("total_quantity");
                int material_num = rs.getInt("material_num");
                int warehouse_num = rs.getInt("warehouse_num");
                int mdm_num = rs.getInt("mdm_num");

                DTO.setArea_quantity(total_quantity);
                DTO.setMaterial_num(material_num);
                DTO.setWarehouse_num(warehouse_num);
                DTO.setMdm_num(mdm_num);
                list.add(DTO);
            }
//            System.out.println("DAOlist:"+list);
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