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

    public List<InspectionDTO> selectList(String status, String date, int page) {

        List<InspectionDTO> list = new ArrayList<>();

        try (Connection conn = ds.getConnection()) {

            String sql = "SELECT * FROM PERSONAL_HYGIENE WHERE 1=1";

            if (status != null && !status.equals("all")) {
                sql += " AND WASHED = ?";
            }

            if (date != null && !date.isEmpty()) {
                sql += " AND TRUNC(REGIST_TIME) = TO_DATE(?, 'YYYY-MM-DD')";
            }

            sql += " ORDER BY EMPNO DESC OFFSET ? ROWS FETCH NEXT 10 ROWS ONLY";

            PreparedStatement ps = conn.prepareStatement(sql);

            int idx = 1;

            if (status != null && !status.equals("all")) {
                ps.setString(idx++, status);
            }

            if (date != null && !date.isEmpty()) {
                ps.setString(idx++, date);
            }

            ps.setInt(idx++, (page - 1) * 10);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                InspectionDTO dto = new InspectionDTO();

                dto.setEMPNO(rs.getInt("EMPNO"));
                dto.setREGIST_TIME(rs.getString("REGIST_TIME"));

                // 이름 고정
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