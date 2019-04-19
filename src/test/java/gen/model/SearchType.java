package gen.model;

/**
 * @Description
 * @Author will
 * @Date 2019/04/12
 */
public enum SearchType {
  NONO(0),//没有查询条件
  EXPLICIT(1),//精确匹配查询
  IMPLICIT(2),//精确模糊查询
  CUSTOM(3);//包含自定义sql串

  SearchType(int value) {
    this.value = value;
  }

  private int value;

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
