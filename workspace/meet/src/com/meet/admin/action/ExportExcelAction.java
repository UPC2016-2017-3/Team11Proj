
package com.meet.admin.action;

import java.util.HashMap;
import java.util.List;

import com.meet.admin.domain.Reserve;
import com.meet.admin.manager.AdminManager;
import com.meet.common.action.BaseAction;
import com.meet.common.util.BeanLocator;

public class ExportExcelAction extends BaseAction{

	private static final long serialVersionUID = 8143993190677252109L;
	AdminManager adminManager = (AdminManager)BeanLocator.getInstance().getBean("adminManager");
	
	//导出参数
	Reserve paramsReserve;
	
	HashMap<String, Object> report = new HashMap<String, Object>();

	/**
	 * @Title: exportReserves
	 * @Description: 导出预约信息
	 * @return String
	 */
	public String exportReserves(){
		try{
			if (paramsReserve==null) {
				paramsReserve = new Reserve();
			}
			//设置分页信息
			paramsReserve.setStart(-1);
			List<Reserve> reserves = adminManager.listReserves(paramsReserve,null); 
			
			report.put("reserves", reserves);
			
			//设置导出文件名
			setExportExcelName("会议室预约记录.xls");
		}
        catch(Exception e){
            setErrorReason("导出异常，请稍后再试", e);
            return ERROR;
        }
        return SUCCESS; 
	}
	
	/**
	 * @Title: exportReserves
	 * @Description: 导出预约统计信息
	 * @return String
	 */
	public String exportReservesSum(){
		try{
			if (paramsReserve==null) {
				paramsReserve = new Reserve();
			}
			//设置分页信息
			paramsReserve.setStart(-1);
			List<Reserve> reserves = adminManager.listReservesSum(paramsReserve,null); 
			
			report.put("reserves", reserves);
			
			//设置导出文件名
			setExportExcelName("会议室预约统计.xls");
		}
        catch(Exception e){
            setErrorReason("导出异常，请稍后再试", e);
            return ERROR;
        }
        return SUCCESS; 
	}
	
 

	public HashMap<String, Object> getReport() {
		return report;
	}

	public void setReport(HashMap<String, Object> report) {
		this.report = report;
	}



	public Reserve getParamsReserve() {
		return paramsReserve;
	}



	public void setParamsReserve(Reserve paramsReserve) {
		this.paramsReserve = paramsReserve;
	}
 
	
	
}
