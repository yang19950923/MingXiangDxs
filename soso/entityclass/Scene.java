package cn.soso.entityclass;
/*
 * ʹ�ó���
 */
public class Scene {
	private String type;// ��������
	private int data;// ��������
	private String scene;// ���ѳ���

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
