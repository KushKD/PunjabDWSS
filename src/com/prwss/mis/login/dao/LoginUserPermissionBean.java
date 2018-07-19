package com.prwss.mis.login.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.prwss.mis.admin.dao.MenuBean;
import com.prwss.mis.common.MISAuditBean;
import com.prwss.mis.common.util.MISConstants;

@Entity
@Table(name="sd_user_permission", schema=MISConstants.MIS_DB_SCHEMA_NAME)
public class LoginUserPermissionBean implements Serializable, Comparable<LoginUserPermissionBean> {
	
	/**
	 * Initial Version
	 */
	private static final long serialVersionUID = -5219372729996483276L;

	@Id
	@Column(name="role_id", nullable=false)
	private String roleId;
	
	@Id
	@Column(name="menu_id", nullable=false)
	private String menuId;
	
	@Column(name="menu_name")
	private String menuName;
	
	
	@Column(name="can_inquire", nullable=false)
	private boolean canInquire;
	
	@Column(name="can_add")
	private boolean canAdd;
	
	@Column(name="can_modify", nullable=false)
	private boolean canModify;
	
	@Column(name="can_delete", nullable=false)
	private boolean canDelete;
	
	@Column(name="can_post", nullable=false)
	private boolean canPost;
	
	@Column(name="can_view", nullable=false)
	private boolean canView;
	
	@Column(name="can_print", nullable=false)
	private boolean canPrint;
	
	@Column(name="can_email", nullable=false)
	private boolean canEmail;
	
	@Column(name="can_export", nullable=false)
	private boolean canExport;
	
	@Column(name="can_graph")
	private boolean canGraph;
	
	@Column(name="can_file", nullable=false)
	private boolean canFile = true;
	
	@Column(name="can_repost", nullable=false)
	private boolean canRepost = true;
	
	@Column(name="can_qrcode", nullable=false)
	private boolean canQrcode = true;
	
	@Column(name="can_update", nullable=false)
	private boolean canUpdate = true;
	
	@Column(name="can_forward", nullable=false)
	private boolean canForward = true;
	
	@ManyToOne(targetEntity=MenuBean.class, fetch=FetchType.EAGER)
	@JoinColumn(name="menu_id", insertable=false, updatable=false)
	private MenuBean menuBean;
	
	
	
	public boolean isCanForward() {
		return canForward;
	}

	public void setCanForward(boolean canForward) {
		this.canForward = canForward;
	}

	public boolean isCanUpdate() {
		return canUpdate;
	}

	public void setCanUpdate(boolean canUpdate) {
		this.canUpdate = canUpdate;
	}

	@Embedded
	private MISAuditBean misAuditBean;
	
	
	public boolean isCanQrcode() {
		return canQrcode;
	}

	public void setCanQrcode(boolean canQrcode) {
		this.canQrcode = canQrcode;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public boolean isCanInquire() {
		return canInquire;
	}

	public void setCanInquire(boolean canInquire) {
		this.canInquire = canInquire;
	}

	public boolean isCanAdd() {
		return canAdd;
	}

	public void setCanAdd(boolean canAdd) {
		this.canAdd = canAdd;
	}

	public boolean isCanModify() {
		return canModify;
	}

	public void setCanModify(boolean canModify) {
		this.canModify = canModify;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

	public boolean isCanPost() {
		return canPost;
	}

	public void setCanPost(boolean canPost) {
		this.canPost = canPost;
	}

	public boolean isCanView() {
		return canView;
	}

	public void setCanView(boolean canView) {
		this.canView = canView;
	}

	public boolean isCanPrint() {
		return canPrint;
	}

	public void setCanPrint(boolean canPrint) {
		this.canPrint = canPrint;
	}

	public boolean isCanEmail() {
		return canEmail;
	}

	public void setCanEmail(boolean canEmail) {
		this.canEmail = canEmail;
	}

	public boolean isCanExport() {
		return canExport;
	}

	public void setCanExport(boolean canExport) {
		this.canExport = canExport;
	}

	public boolean isCanGraph() {
		return canGraph;
	}

	public void setCanGraph(boolean canGraph) {
		this.canGraph = canGraph;
	}

	public boolean isCanFile() {
		return canFile;
	}

	public void setCanFile(boolean canFile) {
		this.canFile = canFile;
	}

	public MISAuditBean getMisAuditBean() {
		return misAuditBean;
	}

	public void setMisAuditBean(MISAuditBean misAuditBean) {
		this.misAuditBean = misAuditBean;
	}

	@Override
	public String toString() {
		return "\nLoginUserPermission [roleId=" + roleId + ", menuId=" + menuId + ", canInquire=" + canInquire
				+ ", canAdd=" + canAdd + ", canModify=" + canModify + ", canDelete=" + canDelete + ", canPost="
				+ canPost + ", canView=" + canView + ", canPrint=" + canPrint + ", canEmail=" + canEmail
				+ ", canExport=" + canExport + ", canGraph=" + canGraph + ", canFile=" + canFile + ",canRepost = "+ canRepost +", misAuditBean="
				+ misAuditBean + "]\n";
	}

	@Override
	public int compareTo(LoginUserPermissionBean o) {
		
		return o.getMenuId().compareTo(this.getMenuId());
	}

	public void setMenuBean(MenuBean menuBean) {
		this.menuBean = menuBean;
	}

	public MenuBean getMenuBean() {
		return menuBean;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setCanRepost(boolean canRepost) {
		this.canRepost = canRepost;
	}

	public boolean isCanRepost() {
		return canRepost;
	}
	

}
