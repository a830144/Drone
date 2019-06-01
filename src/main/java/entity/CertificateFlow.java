package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class CertificateFlow {
	
	
	@Override
	public String toString() {
		return "CertificateFlow [sendDate=" + sendDate + ", sendId=" + sendId + ", checkDate=" + checkDate
				+ ", checkId=" + checkId + ", approveDate=" + approveDate + ", approveId=" + approveId + ", state="
				+ state + "]";
	}

	private Date sendDate;
	private Integer sendId;
	private Date checkDate;
	private Integer checkId;
	private Date approveDate;
	private Integer approveId;
	private stateMachine.States state;
	
	
	@Column(name = "state")
	@Enumerated(value = EnumType.STRING)
	public stateMachine.States getState() {
		return state;
	}

	public void setState(stateMachine.States state) {
		this.state = state;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Send_Date", length = 19)
	public Date getSendDate() {
		return this.sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	@Column(name = "Send_ID")
	public Integer getSendId() {
		return this.sendId;
	}

	public void setSendId(Integer sendId) {
		this.sendId = sendId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Check_Date", length = 19)
	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	@Column(name = "Check_ID")
	public Integer getCheckId() {
		return this.checkId;
	}

	public void setCheckId(Integer checkId) {
		this.checkId = checkId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Approve_Date", length = 19)
	public Date getApproveDate() {
		return this.approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	@Column(name = "Approve_ID")
	public Integer getApproveId() {
		return this.approveId;
	}

	public void setApproveId(Integer approveId) {
		this.approveId = approveId;
	}
}
