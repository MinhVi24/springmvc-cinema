// Phân Trang
package fa.training.page;

public class PageAble {
	private int page;
	private int size = 5;

	public PageAble(int page) {
		super();
		this.page = page;
	}

	public int getOffset() {
		return (page - 1) * size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
