import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class InspectionDAO {

    private DataSource ds;

    public InspectionDAO() {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/charlie");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<InspectionDTO> selectList(String name, String status, String date) {

        List<InspectionDTO> list = new ArrayList<>();

        try (Connection conn = ds.getConnection()) {

            StringBuilder sql = new StringBuilder();
            sql.append("SELECT EMPNO, REGIST_TIME, PH_NUM, WASHED ");
            sql.append("FROM PERSONAL_HYGIENE WHERE 1=1 ");

            // 이름 검색
            if (name != null && !name.trim().isEmpty()) {
                sql.append("AND PH_NUM LIKE ? ");
            }

            // 상태 검색
            if (status != null && !status.equals("전체")) {
                if (status.equals("완료")) {
                    sql.append("AND WASHED = 'T' ");
                } else if (status.equals("미완료")) {
                    sql.append("AND WASHED = 'F' ");
                }
            }

            // 날짜 검색
            if (date != null && !date.isEmpty()) {
                sql.append("AND TRUNC(REGIST_TIME) = TO_DATE(?, 'YYYY-MM-DD') ");
            }

            sql.append("ORDER BY EMPNO DESC");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int idx = 1;

            if (name != null && !name.trim().isEmpty()) {
                ps.setString(idx++, "%" + name + "%");
            }

            if (date != null && !date.isEmpty()) {
                ps.setString(idx++, date);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                InspectionDTO dto = new InspectionDTO();

                dto.setEMPNO(rs.getInt("EMPNO"));
                dto.setREGIST_TIME(rs.getString("REGIST_TIME"));

                // 이름 강제 통일
                dto.setPH_NUM("Inspection");

                dto.setWASHED(rs.getString("WASHED"));

                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}