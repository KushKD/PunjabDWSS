/**
 * 
 */
package com.prwss.mis.common.util;

/**
 * @author vgadiraju
 *
 */
public class MISConstants {

	
	public static final String NORMAL = "Normal";
	
	
	public static final String LABEL_VALUE_BEAN_SEPARATOR = "->";
	public static final String LABEL_VALUE_DATE_SEPARATOR = "--";
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String EIGHTY_NINE = "89";
	public static final String ZERO = "0";
	public static final String MASTER_STATUS_VERIFIED = "U";
	public static final String MASTER_STATUS_DELETED = "D";
	public static final String MASTER_STATUS_APPROVED = "A";
	public static final String MASTER_STATUS_FREEZED = "F";
	public static final String BACK = "return";
	public static final String FORWARD = "forward";
	
	public static final String D_MODE_ENQUIRE = "ent_inquire";
	public static final String D_MODE_MODIFY = "ent_modify";
	public static final String D_MODE_DELETE = "ent_delete";
	public static final String D_MODE_POST = "ent_post";
	public static final String D_MODE_REPOST = "ent_repost";
	public static final String D_MODE_ADD = "ent_add";
	
	public static final String TICKET_STATUS_OPEN = "Open";
	public static final String TICKET_STATUS_CLOSE = "Close";
	public static final String CHECKED = "On";
	public static final String SAN = "SAN";
	
	public static final String MIS_EMPLOYEE_CATEGORY_GENERAL= "GENERAL";
	public static final String MIS_EMPLOYEE_CATEGORY_SC = "SC";
	public static final String MIS_EMPLOYEE_CATEGORY_ST = "ST";
	public static final String MIS_EMPLOYEE_CATEGORY_BC = "BC";
	public static final String MIS_EMPLOYEE_CATEGORY_OBC = "OBC";

	
	public static final String MIS_EMPLOYEE_TYPE_PERMANENT = "PERMANENT";
	public static final String MIS_EMPLOYEE_TYPE_CONTRACTUAL = "CONTRACTUAL";
	
	
	//For Program Type 
	public static final String PROGRAM_PLANNED = "PLANNED";
	public static final String PROGRAM_NON_PLANNED = "NON-PLANNED";
	public static final String PROGRAM_SWAP = "SWAP";
	public static final String PROGRAM_NON_SWAP = "NON-SWAP";
	//For Village Category
	
	public static final String VILLAGE_CATEGORY_BET = "BET";
	public static final String VILLAGE_CATEGORY_KANDI = "KANDI";
	public static final String VILLAGE_CATEGORY_BORDER = "BORDER";
	public static final String VILLAGE_CATEGORY_SC = "SC";
	public static final String VILLAGE_CATEGORY_GENERAL = "GENERAL";
	public static final String VILLAGE_CATEGORY_WATER_LOGGING = "WATER-LOGGING";
	
	public static final String PROCUREMENT_PACKAGE_PRIOR_REVIEW = "PRIOR-REVIEW";
	public static final String PROCUREMENT_PACKAGE_POST_REVIEW = "POST-REVIEW";
	//For Village water source
	public static final String VILLAGE_WATER_SOURCE_CANNAL = "CANAL";
	public static final String VILLAGE_WATER_SOURCE_TUBE_WELL = "TUBEWELL";
	public static final String VILLAGE_WATER_SOURCE_TUBE_WELL_WITH_RO = "TUBEWELLWITHRO";
	public static final String VILLAGE_WATER_SOURCE_PERCULATION_WELL = "PERCULATIONWELL";
	public static final String VILLAGE_WATER_SOURCE_LIFTING_WATER_RSD_LAKE = "LIFTINGOFWATERFROMRSDLAKE";
	public static final String VILLAGE_WATER_SOURCE_DISTRIBUTION = "DISTRIBUTION";
	public static final String VILLAGE_WATER_SOURCE_HAND_PUMP = "HANDPUMP";
	public static final String VILLAGE_WATER_SOURCE_PONDS = "PONDS";
	public static final String VILLAGE_WATER_SOURCE_ROOF_TOP = "ROOFTOPRAINHARVESTING";
	public static final String VILLAGE_WATER_SOURCE_SEWERAGE = "SEWERAGE";
	public static final String VILLAGE_WATER_SOURCE_IMPROVEMENT = "IMPROVEMENT";
	public static final String VILLAGE_WATER_SOURCE_WATER_METER = "WATERMETER";
	
	
	
	public static final String VILLAGE_IS_BATCH_NO = "NO";
	public static final String VILLAGE_IS_BATCH_YES = "YES";
//	public static final String TICKET_INITIAL_COMMENTS = "Initial Entry";
	
	public static final String MIS_DB_SCHEMA_NAME ="prwss_main";
	public static final String MIS_STATUS_COMPLETE = "C";
	public static final String MIS_STATUS_ON_GOING = "O";
	public static final String MIS_STATUS_PENDING = "P";
	
