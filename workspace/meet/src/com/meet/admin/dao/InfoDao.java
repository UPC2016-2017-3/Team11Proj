package com.meet.admin.dao;

import java.util.ArrayList;
import java.util.List;

import com.meet.admin.domain.Info;
import com.meet.common.dao.BaseDao;
import com.meet.common.util.StringUtil;

public class InfoDao extends BaseDao{
	
	public void addInfo(Info info){
		super.add(info);
	}
	
	public void delInfo(Integer info_id){
		super.del(Info.class, info_id);
	}
	
	public void delInfo(String[] info_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <info_ids.length; i++) {
			sBuilder.append(info_ids[i]);
			if (i !=info_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Info d WHERE d.info_id IN (" +sBuilder.toString()+")";

		Object[] params = null;
		
		super.executeUpdateHql(hql, params);
	}
	
	public void updateInfo(Info info){
		Info _info = (Info)super.get(Info.class, info.getInfo_id());
		if (!StringUtil.isEmptyString(info.getInfo_title())) {
			_info.setInfo_title(info.getInfo_title());
		}
		if (!StringUtil.isEmptyString(info.getInfo_content())) {
			_info.setInfo_content(info.getInfo_content());
		}
		if (info.getInfo_date()!=null) {
			_info.setInfo_date(info.getInfo_date());
		}
		if (!StringUtil.isEmptyString(info.getInfo_admin())) {
			_info.setInfo_admin(info.getInfo_admin());
		}
	    //super.update(info);
	}
	
	@SuppressWarnings("rawtypes")
	public Info getInfo(Info info){
		Info _info = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Info a WHERE 1=1");
		
		List<Object> paramsList = new ArrayList<Object>();
		if (info.getInfo_id()!=null && info.getInfo_id()!=0) {
			sBuilder.append(" and a.info_id = ? ");
			paramsList.add(info.getInfo_id());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		List list = super.executeQueryHql(sBuilder.toString(), params);
		if (list != null && list.size() > 0) {
			_info = (Info)list.get(0);
		}
		return _info;
	}
	
	@SuppressWarnings("rawtypes")
	public List<Info>  listInfos(Info info){
		List<Info> infos = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Info a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (info.getInfo_id()!=null && info.getInfo_id()!=0) {
			sBuilder.append(" and a.info_id = ? ");
			paramsList.add(info.getInfo_id());
		}
		if (!StringUtil.isEmptyString(info.getInfo_title())) {
			sBuilder.append(" and a.info_title like ? ");
			paramsList.add("%"+info.getInfo_title()+"%");
		}
		if (!StringUtil.isEmptyString(info.getInfo_admin())) {
			sBuilder.append(" and a.info_admin like ? ");
			paramsList.add("%"+info.getInfo_admin()+"%");
		}
		if (info.getInfo_date()!=null) {
			sBuilder.append(" and a.info_date = ? ");
			paramsList.add(info.getInfo_date());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}
		
		sBuilder.append(" order by a.info_id asc");

		List list = null;
		if (info.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, info.getStart(), info.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			infos = new ArrayList<Info>();
			for (Object object : list) {
				infos.add((Info)object);
			}
		}
		return infos;
	}
	
	
	public int listInfosCount(Info info){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Info a WHERE 1=1");

		List<Object> paramsList = new ArrayList<Object>();
		if (info.getInfo_id()!=null && info.getInfo_id()!=0) {
			sBuilder.append(" and a.info_id = ? ");
			paramsList.add(info.getInfo_id());
		}
		if (!StringUtil.isEmptyString(info.getInfo_title())) {
			sBuilder.append(" and a.info_title like ? ");
			paramsList.add("%"+info.getInfo_title()+"%");
		}
		if (!StringUtil.isEmptyString(info.getInfo_admin())) {
			sBuilder.append(" and a.info_admin like ? ");
			paramsList.add("%"+info.getInfo_admin()+"%");
		}
		if (info.getInfo_date()!=null) {
			sBuilder.append(" and a.info_date = ? ");
			paramsList.add(info.getInfo_date());
		}
		
		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), params);
		sum = (int)count;
		return sum;
	}
	
	
	
	
}
