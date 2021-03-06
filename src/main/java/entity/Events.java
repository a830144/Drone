package entity;

// default package
// Generated Mar 21, 2019 12:09:06 AM by Hibernate Tools 5.2.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Events generated by hbm2java
 */
@Entity
@Table(name = "events")
public class Events implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6440099302815722487L;
	private Integer eventId;
	private String eventName;
	private Date startDate;
	private Date endDate;
	private String description;
	private Set<Participations> participationses = new HashSet<Participations>(0);

	public Events() {
	}

	public Events(String eventName, Date startDate, Date endDate, String description, Set<Participations> participationses) {
		this.eventName = eventName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.participationses = participationses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Event_ID", unique = true, nullable = false)
	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	@Column(name = "Name", length = 45)
	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "Start_Date", length = 10)
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "End_Date", length = 10)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "Description", length = 45)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL, mappedBy = "events")
	public Set<Participations> getParticipationses() {
		return this.participationses;
	}

	public void setParticipationses(Set<Participations> participationses) {
		this.participationses = participationses;
	}

}
