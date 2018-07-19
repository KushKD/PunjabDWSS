package com.prwss.mis.common.document.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public interface DocumentNumberDAO {
	public List<DocumentNumberBean> getDocumentNumberBeans(DocumentNumberBean DocumentNumberBean) throws DataAccessException;
	
	public List<DocumentNumberBean> getDocumentNumber(DocumentNumberBean DocumentNumberBean) throws DataAccessException;
	
	public boolean saveOrUpdateDocumentNumberBeans(DocumentNumberBean DB) throws DataAccessException;

}