	public static final String MIS_TYPE_OF_TENDER_GOODS = "GOODS";
	public static final String MIS_TYPE_OF_TENDER_WORKS = "WORKS";
	public static final String MIS_TYPE_OF_TENDER_CONSULTANCY = "CONSULTANCY";
	public static final String MIS_TYPE_OF_TENDER_SERVICES = "SERVICES";
	public static final String MIS_TENDER_OBJECTION_OPEN = "OPEN";
	public static final String MIS_TENDER_OBJECTION_CLOSE= "CLOSE";
	public static final String MIS_USER_LOCATION_ACTIVE= "ACTIVE";
	public static final String MIS_USER_LOCATION_INACTIVE= "INACTIVE";
	
	
	//Finance LOC RELEASE
	public static final String DAK_TASK_OUTWARD_DAK_TYPE= "Outward";
	public static final String DAK_TASK_INWARD_DAK_TYPE= "Inward";
	
	//Finance LOC RELEASE
	public static final String FIN_LOC_ACCEPT = "ACCEPT";
	public static final String FIN_LOC_REJECT = "REJECT";
	
	//Finance Payment Voucher
	
    public static final String FIN_VOC_TYPE_PAYMENT = "PAYMENT";
    public static final String FIN_VOC_TYPE_RECEIPT = "RECEIPT";
    
    public static final String FIN_ACCOUNT_NATURE_PAYMENT = "PAYMENT";
    public static final String FIN_ACCOUNT_NATURE_RECEIPT = "RECEIPT";
    public static final String FIN_ACCOUNT_NATURE_BOTH = "BOTH";
    
    public static final String FIN_PAYMENT_EMPLOYEE = "EMPLOYEE";
    public static final String FIN_PAYMENT_VENDOR = "VENDOR";
    public static final String FIN_PAYMENT_OFFICE = "OFFICE";
    public static final String FIN_PAYMENT_VILLAGE = "VILLAGE";
    
    public static final String FIN_ADJUSTMENT_MODE = "ADJUSTMENT";
    public static final String FIN_ADJUSTMENT_PAYEE_PAYER_TYPE = "INTERNAL";
    public static final String FIN_ADJUSTMENT_PAYEE_PAYER_ID = "INTERNAL";
    public static final String FIN_ADJUSTMENT_PAYMENT_RECEIPT_TYPE = "OTHER";

    public static final String FIN_NOT_APPLICABLE = "NA";
    public static final String FIN_VOUCHER_CAN_MODIFY_NO = "NO";
	
	//HR APPRAISAL
	public static final String HR_APRAISAL_RECOMMENDED = "RECOMMENDED";
	public static final String HR_APRAISAL_NOT_RECOMMENDED = "NOT-RECOMMENDED";
	public static final String HR_APRAISAL_ON_HOLD = "ON-HOLD";
	
	//HR Attendance
	public static final String HR_ATTENDANCE_PRESENT = "PRESENT";
	public static final String HR_ATTENDANCE_ABSENT = "ABSENT";
	public static final String HR_ATTENDANCE_HALF_DAY = "HALF-DAY";
	public static final String HR_ATTENDANCE_ONE_THIRD = "ONE-THIRD";
	
	
	public static final String LOC_FOR_INSTL_1="Installment-1";
	public static final String LOC_FOR_INSTL_2="Installment-2";
	public static final String LOC_FOR_INSTL_3="Installment-3";
	public static final String LOC_FOR_GAP_FUND="Gap-Fund";
	public static final String GPWSC_BENEFICIARY_SHARE = "Beneficiary-Share";
	public static final String GAP_FUND_VOLUNTARILY = "Gap-Fund-Voluntarily";
	public static final String GAP_FUND_NON_BUDGETARY = "Gap-Fund-Non-Budgetary";
	public static final String GAP_FUND_UNTIED_FUNDS = "Gap-Fund-Untied-Funds";
	public static final String MONTHLY_REVENUE = "Monthly-Revenue";	
	public static final String OTHER_INCOME = "Other-Income";
	public static final String TDS = "TDS";
	public static final String VAT = "VAT";
	public static final String LABOUR_CESS = "Labour Cess";
	public static final String SECURITY_RETENTION = "Security Deposits/Retention Money";
	
	public static final String STAFF_COST = "Staff-Cost";
	public static final String ELECTRICITY_COST = "Electricity-Cost";
	public static final String MTC_REPAIR_COST = "Mtc-And-Repair-Cost";
	
	//Location Type
	public static final String MIS_LOCATION_TYPE_SPMC = "SPMC";
	public static final String MIS_LOCATION_TYPE_DPMC = "DPMC";
	public static final String MIS_LOCATION_TYPE_DO = "DO";
	
