package UploadFile;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import IOLog.IOLogDTO;
import IOLog.IOLogservice;

@WebServlet("/file")
public class UploadFileControll extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sfile_num = request.getParameter("file_num");
		String mod = request.getParameter("mod");

		int file_num = -1;
		if (sfile_num != null) {
			file_num = Integer.parseInt(sfile_num);
		}
		UploadFileDTO fileDTO = new UploadFileDTO();
		fileDTO.setFile_num(file_num);
		fileDTO.setMod(mod);
		UploadFileService service = new UploadFileService();
		List<UploadFileDTO> list = service.select(fileDTO);
		System.out.println("file컨트롤마지막: "+list);
		request.setAttribute("file", list);
		if ("detail".equals(mod)) {
			System.out.println("디테일로고고씽");
			request.getRequestDispatcher("WEB-INF/views/file/fileDetail.jsp").forward(request, response);
			return;
		} else if ("up".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/file/fileUp.jsp").forward(request, response);
			return;
		} else if ("add".equals(mod)) {
			request.getRequestDispatcher("WEB-INF/views/file/fileAdd.jsp").forward(request, response);
			return;
		} else if ("delete".equals(mod)) {
			fileDelete(request, response);
			return;
		}
		System.out.println("리스트로 고고씽");
		request.getRequestDispatcher("WEB-INF/views/file/fileList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String mod = request.getParameter("mod");
		if ("add".equals(mod)) {
			fileAdd(request, response);
		} else if ("up".equals(mod)) {
			fileUP(request, response);
		} else if ("delete".equals(mod)) {
			fileDelete(request, response);
		}

	}

	protected void fileUP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sfile_num = request.getParameter("file_num");
		String url = request.getParameter("url");
		String srandom_code = request.getParameter("random_code");
		String mod = request.getParameter("mod");
		int file_num = Integer.parseInt(sfile_num);
		int random_code = Integer.parseInt(srandom_code);
		UploadFileDTO fileDTO = new UploadFileDTO();
		fileDTO.setFile_num(file_num);
		fileDTO.setUrl(url);
		fileDTO.setRandom_code(random_code);
		fileDTO.setMod(mod);
		UploadFileService service = new UploadFileService();
		service.fileService(fileDTO);
		response.sendRedirect("file");

	}

	protected void fileAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sfile_num = request.getParameter("file_num");
		String url = request.getParameter("url");
		String srandom_code = request.getParameter("random_code");
		String mod = request.getParameter("mod");
		int file_num = Integer.parseInt(sfile_num);
		int random_code = Integer.parseInt(srandom_code);
		UploadFileDTO fileDTO = new UploadFileDTO();
		fileDTO.setFile_num(file_num);
		fileDTO.setUrl(url);
		fileDTO.setRandom_code(random_code);
		fileDTO.setMod(mod);
		UploadFileService service = new UploadFileService();
		service.fileService(fileDTO);
		response.sendRedirect("file");
	}

	protected void fileDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8;");
		String sfile_num = request.getParameter("file_num");
		String mod = request.getParameter("mod");
		int file_num = Integer.parseInt(sfile_num);
		UploadFileDTO fileDTO = new UploadFileDTO();
		fileDTO.setFile_num(file_num);
		fileDTO.setMod(mod);
		UploadFileService service = new UploadFileService();
		System.out.println("fileAdd마지막: " + service.fileService(fileDTO));
		response.sendRedirect("file");
	}

}
