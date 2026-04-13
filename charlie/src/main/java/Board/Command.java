package Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
	    // 요청을 처리하고 이동할 경로(path)를 반환하는 메서드
	    public String execute(HttpServletRequest request, HttpServletResponse response);
	}

