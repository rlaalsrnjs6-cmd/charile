package fileLibrary;

public class CommonDTO {

	String tableName;
	
	// select list 
	String orderBy; // set column Name
	String selector=""; // set select cmd
	String search;
	

	// paging

	// contents row 개수 설정
	int size = 10; // page당 보여줄 컬럼 개수
	int page = 1; // 해당 page 
	
	// section 당 page option
	int section = 5;
	
	// page number 
	int start;  // start page number
	int end;  // end page number
	
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	
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
	
	
	
	
}
