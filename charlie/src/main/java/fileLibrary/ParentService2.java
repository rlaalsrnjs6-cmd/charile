package fileLibrary;

import java.util.List;
import java.util.Map;

public abstract class ParentService2<T, C> {
	
	public abstract Map selectDB(T dto, C commonDTO);
	public abstract T selectOne(T dto, C commonDTO);
	public abstract T insertDB(T dto);
	public abstract T modifyDB(T dto);
	public abstract int deleteDB(T dto);
}
