package ml.lijiabei.entity;

public class CodeColumn {
	private String column_name;// 列名
	private String column_type;// 列属性
	private String column_comments;// 列备注
	private String is_primary;// 1是主键，0非主键
	private String data_name;// 转化成java对应的列名
	private String data_type;// 转化成java对应的类型

	public CodeColumn() {
	}

	public CodeColumn(CodeColumn cp) {
		this.column_name = cp.getColumn_name();
		this.column_type = cp.getColumn_type();
		this.column_comments = cp.getColumn_comments();
		this.is_primary = cp.getIs_primary();
		this.data_type = cp.getData_type();
	}

	public String getColumn_name() {
		return column_name;
	}

	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}

	public String getColumn_type() {
		return column_type;
	}

	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}

	public String getColumn_comments() {
		return column_comments;
	}

	public void setColumn_comments(String column_comments) {
		this.column_comments = column_comments;
	}

	public String getIs_primary() {
		return is_primary;
	}

	public void setIs_primary(String is_primary) {
		this.is_primary = is_primary;
	}

	public String getData_name() {
		return data_name;
	}

	public void setData_name(String data_name) {
		this.data_name = data_name;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
}
