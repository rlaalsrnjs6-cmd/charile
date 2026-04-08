package Process;

public class ProcessDTO {
	int process_num;
	String process_content;
	int flow;
	String img_url;
	String code;
	
	public int getProcess_num() {
		return process_num;
	}
	public void setProcess_num(int process_num) {
		this.process_num = process_num;
	}
	public String getProcess_content() {
		return process_content;
	}
	public void setProcess_content(String process_content) {
		this.process_content = process_content;
	}
	public int getFlow() {
		return flow;
	}
	public void setFlow(int flow) {
		this.flow = flow;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "ProcessDTO [process_num=" + process_num + ", process_content=" + process_content + ", flow=" + flow
				+ ", img_url=" + img_url + ", code=" + code + "]";
	}
	
}
