package com.spider.Reports.service;

import java.util.List;

import com.spider.Reports.entity.CitizenPlan;
import com.spider.Reports.request.SearchRequest;

import jakarta.servlet.http.HttpServletResponse;

public interface ReportService {

	public List<String> getPlanNames();

	public List<String> getPlanStatus();

	public List<CitizenPlan> search(SearchRequest request);

	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf(HttpServletResponse response) throws Exception  ;




}
