package com.gymble.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.gymble.enumeration.BatchDay;

@Entity
@Table(name = "es_batch_schedules")
public class EsBatchSchedule {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_schedule_id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "day", nullable = false)
	private BatchDay day;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "from_time", nullable = false)
	private Date fromTime;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "to_time", nullable =false)
	private Date toTime;
	
	@Column(name = "teacher")
	private String teacher;
	
	@Column(name = "room_number")
	private String roomNumber;
	
	@ManyToOne
	@JoinColumn(name = "batch_id", referencedColumnName = "batch_id")
	private EsBatch batch;

	public EsBatchSchedule() {
		super();
	}

	public EsBatchSchedule(BatchDay day, Date fromTime, Date toTime,
			String teacher, String roomNumber, EsBatch batch) {
		super();
		this.day = day;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.teacher = teacher;
		this.roomNumber = roomNumber;
		this.batch = batch;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BatchDay getDay() {
		return day;
	}

	public void setDay(BatchDay day) {
		this.day = day;
	}

	public Date getFromTime() {
		return fromTime;
	}

	public void setFromTime(Date fromTime) {
		this.fromTime = fromTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public EsBatch getBatch() {
		return batch;
	}

	public void setBatch(EsBatch batch) {
		this.batch = batch;
	}

	@Override
	public String toString() {
		return "EsBatchSchedule [day=" + day + ", fromTime=" + fromTime
				+ ", toTime=" + toTime + ", batch=" + batch + "]";
	}
	
	
}
