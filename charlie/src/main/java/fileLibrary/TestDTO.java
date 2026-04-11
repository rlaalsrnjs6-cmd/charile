package fileLibrary;

public class TestDTO {
	
	String search_content; // 검색어 내용
	String orderBy; // 기준 정렬
	String selector; // select cmd
	String whereCul; // where 조건 컬럼
	String whereVal; // where 조건 벨류
	
	public String getSelector() {
		return selector;
	}
	public void setSelector(String selector) {
		this.selector = selector;
	}
	public String getWhereCul() {
		return whereCul;
	}
	public void setWhereCul(String whereCul) {
		this.whereCul = whereCul;
	}
	public String getWhereVal() {
		return whereVal;
	}
	public void setWhereVal(String whereVal) {
		this.whereVal = whereVal;
	}
	public String getSearch_content() {
		return search_content;
	}
	public void setSearch_content(String search_content) {
		this.search_content = search_content;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	
	@Override
	public String toString() {
		return "TestDTO [search_content=" + search_content + ", orderBy=" + orderBy + ", whereCul=" + whereCul
				+ ", whereVal=" + whereVal + "]";
	}
	
}
