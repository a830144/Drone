package vo;

import java.util.Date;
import java.util.HashMap;

public class BaseEquipmentAttachment {
	private Integer equipmentId;
	private Integer maintenanceId;
	private Integer modificationId;
	private String productName;
	private String manufactoryName;
	private String constructionType;
	private String maintenanceType;
	private Date maintenanceDate;
	private String maintenancePerson;
	private Date modificationDate;
	private String modificationPerson;
	private Date sendDate;
	private Integer sendId;
	private Date checkDate;
	private Integer checkId;
	private Date approveDate;
	private Integer approveId;
	private stateMachine.States maintainState;
	private stateMachine.States modifyState;
	
	
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Integer getSendId() {
		return sendId;
	}
	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Integer getCheckId() {
		return checkId;
	}
	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}
	public Date getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}
	public Integer getApproveId() {
		return approveId;
	}
	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}
	public stateMachine.States getMaintainState() {
		return maintainState;
	}
	public void setMaintainState(stateMachine.States maintainState) {
		this.maintainState = maintainState;
	}
	public stateMachine.States getModifyState() {
		return modifyState;
	}
	public void setModifyState(stateMachine.States modifyState) {
		this.modifyState = modifyState;
	}
	public Integer getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}
	public Integer getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public Integer getModificationId() {
		return modificationId;
	}
	public void setModificationId(Integer modificationId) {
		this.modificationId = modificationId;
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
	
	public void setShowMaintenanceType(){
		if("W".equals(maintenanceType)){
			maintenanceType = "周檢";
		}else if ("M".equals(maintenanceType)){
			maintenanceType = "月檢";
		}else if("Q".equals(maintenanceType)){
			maintenanceType = "季檢";
		}else{
			maintenanceType = "異常資料";
		}
	}
	
	
	
	public class TempAttach{
		private String detailType;
		private String status;
		private String comment;
		private String modificationPhoto;
		public TempAttach(String detailType, String status, String comment) {
			super();
			this.detailType = detailType;
			this.status = status;
			this.comment = comment;
		}
		public TempAttach(String detailType, String status, String comment, String modificationPhoto) {
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
		public String getModificationPhoto() {
			return modificationPhoto;
		}
		public void setModificationPhoto(String modificationPhoto) {
			this.modificationPhoto = modificationPhoto;
		}
		@Override
		public String toString() {
			return "TempAttach [detailType=" + detailType + ", status=" + status + ", comment=" + comment
					+ ", modificationPhoto=" + modificationPhoto + "]";
		}
		
		
	}
}
