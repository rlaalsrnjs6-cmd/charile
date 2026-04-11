package fileLibrary;

import java.util.List;
import java.util.Map;

public abstract class ParentService2<T, L> {
	
	public abstract Map selectDB(T dto, L listDTO);
	public abstract List selectAll();
	public abstract T insertDB(T dto);
	public abstract T modifyDB(T dto);
	public abstract int deleteDB(T dto);
}
