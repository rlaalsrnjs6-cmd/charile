package Lot;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lot")
public class LotControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String slot_num = request.getParameter("lot_num");
		String mod = request.getParameter("mod");
		
		int lot_num = -1;
		if (slot_num != null) {
			lot_num = Integer.parseInt(slot_num);
		}
		LotDTO lotDTO = new LotDTO();
		lotDTO.setLot_num(lot_num);
		lotDTO.setMod(mod);
		LotService service = new LotService();
		List<LotDTO> list = service.select(lotDTO);
		System.out.println("Lot컨트롤마지막: "+list);
		request.setAttribute("lot", list);
		PrintWriter out = response.getWriter();
		if(list.size()>0 && "fetch".equals(mod)) {
			System.out.println("패치트루출발");
	        out.print(true);
	        out.flush();
			out.close();
			return;
		} else if(list.size()==0 && "fetch".equals(mod)){
			System.out.println("패치펄스출발");
			out.print(false);
			out.flush();
			out.close();
			return;
		}
		
		if ("detail".equals(mod)) {
			System.out.println("디테일로고고씽");
			request.getRequestDispatcher("WEB-INF/views/lot/lotDetail.jsp").forward(request, response);
			return;
		} else if ("up".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/lot/lotUp.jsp").forward(request, response);
			return;
		} else if ("add".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/lot/lotAdd.jsp").forward(request, response);
			return;
		} else if ("delete".equals(mod)) {
			lotDelete(request, response);
			return;
		}
		System.out.println("리스트로 고고씽");
		request.getRequestDispatcher("WEB-INF/views/lot/lotList.jsp").forward(request, response);
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8;");
	String mod = request.getParameter("mod");
	if ("add".equals(mod)) {
		lotAdd(request, response);
	} else if ("up".equals(mod)) {
		lotUP(request, response);
	} else if ("delete".equals(mod)) {
		lotDelete(request, response);
	}

}

protected void lotUP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8;");
	String slot_num = request.getParameter("lot_num");
	String slot_count = request.getParameter("lot_count");
	String sorder_num = request.getParameter("order_num");
	String qc_chk = request.getParameter("qc_chk");
	String smaterial_num = request.getParameter("material_num");
	String smdm_num = request.getParameter("mdm_num");
	String mod = request.getParameter("mod");
	int lot_num = Integer.parseInt(slot_num);
	int lot_count = Integer.parseInt(slot_count);
	int order_num = Integer.parseInt(sorder_num);
	int material_num = Integer.parseInt(smaterial_num);
	int mdm_num = Integer.parseInt(smdm_num);
	LotDTO lotDTO = new LotDTO();
	lotDTO.setLot_num(lot_num);
	lotDTO.setLot_count(lot_count);
	lotDTO.setOrder_num(order_num);
	lotDTO.setQc_chk(qc_chk);
	lotDTO.setMaterial_num(material_num);
	lotDTO.setMdm_num(mdm_num);
	lotDTO.setMod(mod);
	LotService service = new LotService();
	service.lotService(lotDTO);
	response.sendRedirect("lot");

}

protected void lotAdd(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8;");

	String sdefective_num = request.getParameter("defective_num");
	String slot_num = request.getParameter("lot_num");
	String slot_count = request.getParameter("lot_count");
	String sorder_num = request.getParameter("order_num");
	String qc_chk = request.getParameter("qc_chk");
	String smaterial_num = request.getParameter("material_num");
	String smdm_num = request.getParameter("mdm_num");
	String mod = request.getParameter("mod");
	System.out.println("up:" + mod);
	int mdm_num = Integer.parseInt(smdm_num);
	int lot_num = Integer.parseInt(slot_num);
	int lot_count = Integer.parseInt(slot_count);
	int order_num = Integer.parseInt(sorder_num);
	int material_num = Integer.parseInt(smaterial_num);

	LotDTO lotDTO = new LotDTO();
	lotDTO.setLot_num(lot_num);
	lotDTO.setLot_count(lot_count);
	lotDTO.setOrder_num(order_num);
	lotDTO.setQc_chk(qc_chk);
	lotDTO.setMaterial_num(material_num);
	lotDTO.setMdm_num(mdm_num);
	lotDTO.setMod(mod);

	LotService service = new LotService();

	System.out.println("lotadd마지막: " + service.lotService(lotDTO));

	response.sendRedirect("lot");
}

protected void lotDelete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8;");
	String slot_num = request.getParameter("lot_num");
	String mod = request.getParameter("mod");
	int lot_num = Integer.parseInt(slot_num);
	LotDTO lotDTO = new LotDTO();
	lotDTO.setLot_num(lot_num);
	lotDTO.setMod(mod);
	LotService service = new LotService();
	System.out.println("lotAdd마지막: " + service.lotService(lotDTO));
	response.sendRedirect("lot");
}

}
