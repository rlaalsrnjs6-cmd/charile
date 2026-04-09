package fileLibrary;

import java.util.List;

public abstract class ParentService<T> {
	
	public abstract List selectDB(T dto);
	public abstract T insertDB(T dto);
	public abstract T modifyDB(T dto);
	public abstract int deleteDB(T dto);
}
