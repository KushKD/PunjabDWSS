package com.prwss.min.SDU.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.prwss.min.SDU.bean.ActivityVillageMappingBean;
import com.prwss.min.SDU.bean.ActivityVillageMappingDetalBean;
import com.prwss.min.SDU.bean.SduVillageTrainingAttachmentBean;
import com.prwss.min.SDU.bean.SduVillageTrainingDetailsBean;
import com.prwss.min.SDU.bean.SduVillageTrainingMaterialBean;
import com.prwss.mis.admin.dao.LocationDivisionSubDivisonDetailsBean;

public interface TrainingDetailsEntryDao {

	public List<String> getAllLocationIds(String UserID) throws DataAccessException;
	public List<LocationDivisionSubDivisonDetailsBean> getLocationNameandId(List<Integer> idLocations) throws DataAccessException;
	public List<ActivityVillageMappingBean> getVillageNameAndId(Integer financialYr, Integer division) throws DataAccessException;
	public List<ActivityVillageMappingDetalBean> getActivityNameandId(Integer financialYr, Integer division, Integer village) throws DataAccessException;
	public Integer saveVillageTrainingDetail(SduVillageTrainingDetailsBean sduVillageTrainingDetailsBean)throws DataAccessException;
	public boolean saveVillageTrainingMaterial(List<SduVillageTrainingMaterialBean> sduVillageTrainingMaterialBeans)throws DataAccessException;
	public boolean saveSduVillageTrainingAttachment(SduVillageTrainingAttachmentBean sduVillageTrainingAttachmentBean)throws DataAccessException;

}