	//HR LEAVE
	public static final String HR_LEAVE_NO_ACTION = "NO-ACTION-TAKEN";
	public static final String HR_LEAVE_ACCEPTED = "ACCEPTED";
	public static final String HR_LEAVE_REJECTED = "REJECTED";
	
	/*Dak and task Constants*/
	public static final String INWARDDAKTABLE= "daktask_inward_dak";
	public static final String OUTWARDDAKTABLE= "daktask_outward_dak";
	public static final String TASKALLOCATIONTABLE= "daktask_task_allocation";
	public static final String OUWARDDAKSEQUESNCE="prwss_main.daktask_outward_dak_dak_id_seq";
	
	/*Inventory constants*/
	public static final String GOODSRECEIPTHEADERTABLE="t_goods_receipt_header";
	public static final String GOODSRECEIPTDETAILSTABLE="t_goods_receipt_details";
	public static final String SUPPLYORDERHEADERTABLE="t_supply_order_header";
	public static final String SUPPLYORDERDETAILSTABLE="t_supply_order_details";
	public static final String GOODSISSUEHEADERTABLE="t_goods_issue_header";
	public static final String GOODSISSUEDETAILSTABLE="t_goods_issue_details";
	public static final String ISSUE_TO_EMPLOYEE="EMPLOYEE";
	public static final String ISSUE_TO_OFFICE="OFFICE";
	
	/*Procurement Mode List*/
	public static final String PRO_MODE_ICB = "ICB";
	public static final String PRO_MODE_NCB = "NCB";
	public static final String PRO_MODE_SHOPPING = "SHOPPING";
	public static final String PRO_MODE_DIRECT_CONTRACTING = "DIRECT CONTRACTING";
	public static final String PRO_MODE_FORCE_ACCOUNT = "FORCE ACCOUNT";
	public static final String PRO_MODE_DIRECT_CPP = "CPP";
	public static final String PRO_MODE_DIRECT_DGSD = "DGS&D";
	public static final String PRO_MODE_DIRECT_QCBS = "QCBS";
	public static final String PRO_MODE_DIRECT_QBS = "QBS";
	public static final String PRO_MODE_DIRECT_FBS = "FBS";
	public static final String PRO_MODE_DIRECT_LCS = "LCS";
	public static final String PRO_MODE_DIRECT_CQ = "CQ";
	public static final String PRO_MODE_DIRECT_SSS = "SSS";
	public static final String PRO_MODE_DIRECT_IC = "IC";
	public static final String PRO_MODE_DIRECT_CP = "CP";
	
	public static final String D_MODE_PROCEDURE_POST = "POST";
	public static final String D_MODE_PROCEDURE_ADD = "ADD";
	public static final String D_MODE_PROCEDURE_MODIFY = "MODIFY";
	
	public static final String SCHEME_SERVICE_LEVEL_40 = "40";
	public static final String SCHEME_SERVICE_LEVEL_70 = "70";
	public static final String SCHEME_SERVICE_LEVEL_135 = "135";
	public static final String SCHEME_SERVICE_LEVEL_HP = "HP";
	
	public static final String SCHEME_VILLAGE_CATEGORY_BLANK="";
	public static final String SCHEME_VILLAGE_CATEGORY_1A="1A";
	public static final String SCHEME_VILLAGE_CATEGORY_2A="2A";
	public static final String SCHEME_VILLAGE_CATEGORY_2B="2B";
	public static final String SCHEME_VILLAGE_CATEGORY_3A="3A";
	public static final String SCHEME_VILLAGE_CATEGORY_3B="3B";
	public static final String SCHEME_VILLAGE_CATEGORY_OTHERS="OTHERS";
	
	public static final String SCHEME_OPERATED_BY_GP_SELF = "GP-Self";
	public static final String SCHEME_OPERATED_BY_GP_OUTSOURCED = "GP-Outsourced";
	public static final String SCHEME_OPERATED_BY_GPWSC_SELF = "GPWSC-Self";
	public static final String SCHEME_OPERATED_BY_GPWSC_OUTSOURCED = "GPWSC-Outsourced";
	public static final String SCHEME_OPERATED_BY_DWSS_SELF = "DWSS-Self";
	public static final String SCHEME_OPERATED_BY_DWSS_OUTSOURCED = "DWSS-Outsourced";
	
	
	//DSR DISTRIBUTED TYPES
	public static final String SILVER_IONIZATION_UNIT = "Silver-Ionization-Unit";
	public static final String GAS_CLORINATION = "Gas-Clorination";
	public static final String	BLEACHING_POWDER = "Bleaching-Powder";
	public static final String HYPO = "Hypo";
	
	//Scheme Type
	public static final String SCHEME_TYPE_EXISTING="EXISTING";
	public static final String SCHEME_TYPE_NEW="NEW";
	public static final String SCHEME_TYPE_NA="NA";
	
