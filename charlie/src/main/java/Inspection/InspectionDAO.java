public List<InspectionDTO> selectList(String name, String status, String date) {

    List<InspectionDTO> list = new ArrayList<>();

    String sql = "SELECT EMPNO, REGIST_TIME, PH_NUM, WASHED FROM PERSONAL_HYGIENE WHERE 1=1";
    
    try (Connection conn = ds.getConnection()) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT EMPNO, REGIST_TIME, PH_NUM, WASHED ");
        sql.append("FROM PERSONAL_HYGIENE WHERE 1=1 ");

        List<Object> params = new ArrayList<>();

        // 🔍 이름 검색
        if (name != null && !name.trim().isEmpty()) {
            sql.append("AND PH_NUM LIKE ? ");
            params.add("%" + name + "%");
        }

        // 🔍 상태 검색 (DB는 T/F 기준)
        if (status != null && !status.equals("전체")) {
            if (status.equals("완료")) {
                sql.append("AND WASHED = ? ");
                params.add("T");
            } else if (status.equals("미완료")) {
                sql.append("AND WASHED = ? ");
                params.add("F");
            }
        }

        // 🔍 날짜 검색 (시간 제거 핵심)
        if (date != null && !date.isEmpty()) {
            sql.append("AND TRUNC(REGIST_TIME) = TO_DATE(?, 'YYYY-MM-DD') ");
            params.add(date);
        }

        sql.append("ORDER BY EMPNO DESC");

        PreparedStatement ps = conn.prepareStatement(sql.toString());

        // 🔥 파라미터 순서 자동 처리
        for (int i = 0; i < params.size(); i++) {
            ps.setObject(i + 1, params.get(i));
        }

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