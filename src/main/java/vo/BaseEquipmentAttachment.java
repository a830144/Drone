package vo;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class BaseEquipmentAttachment {
	private Integer equipmentId;
	private String productName;
	private String manufactoryName;
	private String constructionType;
	private String maintenanceType;
	private Date maintenanceDate;
	private String maintenancePerson;
	private Date modificationDate;
	private String modificationPerson;
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getManufactoryName() {
		return manufactoryName;
	}
	public void setManufactoryName(String manufactoryName) {
		this.manufactoryName = manufactoryName;
	}
	public String getConstructionType() {
		return constructionType;
	}
	public void setConstructionType(String constructionType) {
		this.constructionType = constructionType;
	}
	public String getMaintenanceType() {
		return maintenanceType;
	}
	public void setMaintenanceType(String maintenanceType) {
		this.maintenanceType = maintenanceType;
	}
	public Date getMaintenanceDate() {
		return maintenanceDate;
	}
	public void setMaintenanceDate(Date maintenanceDate) {
		this.maintenanceDate = maintenanceDate;
	}
	public String getMaintenancePerson() {
		return maintenancePerson;
	}
	public void setMaintenancePerson(String maintenancePerson) {
		this.maintenancePerson = maintenancePerson;
	}
	public Date getModificationDate() {
		return modificationDate;
	}
	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}
	public String getModificationPerson() {
		return modificationPerson;
	}
	public void setModificationPerson(String modificationPerson) {
		this.modificationPerson = modificationPerson;
	}

	public static HashMap<String, String> detailMap = new HashMap<String, String>();
	public static HashMap<String, String> detailReverseMap = new HashMap<String, String>();
	
	
	
	public BaseEquipmentAttachment() {
		super();
		detailMap.put("airframe", "1");
		detailMap.put("propulsion", "2");
		detailMap.put("battery", "3");
		detailMap.put("controller", "4");
		detailMap.put("payload", "5");
		detailMap.put("others", "6");

		detailReverseMap.put("1", "airframe");
		detailReverseMap.put("2", "propulsion");
		detailReverseMap.put("3", "battery");
		detailReverseMap.put("4", "controller");
		detailReverseMap.put("5", "payload");
		detailReverseMap.put("6", "others");
	}
	
	
	
	public class TempAttach{
		private String detailType;
		private String status;
		private String comment;
		private byte[] modificationPhoto;
		public TempAttach(String detailType, String status, String comment) {
			super();
			this.detailType = detailType;
			this.status = status;
			this.comment = comment;
		}
		public TempAttach(String detailType, String status, String comment, byte[] modificationPhoto) {
			super();
			this.detailType = detailType;
			this.status = status;
			this.comment = comment;
			this.modificationPhoto = modificationPhoto;
		}
		public String getDetailType() {
			return detailType;
		}
		public void setDetailType(String detailType) {
			this.detailType = detailType;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}
		public byte[] getModificationPhoto() {
			return modificationPhoto;
		}
		public void setModificationPhoto(byte[] modificationPhoto) {
			this.modificationPhoto = modificationPhoto;
		}
		@Override
		public String toString() {
			return "TempAttach [detailType=" + detailType + ", status=" + status + ", comment=" + comment
					+ ", modificationPhoto=" + Arrays.toString(modificationPhoto) + "]";
		}
	}
}
