package service.impl;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import entity.MaintenanceDetail;
import entity.ModificationDetail;
import vo.BaseEquipmentAttachment.TempAttach;

public class DetailBuilder {
	private int seq;
	private Integer detailType;
	private String status;
	private String comment;
	private String modificationPhoto;
	public int getSeq() {
		return seq;
	}

	public DetailBuilder setSeq(int seq) {
		this.seq = seq;
		return this;
	}

	public Integer getDetailType() {
		return detailType;
	}

	public DetailBuilder setDetailType(Integer detailType) {
		this.detailType = detailType;
		return this;
	}

	public String getComment() {
		return comment;
	}

	public DetailBuilder setComment(String comment) {
		this.comment = comment;
		return this;
	}

	public String getModificationPhoto() {
		return modificationPhoto;
	}

	public DetailBuilder setModificationPhoto(String modificationPhoto) {
		this.modificationPhoto = modificationPhoto;
		return this;
	}
	
	public ModificationDetail build() {
        return new ModificationDetail(seq, detailType, status, comment, modificationPhoto);
    }
	
	public ModificationDetail buildModifyDetail(TempAttach model) {
		if(model==null){
			throw new IllegalArgumentException();
		}
		ModificationDetail entity = new ModificationDetail(seq, detailType, status, comment, modificationPhoto);
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
        return entity;
    }
	
	public MaintenanceDetail buildMaintainDetail(TempAttach model) {
		if(model==null){
			throw new IllegalArgumentException();
		}
		MaintenanceDetail entity = new MaintenanceDetail(seq, detailType+"", status, comment);
		
		try {
			BeanUtils.copyProperties(entity, model);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
        return entity;
    }

}
