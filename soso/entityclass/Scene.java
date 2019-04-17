package cn.soso.entityclass;
/*
 * 使用场景
 */
public class Scene {
	private String type;// 消费类型
	private int data;// 消费数据
	private String scene;// 消费场景

	public Scene(String type, int data, String scene) {
		super();
		this.type = type;
		this.data = data;
		this.scene = scene;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}
     
     
}