	//Target Type
		public static final String TARGET_TYPE_EXISTING1="EXISTING1";
		public static final String TARGET_TYPE_EXISTING2="EXISTING2";
		public static final String TARGET_TYPE_EXISTING3="EXISTING3";
		public static final String TARGET_TYPE_EXISTING4="EXISTING4";
		public static final String TARGET_TYPE_EXISTING5="EXISTING5";
		public static final String TARGET_TYPE_EXISTING6="EXISTING6";
		
	
		//order by column
		public static final String CLICKED_ONE="0";
		public static final String CLICKED_TWO="1";
		public static final String CLICKED_THREE="2";
		public static final String CLICKED_FOUR="3";
		public static final String CLICKED_FIVE="4";
		public static final String CLICKED_SIX="5";
		public static final String CLICKED_SEVEN="6";
		public static final String CLICKED_EIGHT="7";
		public static final String ASC="asc";
		public static final String DSC="desc";
		
		public static final String FOUR="4";
		//request level
		
		public static final String REQUEST_ONE="1";
		public static final String REQUEST_TWO="2";
		public static final String REQUEST_THREE="3";
		public static final String REQUEST_TEN="10";
		public static final String ONE="1";
		public static final String TWO="2";
		public static final String THREE="3";
		public static final String AS_PER_BASELINE="As per Baseline";
		public static final String AP="AP";
		public static final String REMOVE="Remove";
		public static final String RE="RE";
		
		public static final String XEN="XEN";
		
		public static final String INACTIVE_STATUS="0";
		public static final String ACTIVE_STATUS="1";
		public static final String YES="Yes";
		//Data Source Details
		public static final String driverClassName = "org.postgresql.Driver";
		public static final String dbUsername = "postgres";
		public static final String dbPassword = "3xchange!";
		
		
		//Combo Details
				public static final String HABITATION_COMBO = "8";
				public static final String GENDER_COMBO = "1";
				public static final String YES_NO_COMBO = "5";
				public static final String CATEGORY_COMBO = "11";
				public static final String SUB_CATEGORY_COMBO_APL  = "12";
				public static final String SUB_CATEGORY_COMBO_BPL  = "13";
				public static final String TOILET_TYPE_COMBO  = "24";
				public static final String TOILET_CONSTRUCTED_COMBO ="27";
				public static final String APL_CARD_COMBO="32";
				public static final String BPL_CARD_COMBO="39";
				public static final String CASTE_COMBO="47";
				public static final String RELIGION="51";
				public static final String BANK_NAME="56";
				public static final String POI_TYPE="80";
				public static final String UOM="90";
				public static final String WATER_SOURCE="96";
				public static final String BOTTLE_TYPE="101";
				public static final String STATUS="85";
				
				public static final String TECHNICIAN="76";
				public static final String LAB_ASSISTANT="44";
				public static final String CHEMIST="14";
				public static final String PRE_IMPLEMENTATION="137";
				public static final String IMPLEMENTATION="138";
				public static final String POST_IMPLEMENTATION="139";
				public static final String IMPORTANT_OBSERVATIONS="140";
				
				public static final String ONE_SIXTY_NINE="169";
				public static final String ONE_SEVENTY="170";
				public static final String ONE_SEVENTY_ONE="171";
				public static final String ONE_SEVENTY_TWO="172";
				public static final String ONE_SEVENTY_THREE="173";
				public static final String ONE_SEVENTY_FOUR="174";
				public static final String ONE_SEVENTY_FIVE="175";
				public static final String ONE_EIGHTY_THREE="183";
				public static final String ONE_EIGHTY_FOUR="184";
				public static final String ONE_EIGHTY_FIVE="185";
				public static final String ONE_EIGHTY_SIX="186";
				
				public static final String ONE_SEVENTY_NINE="179";
				public static final String ONE_SEVENTY_EIGHT="178";
				public static final String ONE_EIGHTY="180";
				public static final String ONE_EIGHTY_ONE="181";

				public static final String ONE_EIGHTY_TWO="182";
				public static final String ONE_NIGHTY_THREE="193";
				public static final String ONE_NIGHTY_TWO="192";
				public static final String ONE_NIGHTY_FOUR="194";
				public static final String ONE_NIGHTY_FIVE="195";
				public static final String TWO_HUNDRED_EIGHT="208";
				public static final String TWO_HUNDRED_NINE="209";
				public static final String JCFA="ee.moga";
				
		//local
		public static final String url = "jdbc:postgresql://localhost/DWSS_NEW";
		
		//test
			//public static final String url="jdbc:postgresql://192.168.1.113/newdb";

		//Public Live DB
		//public static final String url = "jdbc:postgresql://192.168.1.114/prwss_db_uat_final";
}
