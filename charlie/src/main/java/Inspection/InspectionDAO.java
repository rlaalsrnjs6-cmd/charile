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

    public List<InspectionDTO> getAll() {

        List<InspectionDTO> list = new ArrayList<>();

        try (Connection conn = ds.getConnection()) {

            String sql = "SELECT * FROM PERSONAL_HYGIENE ORDER BY EMPNO DESC";

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                InspectionDTO dto = new InspectionDTO();

                dto.setEmpno(rs.getInt("EMPNO"));
                dto.setRegist_time(rs.getString("REGIST_TIME"));

                // 이름은 항상 Inspection
                dto.setPh_num("Inspection");

                dto.setWashed(rs.getString("WASHED"));

                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}