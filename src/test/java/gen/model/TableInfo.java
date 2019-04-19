package gen.model;

/**
 * @Description
 * @Author will
 * @Date 2019/04/11
 */
public class TableInfo {

  private String tableName;//表名

  private String modelName;//实体

  private SearchType searchType;//查询


  public String getTableName() {
    return tableName;
  }

  public TableInfo setTableName(String tableName) {
    this.tableName = tableName;
    return this;
  }

  public String getModelName() {
    return modelName;
  }

  public TableInfo setModelName(String modelName) {
    this.modelName = modelName;
    return this;
  }

  public SearchType getSearchType() {
    return searchType;
  }

  public TableInfo setSearchType(SearchType searchType) {
    this.searchType = searchType;
    return this;
  }


}
