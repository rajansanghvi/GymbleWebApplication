package com.gymble.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "es_batches")
public class EsBatch extends ESEntityBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "batch_id")
	private Long id;
	
	@Column(name = "code", nullable = false)
	private String code;
	
	@Column(name = "capacity", nullable = true)
	private long capacity;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "from_age", nullable = true)
	private int fromAge;
	
	@Column(name = "to_age", nullable = true)
	private int toAge;
	
	@Column(name = "location", nullable = true)
	private String location;
	
	@OneToOne
	@JoinColumn(name="activity_id", referencedColumnName = "activity_id")
	private EsActivity activity;
	
	@OneToMany(mappedBy = "batch", cascade = CascadeType.ALL)
	private List<EsBatchSchedule> batchSchedules;

	public EsBatch() {
		super();
	}

	public EsBatch(String code, long capacity, double price, int fromAge,
			int toAge, String location, EsActivity activity,
			List<EsBatchSchedule> batchSchedules) {
		super();
		this.code = code;
		this.capacity = capacity;
		this.price = price;
		this.fromAge = fromAge;
		this.toAge = toAge;
		this.location = location;
		this.activity = activity;
		this.batchSchedules = batchSchedules;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getCapacity() {
		return capacity;
	}

	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getFromAge() {
		return fromAge;
	}

	public void setFromAge(int fromAge) {
		this.fromAge = fromAge;
	}

	public int getToAge() {
		return toAge;
	}

	public void setToAge(int toAge) {
		this.toAge = toAge;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public EsActivity getActivity() {
		return activity;
	}

	public void setActivity(EsActivity activity) {
		this.activity = activity;
	}

	public List<EsBatchSchedule> getBatchSchedules() {
		return batchSchedules;
	}

	public void setBatchSchedules(List<EsBatchSchedule> batchSchedules) {
		this.batchSchedules = batchSchedules;
	}
	
	public void addBatchSchedules(EsBatchSchedule batchSchedule)
	{
		if(this.batchSchedules == null)
			this.batchSchedules = new ArrayList<EsBatchSchedule>();
			
		this.batchSchedules.add(batchSchedule);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EsBatch other = (EsBatch) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EsBatch [code=" + code + ", fromAge=" + fromAge + ", toAge="
				+ toAge + ", activity=" + activity + "]";
	}
	
}
