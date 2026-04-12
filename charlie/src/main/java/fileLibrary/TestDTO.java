package fileLibrary;

public class TestDTO {

	String tableName;
	
	// select list 
	String search_content; // 검색어 내용 
	String orderBy; // set column Name
	String selector; // set select cmd
	
	// 아직 구상만 하는중
	String whereCul; // where 조건 컬럼
	String whereVal; // where 조건 벨류

	// paging

	// contents row 개수 설정
	int size = 10; // page당 보여줄 컬럼 개수
	int page = 1; // 해당 page 
	
	// section 당 page option
	int section = 5;
	
	// page number 
	int start;  // start page number
	int end;  // end page number
	
	
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	// select option
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
	
	
	// paging option
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getSection() {
		return section;
	}
	public void setSection(int section) {
		this.section = section;
	}
	
	
	
	@Override
	public String toString() {
		return "TestDTO [search_content=" + search_content + ", orderBy=" + orderBy + ", whereCul=" + whereCul
				+ ", whereVal=" + whereVal + "]";
	}
	
}
