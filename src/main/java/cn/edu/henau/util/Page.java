package cn.edu.henau.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page<T> implements Serializable {  
	  
    /** 
     *  
     */  
    private static final long serialVersionUID = 8338235914409958290L;
  
    /** 
     *  
     */  
    public static final int MAX_LIMIT = 1000;  
  
    private int pageIndex = 1;
  
    private long pageCount = 1;
  
    private int countPerPage = 10;
  
    /** 
     *  
     */  
    private int limit = 20;
  
    /** 
     *  
     */  
    private int start = 0;
  
    /** 
     *  
     */  
    private long results = 0;
  
    /** 
     *  
     */  
    private List<T> rows = new ArrayList<T>();
  
    /** 
     *  
     */  
    private T condition;
  
    /** 
     *  
     */  
    private int size = 0;
  
    private Long codeTableId;
  
    public Page() {  
        super();  
    }  
  
    public int getPageIndex() {  
        return pageIndex;  
    }  
  
    public void setPageIndex(int pageIndex) {  
        this.pageIndex = pageIndex;  
    }  
  
    public long getPageCount() {  
        return this.pageCount;  
    }  
  
    public void setPageCount(long pageCount) {  
        this.pageCount = pageCount;  
    }  
  
    public int getCountPerPage() {  
        return countPerPage;  
    }  
  
    public void setCountPerPage(int countPerPage) {  
        this.countPerPage = countPerPage;  
    }  
  
    public int getLimit() {  
        return limit;  
    }  
  
    public void setLimit(int limit) {  
        if (limit > Page.MAX_LIMIT) {  
            throw new RuntimeException("max records");  
        }  
        this.limit = limit;  
    }  
  
    public int getStart() {  
        return start;  
    }  
  
    public void setStart(int start) {  
        this.start = start;  
    }  
  
    public long getResults() {  
        return results;  
    }  
  
    public void setResults(long results) {  
        this.results = results;  
    }  
  
    public List<T> getRows() {  
        return rows;  
    }  
  
    public void setRows(List<T> rows) {  
        this.size = rows != null ? rows.size() : 0;  
        this.rows = rows;  
    }  
  
    public T getCondition() {  
        return condition;  
    }  
  
    public void setCondition(T condition) {  
        this.condition = condition;  
    }  
  
    public int getSize() {  
        return size;  
    }  
  
    public void setSize(int size) {  
        this.size = size;  
    }  
  
    public Long getCodeTableId() {  
        return codeTableId;  
    }  
  
    public void setCodeTableId(Long codeTableId) {  
        this.codeTableId = codeTableId;  
    }  
  
    /** 
     * @return 
     */  
    public Map<String, Object> getPageJsonMap(Page<T> dataList) {  
        Map<String, Object> jsonMap = new HashMap<String, Object>();  
        if (null != dataList) {  
            jsonMap.put("total", dataList.getResults() >= 0 ? dataList.getResults() : 0);// total  
  
            if (dataList.getRows() != null) {  
                jsonMap.put("rows", dataList.getRows().size() > 0 ? dataList.getRows() : "");// rows  
            }  
        }  
        return jsonMap;  
    }  
  
    /** 
     * @return 
     */  
    public void setPageRow(int page, int rows) {  
        page = page == 0 ? 1 : page;  
        rows = rows == 0 ? 10 : rows;  
        int start = rows * (page - 1);  
        this.setStart(start);  
        this.setLimit(rows);  
    }  
  
}  
  